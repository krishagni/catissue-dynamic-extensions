package edu.common.dynamicextensions.query;

import java.sql.ResultSet;
import java.util.List;

public interface ResultPostProc {
	int processResultSet(ResultSet rs);
	
	List<ResultColumn> getResultColumns();
	
	List<Object[]> getRows();
	
	void cleanup();
}
