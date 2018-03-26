package edu.common.dynamicextensions.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import edu.common.dynamicextensions.ndao.JdbcDaoFactory;
import edu.common.dynamicextensions.ndao.ResultExtractor;
import edu.common.dynamicextensions.query.ast.ConcatNode;
import edu.common.dynamicextensions.query.ast.ExpressionNode;
import edu.common.dynamicextensions.query.ast.FieldNode;
import edu.common.dynamicextensions.query.ast.QueryExpressionNode;
import edu.common.dynamicextensions.query.ast.SelectListNode;

public class Query {
    private static final Logger logger = Logger.getLogger(Query.class);
    
    private JoinTree queryJoinTree;

    private QueryExpressionNode queryExpr;

    private WideRowMode wideRowMode;
    
    private boolean ic;

	private boolean outputIsoDateTime;

	private boolean outputExpression;

    private String dateFormat = "MM-dd-yyyy";

    private String timeFormat = "HH:mm";
    
    private boolean vcEnabled;
    
    private ResultPostProc resultPostProc;

	private PathConfig pathConfig;
        
    public static Query createQuery() {
        return new Query();
    }
    
    private Query() {
    }
            
    public Query wideRowMode(WideRowMode mode) {
        this.wideRowMode = mode;
        return this;
    }
    
    public Query ic(boolean ic) {
    	this.ic = ic;
    	return this;
    }

    public Query outputIsoDateTime(boolean outputIsoDateTime) {
		this.outputIsoDateTime = outputIsoDateTime;
		return this;
	}

	public Query outputExpression(boolean outputExpression) {
		this.outputExpression = outputExpression;
		return this;
	}

    public Query dateFormat(String dateFormat) {
    	this.dateFormat = dateFormat;
    	return this;
    }

    public Query timeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
        return this;
    }
    
    public Query enableVersionedForms(boolean vcEnabled) {
    	this.vcEnabled = vcEnabled;
    	return this;
    }

    public Query pathConfig(PathConfig pathConfig) {
		this.pathConfig = pathConfig;
		return this;
	}
  
    public void compile(String rootFormName, String query) {
        compile(rootFormName, query, null);
    }
    
    public void compile(String rootFormName, String query, String restriction) {
        QueryCompiler compiler = new QueryCompiler(rootFormName, query, restriction);
        compiler.enabledVersionedForms(vcEnabled).pathConfig(pathConfig).compile();
        queryExpr     = compiler.getQueryExpr();
        queryJoinTree = compiler.getQueryJoinTree();
        
        if (queryExpr.hasResultPostProc()) {
        	String procName = queryExpr.getResultPostProcName();
        	ResultPostProcFactory factory = ResultPostProcManager.getInstance().getFactory(procName);
        	resultPostProc = factory.create(queryExpr);
        }        
    }

    public boolean isAggregateQuery() {
        if (queryExpr == null || queryExpr.getSelectList() == null) {
            return false;
        }

        return queryExpr.getSelectList().hasAggregateExpr();
    }

    public boolean isPhiResult(boolean onlyFields) {
        if (queryExpr == null || queryExpr.getSelectList() == null) {
            return false;
        }

        for (ExpressionNode element : queryExpr.getSelectList().getElements()) {
            if (!element.isPhi()) {
                continue;
            }

            if (!onlyFields || element instanceof FieldNode || (element instanceof ConcatNode && ((ConcatNode) element).hasPhiField())) {
				return true;
			}
        }

        return false;
    }

    public String getResultProcessorName() {
        return queryExpr != null && queryExpr.hasResultPostProc() ? queryExpr.getResultPostProcName() : null;
    }

    public long getCount() {
        QueryGenerator gen = new QueryGenerator(false, ic, dateFormat, timeFormat);
        String countSql = gen.getCountSql(queryExpr, queryJoinTree);

        long t1 = System.currentTimeMillis();            
        long count = JdbcDaoFactory.getJdbcDao().getResultSet(countSql, null, new ResultExtractor<Long>() {
        	@Override
        	public Long extract(ResultSet rs) throws SQLException {
        		return rs.next() ? rs.getLong(1) : -1L;
        	}
        });
        
        long t2 = System.currentTimeMillis();
        logger.info("Count SQL: " + countSql + "; Query Exec Time: " + (t2 - t1));            
        return count;
    }

    public QueryResponse getData() {
        return getData(0, 0);
    }

    public QueryResponse getData(int start, int numRows) {
    	final boolean wideRowSupport = 
    			(wideRowMode != WideRowMode.OFF) && 
    			!queryExpr.isAggregateQuery() &&
    			!queryExpr.hasResultPostProc();
    	
        final String dataSql = getDataSql(wideRowSupport, start, numRows);        
        final long t1 = System.currentTimeMillis();        
        return JdbcDaoFactory.getJdbcDao().getResultSet(dataSql, null, new ResultExtractor<QueryResponse>() {
        	@Override
        	public QueryResponse extract(ResultSet rs)
        	throws SQLException {
        		long t2 = System.currentTimeMillis();
        		QueryResultData resultData = null;
        		if (wideRowSupport) {
        			resultData = getWideRowData(rs);
        		} else if (resultPostProc != null) {
        			resultData = getProcessedData(rs);
        		} else {
        			resultData = getQueryResultData(rs);
        		}

				resultData.setOutputExpression(!(resultPostProc instanceof Crosstab) && outputExpression);
        		long t3 = System.currentTimeMillis();        		
        		logger.info("Data SQL: " + dataSql + "; Query Exec Time: " + (t2 - t1) + "; Result Prep Time: " + (t3 - t2));
        		
        		QueryResponse resp = new QueryResponse();
        		resp.setSql(dataSql);																																																																																										
        		resp.setResultData(resultData);
        		resp.setExecutionTime(t2 - t1);
        		resp.setPostExecutionTime(t3 - t2);
        		
        		Calendar cal = Calendar.getInstance();
        		cal.setTimeInMillis(t1);
        		resp.setTimeOfExecution(cal.getTime());
        		return resp;
        	}
        });
    }
            
    public String getDataSql() {
        return getDataSql(false, 0, 0);
    }
    
    private String getDataSql(boolean wideRows, int start, int numRows) {
        QueryGenerator gen = new QueryGenerator(wideRows, ic, dateFormat, timeFormat);
        return gen.getDataSql(queryExpr, queryJoinTree, start, numRows);        
    }

    private QueryResultData getWideRowData(ResultSet rs) {
        ShallowWideRowGenerator wideRowGenerator = new ShallowWideRowGenerator(queryJoinTree, queryExpr, wideRowMode);
        wideRowGenerator.start();
        int dbRowsCount = wideRowGenerator.processResultSet(rs);
        wideRowGenerator.end();

		QueryResultData qrd = getQueryResultData(wideRowGenerator.getResultColumns());
        qrd.dataSource(wideRowGenerator);
        qrd.setDbRowsCount(dbRowsCount);
        return qrd;
    }
    
    private QueryResultData getProcessedData(ResultSet rs) {
    	int dbRowsCount = resultPostProc.processResultSet(rs);
    	QueryResultData qrd = getQueryResultData(resultPostProc.getResultColumns());
    	qrd.setDbRowsCount(dbRowsCount);
    	qrd.dataSource(resultPostProc.getRows());
    	return qrd;
    }

    private QueryResultData getQueryResultData(ResultSet rs)
    throws SQLException {
        QueryResultData queryResult = getQueryResultData(getResultColumns(queryExpr));
        queryResult.dataSource(rs);
        return queryResult;
    }

    private QueryResultData getQueryResultData(List<ResultColumn> columns) {
		if (outputIsoDateTime) {
			return new QueryResultData(columns);
		} else {
			return new QueryResultData(columns, dateFormat, timeFormat);
		}
	}
            
    private List<ResultColumn> getResultColumns(QueryExpressionNode queryExpr) {
		return queryExpr.getSelectList().getElements().stream()
			.map(node -> new ResultColumn(node, 0))
			.collect(Collectors.toList());
    }
}
