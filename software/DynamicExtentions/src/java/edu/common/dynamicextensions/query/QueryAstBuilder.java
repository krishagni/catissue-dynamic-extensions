package edu.common.dynamicextensions.query;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import edu.common.dynamicextensions.domain.nui.DataType;
import edu.common.dynamicextensions.query.antlr.AQLBaseVisitor;
import edu.common.dynamicextensions.query.antlr.AQLParser;
import edu.common.dynamicextensions.query.antlr.AQLParser.LiteralContext;
import edu.common.dynamicextensions.query.ast.AggregateNode;
import edu.common.dynamicextensions.query.ast.AggregateNode.AGG_FN;
import edu.common.dynamicextensions.query.ast.ArithExpressionNode;
import edu.common.dynamicextensions.query.ast.ArithExpressionNode.ArithOp;
import edu.common.dynamicextensions.query.ast.BetweenNode;
import edu.common.dynamicextensions.query.ast.ConcatNode;
import edu.common.dynamicextensions.query.ast.CrosstabNode;
import edu.common.dynamicextensions.query.ast.CurrentDateNode;
import edu.common.dynamicextensions.query.ast.DateDiffFuncNode;
import edu.common.dynamicextensions.query.ast.DateDiffFuncNode.DiffType;
import edu.common.dynamicextensions.query.ast.DateIntervalNode;
import edu.common.dynamicextensions.query.ast.DateRangeFuncNode;
import edu.common.dynamicextensions.query.ast.ExpressionNode;
import edu.common.dynamicextensions.query.ast.FieldNode;
import edu.common.dynamicextensions.query.ast.FilterExpressionNode;
import edu.common.dynamicextensions.query.ast.FilterNode;
import edu.common.dynamicextensions.query.ast.FilterNode.RelationalOp;
import edu.common.dynamicextensions.query.ast.FilterNodeMarker;
import edu.common.dynamicextensions.query.ast.LimitExprNode;
import edu.common.dynamicextensions.query.ast.LiteralValueListNode;
import edu.common.dynamicextensions.query.ast.LiteralValueNode;
import edu.common.dynamicextensions.query.ast.Node;
import edu.common.dynamicextensions.query.ast.OrderExprListNode;
import edu.common.dynamicextensions.query.ast.OrderExprNode;
import edu.common.dynamicextensions.query.ast.QueryExpressionNode;
import edu.common.dynamicextensions.query.ast.ResultPostProcNode;
import edu.common.dynamicextensions.query.ast.RoundOffNode;
import edu.common.dynamicextensions.query.ast.SelectListNode;

public class QueryAstBuilder extends AQLBaseVisitor<Node> {

    public QueryAstBuilder() {
    }
    
    @Override 
    public QueryExpressionNode visitQueryExpr(AQLParser.QueryExprContext ctx) {
    	QueryExpressionNode queryExpr = new QueryExpressionNode();

    	SelectListNode selectList = new SelectListNode();
    	if (ctx.select_list() != null) {
    		selectList = (SelectListNode)visit(ctx.select_list());
    	} 
    	queryExpr.setSelectList(selectList);    	
    	queryExpr.setFilterExpr((FilterExpressionNode)visit(ctx.filter_expr()));

		if (ctx.order_expr() != null) {
			queryExpr.setOrderExpr((OrderExprListNode)visit(ctx.order_expr()));
		}

    	if (ctx.limit_expr() != null) {
    		queryExpr.setLimitExpr((LimitExprNode)visit(ctx.limit_expr()));
    	}
    	
    	if (ctx.crosstab_expr() != null) {
    		CrosstabNode crosstabSpec = (CrosstabNode)visit(ctx.crosstab_expr());
    		queryExpr.setCrosstabSpec(crosstabSpec);
    	} else if (ctx.report_expr() != null) {
    		queryExpr.setResultPostProc((ResultPostProcNode)visit(ctx.report_expr()));
    	}
    	    	    	
    	return queryExpr;
    }    
    
    @Override
    public SelectListNode visitSelectExpr(AQLParser.SelectExprContext ctx) {
    	SelectListNode list = new SelectListNode();    	
    	list.setDistinct(ctx.DISTINCT() != null);
    	
    	for (int i = 0; i < ctx.select_element().size(); ++i) {
    		list.addElement((ExpressionNode)visit(ctx.select_element(i)));    		
    	}
    	
    	return list;  
    }

	@Override
	public OrderExprListNode visitOrderExpr(AQLParser.OrderExprContext ctx) {
		List<OrderExprNode> exprs = new ArrayList<>();
		for (int i = 0; i < ctx.order_element().size(); ++i) {
			exprs.add((OrderExprNode)visit(ctx.order_element(i)));
		}

		OrderExprListNode orderBy = new OrderExprListNode();
		orderBy.setExprs(exprs);
		return orderBy;
	}

	@Override
	public OrderExprNode visitOrderElement(AQLParser.OrderElementContext ctx) {
		OrderExprNode expr = new OrderExprNode();
		expr.setExpr((ExpressionNode)visit(ctx.arith_expr()));
		if (ctx.ORD_DIR() != null) {
			expr.setDescending(ctx.ORD_DIR().getText().equals("desc"));
		}

		return expr;
	}

    @Override
    public LimitExprNode visitLimitExpr(AQLParser.LimitExprContext ctx) {
    	LimitExprNode limitExpr = new LimitExprNode();
    	if (ctx.INT().size() == 2) {
    		limitExpr.setStartAt(Integer.parseInt(ctx.INT(0).getText()));
    		limitExpr.setNumRecords(Integer.parseInt(ctx.INT(1).getText()));
    	} else {
    		limitExpr.setNumRecords(Integer.parseInt(ctx.INT(0).getText()));
    	}
    	
    	return limitExpr;
    }
    
    @Override
    public ExpressionNode visitSelectElement(AQLParser.SelectElementContext ctx) {
    	ExpressionNode expr = (ExpressionNode)visit(ctx.arith_expr());
    	if (ctx.SLITERAL() != null) {
    		String text = ctx.SLITERAL().getText();
    		expr.setLabel(text.substring(1, text.length() - 1));
    	}
    	
    	return expr;
    }

    @Override
    public FilterExpressionNode visitAndFilterExpr(AQLParser.AndFilterExprContext ctx) {
        return FilterExpressionNode.andExpr(
        		(FilterNodeMarker)visit(ctx.filter_expr(0)), 
        		(FilterNodeMarker)visit(ctx.filter_expr(1)));
    }

    @Override
    public Node visitOrFilterExpr(AQLParser.OrFilterExprContext ctx) {
        return FilterExpressionNode.orExpr(
        		(FilterNodeMarker)visit(ctx.filter_expr(0)), 
        		(FilterNodeMarker)visit(ctx.filter_expr(1)));
    }
    
    @Override
    public Node visitPandFilterExpr(AQLParser.PandFilterExprContext ctx) {
    	return FilterExpressionNode.pAndExpr(
    			(FilterNodeMarker)visit(ctx.filter_expr(0)),
    			(FilterNodeMarker)visit(ctx.filter_expr(1)));
    }
    
    @Override
    public Node visitNotFilterExpr(AQLParser.NotFilterExprContext ctx) {
        return FilterExpressionNode.notExpr((FilterNodeMarker)visit(ctx.filter_expr()));
    }

    @Override
    public FilterExpressionNode visitParensFilterExpr(AQLParser.ParensFilterExprContext ctx) {
        return FilterExpressionNode.parenExpr((FilterNodeMarker)visit(ctx.filter_expr()));
    }
    
    @Override
    public FilterExpressionNode visitNthChildFilterExpr(AQLParser.NthChildFilterExprContext ctx) { 
        return FilterExpressionNode.nthChildExpr((FilterNodeMarker)visit(ctx.filter_expr()));
    }

    @Override
    public FilterExpressionNode visitSimpleFilter(AQLParser.SimpleFilterContext ctx) {
    	return FilterExpressionNode.identity((FilterNodeMarker)visit(ctx.filter()));
    }
    
    @Override
    public FilterNode visitBasicFilter(AQLParser.BasicFilterContext ctx) {
    	FilterNode filter = new FilterNode();
    	filter.setLhs((ExpressionNode)visit(ctx.arith_expr(0)));
    	filter.setRhs((ExpressionNode)visit(ctx.arith_expr(1)));
    	filter.setRelOp(RelationalOp.getBySymbol(ctx.OP().getText()));
    	return filter;    	
    }
    
    @Override
    public FilterNode visitMvFilter(AQLParser.MvFilterContext ctx) {
    	FilterNode filter = new FilterNode();
    	filter.setLhs((ExpressionNode)visit(ctx.arith_expr()));
    	filter.setRhs((ExpressionNode)visit(ctx.literal_values()));
    	filter.setRelOp(RelationalOp.getBySymbol(ctx.MOP().getText()));
    	return filter;    	
    }

	@Override
	public FilterNode visitConcatCompFilter(AQLParser.ConcatCompFilterContext ctx) {
		FilterNode filter = new FilterNode();
		filter.setLhs((ExpressionNode)visit(ctx.concat_expr()));

		LiteralValueNode value = new LiteralValueNode(DataType.STRING);
		value.getValues().add(ctx.SLITERAL().getText());
		filter.setRhs(value);

		filter.setRelOp(RelationalOp.getBySymbol(ctx.SOP().getText()));
		return filter;
	}

	@Override
	public FilterNode visitConcatWsCompFilter(AQLParser.ConcatWsCompFilterContext ctx) {
		FilterNode filter = new FilterNode();
		filter.setLhs((ExpressionNode)visit(ctx.concat_ws_expr()));

		LiteralValueNode value = new LiteralValueNode(DataType.STRING);
		value.getValues().add(ctx.SLITERAL().getText());
		filter.setRhs(value);

		filter.setRelOp(RelationalOp.getBySymbol(ctx.SOP().getText()));
		return filter;
	}

    @Override
    public FilterNode visitStringCompFilter(AQLParser.StringCompFilterContext ctx) {
    	FilterNode filter = new FilterNode();
    	
    	FieldNode field = new FieldNode();
    	field.setName(ctx.FIELD().getText());    	
    	filter.setLhs(field);
    	
    	LiteralValueNode value = new LiteralValueNode(DataType.STRING);
    	value.getValues().add(ctx.SLITERAL().getText());
    	filter.setRhs(value);
    	
    	filter.setRelOp(RelationalOp.getBySymbol(ctx.SOP().getText()));
    	return filter;
    }
    
    @Override
    public FilterNode visitUnaryFilter(AQLParser.UnaryFilterContext ctx) {
    	FilterNode filter = new FilterNode();
    	filter.setLhs((ExpressionNode)visit(ctx.arith_expr()));
    	filter.setRelOp(RelationalOp.getBySymbol(ctx.UOP().getText()));
    	return filter;
    }

	public FilterNode visitDateRangeFilter(AQLParser.DateRangeFilterContext ctx) {
		FilterNode filter = new FilterNode();
		filter.setLhs((ExpressionNode)visit(ctx.date_range()));
		filter.setRelOp(RelationalOp.BETWEEN);
		return filter;
	}

	@Override
	public FilterNode visitBetweenFilter(AQLParser.BetweenFilterContext ctx) {
		FilterNode filter = new FilterNode();

		BetweenNode betweenNode = new BetweenNode();
		betweenNode.setLhs((ExpressionNode)visit(ctx.arith_expr(0)));
		betweenNode.setMinNode((ExpressionNode)visit(ctx.arith_expr(1)));
		betweenNode.setMaxNode((ExpressionNode)visit(ctx.arith_expr(2)));

		filter.setRelOp(RelationalOp.BETWEEN);
		filter.setLhs(betweenNode);
		return filter;
	}
    
    public LiteralValueListNode visitLiteral_values(AQLParser.Literal_valuesContext ctx) {
    	LiteralValueListNode literals = new LiteralValueListNode();
    	for (LiteralContext literalCtx : ctx.literal()) {
    		literals.addLiteralVal((LiteralValueNode)visit(literalCtx));
    	}
    	
    	return literals;
    }

    @Override
    public ArithExpressionNode visitArithExpr(AQLParser.ArithExprContext ctx) {
    	ExpressionNode loperand = (ExpressionNode)visit(ctx.arith_expr(0));
    	ExpressionNode roperand = (ExpressionNode)visit(ctx.arith_expr(1));
    	ArithOp op = ArithOp.getBySymbol(ctx.ARITH_OP().getText());
    	
    	ArithExpressionNode expr = new ArithExpressionNode();
    	expr.setLeftOperand(loperand);
    	expr.setRightOperand(roperand);
    	expr.setOp(op);
    	return expr;
    }

    @Override 
    public ArithExpressionNode visitDateIntervalExpr(AQLParser.DateIntervalExprContext ctx) {
    	ExpressionNode loperand = (ExpressionNode)visit(ctx.arith_expr());
    	ExpressionNode roperand = (ExpressionNode)visit(ctx.date_interval());    	
    	ArithOp op = ArithOp.getBySymbol(ctx.ARITH_OP().getText());
    	    	
    	ArithExpressionNode expr = new ArithExpressionNode();
    	expr.setLeftOperand(loperand);
    	expr.setRightOperand(roperand);
    	expr.setOp(op);    	
    	return expr; 
    }

    @Override 
    public ExpressionNode visitParensArithExpr(AQLParser.ParensArithExprContext ctx) { 
    	return (ExpressionNode)visit(ctx.arith_expr()); 
    }
    
    @Override 
    public DateDiffFuncNode visitMonthsDiffFunc(AQLParser.MonthsDiffFuncContext ctx) { 
    	ExpressionNode leftOperand = (ExpressionNode)visit(ctx.arith_expr(0));
    	ExpressionNode rightOperand = (ExpressionNode)visit(ctx.arith_expr(1));
    	return getDateDiffFuncNode(DiffType.MONTH, leftOperand, rightOperand);
    }

    @Override 
    public DateDiffFuncNode visitYearsDiffFunc(AQLParser.YearsDiffFuncContext ctx) {
    	ExpressionNode leftOperand = (ExpressionNode)visit(ctx.arith_expr(0));
    	ExpressionNode rightOperand = (ExpressionNode)visit(ctx.arith_expr(1));
    	return getDateDiffFuncNode(DiffType.YEAR, leftOperand, rightOperand);
    }

	@Override
	public DateDiffFuncNode visitMinsDiffFunc(AQLParser.MinsDiffFuncContext ctx) {
		ExpressionNode leftOperand = (ExpressionNode)visit(ctx.arith_expr(0));
		ExpressionNode rightOperand = (ExpressionNode)visit(ctx.arith_expr(1));
		return getDateDiffFuncNode(DiffType.MINUTES, leftOperand, rightOperand);
	}
    
    @Override
    public CurrentDateNode visitCurrentDateFunc(AQLParser.CurrentDateFuncContext ctx) {
    	return new CurrentDateNode();
    }
    
    @Override
    public AggregateNode visitAggExpr(AQLParser.AggExprContext ctx) {
    	return (AggregateNode)visit(ctx.agg_expr());
    }

	@Override
	public DateRangeFuncNode visitDateRangeFunc(AQLParser.DateRangeFuncContext ctx) {
		DateRangeFuncNode func = new DateRangeFuncNode();
		func.setDateExpr((ExpressionNode)visit(ctx.arith_expr()));
		func.setRangeType(DateRangeFuncNode.RangeType.from(ctx.ID().getText()));

		if (ctx.INT() != null) {
			func.setRange(Integer.parseInt(ctx.INT().getText()));
		}

		return func;
	}

    @Override
    public AggregateNode visitAggFunc(AQLParser.AggFuncContext ctx) {
    	AggregateNode countNode = new AggregateNode();
    	if (ctx.COUNT() != null) {
			countNode.setAggFn(AGG_FN.COUNT);
		} else if (ctx.CCOUNT() != null) {
			countNode.setAggFn(AGG_FN.CCOUNT);
    	} else if (ctx.SUM() != null) {
			countNode.setAggFn(AGG_FN.SUM);
		} else if (ctx.CSUM() != null) {
			countNode.setAggFn(AGG_FN.CSUM);
    	} else if (ctx.MIN() != null) {
    		countNode.setAggFn(AGG_FN.MIN);
    	} else if (ctx.MAX() != null) {
    		countNode.setAggFn(AGG_FN.MAX);
    	} else if (ctx.AVG() != null) {
    		countNode.setAggFn(AGG_FN.AVG);
    	} else {
    		throw new IllegalArgumentException("Unknown aggregate function");
    	}
    	    	
    	if (ctx.DISTINCT() != null) {
    		countNode.setDistinct(true);
    	}
    	
    	FieldNode field = new FieldNode();
    	field.setName(ctx.FIELD().getText());
    	countNode.setField(field);    	
    	return countNode; 
    }

	@Override
	public ConcatNode visitConcatExpr(AQLParser.ConcatExprContext ctx) {
		return (ConcatNode)visit(ctx.concat_expr());
	}

	@Override
	public ConcatNode visitConcatFunc(AQLParser.ConcatFuncContext ctx) {
		ConcatNode concatNode = new ConcatNode();
		for (int i = 0; i < ctx.arith_expr().size(); ++i) {
			concatNode.addArg((ExpressionNode)visit(ctx.arith_expr(i)));
		}

		return concatNode;
	}

	@Override
	public ConcatNode visitConcatWsExpr(AQLParser.ConcatWsExprContext ctx) {
		return (ConcatNode)visit(ctx.concat_ws_expr());
	}

	@Override
	public ConcatNode visitConcatWsFunc(AQLParser.ConcatWsFuncContext ctx) {
		ConcatNode concatNode = new ConcatNode();
		concatNode.setSeparator(ctx.SLITERAL().getText());
		for (int i = 0; i < ctx.arith_expr().size(); ++i) {
			concatNode.addArg((ExpressionNode)visit(ctx.arith_expr(i)));
		}

		return concatNode;
	}
    
    public RoundOffNode visitRoundFunc(AQLParser.RoundFuncContext ctx) {
    	RoundOffNode node = new RoundOffNode();
    	node.setExprNode((ExpressionNode)visit(ctx.arith_expr()));
    	node.setNoOfDigitsAfterDecimal(Integer.parseInt(ctx.INT().getText()));
    	return node;
    }
    
    @Override 
    public FieldNode visitField(AQLParser.FieldContext ctx) {
    	FieldNode field = new FieldNode();
    	field.setName(ctx.FIELD().getText());
    	return field; 
    }    
        
    @Override
    public LiteralValueNode visitStringLiteral(AQLParser.StringLiteralContext ctx) {
    	LiteralValueNode value = new LiteralValueNode(DataType.STRING);
    	value.getValues().add(ctx.SLITERAL().getText());
    	return value;
    }
    
    @Override
    public LiteralValueNode visitIntLiteral(AQLParser.IntLiteralContext ctx) {
    	LiteralValueNode value = new LiteralValueNode(DataType.INTEGER);
    	value.getValues().add(Long.parseLong(ctx.INT().getText()));
    	return value;
    }

    @Override
    public LiteralValueNode visitFloatLiteral(AQLParser.FloatLiteralContext ctx) {
    	LiteralValueNode value = new LiteralValueNode(DataType.FLOAT);
    	value.getValues().add(Double.parseDouble(ctx.FLOAT().getText()));
    	return value;
    }
    
    @Override
    public LiteralValueNode visitBoolLiteral(AQLParser.BoolLiteralContext ctx) {
    	LiteralValueNode value = new LiteralValueNode(DataType.BOOLEAN);
    	value.getValues().add(Boolean.parseBoolean(ctx.BOOL().getText()));
    	return value;
    }
    
    @Override 
    public DateIntervalNode visitDate_interval(AQLParser.Date_intervalContext ctx) {
    	DateIntervalNode di = new DateIntervalNode();
    	
    	di.setDays(diPart(ctx.DAY()));
    	di.setMonths(diPart(ctx.MONTH()));
    	di.setYears(diPart(ctx.YEAR()));
    	return di;    	
    }
    
    @Override
    public CrosstabNode visitCrossTabExpr(AQLParser.CrossTabExprContext ctx) {
    	CrosstabNode crosstabSpec = new CrosstabNode();
    	crosstabSpec.setName(ctx.CROSSTAB().getText());
    	
    	List<Integer> rowCols = new ArrayList<Integer>();
    	for (Token rowIdx : ctx.row) {
    		rowCols.add(getInt(rowIdx));
    	}    	
    	crosstabSpec.setRowGroupByColumns(rowCols);
    	
    	crosstabSpec.setColGroupByColumn(getInt(ctx.col));

    	List<Integer> valCols = new ArrayList<Integer>();
    	for (Token valIdx : ctx.value) {
    		valCols.add(getInt(valIdx));
    	}
    	crosstabSpec.setMeasureColumns(valCols);
    	if (ctx.BOOL() != null) {
    		crosstabSpec.setIncludeSubTotals(Boolean.parseBoolean(ctx.BOOL().getText()));
    	}
    	
    	return crosstabSpec; 
    }
    
    @Override
    public ResultPostProcNode visitReportExpr(AQLParser.ReportExprContext ctx) {
    	List<String> args = new ArrayList<String>();
    	if (ctx.SLITERAL() != null) {
    		for (TerminalNode tn : ctx.SLITERAL()) {
    			String text = tn.getText();
    			args.add(text.substring(1, text.length() - 1).trim()); // remove quotes
    		}
    	}
    	
    	ResultPostProcNode postProc = new ResultPostProcNode();
    	postProc.setName(ctx.ID().getText());
    	postProc.setArgs(args);
    	return postProc;    	
    }
    
    private DateDiffFuncNode getDateDiffFuncNode(DiffType diffType, ExpressionNode leftOperand, ExpressionNode rightOperand) {
    	DateDiffFuncNode diff = new DateDiffFuncNode();
    	diff.setDiffType(diffType);    	
    	diff.setLeftOperand(leftOperand);
    	diff.setRightOperand(rightOperand);
    	return diff;    	
    }
    
    private int diPart(TerminalNode term) {
    	int result = 0;
    	
    	if (term != null && term.getText() != null && !term.getText().isEmpty()) {
    		String text = term.getText().substring(0, term.getText().length() - 1);
    		result = Integer.parseInt(text);
    	}
    	
    	return result;
    }
    
//    private int getInt(TerminalNode node) {
//    	return Integer.parseInt(node.getText());
//    }
    
    private int getInt(Token token) {
    	return Integer.parseInt(token.getText());
    }
}
