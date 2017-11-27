package edu.common.dynamicextensions.napi.impl;

import java.io.File;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import edu.common.dynamicextensions.domain.nui.Container;
import edu.common.dynamicextensions.domain.nui.Control;
import edu.common.dynamicextensions.domain.nui.ControlValueCrud;
import edu.common.dynamicextensions.domain.nui.DatePicker;
import edu.common.dynamicextensions.domain.nui.FileUploadControl;
import edu.common.dynamicextensions.domain.nui.Label;
import edu.common.dynamicextensions.domain.nui.LinkControl;
import edu.common.dynamicextensions.domain.nui.LookupControl;
import edu.common.dynamicextensions.domain.nui.MultiSelectControl;
import edu.common.dynamicextensions.domain.nui.PageBreak;
import edu.common.dynamicextensions.domain.nui.SubFormControl;
import edu.common.dynamicextensions.domain.nui.UserContext;
import edu.common.dynamicextensions.domain.nui.ValidationErrors;
import edu.common.dynamicextensions.domain.nui.ValidationStatus;
import edu.common.dynamicextensions.napi.ControlValue;
import edu.common.dynamicextensions.napi.FileControlValue;
import edu.common.dynamicextensions.napi.FormAuditManager;
import edu.common.dynamicextensions.napi.FormData;
import edu.common.dynamicextensions.napi.FormDataFilterManager;
import edu.common.dynamicextensions.napi.FormDataManager;
import edu.common.dynamicextensions.ndao.JdbcDao;
import edu.common.dynamicextensions.ndao.JdbcDaoFactory;
import edu.common.dynamicextensions.ndao.ResultExtractor;
import edu.common.dynamicextensions.nutility.DeConfiguration;
import edu.common.dynamicextensions.nutility.IoUtil;

public class FormDataManagerImpl implements FormDataManager {
	private static final String GET_MULTI_SELECT_VALUES_SQL = "SELECT %s FROM %s WHERE RECORD_ID = ?";
	
	private static final String DELETE_MULTI_SELECT_VALUES_SQL = "DELETE FROM %s WHERE RECORD_ID = ?";
	
	private static final String INSERT_MULTI_SELECT_VALUES_SQL = "INSERT INTO %s (RECORD_ID, VALUE) VALUES (?, ?)";
	
	private static final String GET_SUB_FORM_IDS_SQL = "SELECT IDENTIFIER FROM %s WHERE PARENT_RECORD_ID = ?";

	private static final String RECORD_ID_SEQ = "RECORD_ID_SEQ";
	
	private static final String GET_FILE_CONTROL_VALUES = "SELECT %s, %s, %s from %s where IDENTIFIER = ?";

	private boolean auditEnable = true;

	private String activeRecordsJoinSql;
	
	public FormDataManagerImpl(boolean auditEnable) {
		this.auditEnable = auditEnable;
	}
	
	public FormDataManagerImpl() { 
	}

	public void setAuditEnable(boolean auditEnable) {
		this.auditEnable = auditEnable;
	}

	public void setActiveRecordsJoinSql(String activeRecordsJoinSql) {
		this.activeRecordsJoinSql = activeRecordsJoinSql;
	}

	@Override
	public FormDataFilterManager getFilterMgr() {
		return FormDataFilterManagerImpl.getInstance();
	}

	@Override
	public FormData getFormData(Long containerId, Long recordId) {		
		try {
			FormData result = null;
			Container container = Container.getContainer(containerId);
			
			if (container != null) {
				List<FormData> formsData = getFormData(JdbcDaoFactory.getJdbcDao(), container, "IDENTIFIER", recordId);
				if (formsData != null && !formsData.isEmpty()) {
					result = formsData.get(0);
				}
			}
			
			return result;
		} catch (Exception e) {
			throw new RuntimeException("Error obtaining form data: [" + containerId + ", " + recordId  + "]", e);
		}
	}
	
	@Override
	public FormData getFormData(Container container, Long recordId) {		
		try {
			FormData result = null;			
			List<FormData> formData = getFormData(JdbcDaoFactory.getJdbcDao(), container, "IDENTIFIER", recordId);
			if (formData != null && !formData.isEmpty()) {
				result = formData.get(0);
			}
			
			return result;
		} catch (Exception e) {
			throw new RuntimeException("Error obtaining form data: [" + container.getId() + ", " + recordId  + "]", e);
		}	
	}
	
	@Override
	public List<FormData> getSummaryData(Long containerId, List<Long> recordIds) {
		try {
			List<FormData> result = Collections.emptyList();
			
			Container container = Container.getContainer(containerId);
			if (container != null) {
				result = getSummaryData(container, recordIds);
			}
			
			return result;
		} catch (Exception e) {
			throw new RuntimeException("Error obtaining summarized form data list", e);
		}
	}

	@Override
	public List<FormData> getSummaryData(final Container container, final List<Long> recordIds) {
		try {
			JdbcDao jdbcDao = JdbcDaoFactory.getJdbcDao();
			
			final List<Control> summaryCtrls = new ArrayList<Control>();
			for (Control ctrl : container.getControls()) {
				if (isGridControl(ctrl)) {
					summaryCtrls.add(ctrl);
				}
			}
			
			if (summaryCtrls.isEmpty()) {
				return Collections.emptyList();
			}
			
			String sql = buildRecsSummaryQuery(summaryCtrls, container.getDbTableName(), "IDENTIFIER", recordIds.size());
			return jdbcDao.getResultSet(sql, recordIds, new ResultExtractor<List<FormData>>() {
				@Override
				public List<FormData> extract(ResultSet rs) throws SQLException {
					List<FormData> result = new ArrayList<FormData>();
					
					while (rs.next()) {
						Long recordId = rs.getLong("IDENTIFIER");
						FormData formData = new FormData(container);
						formData.setRecordId(recordId);
						
						int idx = 1;
						for (Control ctrl : summaryCtrls) {
							Object rsObj = null;
							if (ctrl instanceof DatePicker) {
								rsObj = rs.getTimestamp(idx++);
							} else {
								rsObj = rs.getObject(idx++);
							}
							
							formData.addFieldValue(new ControlValue(ctrl, rsObj));
						}
						
						result.add(formData);
					}
					
					return result;
				}				
			});			
		} catch (Exception e) {
			throw new RuntimeException("Error obtaining summarized form data list", e);
		}
	}
	
	@Override
	public Long saveOrUpdateFormData(UserContext userCtxt, FormData formData) {
		return saveOrUpdateFormData(userCtxt, formData, JdbcDaoFactory.getJdbcDao());
	}

	@Override
	public Long saveOrUpdateFormData(UserContext userCtxt, FormData formData, JdbcDao jdbcDao) {
		try {
			ensureUniqueConstraints(jdbcDao, formData);
			ensureLinkConstraints(jdbcDao, formData.getContainer(), formData);

			formData = getFilterMgr().executePreFilters(userCtxt, formData);
			
			String operation = formData.getRecordId() == null ? "INSERT" : "UPDATE";
			Long recordId = saveOrUpdateFormData(jdbcDao, formData, null);
			formData.setRecordId(recordId);
			
			if(auditEnable) {
				FormAuditManager auditManager = new FormAuditManagerImpl();
				auditManager.audit(userCtxt, formData, operation, jdbcDao);
			}
			
			formData = getFilterMgr().executePostFilters(userCtxt, formData);
			return recordId;
		} catch (IllegalArgumentException iae) {
			throw iae;
		} catch (Exception e) {
			StringBuilder msg = new StringBuilder("Error saving form data: ");
			if (e.getMessage() != null) {
				msg.append(e.getMessage());
			}
			
			throw new RuntimeException(msg.toString(), e);
		}
	}
	
	@Override
	public void deleteFormData(UserContext userCtxt, Long containerId, Long recordId) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void anonymize(UserContext userCtxt, Container form, Long recordId) {
		FormData formData = getFormData(form, recordId);
		if (formData == null) {
			return;
		}

		anonymize(formData);
		saveOrUpdateFormData(userCtxt, formData);
	}

	@Override
	public List<Long> getRecordIds(Container container, String ctrlName, Object value, boolean useUdn) {
		Control ctrl = useUdn ? container.getControlByUdn(ctrlName, "\\.") : container.getControl(ctrlName, "\\.");
		if (ctrl == null) {
			throw new IllegalArgumentException("No such control: " + ctrlName);
		}

		return getRecordIds(JdbcDaoFactory.getJdbcDao(), ctrl, value, (ctrlName.split("\\.").length > 1));
	}

	private List<FormData> getFormData(final JdbcDao jdbcDao, final Container container, String identifyingColumn, Long identifier)
	throws Exception {
		final List<Control> simpleCtrls = new ArrayList<Control>();
		final List<Control> multiSelectCtrls = new ArrayList<Control>();
		final List<Control> subFormCtrls = new ArrayList<Control>();
		
		segregateControls(container, simpleCtrls, multiSelectCtrls, subFormCtrls);
								
		String query = buildQuery(simpleCtrls, container.getDbTableName(), identifyingColumn);
		List<FormData> formsData = jdbcDao.getResultSet(query, Collections.singletonList(identifier), new ResultExtractor<List<FormData>>() {
			@Override
			public List<FormData> extract(ResultSet rs) 
			throws SQLException {
				List<FormData> formsData = new ArrayList<FormData>();
					
				while (rs.next()) {
					Long recordId = rs.getLong("IDENTIFIER");
					FormData formData = new FormData(container);
					formData.setRecordId(recordId);
						
					extractSimpleValues(simpleCtrls, rs, formData);
						
					for (Control ctrl : multiSelectCtrls) {
						List<String> msValues = getMultiSelectValues(jdbcDao, ctrl, recordId);
						ControlValue ctrlValue = new ControlValue(ctrl, msValues.toArray(new String[0]));					
						formData.addFieldValue(ctrlValue);					
					}
					
					formsData.add(formData);
				}
					
				return formsData;
			}
		});
		
		for (FormData formData : formsData) {
			for (Control ctrl : subFormCtrls) {
				SubFormControl sfCtrl = (SubFormControl)ctrl;
				if (sfCtrl instanceof ControlValueCrud) {
					ControlValueCrud crud = (ControlValueCrud)sfCtrl;
					formData.addFieldValue(crud.getValue(jdbcDao, formData));
				} else if (!sfCtrl.isOneToOne() || !sfCtrl.isInverse()) {
					String fk = StringUtils.isBlank(sfCtrl.getForeignKey()) ? "PARENT_RECORD_ID" : sfCtrl.getForeignKey();
					List<FormData> sfData = getFormData(jdbcDao, sfCtrl.getSubContainer(), fk, formData.getRecordId());

					ControlValue cv = null;
					if (sfCtrl.isOneToOne() && !CollectionUtils.isEmpty(sfData)) {
						cv = new ControlValue(sfCtrl, sfData.iterator().next());
					} else {
						cv = new ControlValue(sfCtrl, sfData);
					}
					
					formData.addFieldValue(cv);					
				} else {
					throw new RuntimeException("One-to-one inverse not yet implemented - TODO");
				}
			}			
		}
		
		return formsData;		
	}
	
	private String buildQuery(List<Control> simpleCtrls, String tableName, String identifyingColumn) {
		StringBuilder query = new StringBuilder("SELECT ");
		for (Control ctrl : simpleCtrls) {
			if (ctrl instanceof FileUploadControl) {
				query.append(ctrl.getDbColumnName()).append("_NAME, ")
					.append(ctrl.getDbColumnName()).append("_TYPE, ")
					.append(ctrl.getDbColumnName()).append("_ID, ");
			} else {
				query.append(ctrl.getDbColumnName()).append(", ");
			}
		}
		
		query.append("IDENTIFIER FROM ").append(tableName)
			.append(" WHERE ").append(identifyingColumn).append(" = ?");		
		
		return query.toString();
	}
	
	private String buildRecsSummaryQuery(List<Control> ctrls, String tableName, String identifyingColumn, int noOfRecords) {
		StringBuilder query = new StringBuilder("SELECT ");
		
		int lookup = 0;
		for (Control ctrl : ctrls) {
			if (ctrl instanceof FileUploadControl) {
				query.append("m.").append(ctrl.getDbColumnName()).append("_NAME, ");
			} else if (ctrl instanceof LookupControl) {
				LookupControl lu = (LookupControl)ctrl;
				query.append("l").append(lookup).append(".").append(lu.getValueColumn()).append(", ");
				lookup++;
			} else {
				query.append("m.").append(ctrl.getDbColumnName()).append(", ");
			}
		}

		
		query.append("m.").append(identifyingColumn)
			.append(" from ")
			.append(tableName)
			.append(" m ");
		
		lookup = 0;
		for (Control ctrl : ctrls) {
			if (ctrl instanceof LookupControl) {
				LookupControl lu = (LookupControl)ctrl;
				String alias = "l" + lookup;
				query.append(" left join ").append(lu.getTableName()).append(" ").append(alias)
					.append(" on ").append(alias).append(".").append(lu.getLookupKey()).append(" = ").append("m.").append(lu.getParentKey());
				
				lookup++;
			}
		}
		
		query.append(" WHERE m.").append(identifyingColumn).append(" in (");
		for (int i = 0; i < noOfRecords - 1; ++i) {
			query.append("?, ");
		}
		query.append("?)");			
		return query.toString();
	}
		
	private List<String> getMultiSelectValues(final JdbcDao jdbcDao, final Control ctrl, Long recordId) 
	throws SQLException {		
		MultiSelectControl msCtrl = (MultiSelectControl)ctrl;
		String query = String.format(GET_MULTI_SELECT_VALUES_SQL, ctrl.getDbColumnName(), msCtrl.getTableName());
		return jdbcDao.getResultSet(query, Collections.singletonList(recordId), new ResultExtractor<List<String>>() {
			@Override
			public List<String> extract(ResultSet rs) throws SQLException {
				List<String> result = new ArrayList<String>();
				while (rs.next()) {
					result.add(ctrl.toString(rs.getObject("VALUE")));
				}
				
				return result;
			}				
		});
	}
	
	public FileControlValue getFileControlValue(Long formId, Long recordId, String ctrlName) {
		Container form = Container.getContainer(formId);
		if (form == null) {
			return null;
		}
		
		Control ctrl = form.getControl(ctrlName, "\\.");
		if (!(ctrl instanceof FileUploadControl)) {
			return null;
		}
		
		FileUploadControl fileCtrl = (FileUploadControl)ctrl;
		String dbTable = fileCtrl.getContainer().getDbTableName();
		String column = fileCtrl.getDbColumnName();
		
		String query = String.format(GET_FILE_CONTROL_VALUES, column + "_NAME", column +"_TYPE", column + "_ID", dbTable);
		return JdbcDaoFactory.getJdbcDao().getResultSet(
				query, Collections.singletonList(recordId),
				new ResultExtractor<FileControlValue>() {
					@Override
					public FileControlValue extract(ResultSet rs)
					throws SQLException {
						if (!rs.next()) {
							return null;
						}
						
						FileControlValue fcv = new FileControlValue(rs.getString(1), rs.getString(2), rs.getString(3));
						fcv.setPath(filePath(fcv.getFileId()));
						return fcv;
					}					
				});		
	}
	
	private Long saveOrUpdateFormData(JdbcDao jdbcDao, FormData formData, Long parentRecId)
	throws Exception {
		List<Control> simpleCtrls = new ArrayList<>();
		List<Control> multiSelectCtrls = new ArrayList<>();
		List<Control> subFormCtrls = new ArrayList<>();

		Container container = formData.getContainer();
		Long recordId = formData.getRecordId();
		List<InputStream> inputStreams = new ArrayList<>();
		
		segregateControls(container, simpleCtrls, multiSelectCtrls, subFormCtrls);
		
		String upsertSql = buildUpsertSql(simpleCtrls, subFormCtrls, container.getDbTableName(), recordId, parentRecId);
		List<Object> params = new ArrayList<>();

		try {
			for (Control ctrl : simpleCtrls) {
				ControlValue ctrlValue = formData.getFieldValue(ctrl.getName());

				if (ctrl instanceof FileUploadControl) {
					FileControlValue fcv = ctrlValue != null ? (FileControlValue) ctrlValue.getValue() : null;
					if (fcv == null) {
						params.add(null);
						params.add(null);
						params.add(null);
					} else {
						params.add(fcv.getFilename());
						params.add(fcv.getContentType());
						params.add(fcv.getFileId());
					}
				} else if (ctrlValue == null || ctrlValue.getValue() == null || ctrlValue.getValue().toString().trim().isEmpty()) {
					params.add(null);
				} else {
					Object value = ctrl.fromString(ctrlValue.getValue().toString());
					params.add(value);
				}
			}

			if (recordId == null && parentRecId != null) {
				params.add(parentRecId);
			}

			if (recordId == null) {
				recordId = jdbcDao.getNextId(RECORD_ID_SEQ);
				formData.setRecordId(recordId);
			}
			
			for (Control ctrl : subFormCtrls) {
				SubFormControl sfCtrl = (SubFormControl)ctrl;
				if (!sfCtrl.isOneToOne() || !sfCtrl.isInverse()) {
					continue;
				}
				
				
				if (sfCtrl instanceof ControlValueCrud) {
					ControlValueCrud crud = (ControlValueCrud)sfCtrl;
					Object key = crud.saveValue(jdbcDao, formData, formData.getFieldValue(sfCtrl.getName()));
					params.add(key);
				} else {
					throw new RuntimeException("One-to-one inverse not yet implemented - TODO");
				}
			}
			
			params.add(recordId);

			if (!upsertSql.isEmpty()) {
				//
				// This happens when there are no simple controls in main form
				// and during form edit
				//
				jdbcDao.executeUpdate(upsertSql, params);
			} else {
				assert (recordId != null);
			}

			for (Control msCtrl : multiSelectCtrls) {
				ControlValue ctrlValue = formData.getFieldValue(msCtrl.getName());
				updateMultiSelectValues(jdbcDao, ctrlValue, recordId);
			}

			for (Control sfCtrl : subFormCtrls) {
				SubFormControl subFormCtrl = (SubFormControl) sfCtrl;
				if (subFormCtrl.isOneToOne() && subFormCtrl.isInverse()) {
					continue;
				}
				
				ControlValue subFormVal = formData.getFieldValue(subFormCtrl.getName());
				
				if (subFormCtrl instanceof ControlValueCrud) {
					ControlValueCrud crud = (ControlValueCrud)subFormCtrl;
					crud.saveValue(jdbcDao, formData, subFormVal);
					continue;
				} 
				
				List<FormData> subFormsData = null;
				if (subFormCtrl.isOneToOne() && subFormVal != null) {
					subFormsData = new ArrayList<>();
					subFormsData.add((FormData)subFormVal.getValue());
				} else if (subFormVal != null) {
					subFormsData = (List<FormData>) subFormVal.getValue();
				}
				
				if (subFormsData == null) {
					continue;
				}
				
				String sfTableName = subFormCtrl.getSubContainer().getDbTableName();
				Set<Long> currentSfIds = new HashSet<>();
				for (FormData subFormData : subFormsData) {
					Long subFormRecId = saveOrUpdateFormData(jdbcDao, subFormData, recordId);
					subFormData.setRecordId(subFormRecId);
					currentSfIds.add(subFormRecId);
				}
		
				Set<Long> persistedSfIds = getPersistedSfIds(jdbcDao, sfTableName, formData.getRecordId());
				List<Long> deletedSfData = new ArrayList<>();
				
				for (Long persistedId : persistedSfIds) {
					if (!currentSfIds.contains(persistedId)) {
						deletedSfData.add(persistedId);
					}
				}
				
				if (!deletedSfData.isEmpty()) {
					removeDeletedSfData(jdbcDao, sfTableName, deletedSfData);
				}
			}
		} finally {
			inputStreams.forEach(IoUtil::close);
		}
		
		return recordId;		
	}

	private String buildUpsertSql(List<Control> simpleCtrls, List<Control> subFormCtrls, String tableName, Long recordId, Long parentRecId) {
		String sql = null;
		if (recordId == null) {
			sql = buildInsertSql(simpleCtrls, subFormCtrls, tableName, parentRecId != null);
		} else {
			sql = buildUpdateSql(simpleCtrls, subFormCtrls, tableName);
		}
		
		return sql;
	}
	
	//
	// take care of file control;
	// 
	private String buildInsertSql(List<Control> simpleCtrls, List<Control> subFormCtrls, String tableName, boolean insertParentRecId) {
		StringBuilder columnNames = new StringBuilder();
		StringBuilder bindVars = new StringBuilder();
		
		for (Control ctrl : simpleCtrls) {
			if (ctrl instanceof FileUploadControl) {
				columnNames.append(ctrl.getDbColumnName()).append("_NAME").append(", ");
				bindVars.append("?, ");
				
				columnNames.append(ctrl.getDbColumnName()).append("_TYPE").append(", ");
				bindVars.append("?, ");
				
				columnNames.append(ctrl.getDbColumnName()).append("_ID").append(", ");
				bindVars.append("?, ");
			} else {
				columnNames.append(ctrl.getDbColumnName()).append(", ");
				bindVars.append("?, ");
			}			
		}
		
		if (insertParentRecId) {
			columnNames.append("PARENT_RECORD_ID, ");
			bindVars.append("?, ");
		}
		
		for (Control ctrl : subFormCtrls) {
			SubFormControl sfCtrl = (SubFormControl)ctrl;
			if (sfCtrl.isOneToOne() && sfCtrl.isInverse()) {
				columnNames.append(sfCtrl.getDbColumnName()).append(", ");
				bindVars.append("?, ");				
			}
		}
		
		columnNames.append("IDENTIFIER");
		bindVars.append("?");
		
		StringBuilder insertSql = new StringBuilder();
		insertSql.append("INSERT INTO ").append(tableName)
			.append("(").append(columnNames).append(") VALUES(")
			.append(bindVars).append(")");
		
		return insertSql.toString();
	}
	
	private String buildUpdateSql(List<Control> simpleCtrls, List<Control> subFormCtrls, String tableName) {
		if (simpleCtrls.isEmpty()) {
			return "";
		}
		
		StringBuilder updateSql = new StringBuilder();		
		updateSql.append("UPDATE ").append(tableName).append(" SET ");
		
		for (Control ctrl : simpleCtrls) {
			if (ctrl instanceof FileUploadControl) {
				updateSql.append(ctrl.getDbColumnName()).append("_NAME = ?, ");
				updateSql.append(ctrl.getDbColumnName()).append("_TYPE = ?, ");
				updateSql.append(ctrl.getDbColumnName()).append("_ID = ?, ");
			} else {
				updateSql.append(ctrl.getDbColumnName()).append(" = ?, ");
			}			
		}
		
		for (Control ctrl : subFormCtrls) {
			SubFormControl sfCtrl = (SubFormControl)ctrl;
			if (sfCtrl.isOneToOne() && sfCtrl.isInverse()) {
				updateSql.append(ctrl.getDbColumnName()).append(" = ?, ");
			}			
		}
		
		updateSql.delete(updateSql.length() - 2, updateSql.length());
		
		updateSql.append(" WHERE IDENTIFIER = ?");
		return updateSql.toString();
	}
	
	private void updateMultiSelectValues(JdbcDao jdbcDao, ControlValue msCtrlValue, Long recordId) 
	throws Exception {
		String[] strValues =  msCtrlValue != null ? (String[])msCtrlValue.getValue() : null;
		MultiSelectControl msCtrl = msCtrlValue != null ? (MultiSelectControl)msCtrlValue.getControl() : null;
			
		if (msCtrl != null) {
			String deleteSql = String.format(DELETE_MULTI_SELECT_VALUES_SQL, msCtrl.getTableName());		
			jdbcDao.executeUpdate(deleteSql, Collections.singletonList(recordId));
		}
		
		if (strValues != null) {
			String insertSql = String.format(INSERT_MULTI_SELECT_VALUES_SQL, msCtrl.getTableName());
			
			List<Object> params = new ArrayList<Object>();
			for (String strValue : strValues) {
				Object value = msCtrl.fromString(strValue);
				
				params.clear();
				params.add(recordId);				
				params.add(value);				
				jdbcDao.executeUpdate(insertSql, params);				
			}
		}		
	}
	
	private void segregateControls(
			Container container, 
			List<Control> simpleCtrls, 
			List<Control> multiSelectCtrls, 
			List<Control> subFormCtrls) {
		
		for (Control ctrl : container.getControlsMap().values()) {
			if (ctrl instanceof SubFormControl) {
				subFormCtrls.add(ctrl);
			} else if (ctrl instanceof MultiSelectControl) {
				multiSelectCtrls.add(ctrl); 
			} else if (!(ctrl instanceof Label || ctrl instanceof PageBreak)) {
				simpleCtrls.add(ctrl);
			}
		}
	}
	
	private Set<Long> getPersistedSfIds(JdbcDao jdbcDao, String dbTableName, Long parentRecId) {
		String selectSql = String.format(GET_SUB_FORM_IDS_SQL, dbTableName);
		return jdbcDao.getResultSet(selectSql, Collections.singletonList(parentRecId), new ResultExtractor<Set<Long>>() {
			@Override
			public Set<Long> extract(ResultSet rs) throws SQLException {
				Set<Long> sfIds = new HashSet<Long>();
					
				while (rs.next()) {
					sfIds.add(rs.getLong("IDENTIFIER"));
				}
				
				return sfIds;
			}
		});			
	}
	
	private void removeDeletedSfData(JdbcDao jdbcDao, String sfTableName, List<Long> toBeDeletedSfData) {
		StringBuilder deleteSqlBuilder = new StringBuilder().append("DELETE FROM ")
				.append(sfTableName).append(" WHERE IDENTIFIER IN ( ");
		
		for (int i = 0 ; i < toBeDeletedSfData.size() ; i++) {
			deleteSqlBuilder.append(" ?,");
		}
		String deleteSql = deleteSqlBuilder.substring(0, deleteSqlBuilder.lastIndexOf(",")).concat(")");
		jdbcDao.executeUpdate(deleteSql, toBeDeletedSfData);
	}

	private void extractSimpleValues(List<Control> ctrls, ResultSet rs, FormData formData) 
	throws SQLException {
		for (Control ctrl : ctrls) {
			ControlValue ctrlValue = null;

			if (ctrl instanceof FileUploadControl) {
				String filename = rs.getString(ctrl.getDbColumnName() + "_NAME");
				if (filename != null) {
					String type = rs.getString(ctrl.getDbColumnName() + "_TYPE");
					String fileId = rs.getString(ctrl.getDbColumnName() + "_ID");
					ctrlValue = new ControlValue(ctrl, new FileControlValue(filename, type, fileId));
				} else {
					ctrlValue = new ControlValue(ctrl, null);
				}
			} else {
				Object rsObj = null;
				if (ctrl instanceof DatePicker) {
					rsObj = rs.getTimestamp(ctrl.getDbColumnName());
				} else {
					rsObj = rs.getObject(ctrl.getDbColumnName());
				}
				
				String value = ctrl.toString(rsObj);
				ctrlValue = new ControlValue(ctrl, value);
			}
				
			formData.addFieldValue(ctrlValue);
		}		
	}
	
	private boolean isGridControl(Control ctrl) {
		if (ctrl instanceof MultiSelectControl || ctrl instanceof SubFormControl) {
			return false;
		}
		
		if (ctrl instanceof PageBreak || ctrl instanceof Label) {
			return false;
		}
		
		return ctrl.showInGrid();
	}
	
	@SuppressWarnings("unchecked")
	private void anonymize(FormData formData) {
		formData.getFieldValues().forEach(ctrlValue -> {
			Control ctrl = ctrlValue.getControl();
			if (ctrl instanceof SubFormControl) {
				SubFormControl sfCtrl = (SubFormControl) ctrl;
				if (sfCtrl.isOneToOne()) {
					anonymize((FormData) ctrlValue.getValue());
				} else {
					((List<FormData>) ctrlValue.getValue()).forEach(this::anonymize);
				}
			} else if (ctrl.isPhi()) {
				if (ctrl instanceof FileUploadControl) {
					FileControlValue fcv = (FileControlValue)ctrlValue.getValue();
					if (fcv != null && StringUtils.isNotBlank(fcv.getFileId())) {
						FileUtils.deleteQuietly(new File(filePath(fcv.getFileId())));
					}
				}

				ctrlValue.setValue(null);
			}
		});
	}

	private String filePath(String fileId) {
		return DeConfiguration.getInstance().fileUploadDir() + File.separator + fileId;
	}

	private void ensureUniqueConstraints(final JdbcDao jdbcDao, FormData formData) {
		ValidationErrors errors = new ValidationErrors();
		for (Control ctrl : formData.getContainer().getUniqueControls()) {
			ControlValue cv = formData.getFieldValue(ctrl.getName());
			if (cv == null || cv.getValue() == null) {
				//
				// no (new) value specified
				//
				continue;
			}

			List<Long> recIds = getRecordIds(jdbcDao, ctrl, cv.getValue(), false);
			if (CollectionUtils.isEmpty(recIds)) {
				//
				// no records found with this value
				//
				continue;
			}

			if (recIds.size() == 1 && recIds.get(0).equals(formData.getRecordId())) {
				//
				// only one record found with field value and the record is same as that being updated
				//
				continue;
			}

			errors.addError(ctrl.getCaption(), ValidationStatus.VALUE_NOT_UNIQUE);
		}

		errors.throwIfErrors();
	}

	private void ensureLinkConstraints(final JdbcDao jdbcDao, Container form, FormData formData) {
		ValidationErrors errors = new ValidationErrors();
		for (Control ctrl : formData.getContainer().getOrderedControlList()) {
			ControlValue cv = formData.getFieldValue(ctrl.getName());
			if (cv == null || cv.getValue() == null) {
				continue;
			}

			if (ctrl instanceof SubFormControl) {
				SubFormControl sfCtrl = (SubFormControl) ctrl;
				try {
					if (sfCtrl.isOneToOne()) {
						ensureLinkConstraints(jdbcDao, sfCtrl.getSubContainer(), (FormData)cv.getValue());
					} else {
						List<FormData> sfDataList = (List<FormData>)cv.getValue();
						sfDataList.forEach(sfData -> ensureLinkConstraints(jdbcDao, sfCtrl.getSubContainer(), sfData));
					}
				} catch (ValidationErrors ve) {
					errors.addErrors(ve.getErrors());
				}
			} else if (ctrl instanceof LinkControl) {
				LinkControl linkCtrl = (LinkControl) ctrl;
				Container linkedForm = Container.getContainer(linkCtrl.getFormName());
				if (!isValidRecord(jdbcDao, linkedForm, cv.getValue())) {
					errors.addError(linkCtrl.getCaption(), ValidationStatus.INVALID_VALUE);
				}
			}
		}

		errors.throwIfErrors();
	}

	private List<Long> getRecordIds(final JdbcDao jdbcDao, Control ctrl, Object value, boolean sfField) {
		String valueTabAlias = "pf";

		StringBuilder sql = new StringBuilder("select ")
			.append(sfField ? "pf.parent_record_id" : "pf.identifier")
			.append(" from ").append(ctrl.getContainer().getDbTableName()).append(" pf ");

		if (ctrl instanceof MultiSelectControl) {
			MultiSelectControl msCtrl = (MultiSelectControl) ctrl;
			sql.append(" inner join ").append(msCtrl.getTableName()).append(" ms on ms.record_id = pf.identifier");
			valueTabAlias = "ms";
		}

		if (StringUtils.isNotBlank(activeRecordsJoinSql)) {
			String joinSql = String.format(activeRecordsJoinSql, "pf", sfField ? "parent_record_id" : "identifier");
			sql.append(" inner join ").append(joinSql);
		}

		String valueColumnName = ctrl.getDbColumnName();
		Object valueObj = value instanceof String ? ctrl.fromString((String)value) : value;
		if (ctrl instanceof FileUploadControl) {
			valueColumnName += "_NAME";
			valueObj = value;
		}

		sql.append(" where ").append(valueTabAlias).append(".").append(valueColumnName).append(" = ?");
		return jdbcDao.getResultSet(sql.toString(), Collections.singletonList(valueObj),
			(rs) -> {
				List<Long> recordIds = new ArrayList<>();
				while (rs.next()) {
					recordIds.add(rs.getLong(1));
				}

				return recordIds;
			}
		);
	}

	private boolean isValidRecord(final JdbcDao jdbcDao, Container form, Object value) {
		if (value == null || value.toString().trim().isEmpty()) {
			return true;
		}

		try {
			Long recordId = (value instanceof Long) ? (Long) value : Long.parseLong(value.toString());

			StringBuilder sql = new StringBuilder("select t.identifier from ")
				.append(form.getDbTableName()).append(" t ");
			if (StringUtils.isNotBlank(activeRecordsJoinSql)) {
				String joinSql = String.format(activeRecordsJoinSql, "t", "identifier");
				sql.append(" inner join ").append(joinSql);
			}

			sql.append(" where t.identifier = ?");
			return jdbcDao.getResultSet(sql.toString(), Collections.singletonList(recordId), ResultSet::next);
		} catch (Exception e) {
			return false;
		}
	}
}
