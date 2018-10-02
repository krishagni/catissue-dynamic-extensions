/**
 * 
 */
package edu.common.dynamicextensions.napi.impl;

import java.io.IOException;
import java.io.Writer;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.common.dynamicextensions.domain.FormAuditEvent;
import edu.common.dynamicextensions.domain.nui.Container;
import edu.common.dynamicextensions.domain.nui.Control;
import edu.common.dynamicextensions.domain.nui.Label;
import edu.common.dynamicextensions.domain.nui.MultiSelectControl;
import edu.common.dynamicextensions.domain.nui.PageBreak;
import edu.common.dynamicextensions.domain.nui.SubFormControl;
import edu.common.dynamicextensions.domain.nui.UserContext;
import edu.common.dynamicextensions.napi.ControlValue;
import edu.common.dynamicextensions.napi.FormAuditManager;
import edu.common.dynamicextensions.napi.FormData;
import edu.common.dynamicextensions.ndao.DbSettingsFactory;
import edu.common.dynamicextensions.ndao.JdbcDao;
import edu.common.dynamicextensions.ndao.JdbcDaoFactory;

public class FormAuditManagerImpl implements FormAuditManager {
	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public void audit(UserContext userCtxt, FormData formData, String operation) {
		try {
			audit(userCtxt, formData, operation, JdbcDaoFactory.getJdbcDao());
		} catch (Exception e) {
			throw new RuntimeException("Error saving form audit data", e);
		} 
	}
	@Override
	public void audit(UserContext userCtxt, FormData formData, String operation, JdbcDao jdbcDao) {
		FormAuditEvent formAuditEvent = new FormAuditEvent();

		formAuditEvent.setFormId(formData.getContainer().getId());
		formAuditEvent.setFormName(formData.getContainer().getName());
		formAuditEvent.setRecordId(formData.getRecordId());
		formAuditEvent.setFormData(getAuditDataJson(userCtxt, formData));
		formAuditEvent.setIpAddress(userCtxt.getIpAddress());
		formAuditEvent.setUserId(userCtxt.getUserId());
		formAuditEvent.setOperation(operation != null ? operation : "");
	
		persist(formAuditEvent, jdbcDao);
	}

	private String getAuditDataJson(UserContext userCtxt, FormData formData) {
		try {
			Map<String, Object> data = new HashMap<>();
			data.put("name", formData.getContainer().getName());
			data.put("user", userCtxt != null ? userCtxt.getUserName() : "no-user");
			data.put("ipAddress", userCtxt != null ? userCtxt.getIpAddress() : "no-ip");
			data.put("recordId", formData.getRecordId());
			data.put("data", getFieldSets(formData));

			return mapper.writeValueAsString(data);
		} catch (Exception e) {
			throw new RuntimeException("Error generating audit event data JSON: " + e.getMessage(), e);
		}
	}

	private Map<String, Object> getFieldSets(FormData formData) {
		Container c = formData.getContainer();

		Map<String, Object> result = new HashMap<>();
		result.put("formId", c.getId());
		result.put("formName", c.getName());
		result.put("dbTable", c.getDbTableName());

		List<Map<String, Object>> fields = new ArrayList<>();
		for (ControlValue fieldValue :formData.getFieldValues()) {
			Control ctrl = fieldValue.getControl();
			Object val = fieldValue.getValue();

			if (ctrl instanceof Label || ctrl instanceof PageBreak) {
				continue;
			}

			Map<String, Object> field = new HashMap<>();
			field.put("controlName", ctrl.getName());

			if (ctrl instanceof SubFormControl) {
				List<FormData> subFormsData = (List<FormData>) val;
				if (subFormsData == null) {
					continue;
				}

				List<Map<String, Object>> subRecords = new ArrayList<>();
				for (FormData subFormData : subFormsData) {
					subRecords.add(getFieldSets(subFormData));
				}

				field.put("subRecords", subRecords);
			} else {
				if (ctrl instanceof MultiSelectControl) {
					MultiSelectControl msCtrl = (MultiSelectControl) ctrl;
					field.put("multiple", true);
					field.put("dbTable", msCtrl.getTableName());
				}

				field.put("dbColumn", ctrl.getDbColumnName());
				field.put("value", val);
			}

			fields.add(field);
		}

		result.put("fields", fields);
		return result;
	}

	private void persist(FormAuditEvent formAuditEvent, JdbcDao jdbcDao) {
		try {
			Long auditId = insertAndRetrieveAuditEvent(formAuditEvent, jdbcDao);
			formAuditEvent.setIdentifier(auditId);

			if (DbSettingsFactory.isOracle()) {
				insertFormAuditEventData(formAuditEvent, jdbcDao);
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to persist audit data", e);
		}
	}


	private Long insertAndRetrieveAuditEvent(FormAuditEvent auditEvent, JdbcDao jdbcDao) 
	throws Exception {
		List<Object> params = new ArrayList<>();
		params.add(auditEvent.getIpAddress());
		params.add(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		params.add(auditEvent.getUserId());
		params.add(auditEvent.getOperation());
		params.add(auditEvent.getFormId());
		params.add(auditEvent.getFormName());
		params.add(auditEvent.getRecordId());

		String sql = INSERT_AUDIT_EVENT_SQL_ORA;
		if (DbSettingsFactory.isMySQL()) {
			sql = INSERT_AUDIT_EVENT_SQL_MSQL;
			params.add(auditEvent.getFormData());
		}

		Number key = jdbcDao.executeUpdateAndGetKey(sql, params, "IDENTIFIER");
		return key != null ? key.longValue() : null;
	}

	private void insertFormAuditEventData(FormAuditEvent formAuditEvent, JdbcDao jdbcDao)
	throws Exception {
		List<Object> params = new ArrayList<>();
		params.add(formAuditEvent.getIdentifier());
		
		Clob clob = jdbcDao.getResultSet(GET_AUDIT_DATA_BY_ID_SQL, params, (rs) -> {
			rs.next();
			return rs.getClob("FORM_DATA");
		});

		writeToClob(clob, formAuditEvent.getFormData());
	}
	
	private void writeToClob(Clob clob, String auditData) throws IOException {
		Writer clobOut = null;
		try {
			clobOut = clob.setCharacterStream(0);	
			clobOut.write(auditData);
		} catch (Exception e) {
			throw new RuntimeException("Error writing clob", e);
		} finally {
			IOUtils.closeQuietly(clobOut);
		}
	}

	private static final String INSERT_AUDIT_EVENT_SQL_ORA =
			"INSERT INTO " +
			"  DYEXTN_AUDIT_EVENTS(IDENTIFIER, IP_ADDRESS, EVENT_TIMESTAMP, USER_ID, EVENT_TYPE, FORM_ID, FORM_NAME, RECORD_ID, FORM_DATA) " +
			"VALUES" +
			"  (DYEXTN_AUDIT_EVENTS_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, empty_clob())";

	private static final String INSERT_AUDIT_EVENT_SQL_MSQL =
			"INSERT INTO " +
			"  DYEXTN_AUDIT_EVENTS(IDENTIFIER, IP_ADDRESS, EVENT_TIMESTAMP, USER_ID, EVENT_TYPE, FORM_ID, FORM_NAME, RECORD_ID, FORM_DATA) " +
			"VALUES" +
			"  (default, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String GET_AUDIT_DATA_BY_ID_SQL =
			"SELECT " +
			"  FORM_DATA " +
			"FROM " +
			"  DYEXTN_AUDIT_EVENTS " +
			"WHERE " +
			"  IDENTIFIER = ? " +
			"FOR UPDATE";
}
