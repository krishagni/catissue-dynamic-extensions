package edu.common.dynamicextensions.domain.nui;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import edu.common.dynamicextensions.ndao.DbSettingsFactory;
import edu.common.dynamicextensions.ndao.JdbcDaoFactory;

public class PvDataSource implements Serializable {
	private static final long serialVersionUID = 276983397161935250L;

	private static final String LIMIT_QUERY = "select * from (select tab.*, rownum rnum from (%s) tab where rownum <= %d) where rnum >= %d";

	private static final String VALIDITY_QUERY = "select count(*) from (%s) t where t.%s = ?";

	private static final String SEARCH_QUERY = "select * from (%s) t where t.%s like '%%%s%%'";

	public enum Ordering {
		NONE, ASC, DESC
	}
	
	private List<PvVersion> pvVersions = new ArrayList<PvVersion>();
	
	private DataType dataType;
	
	private String dateFormat;
	
	private Ordering ordering = Ordering.NONE;
	
	private String sql;

	public List<PvVersion> getPvVersions() {
		return pvVersions;
	}

	public void setPvVersions(List<PvVersion> pvVersions) {
		this.pvVersions = pvVersions;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Ordering getOrdering() {
		return ordering;
	}

	public void setOrdering(Ordering ordering) {
		this.ordering = ordering;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String pvSql) {
		this.sql = pvSql;
	}  
	
	public List<PermissibleValue> getPermissibleValues(Date activationDate) {
		return getPermissibleValues(activationDate, 0);
	}

	public List<PermissibleValue> getPermissibleValues(Date activationDate, int maxPvs) {
		List<PermissibleValue> pvs;
		
		if (sql != null) {
			pvs = getPvsFromDb(sql, maxPvs);
		} else {
			pvs = getPvVersion(activationDate).getPermissibleValues();
			switch (ordering) {
				case ASC:
					Collections.sort(pvs);
					break;

				case DESC:
					Collections.sort(pvs, Collections.reverseOrder());
					break;

				default:
					// do nothing
					break;
			}

			if (maxPvs > 0 && pvs.size() > maxPvs) {
				pvs = pvs.subList(0, maxPvs);
			}
		}

		return pvs;
	}

	public List<PermissibleValue> getPermissibleValues(String searchStr, int maxPvs) {
		return getPermissibleValues(Calendar.getInstance().getTime(), searchStr, maxPvs);
	}

	public List<PermissibleValue> getPermissibleValues(Date activationDate, String searchStr, int maxPvs) {
		List<PermissibleValue> pvs;

		if (sql != null) {
			String searchSql = sql;
			if (StringUtils.isNotBlank(searchStr)) {
				searchSql = String.format(SEARCH_QUERY, sql, getColumnName(sql), searchStr.trim());
			}

			pvs = getPvsFromDb(searchSql, maxPvs);
		} else {
			pvs = getPvVersion(activationDate).getPermissibleValues();
			sort(pvs);

			if (StringUtils.isNotBlank(searchStr)) {
				pvs = pvs.stream()
					.filter(pv -> pv.getValue().toLowerCase().contains(searchStr.toLowerCase()))
					.limit(maxPvs)
					.collect(Collectors.toList());
			}
		}

		return pvs;
	}

	public boolean isValidPv(Date activationDate, String input) {
		if (sql != null) {
			String searchSql = String.format(VALIDITY_QUERY, sql, getColumnName(sql));
			return JdbcDaoFactory.getJdbcDao().getResultSet(searchSql, Collections.singletonList(input), ResultSet::next);
		} else {
			List<PermissibleValue> pvs = getPvVersion(activationDate).getPermissibleValues();
			if (pvs == null || pvs.isEmpty()) {
				return false;
			}

			return pvs.stream().filter(pv -> pv.getValue().equals(input)).findFirst().orElse(null) != null;
		}
	}
	
	public PermissibleValue getDefaultValue(Date activationDate) {
		if (sql == null ) {
			return getPvVersion(activationDate).getDefaultValue();
		}
		
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result	+ ((pvVersions == null) ? 0 : pvVersions.hashCode());
		result = prime * result	+ ((dataType == null) ? 0 : dataType.hashCode());
		result = prime * result + ((dateFormat == null) ? 0 : dateFormat.hashCode());
		result = prime * result	+ ((ordering == null) ? 0 : ordering.hashCode());		
		result = prime * result + ((sql == null) ? 0 : sql.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {		
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
						
		PvDataSource other = (PvDataSource) obj;
		if ((pvVersions == null && other.pvVersions != null) ||
			!pvVersions.equals(other.pvVersions) ||
			dataType != other.dataType ||
			!StringUtils.equals(dateFormat, other.dateFormat) ||
			ordering != other.ordering ||
			!StringUtils.equals(sql, other.sql)) {
			return false;
		} 
		
		return true;
	}

	private PvVersion getPvVersion(Date activationDate) {
		PvVersion result = null;

		for (PvVersion pvVersion : pvVersions) {
			Date versionDate = pvVersion.getActivationDate();
			if (result == null) {
				result = pvVersion;
			} else if (result.getActivationDate() == null || versionDate == null ||
				result.getActivationDate().before(versionDate) && versionDate.before(activationDate)) {
				result = pvVersion;
			}
		}
				
		return result;		
	}
	
	private List<PermissibleValue> getPvsFromDb(String sql, int maxPvs) {
		if (maxPvs > 0) {
			sql = getLimitQuery(sql, maxPvs);
		}

		return JdbcDaoFactory.getJdbcDao().getResultSet(sql, null, (rs) -> {
			List<PermissibleValue> result = new ArrayList<>();

			while (rs.next()) {
				String value = rs.getString(1);
				if (value == null || value.trim().isEmpty()) {
					continue;
				}

				PermissibleValue pv = new PermissibleValue();
				pv.setOptionName(value);
				pv.setValue(value);
				result.add(pv);
			}

			return result;
		});
	}

	private String getLimitQuery(String sql, int maxPvs) {
		if (DbSettingsFactory.isOracle()) {
			sql = String.format(LIMIT_QUERY, sql, maxPvs, 1);
		} else {
			sql = sql + " limit 0, " + maxPvs;
		}

		return sql;
	}

	private String getColumnName(String sql) {
		return JdbcDaoFactory.getJdbcDao().getResultSet(getLimitQuery(sql, 1), null, (rs) -> {
			ResultSetMetaData rsmd = rs.getMetaData();
			return rsmd.getColumnName(1);
		});
	}

	private void sort(List<PermissibleValue> pvs) {
		switch (ordering) {
			case ASC:
				Collections.sort(pvs);
				break;

			case DESC:
				Collections.sort(pvs, Collections.reverseOrder());
				break;

			default:
				// do nothing
				break;
		}
	}
}
