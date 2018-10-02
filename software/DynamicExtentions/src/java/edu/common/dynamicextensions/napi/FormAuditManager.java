package edu.common.dynamicextensions.napi;

import edu.common.dynamicextensions.domain.nui.UserContext;
import edu.common.dynamicextensions.ndao.JdbcDao;

public interface FormAuditManager {

	void audit(UserContext userCtxt, FormData formData, String operation);

	void audit(UserContext userCtxt, FormData formData, String operation, JdbcDao jdbcDao);
}
