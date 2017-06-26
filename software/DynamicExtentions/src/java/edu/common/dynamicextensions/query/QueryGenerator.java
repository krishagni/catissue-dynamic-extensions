package edu.common.dynamicextensions.query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import edu.common.dynamicextensions.domain.nui.Container;
import edu.common.dynamicextensions.domain.nui.Control;
import edu.common.dynamicextensions.domain.nui.DataType;
import edu.common.dynamicextensions.domain.nui.FileUploadControl;
import edu.common.dynamicextensions.domain.nui.LinkControl;
import edu.common.dynamicextensions.domain.nui.LookupControl;
import edu.common.dynamicextensions.domain.nui.MultiSelectControl;
import edu.common.dynamicextensions.ndao.DbSettingsFactory;
import edu.common.dynamicextensions.query.ast.AggregateNode;
import edu.common.dynamicextensions.query.ast.ArithExpressionNode;
import edu.common.dynamicextensions.query.ast.ArithExpressionNode.ArithOp;
import edu.common.dynamicextensions.query.ast.BetweenNode;
import edu.common.dynamicextensions.query.ast.ConcatNode;
import edu.common.dynamicextensions.query.ast.CurrentDateNode;
import edu.common.dynamicextensions.query.ast.DateDiffFuncNode;
import edu.common.dynamicextensions.query.ast.DateDiffFuncNode.DiffType;
import edu.common.dynamicextensions.query.ast.DateFormatFuncNode;
import edu.common.dynamicextensions.query.ast.DateIntervalNode;
import edu.common.dynamicextensions.query.ast.DateRangeFuncNode;
import edu.common.dynamicextensions.query.ast.OrderExprListNode;
import edu.common.dynamicextensions.query.ast.ExpressionNode;
import edu.common.dynamicextensions.query.ast.FieldNode;
import edu.common.dynamicextensions.query.ast.FilterExpressionNode;
import edu.common.dynamicextensions.query.ast.FilterNode;
import edu.common.dynamicextensions.query.ast.FilterNode.RelationalOp;
import edu.common.dynamicextensions.query.ast.LimitExprNode;
import edu.common.dynamicextensions.query.ast.LiteralValueListNode;
import edu.common.dynamicextensions.query.ast.LiteralValueNode;
import edu.common.dynamicextensions.query.ast.Node;
import edu.common.dynamicextensions.query.ast.OrderExprNode;
import edu.common.dynamicextensions.query.ast.QueryExpressionNode;
import edu.common.dynamicextensions.query.ast.RoundOffNode;
import edu.common.dynamicextensions.query.ast.SelectListNode;

public class QueryGenerator {
	
	private static final String LIMIT_QUERY = "select * from (select tab.*, rownum rnum from (%s) tab where rownum <= %d) where rnum >= %d";

	private static final Pattern DATE_FMT_PATTERN = Pattern.compile("%(.+?)%");

	private static final Map<String, String> MYSQL_DATE_FMTS = new HashMap<String, String>() {
		{
			put("year4", "%Y");
			put("year2", "%y");
			put("month2", "%m");
			put("month3", "%b");
			put("month", "%M");
			put("month_day", "%d");
			put("hour", "%H");
			put("hour12", "%h");
			put("minute", "%i");
			put("second", "%s");
			put("meridian", "%p");
		}
	};

	private static final Map<String, String> ORACLE_DATE_FMTS = new HashMap<String, String>() {
		{
			put("year4", "YYYY");
			put("year2", "YY");
			put("month2", "MM");
			put("month3", "MON");
			put("month", "MONTH");
			put("month_day", "DD");
			put("hour", "HH24");
			put("hour12", "HH12");
			put("minute", "MI");
			put("second", "SS");
			put("meridian", "AM");
		}
	};

	private boolean wideRowSupport;
	
	private boolean ic;

	private String dateFormat;

	private String timeFormat;

	private String dateTimeFormat;

	private String dbDateFormat = "MM-dd-yyyy HH:mm";

    public QueryGenerator() {
    }
    
    public QueryGenerator(boolean wideRowSupport, boolean ic, String dateFormat, String timeFormat) {
    	this.wideRowSupport = wideRowSupport;
    	this.ic = ic;
        this.dateFormat = dateFormat;
        this.timeFormat = timeFormat;
        this.dateTimeFormat = dateFormat + " " + timeFormat;
    }
   
    public String getCountSql(QueryExpressionNode queryExpr, JoinTree joinTree) {
    	StringBuilder countSql = new StringBuilder();
    	
    	if (wideRowSupport) {
        	String fromClause  = buildFromClause(joinTree);
        	String whereClause = buildWhereClause(queryExpr.getFilterExpr());
        	String activeClause = buildActiveCond(joinTree);
			String linkClause = buildLinkCond(joinTree);
			whereClause = and(and(whereClause, activeClause), linkClause);
        	
        	String alias = joinTree.getAlias();
        	String pk = joinTree.getForm().getPrimaryKey();
    		countSql.append("select count(distinct ")
    			.append(alias).append(".").append(pk).append(")")
    			.append(" from ").append(fromClause)
    			.append(" where ").append(whereClause);    			    		
    	} else {
    		String dataSql = getDataSql(queryExpr, joinTree);
    		countSql.append("select count(*) from (").append(dataSql).append(")");    		
    	}
    	
    	return countSql.toString();
    }

    public String getDataSql(QueryExpressionNode queryExpr, JoinTree joinTree) {
    	String selectClause = buildSelectClause(queryExpr.getSelectList(), joinTree);
        String fromClause  = buildFromClause(joinTree);
        String whereClause = buildWhereClause(queryExpr.getFilterExpr());        
        String activeClause = buildActiveCond(joinTree);
		String linkClause = buildLinkCond(joinTree);
        String groupBy = buildGroupBy(queryExpr.getSelectList());

		whereClause = and(and(whereClause, activeClause), linkClause);
        String sql = new StringBuilder("select ").append(selectClause)
        	.append(" from ").append(fromClause)
        	.append(" where ").append(whereClause)
			.toString();

		String orderBy = null;
		if (wideRowSupport) {
			if (StringUtils.isBlank(groupBy)) {
				orderBy = joinTree.getAlias() + "." + joinTree.getForm().getPrimaryKey();
			}
		} else {
			orderBy = buildOrderBy(queryExpr.getOrderExpr());
		}

		if (StringUtils.isNotBlank(groupBy)) {
			sql += " group by " + groupBy;
		}

		if (StringUtils.isNotBlank(orderBy)) {
			sql += " order by " + orderBy;
		}

        if (queryExpr.getLimitExpr() != null) {
        	sql = addLimitClause(sql, queryExpr.getLimitExpr());
        }

        return sql;
    }

    public String getDataSql(QueryExpressionNode queryExpr, JoinTree joinTree, int start, int numRows) {
        String dataSql = getDataSql(queryExpr, joinTree);

        String result = null;
        if(start == 0 && numRows == 0) {
            result = dataSql;          
        } else {
        	String orderedQuery = dataSql;
        	if (!wideRowSupport && queryExpr.getOrderExpr() == null) {
        		orderedQuery += " order by " + joinTree.getAlias() + "." + joinTree.getForm().getPrimaryKey();
        	}
        	
        	result = addLimitClause(orderedQuery, start, numRows);
        }
        
        return result;
    }
    
    private String buildSelectClause(SelectListNode selectList, JoinTree joinTree) {
    	StringBuilder select = new StringBuilder();
    	if (selectList.isDistinct()) {
    		select.append("distinct ");
    	}

    	int colCnt = 0;
    	for (ExpressionNode element : selectList.getElements()) {
    		String colAlias = "c" + colCnt;
    		element.setColumnAlias(colAlias);
    		
    		select.append(getExpressionNodeSql(element, element.getType()))
    			.append(" as ").append(colAlias).append(", ");
    		colCnt++;    		    		
    	}
    	
    	if (select.length() == 0) {
    		select.append("*"); 
    	} else {
    		if (wideRowSupport) {
    			addWideRowMarkerCols(select, selectList, joinTree);    			
    		}
    		
    		select.delete(select.length() - 2, select.length());
    	}    	
    	
    	return select.toString();
    }
    
    private void addWideRowMarkerCols(StringBuilder select, SelectListNode selectList, JoinTree joinTree) {
    	Set<String> wideRowMarkerColumns = new LinkedHashSet<String>();
    	
    	String alias = joinTree.getAlias();
    	String pk = joinTree.getForm().getPrimaryKey();    	
    	wideRowMarkerColumns.add(getWideRowMarkerColumn(alias, pk));
    	
    	for (ExpressionNode element : selectList.getElements()) {
    		if (element instanceof FieldNode) {
    			FieldNode field = (FieldNode)element;
    			Control ctrl = field.getCtrl();
    			if (ctrl instanceof MultiSelectControl || ctrl instanceof LookupControl) {
    				continue;
    			}
    			
    			alias = field.getTabAlias();
    			pk = ctrl.getContainer().getPrimaryKey();
    			wideRowMarkerColumns.add(getWideRowMarkerColumn(alias, pk));
    		} else {
    			String[] aliasPk = WideRowUtil.getTabAliasPk(joinTree, element);
    			if (aliasPk != null) {
    				wideRowMarkerColumns.add(getWideRowMarkerColumn(aliasPk[0], aliasPk[1]));
    			}
    		}
    	}
    	
    	int colCnt = 0;
    	for (String column : wideRowMarkerColumns) {
    		select.append(column).append(" as mc").append(colCnt).append(", ");
    		colCnt++;
    	}    	
    }
    
    private String getWideRowMarkerColumn(String alias, String primaryKey) {
    	return "'" + alias + "', " + alias + "." + primaryKey;
    }
    
//    private String buildCountClause(JoinTree joinTree) {
//    	StringBuilder countClause = new StringBuilder("count(");
//    	return countClause.append(wideRowSupport ? "distinct " : "")
//    		.append(joinTree.getForm().getPrimaryKey())
//    		.append(")").toString();
//    }
    
    private String buildFromClause(JoinTree joinTree) {
        StringBuilder from = new StringBuilder();
        JoinTree parentTree = joinTree.getParent();
        
        if (parentTree != null && !joinTree.isExtensionForm()) {
        	from.append(" left join ");
        } else if (parentTree != null && joinTree.isExtensionForm()) {
        	//
        	// The parent node is an extension form link (i.e. record entries table)
        	// We'll inner join the extension form with record entries to avoid
        	// selecting other form data entries
        	//
        	from.append(" inner join ");
        }
        
        if (joinTree.getExtnFk() != null) {
        	//
        	// The current node (joinTree) is an extension form link (i.e. record entries table)
        	// We'll group joins related to extension form within parenthesis 
        	//
        	from.append("(");
        }
        
        from.append(joinTree.getTab()).append(" ").append(joinTree.getAlias()).append(" ");
        
        if (!joinTree.isExtensionForm() && parentTree != null && joinTree.getExtnFk() == null) { 
        	//
        	// Normal form
        	//
            from.append(" on ").append(joinTree.getAlias()).append(".").append(joinTree.getForeignKey())
                .append(" = ").append(parentTree.getAlias()).append(".").append(joinTree.getParentKey());

			addActiveJoinCond(joinTree, from);
        } else if (joinTree.isExtensionForm() && parentTree != null) {
        	//
        	// Extension form
        	//
        	from.append(" on ").append(joinTree.getAlias()).append(".").append(joinTree.getForm().getPrimaryKey())
        		.append(" = ").append(parentTree.getAlias()).append(".").append(parentTree.getExtnFk())
        		.append(" and ").append(parentTree.getAlias()).append(".").append(parentTree.getFormIdCol())
        		.append(" = ").append(joinTree.getFormId());
        }

        for (JoinTree child : joinTree.getChildren()) {
        	from.append(buildFromClause(child));
        }
        
        if (joinTree.getExtnFk() != null && parentTree != null) {
        	//
        	// Record entries form. Join with its parent form
        	//
        	from.append(") on ").append(joinTree.getAlias()).append(".").append(joinTree.getForeignKey())
        		.append(" = ").append(parentTree.getAlias()).append(".").append(joinTree.getParentKey());

			addActiveJoinCond(joinTree, from);
        }

        return from.toString();
    }

    private void addActiveJoinCond(JoinTree tree, StringBuilder clause) {
		addActiveJoinCond(tree, clause, true);
	}

	private void addActiveJoinCond(JoinTree tree, StringBuilder clause, boolean addAnd) {
		Container form = tree.getForm();
		if (form != null && StringUtils.isNotBlank(form.getActiveCond())) {
			clause.append(addAnd ? " and " : "").append(tree.getAlias()).append(".").append(form.getActiveCond());
		}
	}

    private String buildWhereClause(Node root) {
        String exprStr = null;
        
        if (root instanceof FilterNode) {
            return buildFilter((FilterNode)root);
        } 
        
        FilterExpressionNode expr = (FilterExpressionNode)root;
        String lhs = buildWhereClause(expr.getOperands().get(0));
        String rhs = null;
            
        switch (expr.getOperator()) {
          	case AND:
           	case OR:
           		rhs = buildWhereClause(expr.getOperands().get(1));
           		exprStr = new StringBuilder(lhs).append(" ")
           				.append(expr.getOperator()).append(" ")
           				.append(rhs).toString();
                break;
                    
            case NOT:
            	exprStr = new StringBuilder("NOT(").append(lhs).append(")").toString();
            	break;
            		
            case PARENTHESIS:
            case NTHCHILD:
            	exprStr = new StringBuilder("(").append(lhs).append(")").toString();
            	break;
            		
            case IDENTITY:
            	exprStr = lhs;
            	break;
            	
            case PAND:
           		rhs = buildWhereClause(expr.getOperands().get(1));
           		exprStr = new StringBuilder(lhs).append(" ")
           				.append(" AND ")
           				.append(rhs).toString();            	
            	break;
        }

        return exprStr;
    }
    
    private String buildActiveCond(JoinTree joinTree) {
    	StringBuilder clause = new StringBuilder();
		addActiveJoinCond(joinTree, clause, false);
		return clause.toString();
    }

    private String buildLinkCond(JoinTree joinTree) {
		StringBuilder clause = new StringBuilder();

		for (Map.Entry<LinkControl, JoinTree> lte : joinTree.getLinkedTrees().entrySet()) {
			LinkControl linkCtrl = lte.getKey();
			JoinTree linkedTree = lte.getValue();

			if (clause.length() != 0) {
				clause.append(" AND ");
			}

			clause.append(joinTree.getAlias()).append(".").append(linkCtrl.getDbColumnName()).append(" = ")
				.append(linkedTree.getAlias()).append(".").append(linkedTree.getForm().getPrimaryKey());
		}

		for (JoinTree childTree : joinTree.getChildren()) {
			String childTreeClause = buildLinkCond(childTree);
			if (childTreeClause.isEmpty()) {
				continue;
			}

			if (clause.length() != 0) {
				clause.append(" AND ");
			}

			clause.append(childTreeClause);
		}

		return clause.toString();
	}

	private String buildOrderBy(OrderExprListNode orderList) {
		StringBuilder orderBy = new StringBuilder();
		if (orderList == null) {
			return orderBy.toString();
		}

		for (OrderExprNode expr : orderList.getExprs()) {
			orderBy.append(getExpressionNodeSql(expr.getExpr(), expr.getExpr().getType()))
				.append(expr.isDescending() ? " desc " : " asc ")
				.append(", ");
		}

		if (orderBy.length() != 0) {
			orderBy.delete(orderBy.length() - 2, orderBy.length());
		}

		return orderBy.toString();
	}

    private String buildGroupBy(SelectListNode selectList) {
    	StringBuilder groupBy = new StringBuilder();
    	if (!selectList.hasAggregateExpr()) {
    		return groupBy.toString();
    	}
    	
    	for (ExpressionNode node : selectList.getElements()) {
    		if (node.isAggregateExpression()) {
    			continue;
    		}
    		
    		groupBy.append(getExpressionNodeSql(node, node.getType())).append(", ");
    	}
    	
    	if (groupBy.length() != 0) {
    		groupBy.delete(groupBy.length() - 2, groupBy.length());
    	}
    	
    	return groupBy.toString(); 
    }
    
    private String buildFilter(FilterNode filter) {
    	if (!isValidFilter(filter)) {
    		throw new RuntimeException("Invalid filter"); // add more info here
    	}
    	
    	if (isDateCmpFilter(filter)) {
    		return getDateCmpSql(filter);
    	}
    	
        String filterExpr = null, lhs = null, rhs = null;
        if (filter.getRelOp() != RelationalOp.ANY) {
        	lhs = getExpressionNodeSql(filter.getLhs(), filter.getLhs().getType());
        }

        switch (filter.getRelOp()) {
            case STARTS_WITH:
            case ENDS_WITH:
            case CONTAINS:
            	rhs = getStringMatchSql((LiteralValueNode)filter.getRhs(), filter.getRelOp());
            	if (ic) {
            		filterExpr = "upper(" + lhs + ") like " + rhs.toUpperCase();
            	} else {
            		filterExpr = lhs + " like " + rhs;
            	}            	
            	break;
            	
            case ANY:
            	filterExpr = " 1 = 1 "; // always true
            	break;

            case EXISTS:
            	filterExpr = lhs + " is not null ";
            	break;
            	
            case NOT_EXISTS:
            	filterExpr = lhs + " is null ";
            	break;
            	
            case BETWEEN:
            	filterExpr = lhs;
            	break;
            	            	
            default:
            	rhs = getExpressionNodeSql(filter.getRhs(), filter.getLhs().getType());            	
            	if (filter.getLhs().isString() && ic) {
            		lhs = "upper(" + lhs + ")";
            		if (filter.getRhs() instanceof FieldNode) {
            			rhs = "upper(" + rhs + ")";
            		} else {
            			rhs = rhs.toUpperCase();
            		}
            	}            	
            	filterExpr = lhs + " " + filter.getRelOp().symbol() + " " + rhs;
            	break;            	
        }
        
        return filterExpr;
    }
    
    private boolean isValidFilter(FilterNode filter) {
    	ExpressionNode lhs = filter.getLhs(), rhs = filter.getRhs();
    	boolean isValid = false;
    	if (rhs == null) { // unary operators like exists and not exists
    		isValid = true;
    	} else if (lhs.isString() && rhs.isString()) {
    		isValid = true;
    	} else if (lhs.isDate() && (rhs.isString() || rhs.isDateInterval() || rhs.isDate() || rhs.isNumber())) {
    		isValid = true;
    	} else if (lhs.isString() && (rhs.isDate() || rhs.isDateInterval() || rhs.isNumber())) {
    		isValid = true;
        } else if (lhs.isNumber() && rhs.isNumber()) {
    		isValid = true;
    	} else if (lhs.isDateInterval() && rhs.isNumber()) {
    		isValid = true;
    	} 
    	
    	return isValid;
    }
    
    private boolean isDateCmpFilter(FilterNode filter) {
    	DataType lhsType = filter.getLhs().getType();
    	if (lhsType != DataType.DATE) {
    		return false;
    	}
    	    	
    	if (filter.getRelOp() == RelationalOp.BETWEEN || !(filter.getRhs() instanceof LiteralValueNode)) {
    		return false;
    	}
    	
    	return true;
    }

//    private boolean isValidOp(ExpressionNode lhs, RelationalOp op, ExpressionNode rhs) {
//    	// check validity of {lhs.getType(), op, rhs.getType()} or {lhs.getType(), op}    	
//    	return true;    	
//    }

    private String removeQuotes(String value) {
        if (value != null && value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"') {
            value = value.substring(1, value.length() - 1);
        }
        
        return value;
    }
    
    private String getDateCmpSql(FilterNode filter) {    	    	
    	String lhsSql = getExpressionNodeSql(filter.getLhs(), filter.getLhs().getType());
    	
    	LiteralValueNode rhsLiteral = (LiteralValueNode)filter.getRhs();
    	String dateLiteral = removeQuotes(rhsLiteral.getValues().get(0).toString());    	
    	Date date = getDateInAppFormat(dateLiteral);
    	
    	String d0Sql = getDateLiteralSql(date);    	
    	if (!isTimePartZero(date)) {
    		return lhsSql + " " + filter.getRelOp().symbol() + " " + d0Sql;
    	} 

    	String d1Sql = getDateLiteralSql(getEndOfDayTime(date));    			    			
    	switch (filter.getRelOp()) {
    		case EQ:
    			return "(" + ge(lhsSql, d0Sql) + " and " + le(lhsSql, d1Sql) + ")";
    			
    		case GT:
    			return gt(lhsSql, d1Sql);
    			
    		case GE:
    			return ge(lhsSql, d0Sql);
    			
    		case LT:
    			return lt(lhsSql, d0Sql);
    			
    		case LE:
    			return le(lhsSql, d1Sql);
    			
    		case NE:
    			return "(" + lt(lhsSql, d0Sql) + " or " + gt(lhsSql, d1Sql) + ")";
    			
    		default:
    			throw new IllegalArgumentException("Unexcepted operator for date: " + filter.getRelOp().symbol());    			    		
    	}
    }
        
    private String getExpressionNodeSql(ExpressionNode exprNode, DataType type) {
    	String result = "";
    	
    	if (exprNode instanceof FieldNode) {
    		result = getFieldNodeSql((FieldNode)exprNode);
    	} else if (exprNode instanceof LiteralValueNode) {
    		result = getLiteralValueNodeSql((LiteralValueNode)exprNode, type);
    	} else if (exprNode instanceof LiteralValueListNode) {
    		LiteralValueListNode literalNodes = (LiteralValueListNode)exprNode;
    		StringBuilder literals = new StringBuilder();
    		for (LiteralValueNode literalNode : literalNodes.getLiteralVals()) {
    			if (literals.length() != 0) {
    				literals.append(", ");
    			}
    			literals.append(getLiteralValueNodeSql(literalNode, type));
    		}
    		
    		result = "(" + literals.toString() + ")";    		    		
    	} else if (exprNode instanceof ArithExpressionNode) {
			result = getArithExpressionNodeSql((ArithExpressionNode) exprNode);
		} else if (exprNode instanceof DateFormatFuncNode) {
			result = getDateFormatFuncNodeSql((DateFormatFuncNode) exprNode);
    	} else if (exprNode instanceof DateDiffFuncNode) {
			result = getDateDiffFuncNodeSql((DateDiffFuncNode) exprNode);
		} else if (exprNode instanceof DateRangeFuncNode) {
			result = getDateRangeFuncNodeSql((DateRangeFuncNode) exprNode);
    	} else if (exprNode instanceof CurrentDateNode) {
    		result = getCurrentDateSql();
    	} else if (exprNode instanceof AggregateNode) {
			result = getAggregateSql((AggregateNode) exprNode);
		} else if (exprNode instanceof ConcatNode) {
			result = getConcatSql((ConcatNode)exprNode);
    	} else if (exprNode instanceof BetweenNode) {
			BetweenNode betweenNode = (BetweenNode)exprNode;
			result = getBetweenNodeSql(betweenNode);
		} else if (exprNode instanceof RoundOffNode) {
			RoundOffNode roundOffNode = (RoundOffNode)exprNode;
			result = new StringBuilder()
					.append(" round(")
					.append(getExpressionNodeSql(roundOffNode.getExprNode(), roundOffNode.getType()))
					.append(", ")
					.append(roundOffNode.getNoOfDigitsAfterDecimal())
					.append(")")
					.toString();
		}
    	
    	return result;
    }
        
    private String getFieldNodeSql(FieldNode field) {    	
    	String column = field.getCtrl().getDbColumnName();
    	if (field.getCtrl() instanceof FileUploadControl) {
    		column += "_NAME";
    	} else if (field.getCtrl() instanceof LookupControl) {
    		LookupControl luCtrl = (LookupControl)field.getCtrl();
    		column = luCtrl.getValueColumn();
    	}
    	
    	return field.getTabAlias() + "." + column;
    }
    
    private String getLiteralValueNodeSql(LiteralValueNode value, DataType coercionType) {
    	String result = "";
    	
        switch (value.getType()) {
    		case STRING:
    			result = removeQuotes(value.getValues().get(0).toString());
    			if (coercionType == DataType.DATE) {
    				result = getDateLiteralSql(result);
    			} else {
    				result = "'" + StringEscapeUtils.escapeSql(result) + "'";
    			}
    			break;
    			
    		case BOOLEAN:
    			result = ((Boolean)value.getValues().get(0)) ? "1" : "0";
    			break;
    		
    		default:
    			result = value.getValues().get(0).toString();
    			break;
        }
    	
        return result;
    }
    
    private String getDateLiteralSql(String dateLiteral) {
    	return getDateLiteralSql0("'" + getDateInDbFormat(dateLiteral) + "'");
    }
    
    private String getDateLiteralSql(Date date) {
    	return getDateLiteralSql0("'" +  getDateInDbFormat(date) + "'");    	
    }
    	
    private String getDateLiteralSql0(String dateLiteralInDbFmt) {
    	String result = "";    
    	if (DbSettingsFactory.isOracle()) {
    		result = "to_date(" + dateLiteralInDbFmt + ", 'MM-DD-YYYY HH24:MI')";    		
    	} else if (DbSettingsFactory.isMySQL()) {
    		result = "str_to_date(" + dateLiteralInDbFmt + ", '%m-%d-%Y %H:%i')";    		
    	}
    	
    	return result;
    }
    
    private String getStringMatchSql(LiteralValueNode stringNode, RelationalOp op) {
    	String prefix = "", suffix = "";
    	
    	switch (op) {
    	    case CONTAINS:
    	    	prefix = suffix = "%";
    	    	break;
    	    	
    	    case STARTS_WITH:
    	    	suffix = "%";
    	    	break;
    	    	
    	    case ENDS_WITH:
    	    	prefix = "%";
    	    	break;
    	    	
    	    default:
    	    	break;
    	}
    	
    	String value = removeQuotes(stringNode.getValues().get(0).toString());
    	return "'" + prefix + value + suffix + "'";
    }
        
    private String getArithExpressionNodeSql(ArithExpressionNode arithExpr) {    	
    	String expr = "";
    	
    	String loperand = getExpressionNodeSql(arithExpr.getLeftOperand(),  arithExpr.getLeftOperandCoercion());
		if (arithExpr.getLeftOperand().isDate() && arithExpr.getRightOperand().isDateInterval()) {
			DateIntervalNode di = (DateIntervalNode)arithExpr.getRightOperand();
			int months = di.getYears() * 12 + di.getMonths();
			if (months == 0) {
				expr = loperand; 
			} else {
				if (arithExpr.getOp() == ArithOp.MINUS) {
					months = -months;    				    	
				}
				
				if (DbSettingsFactory.isMySQL()) {
					expr = "adddate(" + loperand + ", interval " + months + " month)";
				} else if (DbSettingsFactory.isOracle()){
					expr = "add_months(" + loperand + ", " + months + ")";
				}
				
			}
			
			if (di.getDays() != 0) {
				expr += " " + arithExpr.getOp().symbol() + " " + di.getDays(); 
			}
		} else {
			String roperand = getExpressionNodeSql(arithExpr.getRightOperand(), arithExpr.getRightOperandCoercion());
			expr = loperand + " " + arithExpr.getOp().symbol() + " " + roperand;
		}
		
		
		return "(" + expr + ")";    	
    }

    private String getDateFormatFuncNodeSql(DateFormatFuncNode dateFmt) {
		String dateExprSql = getExpressionNodeSql(dateFmt.getDateExpr(), DataType.DATE);
		if (DbSettingsFactory.isMySQL()) {
			return getMySqlDateFormatSql(dateExprSql, dateFmt.getFormat());
		} else if (DbSettingsFactory.isOracle()) {
			return getOracleDateFormatSql(dateExprSql, dateFmt.getFormat());
		} else {
			return dateExprSql;
		}
	}

	private String getMySqlDateFormatSql(String inputExpr, String format) {
		return "date_format(" + inputExpr + ", " + getDbDateFormatString(format, MYSQL_DATE_FMTS) + ")";
	}

	private String getOracleDateFormatSql(String inputExpr, String format) {
		return "to_char(" + inputExpr + ", " + getDbDateFormatString(format, ORACLE_DATE_FMTS) + ")";
	}

	private String getDbDateFormatString(String inputFormat, Map<String, String> dbFormats) {
		Matcher matcher = DATE_FMT_PATTERN.matcher(inputFormat);
		StringBuilder result = new StringBuilder("'");
		int lastIdx = 0;

		while (matcher.find()) {
			String dateFmt = dbFormats.get(matcher.group(1));
			if (dateFmt == null) {
				dateFmt = matcher.group(0);
			}

			result.append(inputFormat.substring(lastIdx, matcher.start())).append(dateFmt);
			lastIdx = matcher.end();
		}

		return result.append(inputFormat.substring(lastIdx)).append("'").toString();
	}

    private String getDateDiffFuncNodeSql(DateDiffFuncNode dateDiff) {    	
    	String loperand = getExpressionNodeSql(dateDiff.getLeftOperand(), DataType.DATE);
		String roperand = getExpressionNodeSql(dateDiff.getRightOperand(), DataType.DATE);
		return getDateDiffSql(dateDiff.getDiffType(), loperand, roperand);
    }

	private String getDateRangeFuncNodeSql(DateRangeFuncNode range) {
		int monthsRange = 0, daysRange = 0, dayOffset = -1;

		Calendar cal = Calendar.getInstance();
		switch (range.getRangeType()) {
			case LAST_CAL_QTR:
				cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) / 3) * 3);
				// fall through to next case

			case LAST_QTR:
				cal.set(Calendar.DAY_OF_MONTH, 1);
				monthsRange = -3 * range.getRange();
				break;

			case LAST_CAL_MONTH:
				cal.set(Calendar.DAY_OF_MONTH, 1);
				// fall through to next case

			case LAST_MONTH:
				monthsRange = -1 * range.getRange();
				break;

			case LAST_WEEK:
				cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				daysRange = -7 * range.getRange();
				break;

			case CURRENT_WEEK:
				int dow = cal.get(Calendar.DAY_OF_WEEK);
				cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				dayOffset = (dow - Calendar.SUNDAY);
				break;

			case LAST_DAYS:
				daysRange = -1 * range.getRange();
				break;

			case TODAY:
				dayOffset = 0;
				break;

			case YESTERDAY:
				daysRange = -1;
				break;
		}

		cal.add(Calendar.MONTH, monthsRange);
		cal.add(Calendar.DATE, daysRange);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date from = cal.getTime();

		cal.add(Calendar.MONTH, -monthsRange);
		cal.add(Calendar.DATE, -daysRange);
		cal.add(Calendar.DATE, dayOffset);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		Date to = cal.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);

		LiteralValueNode minNode = new LiteralValueNode(DataType.STRING);
		minNode.setValues(Collections.singletonList("\"" + sdf.format(from) + "\""));

		LiteralValueNode maxNode = new LiteralValueNode(DataType.STRING);
		maxNode.setValues(Collections.singletonList("\"" + sdf.format(to) + "\""));

		BetweenNode node = new BetweenNode();
		node.setLhs(range.getDateExpr());
		node.setMinNode(minNode);
		node.setMaxNode(maxNode);

		return getBetweenNodeSql(node);
	}

    private String getCurrentDateSql() {
    	if (DbSettingsFactory.getProduct().equals("Oracle")) {
    		return "sysdate";
    	} else if (DbSettingsFactory.getProduct().equals("MySQL")) {
    		return "current_date()";
    	}
    	
    	throw new RuntimeException("Unknown product type: " + DbSettingsFactory.getProduct());
    }
    
    private String getAggregateSql(AggregateNode aggNode) {
		StringBuilder aggSql = new StringBuilder();
		switch (aggNode.getAggFn()) {
			case COUNT:
			case CCOUNT:
				aggSql.append("count(");
				break;

			case SUM:
			case CSUM:
				aggSql.append("sum(");
				break;

			case MIN:
				aggSql.append("min(");
				break;

			case MAX:
				aggSql.append("max(");
				break;

			case AVG:
				aggSql.append("avg(");
				break;
		}

		if (aggNode.isDistinct()) {
			aggSql.append("distinct ");
		}

		aggSql.append(getFieldNodeSql(aggNode.getField())).append(")");
		return aggSql.toString();
    }

	private String getConcatSql(ConcatNode concatNode) {
		String concatFn = "", exprSeparator = ", ", paren = ")";

		StringBuilder sql = new StringBuilder();
		StringBuilder closingParenthesis = new StringBuilder();
		List<ExpressionNode> args = concatNode.getArgs();

		if (concatNode.getSeparator() != null) {
			String separator = "'" + StringEscapeUtils.escapeSql(removeQuotes(concatNode.getSeparator())) + "'";
			if (DbSettingsFactory.isOracle()) {
				concatFn = "concat(de_concat_if_not_null(";
				exprSeparator = ", " + separator + "), ";

				if (args.size() == 1) {
					sql.append("concat(");
					closingParenthesis.append(",").append(separator).append(")");
				}
			} else {
				sql.append("concat_ws(").append(separator).append(", ");
				closingParenthesis.append(")");
				paren = "";
			}
		} else {
			concatFn = DbSettingsFactory.isOracle() ? "de_concat_if_not_null(": "concat(";
		}

		for (int i = 0; i < args.size() - 1; ++i) {
			sql.append(concatFn).append(getExpressionNodeSql(args.get(i), DataType.STRING)).append(exprSeparator);
			closingParenthesis.append(paren);
		}

		return sql.append(getExpressionNodeSql(args.get(args.size() - 1), DataType.STRING))
			.append(closingParenthesis)
			.toString();
	}
    
    private String addLimitClause(String sql, LimitExprNode limitExpr) {
    	return addLimitClause(sql, limitExpr.getStartAt(), limitExpr.getNumRecords());
    }
    
    private String addLimitClause(String sql, int start, int numRows) {
    	if (DbSettingsFactory.getProduct().equals("Oracle")) {
    		return String.format(LIMIT_QUERY, sql, start + numRows, start + 1);
    	} else {
    		return sql + " limit " + start + ", " + numRows;
    	}
    }
    
    private String getDateDiffSql(DiffType diffType, String loperand, String roperand) {
    	if (DbSettingsFactory.getProduct().equals("Oracle")) {
    		return getOracleDateDiffSql(diffType, loperand, roperand);
    	} else if (DbSettingsFactory.getProduct().equals("MySQL")) {
    		return getMySQLDateDiffSql(diffType, loperand, roperand);
    	}
    	
    	throw new RuntimeException("Unknown product type: " + DbSettingsFactory.getProduct());
    }
    
    private String getOracleDateDiffSql(DiffType diffType, String loperand, String roperand) {
    	switch (diffType) {
    		case YEAR:
    			return "trunc(months_between(" + loperand + ", " + roperand + ") / 12)";
			
    		case MONTH:
    			return "months_between(" + loperand + ", " + roperand + ")";
			
    		case DAY:
    			return "(" + loperand + " - " + roperand + ")";

                case MINUTES:
                        return "(round(1440 * (" + loperand + " - " + roperand + "), 2))";
    	}
    	
    	return "";
    }
    
    private String getMySQLDateDiffSql(DiffType diffType, String loperand, String roperand) {
    	switch (diffType) {
    		case YEAR:
    			return "timestampdiff(YEAR, " + roperand + ", " + loperand + ")";
    			
    		case MONTH:
    			return "timestampdiff(MONTH, " + roperand + ", " + loperand + ")";
    			
    		case DAY:
    			return "(" + loperand + " - " + roperand + ")";

                case MINUTES:
                        return "timestampdiff(MINUTE, " + roperand + ", " + loperand + ")";
    	}
    	
    	return "";    	
    }
    
    private String getBetweenNodeSql(BetweenNode node) {    	    	
    	if (node.getType() != DataType.DATE) {
    		String lhsSql = getExpressionNodeSql(node.getLhs(), node.getType());
    		String minSql = getExpressionNodeSql(node.getMinNode(), node.getType());
    		String maxSql = getExpressionNodeSql(node.getMaxNode(), node.getType());
    		return lhsSql + " between " + minSql + " and " + maxSql;
    	}
    	
    	FilterNode lowerBound = new FilterNode();
    	lowerBound.setLhs(node.getLhs());
    	lowerBound.setRelOp(RelationalOp.GE);
    	lowerBound.setRhs(node.getMinNode());
    	
    	FilterNode upperBound = new FilterNode();
    	upperBound.setLhs(node.getLhs());
    	upperBound.setRelOp(RelationalOp.LE);
    	upperBound.setRhs(node.getMaxNode());
    	
    	return "(" + buildFilter(lowerBound) + " and " + buildFilter(upperBound) + ")";    			    	
    }

	private String getDateInDbFormat(String date) {
		Date appDate = getDateInAppFormat(date);
		return getDateInDbFormat(appDate);
	}
	
	private String getDateInDbFormat(Date date) {
		SimpleDateFormat dbSdf = new SimpleDateFormat(dbDateFormat);
		return dbSdf.format(date);		
	}
		
	private Date getDateInAppFormat(String date) {
		try {
			Date appDate = null;
			try {
				appDate = new SimpleDateFormat(dateTimeFormat).parse(date);
			} catch (ParseException pe) {
				appDate = new SimpleDateFormat(dateFormat).parse(date);
			}
			
			return appDate;
		} catch (ParseException pe) {
			throw new IllegalArgumentException("Invalid date: " + date, pe);
		}		
	}
			
	private String and(String clause1, String clause2) {
		if (clause2 == null || clause2.isEmpty()) {
			return clause1;
		}
		
		return new StringBuilder().append("(").append(clause1).append(")")
				.append(" AND ").append("(").append(clause2).append(")")
				.toString();		
	}	
	
	private boolean isTimePartZero(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		int hours = cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);
		int seconds = cal.get(Calendar.SECOND);
		
		return hours == 0 && minutes == 0 && seconds == 0;
	}
	
	private Date getEndOfDayTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();		
	}
	
	private String ge(String lhs, String rhs) {
		return lhs + " " + RelationalOp.GE.symbol() + " " + rhs;
	}
	
	private String le(String lhs, String rhs) {
		return lhs + " " + RelationalOp.LE.symbol() + " " + rhs;
	}

	private String lt(String lhs, String rhs) {
		return lhs + " " + RelationalOp.LT.symbol() + " " + rhs;
	}
	
	private String gt(String lhs, String rhs) {
		return lhs + " " + RelationalOp.GT.symbol() + " " + rhs;
	}	
}
