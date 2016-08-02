// Generated from AQL.g4 by ANTLR 4.5.1
package edu.common.dynamicextensions.query.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, WS=3, SELECT=4, WHERE=5, NTHCHILD=6, BETWEEN=7, MTHS_BTWN=8, 
		YRS_BTWN=9, CURR_DATE=10, MINS_BTWN=11, DATE_RANGE=12, COUNT=13, SUM=14, 
		MIN=15, MAX=16, AVG=17, DISTINCT=18, ORDER=19, ORD_DIR=20, LIMIT=21, CROSSTAB=22, 
		CONCAT=23, OR=24, AND=25, PAND=26, NOT=27, ROUND=28, LP=29, RP=30, MOP=31, 
		SOP=32, UOP=33, BOOL=34, OP=35, INT=36, FLOAT=37, YEAR=38, MONTH=39, DAY=40, 
		DIGIT=41, ID=42, FIELD=43, SLITERAL=44, ESC=45, ARITH_OP=46, ERROR=47, 
		QUOTE=48;
	public static final int
		RULE_query = 0, RULE_select_list = 1, RULE_select_element = 2, RULE_filter_expr = 3, 
		RULE_order_expr = 4, RULE_order_element = 5, RULE_limit_expr = 6, RULE_crosstab_expr = 7, 
		RULE_report_expr = 8, RULE_filter = 9, RULE_literal_values = 10, RULE_literal = 11, 
		RULE_arith_expr = 12, RULE_date_range = 13, RULE_agg_expr = 14, RULE_concat_expr = 15, 
		RULE_date_interval = 16;
	public static final String[] ruleNames = {
		"query", "select_list", "select_element", "filter_expr", "order_expr", 
		"order_element", "limit_expr", "crosstab_expr", "report_expr", "filter", 
		"literal_values", "literal", "arith_expr", "date_range", "agg_expr", "concat_expr", 
		"date_interval"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "'as'", null, "'select'", "'where'", "'nthchild'", "'between'", 
		"'months_between'", "'years_between'", "'current_date'", "'minutes_between'", 
		"'date_range'", "'count'", "'sum'", "'min'", "'max'", "'avg'", "'distinct'", 
		"'order by'", null, "'limit'", "'crosstab'", "'concat'", "'or'", "'and'", 
		"'pand'", "'not'", "'round'", "'('", "')'", null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"'\"'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "WS", "SELECT", "WHERE", "NTHCHILD", "BETWEEN", "MTHS_BTWN", 
		"YRS_BTWN", "CURR_DATE", "MINS_BTWN", "DATE_RANGE", "COUNT", "SUM", "MIN", 
		"MAX", "AVG", "DISTINCT", "ORDER", "ORD_DIR", "LIMIT", "CROSSTAB", "CONCAT", 
		"OR", "AND", "PAND", "NOT", "ROUND", "LP", "RP", "MOP", "SOP", "UOP", 
		"BOOL", "OP", "INT", "FLOAT", "YEAR", "MONTH", "DAY", "DIGIT", "ID", "FIELD", 
		"SLITERAL", "ESC", "ARITH_OP", "ERROR", "QUOTE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "AQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class QueryContext extends ParserRuleContext {
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
	 
		public QueryContext() { }
		public void copyFrom(QueryContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class QueryExprContext extends QueryContext {
		public Filter_exprContext filter_expr() {
			return getRuleContext(Filter_exprContext.class,0);
		}
		public TerminalNode SELECT() { return getToken(AQLParser.SELECT, 0); }
		public Select_listContext select_list() {
			return getRuleContext(Select_listContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(AQLParser.WHERE, 0); }
		public Order_exprContext order_expr() {
			return getRuleContext(Order_exprContext.class,0);
		}
		public Limit_exprContext limit_expr() {
			return getRuleContext(Limit_exprContext.class,0);
		}
		public Crosstab_exprContext crosstab_expr() {
			return getRuleContext(Crosstab_exprContext.class,0);
		}
		public Report_exprContext report_expr() {
			return getRuleContext(Report_exprContext.class,0);
		}
		public QueryExprContext(QueryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterQueryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitQueryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitQueryExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		int _la;
		try {
			_localctx = new QueryExprContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			_la = _input.LA(1);
			if (_la==SELECT) {
				{
				setState(34);
				match(SELECT);
				setState(35);
				select_list();
				setState(36);
				match(WHERE);
				}
			}

			setState(40);
			filter_expr(0);
			setState(42);
			_la = _input.LA(1);
			if (_la==ORDER) {
				{
				setState(41);
				order_expr();
				}
			}

			setState(45);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(44);
				limit_expr();
				}
			}

			setState(49);
			switch (_input.LA(1)) {
			case CROSSTAB:
				{
				setState(47);
				crosstab_expr();
				}
				break;
			case ID:
				{
				setState(48);
				report_expr();
				}
				break;
			case EOF:
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_listContext extends ParserRuleContext {
		public Select_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_list; }
	 
		public Select_listContext() { }
		public void copyFrom(Select_listContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SelectExprContext extends Select_listContext {
		public List<Select_elementContext> select_element() {
			return getRuleContexts(Select_elementContext.class);
		}
		public Select_elementContext select_element(int i) {
			return getRuleContext(Select_elementContext.class,i);
		}
		public TerminalNode DISTINCT() { return getToken(AQLParser.DISTINCT, 0); }
		public SelectExprContext(Select_listContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterSelectExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitSelectExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitSelectExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_listContext select_list() throws RecognitionException {
		Select_listContext _localctx = new Select_listContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_select_list);
		int _la;
		try {
			_localctx = new SelectExprContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			_la = _input.LA(1);
			if (_la==DISTINCT) {
				{
				setState(51);
				match(DISTINCT);
				}
			}

			setState(54);
			select_element();
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(55);
				match(T__0);
				setState(56);
				select_element();
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_elementContext extends ParserRuleContext {
		public Select_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_element; }
	 
		public Select_elementContext() { }
		public void copyFrom(Select_elementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SelectElementContext extends Select_elementContext {
		public Arith_exprContext arith_expr() {
			return getRuleContext(Arith_exprContext.class,0);
		}
		public TerminalNode SLITERAL() { return getToken(AQLParser.SLITERAL, 0); }
		public SelectElementContext(Select_elementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterSelectElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitSelectElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitSelectElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_elementContext select_element() throws RecognitionException {
		Select_elementContext _localctx = new Select_elementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_select_element);
		int _la;
		try {
			_localctx = new SelectElementContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			arith_expr(0);
			setState(65);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(63);
				match(T__1);
				setState(64);
				match(SLITERAL);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Filter_exprContext extends ParserRuleContext {
		public Filter_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter_expr; }
	 
		public Filter_exprContext() { }
		public void copyFrom(Filter_exprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OrFilterExprContext extends Filter_exprContext {
		public List<Filter_exprContext> filter_expr() {
			return getRuleContexts(Filter_exprContext.class);
		}
		public Filter_exprContext filter_expr(int i) {
			return getRuleContext(Filter_exprContext.class,i);
		}
		public TerminalNode OR() { return getToken(AQLParser.OR, 0); }
		public OrFilterExprContext(Filter_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterOrFilterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitOrFilterExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitOrFilterExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NthChildFilterExprContext extends Filter_exprContext {
		public TerminalNode NTHCHILD() { return getToken(AQLParser.NTHCHILD, 0); }
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public Filter_exprContext filter_expr() {
			return getRuleContext(Filter_exprContext.class,0);
		}
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public NthChildFilterExprContext(Filter_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterNthChildFilterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitNthChildFilterExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitNthChildFilterExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SimpleFilterContext extends Filter_exprContext {
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public SimpleFilterContext(Filter_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterSimpleFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitSimpleFilter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitSimpleFilter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndFilterExprContext extends Filter_exprContext {
		public List<Filter_exprContext> filter_expr() {
			return getRuleContexts(Filter_exprContext.class);
		}
		public Filter_exprContext filter_expr(int i) {
			return getRuleContext(Filter_exprContext.class,i);
		}
		public TerminalNode AND() { return getToken(AQLParser.AND, 0); }
		public AndFilterExprContext(Filter_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterAndFilterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitAndFilterExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitAndFilterExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PandFilterExprContext extends Filter_exprContext {
		public List<Filter_exprContext> filter_expr() {
			return getRuleContexts(Filter_exprContext.class);
		}
		public Filter_exprContext filter_expr(int i) {
			return getRuleContext(Filter_exprContext.class,i);
		}
		public TerminalNode PAND() { return getToken(AQLParser.PAND, 0); }
		public PandFilterExprContext(Filter_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterPandFilterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitPandFilterExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitPandFilterExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotFilterExprContext extends Filter_exprContext {
		public TerminalNode NOT() { return getToken(AQLParser.NOT, 0); }
		public Filter_exprContext filter_expr() {
			return getRuleContext(Filter_exprContext.class,0);
		}
		public NotFilterExprContext(Filter_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterNotFilterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitNotFilterExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitNotFilterExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParensFilterExprContext extends Filter_exprContext {
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public Filter_exprContext filter_expr() {
			return getRuleContext(Filter_exprContext.class,0);
		}
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public ParensFilterExprContext(Filter_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterParensFilterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitParensFilterExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitParensFilterExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Filter_exprContext filter_expr() throws RecognitionException {
		return filter_expr(0);
	}

	private Filter_exprContext filter_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Filter_exprContext _localctx = new Filter_exprContext(_ctx, _parentState);
		Filter_exprContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_filter_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				_localctx = new NotFilterExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(68);
				match(NOT);
				setState(69);
				filter_expr(2);
				}
				break;
			case 2:
				{
				_localctx = new ParensFilterExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(70);
				match(LP);
				setState(71);
				filter_expr(0);
				setState(72);
				match(RP);
				}
				break;
			case 3:
				{
				_localctx = new NthChildFilterExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(74);
				match(NTHCHILD);
				setState(75);
				match(LP);
				setState(76);
				filter_expr(0);
				setState(77);
				match(RP);
				}
				break;
			case 4:
				{
				_localctx = new SimpleFilterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(79);
				filter();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(93);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(91);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new AndFilterExprContext(new Filter_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_filter_expr);
						setState(82);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(83);
						match(AND);
						setState(84);
						filter_expr(8);
						}
						break;
					case 2:
						{
						_localctx = new OrFilterExprContext(new Filter_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_filter_expr);
						setState(85);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(86);
						match(OR);
						setState(87);
						filter_expr(7);
						}
						break;
					case 3:
						{
						_localctx = new PandFilterExprContext(new Filter_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_filter_expr);
						setState(88);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(89);
						match(PAND);
						setState(90);
						filter_expr(6);
						}
						break;
					}
					} 
				}
				setState(95);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Order_exprContext extends ParserRuleContext {
		public Order_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_order_expr; }
	 
		public Order_exprContext() { }
		public void copyFrom(Order_exprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OrderExprContext extends Order_exprContext {
		public TerminalNode ORDER() { return getToken(AQLParser.ORDER, 0); }
		public List<Order_elementContext> order_element() {
			return getRuleContexts(Order_elementContext.class);
		}
		public Order_elementContext order_element(int i) {
			return getRuleContext(Order_elementContext.class,i);
		}
		public OrderExprContext(Order_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterOrderExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitOrderExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitOrderExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Order_exprContext order_expr() throws RecognitionException {
		Order_exprContext _localctx = new Order_exprContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_order_expr);
		int _la;
		try {
			_localctx = new OrderExprContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(ORDER);
			setState(97);
			order_element();
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(98);
				match(T__0);
				setState(99);
				order_element();
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Order_elementContext extends ParserRuleContext {
		public Order_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_order_element; }
	 
		public Order_elementContext() { }
		public void copyFrom(Order_elementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OrderElementContext extends Order_elementContext {
		public Arith_exprContext arith_expr() {
			return getRuleContext(Arith_exprContext.class,0);
		}
		public TerminalNode ORD_DIR() { return getToken(AQLParser.ORD_DIR, 0); }
		public OrderElementContext(Order_elementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterOrderElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitOrderElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitOrderElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Order_elementContext order_element() throws RecognitionException {
		Order_elementContext _localctx = new Order_elementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_order_element);
		int _la;
		try {
			_localctx = new OrderElementContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			arith_expr(0);
			setState(107);
			_la = _input.LA(1);
			if (_la==ORD_DIR) {
				{
				setState(106);
				match(ORD_DIR);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Limit_exprContext extends ParserRuleContext {
		public Limit_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limit_expr; }
	 
		public Limit_exprContext() { }
		public void copyFrom(Limit_exprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LimitExprContext extends Limit_exprContext {
		public TerminalNode LIMIT() { return getToken(AQLParser.LIMIT, 0); }
		public List<TerminalNode> INT() { return getTokens(AQLParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(AQLParser.INT, i);
		}
		public LimitExprContext(Limit_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterLimitExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitLimitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitLimitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Limit_exprContext limit_expr() throws RecognitionException {
		Limit_exprContext _localctx = new Limit_exprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_limit_expr);
		int _la;
		try {
			_localctx = new LimitExprContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(LIMIT);
			setState(110);
			match(INT);
			setState(113);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(111);
				match(T__0);
				setState(112);
				match(INT);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Crosstab_exprContext extends ParserRuleContext {
		public Crosstab_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_crosstab_expr; }
	 
		public Crosstab_exprContext() { }
		public void copyFrom(Crosstab_exprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CrossTabExprContext extends Crosstab_exprContext {
		public Token INT;
		public List<Token> row = new ArrayList<Token>();
		public Token col;
		public List<Token> value = new ArrayList<Token>();
		public TerminalNode CROSSTAB() { return getToken(AQLParser.CROSSTAB, 0); }
		public List<TerminalNode> LP() { return getTokens(AQLParser.LP); }
		public TerminalNode LP(int i) {
			return getToken(AQLParser.LP, i);
		}
		public List<TerminalNode> RP() { return getTokens(AQLParser.RP); }
		public TerminalNode RP(int i) {
			return getToken(AQLParser.RP, i);
		}
		public List<TerminalNode> INT() { return getTokens(AQLParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(AQLParser.INT, i);
		}
		public TerminalNode BOOL() { return getToken(AQLParser.BOOL, 0); }
		public CrossTabExprContext(Crosstab_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterCrossTabExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitCrossTabExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitCrossTabExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Crosstab_exprContext crosstab_expr() throws RecognitionException {
		Crosstab_exprContext _localctx = new Crosstab_exprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_crosstab_expr);
		int _la;
		try {
			_localctx = new CrossTabExprContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(CROSSTAB);
			setState(116);
			match(LP);
			setState(117);
			match(LP);
			setState(118);
			((CrossTabExprContext)_localctx).INT = match(INT);
			((CrossTabExprContext)_localctx).row.add(((CrossTabExprContext)_localctx).INT);
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(119);
				match(T__0);
				setState(120);
				((CrossTabExprContext)_localctx).INT = match(INT);
				((CrossTabExprContext)_localctx).row.add(((CrossTabExprContext)_localctx).INT);
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(126);
			match(RP);
			setState(127);
			match(T__0);
			setState(128);
			((CrossTabExprContext)_localctx).col = match(INT);
			setState(129);
			match(T__0);
			setState(130);
			match(LP);
			setState(131);
			((CrossTabExprContext)_localctx).INT = match(INT);
			((CrossTabExprContext)_localctx).value.add(((CrossTabExprContext)_localctx).INT);
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(132);
				match(T__0);
				setState(133);
				((CrossTabExprContext)_localctx).INT = match(INT);
				((CrossTabExprContext)_localctx).value.add(((CrossTabExprContext)_localctx).INT);
				}
				}
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(139);
			match(RP);
			setState(142);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(140);
				match(T__0);
				setState(141);
				match(BOOL);
				}
			}

			setState(144);
			match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Report_exprContext extends ParserRuleContext {
		public Report_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_report_expr; }
	 
		public Report_exprContext() { }
		public void copyFrom(Report_exprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ReportExprContext extends Report_exprContext {
		public TerminalNode ID() { return getToken(AQLParser.ID, 0); }
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public List<TerminalNode> SLITERAL() { return getTokens(AQLParser.SLITERAL); }
		public TerminalNode SLITERAL(int i) {
			return getToken(AQLParser.SLITERAL, i);
		}
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public ReportExprContext(Report_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterReportExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitReportExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitReportExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Report_exprContext report_expr() throws RecognitionException {
		Report_exprContext _localctx = new Report_exprContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_report_expr);
		int _la;
		try {
			_localctx = new ReportExprContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(ID);
			setState(157);
			_la = _input.LA(1);
			if (_la==LP) {
				{
				setState(147);
				match(LP);
				setState(148);
				match(SLITERAL);
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(149);
					match(T__0);
					setState(150);
					match(SLITERAL);
					}
					}
					setState(155);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(156);
				match(RP);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FilterContext extends ParserRuleContext {
		public FilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter; }
	 
		public FilterContext() { }
		public void copyFrom(FilterContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BetweenFilterContext extends FilterContext {
		public List<Arith_exprContext> arith_expr() {
			return getRuleContexts(Arith_exprContext.class);
		}
		public Arith_exprContext arith_expr(int i) {
			return getRuleContext(Arith_exprContext.class,i);
		}
		public TerminalNode BETWEEN() { return getToken(AQLParser.BETWEEN, 0); }
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public BetweenFilterContext(FilterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterBetweenFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitBetweenFilter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitBetweenFilter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringCompFilterContext extends FilterContext {
		public TerminalNode FIELD() { return getToken(AQLParser.FIELD, 0); }
		public TerminalNode SOP() { return getToken(AQLParser.SOP, 0); }
		public TerminalNode SLITERAL() { return getToken(AQLParser.SLITERAL, 0); }
		public StringCompFilterContext(FilterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterStringCompFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitStringCompFilter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitStringCompFilter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MvFilterContext extends FilterContext {
		public Arith_exprContext arith_expr() {
			return getRuleContext(Arith_exprContext.class,0);
		}
		public TerminalNode MOP() { return getToken(AQLParser.MOP, 0); }
		public Literal_valuesContext literal_values() {
			return getRuleContext(Literal_valuesContext.class,0);
		}
		public MvFilterContext(FilterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterMvFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitMvFilter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitMvFilter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryFilterContext extends FilterContext {
		public Arith_exprContext arith_expr() {
			return getRuleContext(Arith_exprContext.class,0);
		}
		public TerminalNode UOP() { return getToken(AQLParser.UOP, 0); }
		public UnaryFilterContext(FilterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterUnaryFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitUnaryFilter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitUnaryFilter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DateRangeFilterContext extends FilterContext {
		public Date_rangeContext date_range() {
			return getRuleContext(Date_rangeContext.class,0);
		}
		public DateRangeFilterContext(FilterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterDateRangeFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitDateRangeFilter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitDateRangeFilter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConcatCompFilterContext extends FilterContext {
		public Concat_exprContext concat_expr() {
			return getRuleContext(Concat_exprContext.class,0);
		}
		public TerminalNode SOP() { return getToken(AQLParser.SOP, 0); }
		public TerminalNode SLITERAL() { return getToken(AQLParser.SLITERAL, 0); }
		public ConcatCompFilterContext(FilterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterConcatCompFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitConcatCompFilter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitConcatCompFilter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BasicFilterContext extends FilterContext {
		public List<Arith_exprContext> arith_expr() {
			return getRuleContexts(Arith_exprContext.class);
		}
		public Arith_exprContext arith_expr(int i) {
			return getRuleContext(Arith_exprContext.class,i);
		}
		public TerminalNode OP() { return getToken(AQLParser.OP, 0); }
		public BasicFilterContext(FilterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterBasicFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitBasicFilter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitBasicFilter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterContext filter() throws RecognitionException {
		FilterContext _localctx = new FilterContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_filter);
		try {
			setState(186);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				_localctx = new BasicFilterContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(159);
				arith_expr(0);
				setState(160);
				match(OP);
				setState(161);
				arith_expr(0);
				}
				break;
			case 2:
				_localctx = new MvFilterContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				arith_expr(0);
				setState(164);
				match(MOP);
				setState(165);
				literal_values();
				}
				break;
			case 3:
				_localctx = new ConcatCompFilterContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(167);
				concat_expr();
				setState(168);
				match(SOP);
				setState(169);
				match(SLITERAL);
				}
				break;
			case 4:
				_localctx = new StringCompFilterContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(171);
				match(FIELD);
				setState(172);
				match(SOP);
				setState(173);
				match(SLITERAL);
				}
				break;
			case 5:
				_localctx = new UnaryFilterContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(174);
				arith_expr(0);
				setState(175);
				match(UOP);
				}
				break;
			case 6:
				_localctx = new DateRangeFilterContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(177);
				date_range();
				}
				break;
			case 7:
				_localctx = new BetweenFilterContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(178);
				arith_expr(0);
				setState(179);
				match(BETWEEN);
				setState(180);
				match(LP);
				setState(181);
				arith_expr(0);
				setState(182);
				match(T__0);
				setState(183);
				arith_expr(0);
				setState(184);
				match(RP);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Literal_valuesContext extends ParserRuleContext {
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public Literal_valuesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_values; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterLiteral_values(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitLiteral_values(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitLiteral_values(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Literal_valuesContext literal_values() throws RecognitionException {
		Literal_valuesContext _localctx = new Literal_valuesContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_literal_values);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(LP);
			setState(189);
			literal();
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(190);
				match(T__0);
				setState(191);
				literal();
				}
				}
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(197);
			match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	 
		public LiteralContext() { }
		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StringLiteralContext extends LiteralContext {
		public TerminalNode SLITERAL() { return getToken(AQLParser.SLITERAL, 0); }
		public StringLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitStringLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolLiteralContext extends LiteralContext {
		public TerminalNode BOOL() { return getToken(AQLParser.BOOL, 0); }
		public BoolLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterBoolLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitBoolLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitBoolLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FloatLiteralContext extends LiteralContext {
		public TerminalNode FLOAT() { return getToken(AQLParser.FLOAT, 0); }
		public FloatLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterFloatLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitFloatLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitFloatLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntLiteralContext extends LiteralContext {
		public TerminalNode INT() { return getToken(AQLParser.INT, 0); }
		public IntLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterIntLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitIntLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitIntLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_literal);
		try {
			setState(203);
			switch (_input.LA(1)) {
			case SLITERAL:
				_localctx = new StringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(199);
				match(SLITERAL);
				}
				break;
			case INT:
				_localctx = new IntLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(200);
				match(INT);
				}
				break;
			case FLOAT:
				_localctx = new FloatLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(201);
				match(FLOAT);
				}
				break;
			case BOOL:
				_localctx = new BoolLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(202);
				match(BOOL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Arith_exprContext extends ParserRuleContext {
		public Arith_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arith_expr; }
	 
		public Arith_exprContext() { }
		public void copyFrom(Arith_exprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArithExprContext extends Arith_exprContext {
		public List<Arith_exprContext> arith_expr() {
			return getRuleContexts(Arith_exprContext.class);
		}
		public Arith_exprContext arith_expr(int i) {
			return getRuleContext(Arith_exprContext.class,i);
		}
		public TerminalNode ARITH_OP() { return getToken(AQLParser.ARITH_OP, 0); }
		public ArithExprContext(Arith_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterArithExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitArithExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitArithExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConcatExprContext extends Arith_exprContext {
		public Concat_exprContext concat_expr() {
			return getRuleContext(Concat_exprContext.class,0);
		}
		public ConcatExprContext(Arith_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterConcatExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitConcatExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitConcatExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FieldContext extends Arith_exprContext {
		public TerminalNode FIELD() { return getToken(AQLParser.FIELD, 0); }
		public FieldContext(Arith_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CurrentDateFuncContext extends Arith_exprContext {
		public TerminalNode CURR_DATE() { return getToken(AQLParser.CURR_DATE, 0); }
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public CurrentDateFuncContext(Arith_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterCurrentDateFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitCurrentDateFunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitCurrentDateFunc(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AggExprContext extends Arith_exprContext {
		public Agg_exprContext agg_expr() {
			return getRuleContext(Agg_exprContext.class,0);
		}
		public AggExprContext(Arith_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterAggExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitAggExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitAggExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParensArithExprContext extends Arith_exprContext {
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public Arith_exprContext arith_expr() {
			return getRuleContext(Arith_exprContext.class,0);
		}
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public ParensArithExprContext(Arith_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterParensArithExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitParensArithExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitParensArithExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MonthsDiffFuncContext extends Arith_exprContext {
		public TerminalNode MTHS_BTWN() { return getToken(AQLParser.MTHS_BTWN, 0); }
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public List<Arith_exprContext> arith_expr() {
			return getRuleContexts(Arith_exprContext.class);
		}
		public Arith_exprContext arith_expr(int i) {
			return getRuleContext(Arith_exprContext.class,i);
		}
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public MonthsDiffFuncContext(Arith_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterMonthsDiffFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitMonthsDiffFunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitMonthsDiffFunc(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class YearsDiffFuncContext extends Arith_exprContext {
		public TerminalNode YRS_BTWN() { return getToken(AQLParser.YRS_BTWN, 0); }
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public List<Arith_exprContext> arith_expr() {
			return getRuleContexts(Arith_exprContext.class);
		}
		public Arith_exprContext arith_expr(int i) {
			return getRuleContext(Arith_exprContext.class,i);
		}
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public YearsDiffFuncContext(Arith_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterYearsDiffFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitYearsDiffFunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitYearsDiffFunc(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RoundFuncContext extends Arith_exprContext {
		public TerminalNode ROUND() { return getToken(AQLParser.ROUND, 0); }
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public Arith_exprContext arith_expr() {
			return getRuleContext(Arith_exprContext.class,0);
		}
		public TerminalNode INT() { return getToken(AQLParser.INT, 0); }
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public RoundFuncContext(Arith_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterRoundFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitRoundFunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitRoundFunc(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LiteralValContext extends Arith_exprContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralValContext(Arith_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterLiteralVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitLiteralVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitLiteralVal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DateIntervalExprContext extends Arith_exprContext {
		public Arith_exprContext arith_expr() {
			return getRuleContext(Arith_exprContext.class,0);
		}
		public TerminalNode ARITH_OP() { return getToken(AQLParser.ARITH_OP, 0); }
		public Date_intervalContext date_interval() {
			return getRuleContext(Date_intervalContext.class,0);
		}
		public DateIntervalExprContext(Arith_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterDateIntervalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitDateIntervalExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitDateIntervalExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MinsDiffFuncContext extends Arith_exprContext {
		public TerminalNode MINS_BTWN() { return getToken(AQLParser.MINS_BTWN, 0); }
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public List<Arith_exprContext> arith_expr() {
			return getRuleContexts(Arith_exprContext.class);
		}
		public Arith_exprContext arith_expr(int i) {
			return getRuleContext(Arith_exprContext.class,i);
		}
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public MinsDiffFuncContext(Arith_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterMinsDiffFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitMinsDiffFunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitMinsDiffFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Arith_exprContext arith_expr() throws RecognitionException {
		return arith_expr(0);
	}

	private Arith_exprContext arith_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Arith_exprContext _localctx = new Arith_exprContext(_ctx, _parentState);
		Arith_exprContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_arith_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			switch (_input.LA(1)) {
			case LP:
				{
				_localctx = new ParensArithExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(206);
				match(LP);
				setState(207);
				arith_expr(0);
				setState(208);
				match(RP);
				}
				break;
			case MTHS_BTWN:
				{
				_localctx = new MonthsDiffFuncContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(210);
				match(MTHS_BTWN);
				setState(211);
				match(LP);
				setState(212);
				arith_expr(0);
				setState(213);
				match(T__0);
				setState(214);
				arith_expr(0);
				setState(215);
				match(RP);
				}
				break;
			case YRS_BTWN:
				{
				_localctx = new YearsDiffFuncContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(217);
				match(YRS_BTWN);
				setState(218);
				match(LP);
				setState(219);
				arith_expr(0);
				setState(220);
				match(T__0);
				setState(221);
				arith_expr(0);
				setState(222);
				match(RP);
				}
				break;
			case MINS_BTWN:
				{
				_localctx = new MinsDiffFuncContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(224);
				match(MINS_BTWN);
				setState(225);
				match(LP);
				setState(226);
				arith_expr(0);
				setState(227);
				match(T__0);
				setState(228);
				arith_expr(0);
				setState(229);
				match(RP);
				}
				break;
			case CURR_DATE:
				{
				_localctx = new CurrentDateFuncContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(231);
				match(CURR_DATE);
				setState(232);
				match(LP);
				setState(233);
				match(RP);
				}
				break;
			case COUNT:
			case SUM:
			case MIN:
			case MAX:
			case AVG:
				{
				_localctx = new AggExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(234);
				agg_expr();
				}
				break;
			case CONCAT:
				{
				_localctx = new ConcatExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(235);
				concat_expr();
				}
				break;
			case ROUND:
				{
				_localctx = new RoundFuncContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(236);
				match(ROUND);
				setState(237);
				match(LP);
				setState(238);
				arith_expr(0);
				setState(239);
				match(T__0);
				setState(240);
				match(INT);
				setState(241);
				match(RP);
				}
				break;
			case FIELD:
				{
				_localctx = new FieldContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(243);
				match(FIELD);
				}
				break;
			case BOOL:
			case INT:
			case FLOAT:
			case SLITERAL:
				{
				_localctx = new LiteralValContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(244);
				literal();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(255);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(253);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new ArithExprContext(new Arith_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arith_expr);
						setState(247);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(248);
						match(ARITH_OP);
						setState(249);
						arith_expr(13);
						}
						break;
					case 2:
						{
						_localctx = new DateIntervalExprContext(new Arith_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arith_expr);
						setState(250);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(251);
						match(ARITH_OP);
						setState(252);
						date_interval();
						}
						break;
					}
					} 
				}
				setState(257);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Date_rangeContext extends ParserRuleContext {
		public Date_rangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_date_range; }
	 
		public Date_rangeContext() { }
		public void copyFrom(Date_rangeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DateRangeFuncContext extends Date_rangeContext {
		public TerminalNode DATE_RANGE() { return getToken(AQLParser.DATE_RANGE, 0); }
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public Arith_exprContext arith_expr() {
			return getRuleContext(Arith_exprContext.class,0);
		}
		public TerminalNode ID() { return getToken(AQLParser.ID, 0); }
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public TerminalNode INT() { return getToken(AQLParser.INT, 0); }
		public DateRangeFuncContext(Date_rangeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterDateRangeFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitDateRangeFunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitDateRangeFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Date_rangeContext date_range() throws RecognitionException {
		Date_rangeContext _localctx = new Date_rangeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_date_range);
		int _la;
		try {
			_localctx = new DateRangeFuncContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			match(DATE_RANGE);
			setState(259);
			match(LP);
			setState(260);
			arith_expr(0);
			setState(261);
			match(T__0);
			setState(262);
			match(ID);
			setState(265);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(263);
				match(T__0);
				setState(264);
				match(INT);
				}
			}

			setState(267);
			match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Agg_exprContext extends ParserRuleContext {
		public Agg_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_agg_expr; }
	 
		public Agg_exprContext() { }
		public void copyFrom(Agg_exprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AggFuncContext extends Agg_exprContext {
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public TerminalNode FIELD() { return getToken(AQLParser.FIELD, 0); }
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public TerminalNode COUNT() { return getToken(AQLParser.COUNT, 0); }
		public TerminalNode SUM() { return getToken(AQLParser.SUM, 0); }
		public TerminalNode MIN() { return getToken(AQLParser.MIN, 0); }
		public TerminalNode MAX() { return getToken(AQLParser.MAX, 0); }
		public TerminalNode AVG() { return getToken(AQLParser.AVG, 0); }
		public TerminalNode DISTINCT() { return getToken(AQLParser.DISTINCT, 0); }
		public AggFuncContext(Agg_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterAggFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitAggFunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitAggFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Agg_exprContext agg_expr() throws RecognitionException {
		Agg_exprContext _localctx = new Agg_exprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_agg_expr);
		int _la;
		try {
			_localctx = new AggFuncContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COUNT) | (1L << SUM) | (1L << MIN) | (1L << MAX) | (1L << AVG))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(270);
			match(LP);
			setState(272);
			_la = _input.LA(1);
			if (_la==DISTINCT) {
				{
				setState(271);
				match(DISTINCT);
				}
			}

			setState(274);
			match(FIELD);
			setState(275);
			match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Concat_exprContext extends ParserRuleContext {
		public Concat_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_concat_expr; }
	 
		public Concat_exprContext() { }
		public void copyFrom(Concat_exprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ConcatFuncContext extends Concat_exprContext {
		public TerminalNode CONCAT() { return getToken(AQLParser.CONCAT, 0); }
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public List<Arith_exprContext> arith_expr() {
			return getRuleContexts(Arith_exprContext.class);
		}
		public Arith_exprContext arith_expr(int i) {
			return getRuleContext(Arith_exprContext.class,i);
		}
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public ConcatFuncContext(Concat_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterConcatFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitConcatFunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitConcatFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Concat_exprContext concat_expr() throws RecognitionException {
		Concat_exprContext _localctx = new Concat_exprContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_concat_expr);
		int _la;
		try {
			_localctx = new ConcatFuncContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(CONCAT);
			setState(278);
			match(LP);
			setState(279);
			arith_expr(0);
			setState(280);
			match(T__0);
			setState(281);
			arith_expr(0);
			setState(286);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(282);
				match(T__0);
				setState(283);
				arith_expr(0);
				}
				}
				setState(288);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(289);
			match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Date_intervalContext extends ParserRuleContext {
		public TerminalNode YEAR() { return getToken(AQLParser.YEAR, 0); }
		public TerminalNode MONTH() { return getToken(AQLParser.MONTH, 0); }
		public TerminalNode DAY() { return getToken(AQLParser.DAY, 0); }
		public Date_intervalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_date_interval; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterDate_interval(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitDate_interval(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitDate_interval(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Date_intervalContext date_interval() throws RecognitionException {
		Date_intervalContext _localctx = new Date_intervalContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_date_interval);
		int _la;
		try {
			setState(312);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(291);
				match(YEAR);
				setState(293);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(292);
					match(MONTH);
					}
					break;
				}
				setState(296);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(295);
					match(DAY);
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(299);
				_la = _input.LA(1);
				if (_la==YEAR) {
					{
					setState(298);
					match(YEAR);
					}
				}

				setState(301);
				match(MONTH);
				setState(303);
				switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(302);
					match(DAY);
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(306);
				_la = _input.LA(1);
				if (_la==YEAR) {
					{
					setState(305);
					match(YEAR);
					}
				}

				setState(309);
				_la = _input.LA(1);
				if (_la==MONTH) {
					{
					setState(308);
					match(MONTH);
					}
				}

				setState(311);
				match(DAY);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return filter_expr_sempred((Filter_exprContext)_localctx, predIndex);
		case 12:
			return arith_expr_sempred((Arith_exprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean filter_expr_sempred(Filter_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		}
		return true;
	}
	private boolean arith_expr_sempred(Arith_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 12);
		case 4:
			return precpred(_ctx, 11);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\62\u013d\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\5\2)\n\2\3\2\3\2\5\2-\n\2\3\2\5\2\60\n\2\3\2\3\2\5\2"+
		"\64\n\2\3\3\5\3\67\n\3\3\3\3\3\3\3\7\3<\n\3\f\3\16\3?\13\3\3\4\3\4\3\4"+
		"\5\4D\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5S\n\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5^\n\5\f\5\16\5a\13\5\3\6\3\6\3"+
		"\6\3\6\7\6g\n\6\f\6\16\6j\13\6\3\7\3\7\5\7n\n\7\3\b\3\b\3\b\3\b\5\bt\n"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\t\7\t|\n\t\f\t\16\t\177\13\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\7\t\u0089\n\t\f\t\16\t\u008c\13\t\3\t\3\t\3\t\5\t\u0091"+
		"\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\7\n\u009a\n\n\f\n\16\n\u009d\13\n\3\n"+
		"\5\n\u00a0\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\5\13\u00bd\n\13\3\f\3\f\3\f\3\f\7\f\u00c3\n\f\f\f\16\f\u00c6"+
		"\13\f\3\f\3\f\3\r\3\r\3\r\3\r\5\r\u00ce\n\r\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00f8\n\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\7\16\u0100\n\16\f\16\16\16\u0103\13\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\5\17\u010c\n\17\3\17\3\17\3\20\3\20\3\20\5\20\u0113\n"+
		"\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u011f\n\21"+
		"\f\21\16\21\u0122\13\21\3\21\3\21\3\22\3\22\5\22\u0128\n\22\3\22\5\22"+
		"\u012b\n\22\3\22\5\22\u012e\n\22\3\22\3\22\5\22\u0132\n\22\3\22\5\22\u0135"+
		"\n\22\3\22\5\22\u0138\n\22\3\22\5\22\u013b\n\22\3\22\2\4\b\32\23\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\3\3\2\17\23\u0161\2(\3\2\2\2\4"+
		"\66\3\2\2\2\6@\3\2\2\2\bR\3\2\2\2\nb\3\2\2\2\fk\3\2\2\2\16o\3\2\2\2\20"+
		"u\3\2\2\2\22\u0094\3\2\2\2\24\u00bc\3\2\2\2\26\u00be\3\2\2\2\30\u00cd"+
		"\3\2\2\2\32\u00f7\3\2\2\2\34\u0104\3\2\2\2\36\u010f\3\2\2\2 \u0117\3\2"+
		"\2\2\"\u013a\3\2\2\2$%\7\6\2\2%&\5\4\3\2&\'\7\7\2\2\')\3\2\2\2($\3\2\2"+
		"\2()\3\2\2\2)*\3\2\2\2*,\5\b\5\2+-\5\n\6\2,+\3\2\2\2,-\3\2\2\2-/\3\2\2"+
		"\2.\60\5\16\b\2/.\3\2\2\2/\60\3\2\2\2\60\63\3\2\2\2\61\64\5\20\t\2\62"+
		"\64\5\22\n\2\63\61\3\2\2\2\63\62\3\2\2\2\63\64\3\2\2\2\64\3\3\2\2\2\65"+
		"\67\7\24\2\2\66\65\3\2\2\2\66\67\3\2\2\2\678\3\2\2\28=\5\6\4\29:\7\3\2"+
		"\2:<\5\6\4\2;9\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>\5\3\2\2\2?=\3\2"+
		"\2\2@C\5\32\16\2AB\7\4\2\2BD\7.\2\2CA\3\2\2\2CD\3\2\2\2D\7\3\2\2\2EF\b"+
		"\5\1\2FG\7\35\2\2GS\5\b\5\4HI\7\37\2\2IJ\5\b\5\2JK\7 \2\2KS\3\2\2\2LM"+
		"\7\b\2\2MN\7\37\2\2NO\5\b\5\2OP\7 \2\2PS\3\2\2\2QS\5\24\13\2RE\3\2\2\2"+
		"RH\3\2\2\2RL\3\2\2\2RQ\3\2\2\2S_\3\2\2\2TU\f\t\2\2UV\7\33\2\2V^\5\b\5"+
		"\nWX\f\b\2\2XY\7\32\2\2Y^\5\b\5\tZ[\f\7\2\2[\\\7\34\2\2\\^\5\b\5\b]T\3"+
		"\2\2\2]W\3\2\2\2]Z\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`\t\3\2\2\2a_"+
		"\3\2\2\2bc\7\25\2\2ch\5\f\7\2de\7\3\2\2eg\5\f\7\2fd\3\2\2\2gj\3\2\2\2"+
		"hf\3\2\2\2hi\3\2\2\2i\13\3\2\2\2jh\3\2\2\2km\5\32\16\2ln\7\26\2\2ml\3"+
		"\2\2\2mn\3\2\2\2n\r\3\2\2\2op\7\27\2\2ps\7&\2\2qr\7\3\2\2rt\7&\2\2sq\3"+
		"\2\2\2st\3\2\2\2t\17\3\2\2\2uv\7\30\2\2vw\7\37\2\2wx\7\37\2\2x}\7&\2\2"+
		"yz\7\3\2\2z|\7&\2\2{y\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\u0080"+
		"\3\2\2\2\177}\3\2\2\2\u0080\u0081\7 \2\2\u0081\u0082\7\3\2\2\u0082\u0083"+
		"\7&\2\2\u0083\u0084\7\3\2\2\u0084\u0085\7\37\2\2\u0085\u008a\7&\2\2\u0086"+
		"\u0087\7\3\2\2\u0087\u0089\7&\2\2\u0088\u0086\3\2\2\2\u0089\u008c\3\2"+
		"\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008d\3\2\2\2\u008c"+
		"\u008a\3\2\2\2\u008d\u0090\7 \2\2\u008e\u008f\7\3\2\2\u008f\u0091\7$\2"+
		"\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0093"+
		"\7 \2\2\u0093\21\3\2\2\2\u0094\u009f\7,\2\2\u0095\u0096\7\37\2\2\u0096"+
		"\u009b\7.\2\2\u0097\u0098\7\3\2\2\u0098\u009a\7.\2\2\u0099\u0097\3\2\2"+
		"\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009e"+
		"\3\2\2\2\u009d\u009b\3\2\2\2\u009e\u00a0\7 \2\2\u009f\u0095\3\2\2\2\u009f"+
		"\u00a0\3\2\2\2\u00a0\23\3\2\2\2\u00a1\u00a2\5\32\16\2\u00a2\u00a3\7%\2"+
		"\2\u00a3\u00a4\5\32\16\2\u00a4\u00bd\3\2\2\2\u00a5\u00a6\5\32\16\2\u00a6"+
		"\u00a7\7!\2\2\u00a7\u00a8\5\26\f\2\u00a8\u00bd\3\2\2\2\u00a9\u00aa\5 "+
		"\21\2\u00aa\u00ab\7\"\2\2\u00ab\u00ac\7.\2\2\u00ac\u00bd\3\2\2\2\u00ad"+
		"\u00ae\7-\2\2\u00ae\u00af\7\"\2\2\u00af\u00bd\7.\2\2\u00b0\u00b1\5\32"+
		"\16\2\u00b1\u00b2\7#\2\2\u00b2\u00bd\3\2\2\2\u00b3\u00bd\5\34\17\2\u00b4"+
		"\u00b5\5\32\16\2\u00b5\u00b6\7\t\2\2\u00b6\u00b7\7\37\2\2\u00b7\u00b8"+
		"\5\32\16\2\u00b8\u00b9\7\3\2\2\u00b9\u00ba\5\32\16\2\u00ba\u00bb\7 \2"+
		"\2\u00bb\u00bd\3\2\2\2\u00bc\u00a1\3\2\2\2\u00bc\u00a5\3\2\2\2\u00bc\u00a9"+
		"\3\2\2\2\u00bc\u00ad\3\2\2\2\u00bc\u00b0\3\2\2\2\u00bc\u00b3\3\2\2\2\u00bc"+
		"\u00b4\3\2\2\2\u00bd\25\3\2\2\2\u00be\u00bf\7\37\2\2\u00bf\u00c4\5\30"+
		"\r\2\u00c0\u00c1\7\3\2\2\u00c1\u00c3\5\30\r\2\u00c2\u00c0\3\2\2\2\u00c3"+
		"\u00c6\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c7\3\2"+
		"\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00c8\7 \2\2\u00c8\27\3\2\2\2\u00c9\u00ce"+
		"\7.\2\2\u00ca\u00ce\7&\2\2\u00cb\u00ce\7\'\2\2\u00cc\u00ce\7$\2\2\u00cd"+
		"\u00c9\3\2\2\2\u00cd\u00ca\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00cc\3\2"+
		"\2\2\u00ce\31\3\2\2\2\u00cf\u00d0\b\16\1\2\u00d0\u00d1\7\37\2\2\u00d1"+
		"\u00d2\5\32\16\2\u00d2\u00d3\7 \2\2\u00d3\u00f8\3\2\2\2\u00d4\u00d5\7"+
		"\n\2\2\u00d5\u00d6\7\37\2\2\u00d6\u00d7\5\32\16\2\u00d7\u00d8\7\3\2\2"+
		"\u00d8\u00d9\5\32\16\2\u00d9\u00da\7 \2\2\u00da\u00f8\3\2\2\2\u00db\u00dc"+
		"\7\13\2\2\u00dc\u00dd\7\37\2\2\u00dd\u00de\5\32\16\2\u00de\u00df\7\3\2"+
		"\2\u00df\u00e0\5\32\16\2\u00e0\u00e1\7 \2\2\u00e1\u00f8\3\2\2\2\u00e2"+
		"\u00e3\7\r\2\2\u00e3\u00e4\7\37\2\2\u00e4\u00e5\5\32\16\2\u00e5\u00e6"+
		"\7\3\2\2\u00e6\u00e7\5\32\16\2\u00e7\u00e8\7 \2\2\u00e8\u00f8\3\2\2\2"+
		"\u00e9\u00ea\7\f\2\2\u00ea\u00eb\7\37\2\2\u00eb\u00f8\7 \2\2\u00ec\u00f8"+
		"\5\36\20\2\u00ed\u00f8\5 \21\2\u00ee\u00ef\7\36\2\2\u00ef\u00f0\7\37\2"+
		"\2\u00f0\u00f1\5\32\16\2\u00f1\u00f2\7\3\2\2\u00f2\u00f3\7&\2\2\u00f3"+
		"\u00f4\7 \2\2\u00f4\u00f8\3\2\2\2\u00f5\u00f8\7-\2\2\u00f6\u00f8\5\30"+
		"\r\2\u00f7\u00cf\3\2\2\2\u00f7\u00d4\3\2\2\2\u00f7\u00db\3\2\2\2\u00f7"+
		"\u00e2\3\2\2\2\u00f7\u00e9\3\2\2\2\u00f7\u00ec\3\2\2\2\u00f7\u00ed\3\2"+
		"\2\2\u00f7\u00ee\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f6\3\2\2\2\u00f8"+
		"\u0101\3\2\2\2\u00f9\u00fa\f\16\2\2\u00fa\u00fb\7\60\2\2\u00fb\u0100\5"+
		"\32\16\17\u00fc\u00fd\f\r\2\2\u00fd\u00fe\7\60\2\2\u00fe\u0100\5\"\22"+
		"\2\u00ff\u00f9\3\2\2\2\u00ff\u00fc\3\2\2\2\u0100\u0103\3\2\2\2\u0101\u00ff"+
		"\3\2\2\2\u0101\u0102\3\2\2\2\u0102\33\3\2\2\2\u0103\u0101\3\2\2\2\u0104"+
		"\u0105\7\16\2\2\u0105\u0106\7\37\2\2\u0106\u0107\5\32\16\2\u0107\u0108"+
		"\7\3\2\2\u0108\u010b\7,\2\2\u0109\u010a\7\3\2\2\u010a\u010c\7&\2\2\u010b"+
		"\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010e\7 "+
		"\2\2\u010e\35\3\2\2\2\u010f\u0110\t\2\2\2\u0110\u0112\7\37\2\2\u0111\u0113"+
		"\7\24\2\2\u0112\u0111\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0114\3\2\2\2"+
		"\u0114\u0115\7-\2\2\u0115\u0116\7 \2\2\u0116\37\3\2\2\2\u0117\u0118\7"+
		"\31\2\2\u0118\u0119\7\37\2\2\u0119\u011a\5\32\16\2\u011a\u011b\7\3\2\2"+
		"\u011b\u0120\5\32\16\2\u011c\u011d\7\3\2\2\u011d\u011f\5\32\16\2\u011e"+
		"\u011c\3\2\2\2\u011f\u0122\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121\3\2"+
		"\2\2\u0121\u0123\3\2\2\2\u0122\u0120\3\2\2\2\u0123\u0124\7 \2\2\u0124"+
		"!\3\2\2\2\u0125\u0127\7(\2\2\u0126\u0128\7)\2\2\u0127\u0126\3\2\2\2\u0127"+
		"\u0128\3\2\2\2\u0128\u012a\3\2\2\2\u0129\u012b\7*\2\2\u012a\u0129\3\2"+
		"\2\2\u012a\u012b\3\2\2\2\u012b\u013b\3\2\2\2\u012c\u012e\7(\2\2\u012d"+
		"\u012c\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0131\7)"+
		"\2\2\u0130\u0132\7*\2\2\u0131\u0130\3\2\2\2\u0131\u0132\3\2\2\2\u0132"+
		"\u013b\3\2\2\2\u0133\u0135\7(\2\2\u0134\u0133\3\2\2\2\u0134\u0135\3\2"+
		"\2\2\u0135\u0137\3\2\2\2\u0136\u0138\7)\2\2\u0137\u0136\3\2\2\2\u0137"+
		"\u0138\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u013b\7*\2\2\u013a\u0125\3\2"+
		"\2\2\u013a\u012d\3\2\2\2\u013a\u0134\3\2\2\2\u013b#\3\2\2\2$(,/\63\66"+
		"=CR]_hms}\u008a\u0090\u009b\u009f\u00bc\u00c4\u00cd\u00f7\u00ff\u0101"+
		"\u010b\u0112\u0120\u0127\u012a\u012d\u0131\u0134\u0137\u013a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}