// Generated from AQL.g4 by ANTLR 4.1
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
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__1=1, T__0=2, WS=3, SELECT=4, WHERE=5, NTHCHILD=6, BETWEEN=7, MTHS_BTWN=8, 
		YRS_BTWN=9, CURR_DATE=10, MINS_BTWN=11, COUNT=12, SUM=13, MIN=14, MAX=15, 
		AVG=16, DISTINCT=17, LIMIT=18, CROSSTAB=19, OR=20, AND=21, PAND=22, NOT=23, 
		ROUND=24, LP=25, RP=26, MOP=27, SOP=28, EOP=29, BOOL=30, OP=31, INT=32, 
		FLOAT=33, YEAR=34, MONTH=35, DAY=36, DIGIT=37, ID=38, FIELD=39, SLITERAL=40, 
		ESC=41, ARITH_OP=42, ERROR=43, QUOTE=44;
	public static final String[] tokenNames = {
		"<INVALID>", "'as'", "','", "WS", "'select'", "'where'", "'nthchild'", 
		"'between'", "'months_between'", "'years_between'", "'current_date'", 
		"'minutes_between'", "'count'", "'sum'", "'min'", "'max'", "'avg'", "'distinct'", 
		"'limit'", "'crosstab'", "'or'", "'and'", "'pand'", "'not'", "'round'", 
		"'('", "')'", "MOP", "SOP", "EOP", "BOOL", "OP", "INT", "FLOAT", "YEAR", 
		"MONTH", "DAY", "DIGIT", "ID", "FIELD", "SLITERAL", "ESC", "ARITH_OP", 
		"ERROR", "'\"'"
	};
	public static final int
		RULE_query = 0, RULE_select_list = 1, RULE_select_element = 2, RULE_filter_expr = 3, 
		RULE_limit_expr = 4, RULE_crosstab_expr = 5, RULE_report_expr = 6, RULE_filter = 7, 
		RULE_literal_values = 8, RULE_literal = 9, RULE_arith_expr = 10, RULE_agg_expr = 11, 
		RULE_date_interval = 12;
	public static final String[] ruleNames = {
		"query", "select_list", "select_element", "filter_expr", "limit_expr", 
		"crosstab_expr", "report_expr", "filter", "literal_values", "literal", 
		"arith_expr", "agg_expr", "date_interval"
	};

	@Override
	public String getGrammarFileName() { return "AQL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

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
		public TerminalNode WHERE() { return getToken(AQLParser.WHERE, 0); }
		public Filter_exprContext filter_expr() {
			return getRuleContext(Filter_exprContext.class,0);
		}
		public Crosstab_exprContext crosstab_expr() {
			return getRuleContext(Crosstab_exprContext.class,0);
		}
		public Report_exprContext report_expr() {
			return getRuleContext(Report_exprContext.class,0);
		}
		public Limit_exprContext limit_expr() {
			return getRuleContext(Limit_exprContext.class,0);
		}
		public TerminalNode SELECT() { return getToken(AQLParser.SELECT, 0); }
		public Select_listContext select_list() {
			return getRuleContext(Select_listContext.class,0);
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
			setState(30);
			_la = _input.LA(1);
			if (_la==SELECT) {
				{
				setState(26); match(SELECT);
				setState(27); select_list();
				setState(28); match(WHERE);
				}
			}

			setState(32); filter_expr(0);
			setState(34);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(33); limit_expr();
				}
			}

			setState(38);
			switch (_input.LA(1)) {
			case CROSSTAB:
				{
				setState(36); crosstab_expr();
				}
				break;
			case ID:
				{
				setState(37); report_expr();
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
		public Select_elementContext select_element(int i) {
			return getRuleContext(Select_elementContext.class,i);
		}
		public List<Select_elementContext> select_element() {
			return getRuleContexts(Select_elementContext.class);
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
			setState(41);
			_la = _input.LA(1);
			if (_la==DISTINCT) {
				{
				setState(40); match(DISTINCT);
				}
			}

			setState(43); select_element();
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==2) {
				{
				{
				setState(44); match(2);
				setState(45); select_element();
				}
				}
				setState(50);
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
			setState(51); arith_expr(0);
			setState(54);
			_la = _input.LA(1);
			if (_la==1) {
				{
				setState(52); match(1);
				setState(53); match(SLITERAL);
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
		public int _p;
		public Filter_exprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Filter_exprContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_filter_expr; }
	 
		public Filter_exprContext() { }
		public void copyFrom(Filter_exprContext ctx) {
			super.copyFrom(ctx);
			this._p = ctx._p;
		}
	}
	public static class NotFilterExprContext extends Filter_exprContext {
		public Filter_exprContext filter_expr() {
			return getRuleContext(Filter_exprContext.class,0);
		}
		public TerminalNode NOT() { return getToken(AQLParser.NOT, 0); }
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
		public Filter_exprContext filter_expr() {
			return getRuleContext(Filter_exprContext.class,0);
		}
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
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
	public static class PandFilterExprContext extends Filter_exprContext {
		public List<Filter_exprContext> filter_expr() {
			return getRuleContexts(Filter_exprContext.class);
		}
		public TerminalNode PAND() { return getToken(AQLParser.PAND, 0); }
		public Filter_exprContext filter_expr(int i) {
			return getRuleContext(Filter_exprContext.class,i);
		}
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
	public static class NthChildFilterExprContext extends Filter_exprContext {
		public Filter_exprContext filter_expr() {
			return getRuleContext(Filter_exprContext.class,0);
		}
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public TerminalNode NTHCHILD() { return getToken(AQLParser.NTHCHILD, 0); }
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
		public TerminalNode AND() { return getToken(AQLParser.AND, 0); }
		public Filter_exprContext filter_expr(int i) {
			return getRuleContext(Filter_exprContext.class,i);
		}
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

	public final Filter_exprContext filter_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Filter_exprContext _localctx = new Filter_exprContext(_ctx, _parentState, _p);
		Filter_exprContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, RULE_filter_expr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				_localctx = new NotFilterExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(57); match(NOT);
				setState(58); filter_expr(2);
				}
				break;

			case 2:
				{
				_localctx = new ParensFilterExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(59); match(LP);
				setState(60); filter_expr(0);
				setState(61); match(RP);
				}
				break;

			case 3:
				{
				_localctx = new NthChildFilterExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(63); match(NTHCHILD);
				setState(64); match(LP);
				setState(65); filter_expr(0);
				setState(66); match(RP);
				}
				break;

			case 4:
				{
				_localctx = new SimpleFilterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(68); filter();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(82);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(80);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new AndFilterExprContext(new Filter_exprContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_filter_expr);
						setState(71);
						if (!(7 >= _localctx._p)) throw new FailedPredicateException(this, "7 >= $_p");
						setState(72); match(AND);
						setState(73); filter_expr(8);
						}
						break;

					case 2:
						{
						_localctx = new OrFilterExprContext(new Filter_exprContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_filter_expr);
						setState(74);
						if (!(6 >= _localctx._p)) throw new FailedPredicateException(this, "6 >= $_p");
						setState(75); match(OR);
						setState(76); filter_expr(7);
						}
						break;

					case 3:
						{
						_localctx = new PandFilterExprContext(new Filter_exprContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_filter_expr);
						setState(77);
						if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "5 >= $_p");
						setState(78); match(PAND);
						setState(79); filter_expr(6);
						}
						break;
					}
					} 
				}
				setState(84);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
		enterRule(_localctx, 8, RULE_limit_expr);
		int _la;
		try {
			_localctx = new LimitExprContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(85); match(LIMIT);
			setState(86); match(INT);
			setState(89);
			_la = _input.LA(1);
			if (_la==2) {
				{
				setState(87); match(2);
				setState(88); match(INT);
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
		public TerminalNode BOOL() { return getToken(AQLParser.BOOL, 0); }
		public TerminalNode LP(int i) {
			return getToken(AQLParser.LP, i);
		}
		public List<TerminalNode> INT() { return getTokens(AQLParser.INT); }
		public List<TerminalNode> LP() { return getTokens(AQLParser.LP); }
		public List<TerminalNode> RP() { return getTokens(AQLParser.RP); }
		public TerminalNode INT(int i) {
			return getToken(AQLParser.INT, i);
		}
		public TerminalNode CROSSTAB() { return getToken(AQLParser.CROSSTAB, 0); }
		public TerminalNode RP(int i) {
			return getToken(AQLParser.RP, i);
		}
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
		enterRule(_localctx, 10, RULE_crosstab_expr);
		int _la;
		try {
			_localctx = new CrossTabExprContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(91); match(CROSSTAB);
			setState(92); match(LP);
			setState(93); match(LP);
			setState(94); ((CrossTabExprContext)_localctx).INT = match(INT);
			((CrossTabExprContext)_localctx).row.add(((CrossTabExprContext)_localctx).INT);
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==2) {
				{
				{
				setState(95); match(2);
				setState(96); ((CrossTabExprContext)_localctx).INT = match(INT);
				((CrossTabExprContext)_localctx).row.add(((CrossTabExprContext)_localctx).INT);
				}
				}
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(102); match(RP);
			setState(103); match(2);
			setState(104); ((CrossTabExprContext)_localctx).col = match(INT);
			setState(105); match(2);
			setState(106); match(LP);
			setState(107); ((CrossTabExprContext)_localctx).INT = match(INT);
			((CrossTabExprContext)_localctx).value.add(((CrossTabExprContext)_localctx).INT);
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==2) {
				{
				{
				setState(108); match(2);
				setState(109); ((CrossTabExprContext)_localctx).INT = match(INT);
				((CrossTabExprContext)_localctx).value.add(((CrossTabExprContext)_localctx).INT);
				}
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(115); match(RP);
			setState(118);
			_la = _input.LA(1);
			if (_la==2) {
				{
				setState(116); match(2);
				setState(117); match(BOOL);
				}
			}

			setState(120); match(RP);
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
		public List<TerminalNode> SLITERAL() { return getTokens(AQLParser.SLITERAL); }
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public TerminalNode ID() { return getToken(AQLParser.ID, 0); }
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public TerminalNode SLITERAL(int i) {
			return getToken(AQLParser.SLITERAL, i);
		}
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
		enterRule(_localctx, 12, RULE_report_expr);
		int _la;
		try {
			_localctx = new ReportExprContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(122); match(ID);
			setState(133);
			_la = _input.LA(1);
			if (_la==LP) {
				{
				setState(123); match(LP);
				setState(124); match(SLITERAL);
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==2) {
					{
					{
					setState(125); match(2);
					setState(126); match(SLITERAL);
					}
					}
					setState(131);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(132); match(RP);
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
	public static class BasicFilterContext extends FilterContext {
		public Arith_exprContext arith_expr(int i) {
			return getRuleContext(Arith_exprContext.class,i);
		}
		public List<Arith_exprContext> arith_expr() {
			return getRuleContexts(Arith_exprContext.class);
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
	public static class StringCompFilterContext extends FilterContext {
		public TerminalNode SLITERAL() { return getToken(AQLParser.SLITERAL, 0); }
		public TerminalNode SOP() { return getToken(AQLParser.SOP, 0); }
		public TerminalNode FIELD() { return getToken(AQLParser.FIELD, 0); }
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
	public static class ExistsFilterContext extends FilterContext {
		public TerminalNode EOP() { return getToken(AQLParser.EOP, 0); }
		public TerminalNode FIELD() { return getToken(AQLParser.FIELD, 0); }
		public ExistsFilterContext(FilterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterExistsFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitExistsFilter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitExistsFilter(this);
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
	public static class BetweenFilterContext extends FilterContext {
		public Arith_exprContext arith_expr(int i) {
			return getRuleContext(Arith_exprContext.class,i);
		}
		public List<Arith_exprContext> arith_expr() {
			return getRuleContexts(Arith_exprContext.class);
		}
		public TerminalNode BETWEEN() { return getToken(AQLParser.BETWEEN, 0); }
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public TerminalNode FIELD() { return getToken(AQLParser.FIELD, 0); }
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

	public final FilterContext filter() throws RecognitionException {
		FilterContext _localctx = new FilterContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_filter);
		try {
			setState(156);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				_localctx = new BasicFilterContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(135); arith_expr(0);
				setState(136); match(OP);
				setState(137); arith_expr(0);
				}
				break;

			case 2:
				_localctx = new MvFilterContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(139); arith_expr(0);
				setState(140); match(MOP);
				setState(141); literal_values();
				}
				break;

			case 3:
				_localctx = new StringCompFilterContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(143); match(FIELD);
				setState(144); match(SOP);
				setState(145); match(SLITERAL);
				}
				break;

			case 4:
				_localctx = new ExistsFilterContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(146); match(FIELD);
				setState(147); match(EOP);
				}
				break;

			case 5:
				_localctx = new BetweenFilterContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(148); match(FIELD);
				setState(149); match(BETWEEN);
				setState(150); match(LP);
				setState(151); arith_expr(0);
				setState(152); match(2);
				setState(153); arith_expr(0);
				setState(154); match(RP);
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
		enterRule(_localctx, 16, RULE_literal_values);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158); match(LP);
			setState(159); literal();
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==2) {
				{
				{
				setState(160); match(2);
				setState(161); literal();
				}
				}
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(167); match(RP);
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
		enterRule(_localctx, 18, RULE_literal);
		try {
			setState(173);
			switch (_input.LA(1)) {
			case SLITERAL:
				_localctx = new StringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(169); match(SLITERAL);
				}
				break;
			case INT:
				_localctx = new IntLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(170); match(INT);
				}
				break;
			case FLOAT:
				_localctx = new FloatLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(171); match(FLOAT);
				}
				break;
			case BOOL:
				_localctx = new BoolLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(172); match(BOOL);
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
		public int _p;
		public Arith_exprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Arith_exprContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_arith_expr; }
	 
		public Arith_exprContext() { }
		public void copyFrom(Arith_exprContext ctx) {
			super.copyFrom(ctx);
			this._p = ctx._p;
		}
	}
	public static class MinsDiffFuncContext extends Arith_exprContext {
		public Arith_exprContext arith_expr(int i) {
			return getRuleContext(Arith_exprContext.class,i);
		}
		public List<Arith_exprContext> arith_expr() {
			return getRuleContexts(Arith_exprContext.class);
		}
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public TerminalNode MINS_BTWN() { return getToken(AQLParser.MINS_BTWN, 0); }
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
	public static class ArithExprContext extends Arith_exprContext {
		public Arith_exprContext arith_expr(int i) {
			return getRuleContext(Arith_exprContext.class,i);
		}
		public List<Arith_exprContext> arith_expr() {
			return getRuleContexts(Arith_exprContext.class);
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
	public static class CurrentDateFuncContext extends Arith_exprContext {
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public TerminalNode CURR_DATE() { return getToken(AQLParser.CURR_DATE, 0); }
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
	public static class YearsDiffFuncContext extends Arith_exprContext {
		public Arith_exprContext arith_expr(int i) {
			return getRuleContext(Arith_exprContext.class,i);
		}
		public List<Arith_exprContext> arith_expr() {
			return getRuleContexts(Arith_exprContext.class);
		}
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public TerminalNode YRS_BTWN() { return getToken(AQLParser.YRS_BTWN, 0); }
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
	public static class ParensArithExprContext extends Arith_exprContext {
		public Arith_exprContext arith_expr() {
			return getRuleContext(Arith_exprContext.class,0);
		}
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
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
		public Arith_exprContext arith_expr(int i) {
			return getRuleContext(Arith_exprContext.class,i);
		}
		public TerminalNode MTHS_BTWN() { return getToken(AQLParser.MTHS_BTWN, 0); }
		public List<Arith_exprContext> arith_expr() {
			return getRuleContexts(Arith_exprContext.class);
		}
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
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
	public static class RoundFuncContext extends Arith_exprContext {
		public Arith_exprContext arith_expr() {
			return getRuleContext(Arith_exprContext.class,0);
		}
		public TerminalNode INT() { return getToken(AQLParser.INT, 0); }
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public TerminalNode ROUND() { return getToken(AQLParser.ROUND, 0); }
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

	public final Arith_exprContext arith_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Arith_exprContext _localctx = new Arith_exprContext(_ctx, _parentState, _p);
		Arith_exprContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, RULE_arith_expr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			switch (_input.LA(1)) {
			case LP:
				{
				_localctx = new ParensArithExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(176); match(LP);
				setState(177); arith_expr(0);
				setState(178); match(RP);
				}
				break;
			case MTHS_BTWN:
				{
				_localctx = new MonthsDiffFuncContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(180); match(MTHS_BTWN);
				setState(181); match(LP);
				setState(182); arith_expr(0);
				setState(183); match(2);
				setState(184); arith_expr(0);
				setState(185); match(RP);
				}
				break;
			case YRS_BTWN:
				{
				_localctx = new YearsDiffFuncContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(187); match(YRS_BTWN);
				setState(188); match(LP);
				setState(189); arith_expr(0);
				setState(190); match(2);
				setState(191); arith_expr(0);
				setState(192); match(RP);
				}
				break;
			case MINS_BTWN:
				{
				_localctx = new MinsDiffFuncContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(194); match(MINS_BTWN);
				setState(195); match(LP);
				setState(196); arith_expr(0);
				setState(197); match(2);
				setState(198); arith_expr(0);
				setState(199); match(RP);
				}
				break;
			case CURR_DATE:
				{
				_localctx = new CurrentDateFuncContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(201); match(CURR_DATE);
				setState(202); match(LP);
				setState(203); match(RP);
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
				setState(204); agg_expr();
				}
				break;
			case ROUND:
				{
				_localctx = new RoundFuncContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(205); match(ROUND);
				setState(206); match(LP);
				setState(207); arith_expr(0);
				setState(208); match(2);
				setState(209); match(INT);
				setState(210); match(RP);
				}
				break;
			case FIELD:
				{
				_localctx = new FieldContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(212); match(FIELD);
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
				setState(213); literal();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(224);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(222);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new ArithExprContext(new Arith_exprContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_arith_expr);
						setState(216);
						if (!(11 >= _localctx._p)) throw new FailedPredicateException(this, "11 >= $_p");
						setState(217); match(ARITH_OP);
						setState(218); arith_expr(12);
						}
						break;

					case 2:
						{
						_localctx = new DateIntervalExprContext(new Arith_exprContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_arith_expr);
						setState(219);
						if (!(10 >= _localctx._p)) throw new FailedPredicateException(this, "10 >= $_p");
						setState(220); match(ARITH_OP);
						setState(221); date_interval();
						}
						break;
					}
					} 
				}
				setState(226);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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
		public TerminalNode COUNT() { return getToken(AQLParser.COUNT, 0); }
		public TerminalNode SUM() { return getToken(AQLParser.SUM, 0); }
		public TerminalNode DISTINCT() { return getToken(AQLParser.DISTINCT, 0); }
		public TerminalNode LP() { return getToken(AQLParser.LP, 0); }
		public TerminalNode AVG() { return getToken(AQLParser.AVG, 0); }
		public TerminalNode RP() { return getToken(AQLParser.RP, 0); }
		public TerminalNode MIN() { return getToken(AQLParser.MIN, 0); }
		public TerminalNode MAX() { return getToken(AQLParser.MAX, 0); }
		public TerminalNode FIELD() { return getToken(AQLParser.FIELD, 0); }
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
		enterRule(_localctx, 22, RULE_agg_expr);
		int _la;
		try {
			_localctx = new AggFuncContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COUNT) | (1L << SUM) | (1L << MIN) | (1L << MAX) | (1L << AVG))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(228); match(LP);
			setState(230);
			_la = _input.LA(1);
			if (_la==DISTINCT) {
				{
				setState(229); match(DISTINCT);
				}
			}

			setState(232); match(FIELD);
			setState(233); match(RP);
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
		enterRule(_localctx, 24, RULE_date_interval);
		int _la;
		try {
			setState(256);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(235); match(YEAR);
				setState(237);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(236); match(MONTH);
					}
					break;
				}
				setState(240);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(239); match(DAY);
					}
					break;
				}
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(243);
				_la = _input.LA(1);
				if (_la==YEAR) {
					{
					setState(242); match(YEAR);
					}
				}

				setState(245); match(MONTH);
				setState(247);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(246); match(DAY);
					}
					break;
				}
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(250);
				_la = _input.LA(1);
				if (_la==YEAR) {
					{
					setState(249); match(YEAR);
					}
				}

				setState(253);
				_la = _input.LA(1);
				if (_la==MONTH) {
					{
					setState(252); match(MONTH);
					}
				}

				setState(255); match(DAY);
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
		case 3: return filter_expr_sempred((Filter_exprContext)_localctx, predIndex);

		case 10: return arith_expr_sempred((Arith_exprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean filter_expr_sempred(Filter_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 7 >= _localctx._p;

		case 1: return 6 >= _localctx._p;

		case 2: return 5 >= _localctx._p;
		}
		return true;
	}
	private boolean arith_expr_sempred(Arith_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3: return 11 >= _localctx._p;

		case 4: return 10 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3.\u0105\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\5\2!\n\2\3\2\3\2\5\2%\n"+
		"\2\3\2\3\2\5\2)\n\2\3\3\5\3,\n\3\3\3\3\3\3\3\7\3\61\n\3\f\3\16\3\64\13"+
		"\3\3\4\3\4\3\4\5\49\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\5\5H\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5S\n\5\f\5\16\5"+
		"V\13\5\3\6\3\6\3\6\3\6\5\6\\\n\6\3\7\3\7\3\7\3\7\3\7\3\7\7\7d\n\7\f\7"+
		"\16\7g\13\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7q\n\7\f\7\16\7t\13\7\3"+
		"\7\3\7\3\7\5\7y\n\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\7\b\u0082\n\b\f\b\16\b"+
		"\u0085\13\b\3\b\5\b\u0088\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u009f\n\t\3\n\3\n\3\n"+
		"\3\n\7\n\u00a5\n\n\f\n\16\n\u00a8\13\n\3\n\3\n\3\13\3\13\3\13\3\13\5\13"+
		"\u00b0\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00d9\n\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f"+
		"\u00e1\n\f\f\f\16\f\u00e4\13\f\3\r\3\r\3\r\5\r\u00e9\n\r\3\r\3\r\3\r\3"+
		"\16\3\16\5\16\u00f0\n\16\3\16\5\16\u00f3\n\16\3\16\5\16\u00f6\n\16\3\16"+
		"\3\16\5\16\u00fa\n\16\3\16\5\16\u00fd\n\16\3\16\5\16\u0100\n\16\3\16\5"+
		"\16\u0103\n\16\3\16\2\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\3\3\2\16\22"+
		"\u0125\2 \3\2\2\2\4+\3\2\2\2\6\65\3\2\2\2\bG\3\2\2\2\nW\3\2\2\2\f]\3\2"+
		"\2\2\16|\3\2\2\2\20\u009e\3\2\2\2\22\u00a0\3\2\2\2\24\u00af\3\2\2\2\26"+
		"\u00d8\3\2\2\2\30\u00e5\3\2\2\2\32\u0102\3\2\2\2\34\35\7\6\2\2\35\36\5"+
		"\4\3\2\36\37\7\7\2\2\37!\3\2\2\2 \34\3\2\2\2 !\3\2\2\2!\"\3\2\2\2\"$\5"+
		"\b\5\2#%\5\n\6\2$#\3\2\2\2$%\3\2\2\2%(\3\2\2\2&)\5\f\7\2\')\5\16\b\2("+
		"&\3\2\2\2(\'\3\2\2\2()\3\2\2\2)\3\3\2\2\2*,\7\23\2\2+*\3\2\2\2+,\3\2\2"+
		"\2,-\3\2\2\2-\62\5\6\4\2./\7\4\2\2/\61\5\6\4\2\60.\3\2\2\2\61\64\3\2\2"+
		"\2\62\60\3\2\2\2\62\63\3\2\2\2\63\5\3\2\2\2\64\62\3\2\2\2\658\5\26\f\2"+
		"\66\67\7\3\2\2\679\7*\2\28\66\3\2\2\289\3\2\2\29\7\3\2\2\2:;\b\5\1\2;"+
		"<\7\31\2\2<H\5\b\5\2=>\7\33\2\2>?\5\b\5\2?@\7\34\2\2@H\3\2\2\2AB\7\b\2"+
		"\2BC\7\33\2\2CD\5\b\5\2DE\7\34\2\2EH\3\2\2\2FH\5\20\t\2G:\3\2\2\2G=\3"+
		"\2\2\2GA\3\2\2\2GF\3\2\2\2HT\3\2\2\2IJ\6\5\2\3JK\7\27\2\2KS\5\b\5\2LM"+
		"\6\5\3\3MN\7\26\2\2NS\5\b\5\2OP\6\5\4\3PQ\7\30\2\2QS\5\b\5\2RI\3\2\2\2"+
		"RL\3\2\2\2RO\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2U\t\3\2\2\2VT\3\2\2"+
		"\2WX\7\24\2\2X[\7\"\2\2YZ\7\4\2\2Z\\\7\"\2\2[Y\3\2\2\2[\\\3\2\2\2\\\13"+
		"\3\2\2\2]^\7\25\2\2^_\7\33\2\2_`\7\33\2\2`e\7\"\2\2ab\7\4\2\2bd\7\"\2"+
		"\2ca\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2fh\3\2\2\2ge\3\2\2\2hi\7\34"+
		"\2\2ij\7\4\2\2jk\7\"\2\2kl\7\4\2\2lm\7\33\2\2mr\7\"\2\2no\7\4\2\2oq\7"+
		"\"\2\2pn\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2su\3\2\2\2tr\3\2\2\2ux\7"+
		"\34\2\2vw\7\4\2\2wy\7 \2\2xv\3\2\2\2xy\3\2\2\2yz\3\2\2\2z{\7\34\2\2{\r"+
		"\3\2\2\2|\u0087\7(\2\2}~\7\33\2\2~\u0083\7*\2\2\177\u0080\7\4\2\2\u0080"+
		"\u0082\7*\2\2\u0081\177\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2"+
		"\2\u0083\u0084\3\2\2\2\u0084\u0086\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0088"+
		"\7\34\2\2\u0087}\3\2\2\2\u0087\u0088\3\2\2\2\u0088\17\3\2\2\2\u0089\u008a"+
		"\5\26\f\2\u008a\u008b\7!\2\2\u008b\u008c\5\26\f\2\u008c\u009f\3\2\2\2"+
		"\u008d\u008e\5\26\f\2\u008e\u008f\7\35\2\2\u008f\u0090\5\22\n\2\u0090"+
		"\u009f\3\2\2\2\u0091\u0092\7)\2\2\u0092\u0093\7\36\2\2\u0093\u009f\7*"+
		"\2\2\u0094\u0095\7)\2\2\u0095\u009f\7\37\2\2\u0096\u0097\7)\2\2\u0097"+
		"\u0098\7\t\2\2\u0098\u0099\7\33\2\2\u0099\u009a\5\26\f\2\u009a\u009b\7"+
		"\4\2\2\u009b\u009c\5\26\f\2\u009c\u009d\7\34\2\2\u009d\u009f\3\2\2\2\u009e"+
		"\u0089\3\2\2\2\u009e\u008d\3\2\2\2\u009e\u0091\3\2\2\2\u009e\u0094\3\2"+
		"\2\2\u009e\u0096\3\2\2\2\u009f\21\3\2\2\2\u00a0\u00a1\7\33\2\2\u00a1\u00a6"+
		"\5\24\13\2\u00a2\u00a3\7\4\2\2\u00a3\u00a5\5\24\13\2\u00a4\u00a2\3\2\2"+
		"\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9"+
		"\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00aa\7\34\2\2\u00aa\23\3\2\2\2\u00ab"+
		"\u00b0\7*\2\2\u00ac\u00b0\7\"\2\2\u00ad\u00b0\7#\2\2\u00ae\u00b0\7 \2"+
		"\2\u00af\u00ab\3\2\2\2\u00af\u00ac\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00ae"+
		"\3\2\2\2\u00b0\25\3\2\2\2\u00b1\u00b2\b\f\1\2\u00b2\u00b3\7\33\2\2\u00b3"+
		"\u00b4\5\26\f\2\u00b4\u00b5\7\34\2\2\u00b5\u00d9\3\2\2\2\u00b6\u00b7\7"+
		"\n\2\2\u00b7\u00b8\7\33\2\2\u00b8\u00b9\5\26\f\2\u00b9\u00ba\7\4\2\2\u00ba"+
		"\u00bb\5\26\f\2\u00bb\u00bc\7\34\2\2\u00bc\u00d9\3\2\2\2\u00bd\u00be\7"+
		"\13\2\2\u00be\u00bf\7\33\2\2\u00bf\u00c0\5\26\f\2\u00c0\u00c1\7\4\2\2"+
		"\u00c1\u00c2\5\26\f\2\u00c2\u00c3\7\34\2\2\u00c3\u00d9\3\2\2\2\u00c4\u00c5"+
		"\7\r\2\2\u00c5\u00c6\7\33\2\2\u00c6\u00c7\5\26\f\2\u00c7\u00c8\7\4\2\2"+
		"\u00c8\u00c9\5\26\f\2\u00c9\u00ca\7\34\2\2\u00ca\u00d9\3\2\2\2\u00cb\u00cc"+
		"\7\f\2\2\u00cc\u00cd\7\33\2\2\u00cd\u00d9\7\34\2\2\u00ce\u00d9\5\30\r"+
		"\2\u00cf\u00d0\7\32\2\2\u00d0\u00d1\7\33\2\2\u00d1\u00d2\5\26\f\2\u00d2"+
		"\u00d3\7\4\2\2\u00d3\u00d4\7\"\2\2\u00d4\u00d5\7\34\2\2\u00d5\u00d9\3"+
		"\2\2\2\u00d6\u00d9\7)\2\2\u00d7\u00d9\5\24\13\2\u00d8\u00b1\3\2\2\2\u00d8"+
		"\u00b6\3\2\2\2\u00d8\u00bd\3\2\2\2\u00d8\u00c4\3\2\2\2\u00d8\u00cb\3\2"+
		"\2\2\u00d8\u00ce\3\2\2\2\u00d8\u00cf\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8"+
		"\u00d7\3\2\2\2\u00d9\u00e2\3\2\2\2\u00da\u00db\6\f\5\3\u00db\u00dc\7,"+
		"\2\2\u00dc\u00e1\5\26\f\2\u00dd\u00de\6\f\6\3\u00de\u00df\7,\2\2\u00df"+
		"\u00e1\5\32\16\2\u00e0\u00da\3\2\2\2\u00e0\u00dd\3\2\2\2\u00e1\u00e4\3"+
		"\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\27\3\2\2\2\u00e4"+
		"\u00e2\3\2\2\2\u00e5\u00e6\t\2\2\2\u00e6\u00e8\7\33\2\2\u00e7\u00e9\7"+
		"\23\2\2\u00e8\u00e7\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea"+
		"\u00eb\7)\2\2\u00eb\u00ec\7\34\2\2\u00ec\31\3\2\2\2\u00ed\u00ef\7$\2\2"+
		"\u00ee\u00f0\7%\2\2\u00ef\u00ee\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f2"+
		"\3\2\2\2\u00f1\u00f3\7&\2\2\u00f2\u00f1\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3"+
		"\u0103\3\2\2\2\u00f4\u00f6\7$\2\2\u00f5\u00f4\3\2\2\2\u00f5\u00f6\3\2"+
		"\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f9\7%\2\2\u00f8\u00fa\7&\2\2\u00f9\u00f8"+
		"\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u0103\3\2\2\2\u00fb\u00fd\7$\2\2\u00fc"+
		"\u00fb\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00ff\3\2\2\2\u00fe\u0100\7%"+
		"\2\2\u00ff\u00fe\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0101\3\2\2\2\u0101"+
		"\u0103\7&\2\2\u0102\u00ed\3\2\2\2\u0102\u00f5\3\2\2\2\u0102\u00fc\3\2"+
		"\2\2\u0103\33\3\2\2\2\37 $(+\628GRT[erx\u0083\u0087\u009e\u00a6\u00af"+
		"\u00d8\u00e0\u00e2\u00e8\u00ef\u00f2\u00f5\u00f9\u00fc\u00ff\u0102";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}