// Generated from AQL.g4 by ANTLR 4.5.1
package edu.common.dynamicextensions.query.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AQLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, WS=3, SELECT=4, WHERE=5, NTHCHILD=6, BETWEEN=7, MTHS_BTWN=8, 
		YRS_BTWN=9, CURR_DATE=10, MINS_BTWN=11, DATE_RANGE=12, COUNT=13, CCOUNT=14, 
		SUM=15, CSUM=16, MIN=17, MAX=18, AVG=19, DISTINCT=20, ORDER=21, ORD_DIR=22, 
		LIMIT=23, CROSSTAB=24, CONCAT=25, CONCAT_WS=26, OR=27, AND=28, PAND=29, 
		NOT=30, ROUND=31, LP=32, RP=33, MOP=34, SOP=35, UOP=36, BOOL=37, OP=38, 
		INT=39, FLOAT=40, YEAR=41, MONTH=42, DAY=43, DIGIT=44, ID=45, FIELD=46, 
		SLITERAL=47, ESC=48, ARITH_OP=49, ERROR=50, QUOTE=51;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "WS", "SELECT", "WHERE", "NTHCHILD", "BETWEEN", "MTHS_BTWN", 
		"YRS_BTWN", "CURR_DATE", "MINS_BTWN", "DATE_RANGE", "COUNT", "CCOUNT", 
		"SUM", "CSUM", "MIN", "MAX", "AVG", "DISTINCT", "ORDER", "ORD_DIR", "LIMIT", 
		"CROSSTAB", "CONCAT", "CONCAT_WS", "OR", "AND", "PAND", "NOT", "ROUND", 
		"LP", "RP", "MOP", "SOP", "UOP", "BOOL", "OP", "INT", "FLOAT", "YEAR", 
		"MONTH", "DAY", "DIGIT", "ID", "FIELD", "SLITERAL", "ESC", "ARITH_OP", 
		"ERROR", "SGUTS", "QUOTE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "'as'", null, "'select'", "'where'", "'nthchild'", "'between'", 
		"'months_between'", "'years_between'", "'current_date'", "'minutes_between'", 
		"'date_range'", "'count'", "'c_count'", "'sum'", "'c_sum'", "'min'", "'max'", 
		"'avg'", "'distinct'", "'order by'", null, "'limit'", "'crosstab'", "'concat'", 
		"'concat_ws'", "'or'", "'and'", "'pand'", "'not'", "'round'", "'('", "')'", 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "'\"'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "WS", "SELECT", "WHERE", "NTHCHILD", "BETWEEN", "MTHS_BTWN", 
		"YRS_BTWN", "CURR_DATE", "MINS_BTWN", "DATE_RANGE", "COUNT", "CCOUNT", 
		"SUM", "CSUM", "MIN", "MAX", "AVG", "DISTINCT", "ORDER", "ORD_DIR", "LIMIT", 
		"CROSSTAB", "CONCAT", "CONCAT_WS", "OR", "AND", "PAND", "NOT", "ROUND", 
		"LP", "RP", "MOP", "SOP", "UOP", "BOOL", "OP", "INT", "FLOAT", "YEAR", 
		"MONTH", "DAY", "DIGIT", "ID", "FIELD", "SLITERAL", "ESC", "ARITH_OP", 
		"ERROR", "QUOTE"
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


	public AQLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "AQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\65\u01fe\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\3\2\3\2\3\3\3\3\3\3\3\4\6\4r\n\4\r\4\16\4s\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\5\27\u0118\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35"+
		"\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3"+
		" \3 \3!\3!\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\5#\u015c\n#\3$\3$\3$\3$\3$"+
		"\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$"+
		"\5$\u017a\n$\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%"+
		"\5%\u018f\n%\3&\3&\3&\3&\3&\3&\3&\3&\3&\5&\u019a\n&\3\'\3\'\3\'\3\'\3"+
		"\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u01a8\n\'\3(\5(\u01ab\n(\3(\6(\u01ae"+
		"\n(\r(\16(\u01af\3)\5)\u01b3\n)\3)\6)\u01b6\n)\r)\16)\u01b7\3)\3)\6)\u01bc"+
		"\n)\r)\16)\u01bd\3*\6*\u01c1\n*\r*\16*\u01c2\3*\3*\3+\6+\u01c8\n+\r+\16"+
		"+\u01c9\3+\3+\3,\6,\u01cf\n,\r,\16,\u01d0\3,\3,\3-\3-\3.\3.\7.\u01d9\n"+
		".\f.\16.\u01dc\13.\3/\3/\5/\u01e0\n/\3/\3/\3/\3/\7/\u01e6\n/\f/\16/\u01e9"+
		"\13/\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64"+
		"\7\64\u01f8\n\64\f\64\16\64\u01fb\13\64\3\65\3\65\2\2\66\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\2i\65\3\2\13\5\2\13\f\17\17"+
		"\"\"\4\2>>@@\4\2[[{{\4\2OOoo\4\2FFff\5\2C\\aac|\7\2\62;AAC\\aac|\4\2$"+
		"$^^\5\2,-//\61\61\u0216\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2"+
		"\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2"+
		"Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3"+
		"\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2i\3\2\2\2\3k\3\2\2"+
		"\2\5m\3\2\2\2\7q\3\2\2\2\tw\3\2\2\2\13~\3\2\2\2\r\u0084\3\2\2\2\17\u008d"+
		"\3\2\2\2\21\u0095\3\2\2\2\23\u00a4\3\2\2\2\25\u00b2\3\2\2\2\27\u00bf\3"+
		"\2\2\2\31\u00cf\3\2\2\2\33\u00da\3\2\2\2\35\u00e0\3\2\2\2\37\u00e8\3\2"+
		"\2\2!\u00ec\3\2\2\2#\u00f2\3\2\2\2%\u00f6\3\2\2\2\'\u00fa\3\2\2\2)\u00fe"+
		"\3\2\2\2+\u0107\3\2\2\2-\u0117\3\2\2\2/\u0119\3\2\2\2\61\u011f\3\2\2\2"+
		"\63\u0128\3\2\2\2\65\u012f\3\2\2\2\67\u0139\3\2\2\29\u013c\3\2\2\2;\u0140"+
		"\3\2\2\2=\u0145\3\2\2\2?\u0149\3\2\2\2A\u014f\3\2\2\2C\u0151\3\2\2\2E"+
		"\u015b\3\2\2\2G\u0179\3\2\2\2I\u018e\3\2\2\2K\u0199\3\2\2\2M\u01a7\3\2"+
		"\2\2O\u01aa\3\2\2\2Q\u01b2\3\2\2\2S\u01c0\3\2\2\2U\u01c7\3\2\2\2W\u01ce"+
		"\3\2\2\2Y\u01d4\3\2\2\2[\u01d6\3\2\2\2]\u01df\3\2\2\2_\u01ea\3\2\2\2a"+
		"\u01ee\3\2\2\2c\u01f1\3\2\2\2e\u01f3\3\2\2\2g\u01f9\3\2\2\2i\u01fc\3\2"+
		"\2\2kl\7.\2\2l\4\3\2\2\2mn\7c\2\2no\7u\2\2o\6\3\2\2\2pr\t\2\2\2qp\3\2"+
		"\2\2rs\3\2\2\2sq\3\2\2\2st\3\2\2\2tu\3\2\2\2uv\b\4\2\2v\b\3\2\2\2wx\7"+
		"u\2\2xy\7g\2\2yz\7n\2\2z{\7g\2\2{|\7e\2\2|}\7v\2\2}\n\3\2\2\2~\177\7y"+
		"\2\2\177\u0080\7j\2\2\u0080\u0081\7g\2\2\u0081\u0082\7t\2\2\u0082\u0083"+
		"\7g\2\2\u0083\f\3\2\2\2\u0084\u0085\7p\2\2\u0085\u0086\7v\2\2\u0086\u0087"+
		"\7j\2\2\u0087\u0088\7e\2\2\u0088\u0089\7j\2\2\u0089\u008a\7k\2\2\u008a"+
		"\u008b\7n\2\2\u008b\u008c\7f\2\2\u008c\16\3\2\2\2\u008d\u008e\7d\2\2\u008e"+
		"\u008f\7g\2\2\u008f\u0090\7v\2\2\u0090\u0091\7y\2\2\u0091\u0092\7g\2\2"+
		"\u0092\u0093\7g\2\2\u0093\u0094\7p\2\2\u0094\20\3\2\2\2\u0095\u0096\7"+
		"o\2\2\u0096\u0097\7q\2\2\u0097\u0098\7p\2\2\u0098\u0099\7v\2\2\u0099\u009a"+
		"\7j\2\2\u009a\u009b\7u\2\2\u009b\u009c\7a\2\2\u009c\u009d\7d\2\2\u009d"+
		"\u009e\7g\2\2\u009e\u009f\7v\2\2\u009f\u00a0\7y\2\2\u00a0\u00a1\7g\2\2"+
		"\u00a1\u00a2\7g\2\2\u00a2\u00a3\7p\2\2\u00a3\22\3\2\2\2\u00a4\u00a5\7"+
		"{\2\2\u00a5\u00a6\7g\2\2\u00a6\u00a7\7c\2\2\u00a7\u00a8\7t\2\2\u00a8\u00a9"+
		"\7u\2\2\u00a9\u00aa\7a\2\2\u00aa\u00ab\7d\2\2\u00ab\u00ac\7g\2\2\u00ac"+
		"\u00ad\7v\2\2\u00ad\u00ae\7y\2\2\u00ae\u00af\7g\2\2\u00af\u00b0\7g\2\2"+
		"\u00b0\u00b1\7p\2\2\u00b1\24\3\2\2\2\u00b2\u00b3\7e\2\2\u00b3\u00b4\7"+
		"w\2\2\u00b4\u00b5\7t\2\2\u00b5\u00b6\7t\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8"+
		"\7p\2\2\u00b8\u00b9\7v\2\2\u00b9\u00ba\7a\2\2\u00ba\u00bb\7f\2\2\u00bb"+
		"\u00bc\7c\2\2\u00bc\u00bd\7v\2\2\u00bd\u00be\7g\2\2\u00be\26\3\2\2\2\u00bf"+
		"\u00c0\7o\2\2\u00c0\u00c1\7k\2\2\u00c1\u00c2\7p\2\2\u00c2\u00c3\7w\2\2"+
		"\u00c3\u00c4\7v\2\2\u00c4\u00c5\7g\2\2\u00c5\u00c6\7u\2\2\u00c6\u00c7"+
		"\7a\2\2\u00c7\u00c8\7d\2\2\u00c8\u00c9\7g\2\2\u00c9\u00ca\7v\2\2\u00ca"+
		"\u00cb\7y\2\2\u00cb\u00cc\7g\2\2\u00cc\u00cd\7g\2\2\u00cd\u00ce\7p\2\2"+
		"\u00ce\30\3\2\2\2\u00cf\u00d0\7f\2\2\u00d0\u00d1\7c\2\2\u00d1\u00d2\7"+
		"v\2\2\u00d2\u00d3\7g\2\2\u00d3\u00d4\7a\2\2\u00d4\u00d5\7t\2\2\u00d5\u00d6"+
		"\7c\2\2\u00d6\u00d7\7p\2\2\u00d7\u00d8\7i\2\2\u00d8\u00d9\7g\2\2\u00d9"+
		"\32\3\2\2\2\u00da\u00db\7e\2\2\u00db\u00dc\7q\2\2\u00dc\u00dd\7w\2\2\u00dd"+
		"\u00de\7p\2\2\u00de\u00df\7v\2\2\u00df\34\3\2\2\2\u00e0\u00e1\7e\2\2\u00e1"+
		"\u00e2\7a\2\2\u00e2\u00e3\7e\2\2\u00e3\u00e4\7q\2\2\u00e4\u00e5\7w\2\2"+
		"\u00e5\u00e6\7p\2\2\u00e6\u00e7\7v\2\2\u00e7\36\3\2\2\2\u00e8\u00e9\7"+
		"u\2\2\u00e9\u00ea\7w\2\2\u00ea\u00eb\7o\2\2\u00eb \3\2\2\2\u00ec\u00ed"+
		"\7e\2\2\u00ed\u00ee\7a\2\2\u00ee\u00ef\7u\2\2\u00ef\u00f0\7w\2\2\u00f0"+
		"\u00f1\7o\2\2\u00f1\"\3\2\2\2\u00f2\u00f3\7o\2\2\u00f3\u00f4\7k\2\2\u00f4"+
		"\u00f5\7p\2\2\u00f5$\3\2\2\2\u00f6\u00f7\7o\2\2\u00f7\u00f8\7c\2\2\u00f8"+
		"\u00f9\7z\2\2\u00f9&\3\2\2\2\u00fa\u00fb\7c\2\2\u00fb\u00fc\7x\2\2\u00fc"+
		"\u00fd\7i\2\2\u00fd(\3\2\2\2\u00fe\u00ff\7f\2\2\u00ff\u0100\7k\2\2\u0100"+
		"\u0101\7u\2\2\u0101\u0102\7v\2\2\u0102\u0103\7k\2\2\u0103\u0104\7p\2\2"+
		"\u0104\u0105\7e\2\2\u0105\u0106\7v\2\2\u0106*\3\2\2\2\u0107\u0108\7q\2"+
		"\2\u0108\u0109\7t\2\2\u0109\u010a\7f\2\2\u010a\u010b\7g\2\2\u010b\u010c"+
		"\7t\2\2\u010c\u010d\7\"\2\2\u010d\u010e\7d\2\2\u010e\u010f\7{\2\2\u010f"+
		",\3\2\2\2\u0110\u0111\7f\2\2\u0111\u0112\7g\2\2\u0112\u0113\7u\2\2\u0113"+
		"\u0118\7e\2\2\u0114\u0115\7c\2\2\u0115\u0116\7u\2\2\u0116\u0118\7e\2\2"+
		"\u0117\u0110\3\2\2\2\u0117\u0114\3\2\2\2\u0118.\3\2\2\2\u0119\u011a\7"+
		"n\2\2\u011a\u011b\7k\2\2\u011b\u011c\7o\2\2\u011c\u011d\7k\2\2\u011d\u011e"+
		"\7v\2\2\u011e\60\3\2\2\2\u011f\u0120\7e\2\2\u0120\u0121\7t\2\2\u0121\u0122"+
		"\7q\2\2\u0122\u0123\7u\2\2\u0123\u0124\7u\2\2\u0124\u0125\7v\2\2\u0125"+
		"\u0126\7c\2\2\u0126\u0127\7d\2\2\u0127\62\3\2\2\2\u0128\u0129\7e\2\2\u0129"+
		"\u012a\7q\2\2\u012a\u012b\7p\2\2\u012b\u012c\7e\2\2\u012c\u012d\7c\2\2"+
		"\u012d\u012e\7v\2\2\u012e\64\3\2\2\2\u012f\u0130\7e\2\2\u0130\u0131\7"+
		"q\2\2\u0131\u0132\7p\2\2\u0132\u0133\7e\2\2\u0133\u0134\7c\2\2\u0134\u0135"+
		"\7v\2\2\u0135\u0136\7a\2\2\u0136\u0137\7y\2\2\u0137\u0138\7u\2\2\u0138"+
		"\66\3\2\2\2\u0139\u013a\7q\2\2\u013a\u013b\7t\2\2\u013b8\3\2\2\2\u013c"+
		"\u013d\7c\2\2\u013d\u013e\7p\2\2\u013e\u013f\7f\2\2\u013f:\3\2\2\2\u0140"+
		"\u0141\7r\2\2\u0141\u0142\7c\2\2\u0142\u0143\7p\2\2\u0143\u0144\7f\2\2"+
		"\u0144<\3\2\2\2\u0145\u0146\7p\2\2\u0146\u0147\7q\2\2\u0147\u0148\7v\2"+
		"\2\u0148>\3\2\2\2\u0149\u014a\7t\2\2\u014a\u014b\7q\2\2\u014b\u014c\7"+
		"w\2\2\u014c\u014d\7p\2\2\u014d\u014e\7f\2\2\u014e@\3\2\2\2\u014f\u0150"+
		"\7*\2\2\u0150B\3\2\2\2\u0151\u0152\7+\2\2\u0152D\3\2\2\2\u0153\u0154\7"+
		"k\2\2\u0154\u015c\7p\2\2\u0155\u0156\7p\2\2\u0156\u0157\7q\2\2\u0157\u0158"+
		"\7v\2\2\u0158\u0159\7\"\2\2\u0159\u015a\7k\2\2\u015a\u015c\7p\2\2\u015b"+
		"\u0153\3\2\2\2\u015b\u0155\3\2\2\2\u015cF\3\2\2\2\u015d\u015e\7e\2\2\u015e"+
		"\u015f\7q\2\2\u015f\u0160\7p\2\2\u0160\u0161\7v\2\2\u0161\u0162\7c\2\2"+
		"\u0162\u0163\7k\2\2\u0163\u0164\7p\2\2\u0164\u017a\7u\2\2\u0165\u0166"+
		"\7u\2\2\u0166\u0167\7v\2\2\u0167\u0168\7c\2\2\u0168\u0169\7t\2\2\u0169"+
		"\u016a\7v\2\2\u016a\u016b\7u\2\2\u016b\u016c\7\"\2\2\u016c\u016d\7y\2"+
		"\2\u016d\u016e\7k\2\2\u016e\u016f\7v\2\2\u016f\u017a\7j\2\2\u0170\u0171"+
		"\7g\2\2\u0171\u0172\7p\2\2\u0172\u0173\7f\2\2\u0173\u0174\7u\2\2\u0174"+
		"\u0175\7\"\2\2\u0175\u0176\7y\2\2\u0176\u0177\7k\2\2\u0177\u0178\7v\2"+
		"\2\u0178\u017a\7j\2\2\u0179\u015d\3\2\2\2\u0179\u0165\3\2\2\2\u0179\u0170"+
		"\3\2\2\2\u017aH\3\2\2\2\u017b\u017c\7g\2\2\u017c\u017d\7z\2\2\u017d\u017e"+
		"\7k\2\2\u017e\u017f\7u\2\2\u017f\u0180\7v\2\2\u0180\u018f\7u\2\2\u0181"+
		"\u0182\7p\2\2\u0182\u0183\7q\2\2\u0183\u0184\7v\2\2\u0184\u0185\7\"\2"+
		"\2\u0185\u0186\7g\2\2\u0186\u0187\7z\2\2\u0187\u0188\7k\2\2\u0188\u0189"+
		"\7u\2\2\u0189\u018a\7v\2\2\u018a\u018f\7u\2\2\u018b\u018c\7c\2\2\u018c"+
		"\u018d\7p\2\2\u018d\u018f\7{\2\2\u018e\u017b\3\2\2\2\u018e\u0181\3\2\2"+
		"\2\u018e\u018b\3\2\2\2\u018fJ\3\2\2\2\u0190\u0191\7v\2\2\u0191\u0192\7"+
		"t\2\2\u0192\u0193\7w\2\2\u0193\u019a\7g\2\2\u0194\u0195\7h\2\2\u0195\u0196"+
		"\7c\2\2\u0196\u0197\7n\2\2\u0197\u0198\7u\2\2\u0198\u019a\7g\2\2\u0199"+
		"\u0190\3\2\2\2\u0199\u0194\3\2\2\2\u019aL\3\2\2\2\u019b\u01a8\t\3\2\2"+
		"\u019c\u019d\7@\2\2\u019d\u01a8\7?\2\2\u019e\u019f\7>\2\2\u019f\u01a8"+
		"\7?\2\2\u01a0\u01a8\7?\2\2\u01a1\u01a2\7#\2\2\u01a2\u01a8\7?\2\2\u01a3"+
		"\u01a4\7n\2\2\u01a4\u01a5\7k\2\2\u01a5\u01a6\7m\2\2\u01a6\u01a8\7g\2\2"+
		"\u01a7\u019b\3\2\2\2\u01a7\u019c\3\2\2\2\u01a7\u019e\3\2\2\2\u01a7\u01a0"+
		"\3\2\2\2\u01a7\u01a1\3\2\2\2\u01a7\u01a3\3\2\2\2\u01a8N\3\2\2\2\u01a9"+
		"\u01ab\7/\2\2\u01aa\u01a9\3\2\2\2\u01aa\u01ab\3\2\2\2\u01ab\u01ad\3\2"+
		"\2\2\u01ac\u01ae\5Y-\2\u01ad\u01ac\3\2\2\2\u01ae\u01af\3\2\2\2\u01af\u01ad"+
		"\3\2\2\2\u01af\u01b0\3\2\2\2\u01b0P\3\2\2\2\u01b1\u01b3\7/\2\2\u01b2\u01b1"+
		"\3\2\2\2\u01b2\u01b3\3\2\2\2\u01b3\u01b5\3\2\2\2\u01b4\u01b6\5Y-\2\u01b5"+
		"\u01b4\3\2\2\2\u01b6\u01b7\3\2\2\2\u01b7\u01b5\3\2\2\2\u01b7\u01b8\3\2"+
		"\2\2\u01b8\u01b9\3\2\2\2\u01b9\u01bb\7\60\2\2\u01ba\u01bc\5Y-\2\u01bb"+
		"\u01ba\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd\u01bb\3\2\2\2\u01bd\u01be\3\2"+
		"\2\2\u01beR\3\2\2\2\u01bf\u01c1\5Y-\2\u01c0\u01bf\3\2\2\2\u01c1\u01c2"+
		"\3\2\2\2\u01c2\u01c0\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4"+
		"\u01c5\t\4\2\2\u01c5T\3\2\2\2\u01c6\u01c8\5Y-\2\u01c7\u01c6\3\2\2\2\u01c8"+
		"\u01c9\3\2\2\2\u01c9\u01c7\3\2\2\2\u01c9\u01ca\3\2\2\2\u01ca\u01cb\3\2"+
		"\2\2\u01cb\u01cc\t\5\2\2\u01ccV\3\2\2\2\u01cd\u01cf\5Y-\2\u01ce\u01cd"+
		"\3\2\2\2\u01cf\u01d0\3\2\2\2\u01d0\u01ce\3\2\2\2\u01d0\u01d1\3\2\2\2\u01d1"+
		"\u01d2\3\2\2\2\u01d2\u01d3\t\6\2\2\u01d3X\3\2\2\2\u01d4\u01d5\4\62;\2"+
		"\u01d5Z\3\2\2\2\u01d6\u01da\t\7\2\2\u01d7\u01d9\t\b\2\2\u01d8\u01d7\3"+
		"\2\2\2\u01d9\u01dc\3\2\2\2\u01da\u01d8\3\2\2\2\u01da\u01db\3\2\2\2\u01db"+
		"\\\3\2\2\2\u01dc\u01da\3\2\2\2\u01dd\u01e0\5O(\2\u01de\u01e0\5[.\2\u01df"+
		"\u01dd\3\2\2\2\u01df\u01de\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1\u01e2\7\60"+
		"\2\2\u01e2\u01e7\5[.\2\u01e3\u01e4\7\60\2\2\u01e4\u01e6\5[.\2\u01e5\u01e3"+
		"\3\2\2\2\u01e6\u01e9\3\2\2\2\u01e7\u01e5\3\2\2\2\u01e7\u01e8\3\2\2\2\u01e8"+
		"^\3\2\2\2\u01e9\u01e7\3\2\2\2\u01ea\u01eb\7$\2\2\u01eb\u01ec\5g\64\2\u01ec"+
		"\u01ed\7$\2\2\u01ed`\3\2\2\2\u01ee\u01ef\7^\2\2\u01ef\u01f0\t\t\2\2\u01f0"+
		"b\3\2\2\2\u01f1\u01f2\t\n\2\2\u01f2d\3\2\2\2\u01f3\u01f4\13\2\2\2\u01f4"+
		"f\3\2\2\2\u01f5\u01f8\5a\61\2\u01f6\u01f8\n\t\2\2\u01f7\u01f5\3\2\2\2"+
		"\u01f7\u01f6\3\2\2\2\u01f8\u01fb\3\2\2\2\u01f9\u01f7\3\2\2\2\u01f9\u01fa"+
		"\3\2\2\2\u01fah\3\2\2\2\u01fb\u01f9\3\2\2\2\u01fc\u01fd\7$\2\2\u01fdj"+
		"\3\2\2\2\27\2s\u0117\u015b\u0179\u018e\u0199\u01a7\u01aa\u01af\u01b2\u01b7"+
		"\u01bd\u01c2\u01c9\u01d0\u01da\u01df\u01e7\u01f7\u01f9\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}