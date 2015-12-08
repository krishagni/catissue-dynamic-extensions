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
		YRS_BTWN=9, CURR_DATE=10, MINS_BTWN=11, COUNT=12, SUM=13, MIN=14, MAX=15, 
		AVG=16, DISTINCT=17, LIMIT=18, CROSSTAB=19, OR=20, AND=21, PAND=22, NOT=23, 
		ROUND=24, LP=25, RP=26, MOP=27, SOP=28, UOP=29, BOOL=30, OP=31, INT=32, 
		FLOAT=33, YEAR=34, MONTH=35, DAY=36, DIGIT=37, ID=38, FIELD=39, SLITERAL=40, 
		ESC=41, ARITH_OP=42, ERROR=43, QUOTE=44;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "WS", "SELECT", "WHERE", "NTHCHILD", "BETWEEN", "MTHS_BTWN", 
		"YRS_BTWN", "CURR_DATE", "MINS_BTWN", "COUNT", "SUM", "MIN", "MAX", "AVG", 
		"DISTINCT", "LIMIT", "CROSSTAB", "OR", "AND", "PAND", "NOT", "ROUND", 
		"LP", "RP", "MOP", "SOP", "UOP", "BOOL", "OP", "INT", "FLOAT", "YEAR", 
		"MONTH", "DAY", "DIGIT", "ID", "FIELD", "SLITERAL", "ESC", "ARITH_OP", 
		"ERROR", "SGUTS", "QUOTE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "'as'", null, "'select'", "'where'", "'nthchild'", "'between'", 
		"'months_between'", "'years_between'", "'current_date'", "'minutes_between'", 
		"'count'", "'sum'", "'min'", "'max'", "'avg'", "'distinct'", "'limit'", 
		"'crosstab'", "'or'", "'and'", "'pand'", "'not'", "'round'", "'('", "')'", 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "'\"'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "WS", "SELECT", "WHERE", "NTHCHILD", "BETWEEN", "MTHS_BTWN", 
		"YRS_BTWN", "CURR_DATE", "MINS_BTWN", "COUNT", "SUM", "MIN", "MAX", "AVG", 
		"DISTINCT", "LIMIT", "CROSSTAB", "OR", "AND", "PAND", "NOT", "ROUND", 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2.\u01b4\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\3\2\3\2\3\3\3\3\3\3\3\4\6\4d\n\4\r\4\16\4e\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3"+
		"\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3"+
		"\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\32\3\32\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u0112"+
		"\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\5\35\u0130\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u0145\n\36\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u0150\n\37\3 \3 \3 \3 \3 \3 "+
		"\3 \3 \3 \3 \3 \3 \5 \u015e\n \3!\5!\u0161\n!\3!\6!\u0164\n!\r!\16!\u0165"+
		"\3\"\5\"\u0169\n\"\3\"\6\"\u016c\n\"\r\"\16\"\u016d\3\"\3\"\6\"\u0172"+
		"\n\"\r\"\16\"\u0173\3#\6#\u0177\n#\r#\16#\u0178\3#\3#\3$\6$\u017e\n$\r"+
		"$\16$\u017f\3$\3$\3%\6%\u0185\n%\r%\16%\u0186\3%\3%\3&\3&\3\'\3\'\7\'"+
		"\u018f\n\'\f\'\16\'\u0192\13\'\3(\3(\5(\u0196\n(\3(\3(\3(\3(\7(\u019c"+
		"\n(\f(\16(\u019f\13(\3)\3)\3)\3)\3*\3*\3*\3+\3+\3,\3,\3-\3-\7-\u01ae\n"+
		"-\f-\16-\u01b1\13-\3.\3.\2\2/\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61"+
		"\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y\2[.\3\2\13"+
		"\5\2\13\f\17\17\"\"\4\2>>@@\4\2[[{{\4\2OOoo\4\2FFff\5\2C\\aac|\7\2\62"+
		";AAC\\aac|\4\2$$^^\5\2,-//\61\61\u01cb\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3"+
		"\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2"+
		"\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2[\3\2\2\2\3"+
		"]\3\2\2\2\5_\3\2\2\2\7c\3\2\2\2\ti\3\2\2\2\13p\3\2\2\2\rv\3\2\2\2\17\177"+
		"\3\2\2\2\21\u0087\3\2\2\2\23\u0096\3\2\2\2\25\u00a4\3\2\2\2\27\u00b1\3"+
		"\2\2\2\31\u00c1\3\2\2\2\33\u00c7\3\2\2\2\35\u00cb\3\2\2\2\37\u00cf\3\2"+
		"\2\2!\u00d3\3\2\2\2#\u00d7\3\2\2\2%\u00e0\3\2\2\2\'\u00e6\3\2\2\2)\u00ef"+
		"\3\2\2\2+\u00f2\3\2\2\2-\u00f6\3\2\2\2/\u00fb\3\2\2\2\61\u00ff\3\2\2\2"+
		"\63\u0105\3\2\2\2\65\u0107\3\2\2\2\67\u0111\3\2\2\29\u012f\3\2\2\2;\u0144"+
		"\3\2\2\2=\u014f\3\2\2\2?\u015d\3\2\2\2A\u0160\3\2\2\2C\u0168\3\2\2\2E"+
		"\u0176\3\2\2\2G\u017d\3\2\2\2I\u0184\3\2\2\2K\u018a\3\2\2\2M\u018c\3\2"+
		"\2\2O\u0195\3\2\2\2Q\u01a0\3\2\2\2S\u01a4\3\2\2\2U\u01a7\3\2\2\2W\u01a9"+
		"\3\2\2\2Y\u01af\3\2\2\2[\u01b2\3\2\2\2]^\7.\2\2^\4\3\2\2\2_`\7c\2\2`a"+
		"\7u\2\2a\6\3\2\2\2bd\t\2\2\2cb\3\2\2\2de\3\2\2\2ec\3\2\2\2ef\3\2\2\2f"+
		"g\3\2\2\2gh\b\4\2\2h\b\3\2\2\2ij\7u\2\2jk\7g\2\2kl\7n\2\2lm\7g\2\2mn\7"+
		"e\2\2no\7v\2\2o\n\3\2\2\2pq\7y\2\2qr\7j\2\2rs\7g\2\2st\7t\2\2tu\7g\2\2"+
		"u\f\3\2\2\2vw\7p\2\2wx\7v\2\2xy\7j\2\2yz\7e\2\2z{\7j\2\2{|\7k\2\2|}\7"+
		"n\2\2}~\7f\2\2~\16\3\2\2\2\177\u0080\7d\2\2\u0080\u0081\7g\2\2\u0081\u0082"+
		"\7v\2\2\u0082\u0083\7y\2\2\u0083\u0084\7g\2\2\u0084\u0085\7g\2\2\u0085"+
		"\u0086\7p\2\2\u0086\20\3\2\2\2\u0087\u0088\7o\2\2\u0088\u0089\7q\2\2\u0089"+
		"\u008a\7p\2\2\u008a\u008b\7v\2\2\u008b\u008c\7j\2\2\u008c\u008d\7u\2\2"+
		"\u008d\u008e\7a\2\2\u008e\u008f\7d\2\2\u008f\u0090\7g\2\2\u0090\u0091"+
		"\7v\2\2\u0091\u0092\7y\2\2\u0092\u0093\7g\2\2\u0093\u0094\7g\2\2\u0094"+
		"\u0095\7p\2\2\u0095\22\3\2\2\2\u0096\u0097\7{\2\2\u0097\u0098\7g\2\2\u0098"+
		"\u0099\7c\2\2\u0099\u009a\7t\2\2\u009a\u009b\7u\2\2\u009b\u009c\7a\2\2"+
		"\u009c\u009d\7d\2\2\u009d\u009e\7g\2\2\u009e\u009f\7v\2\2\u009f\u00a0"+
		"\7y\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2\7g\2\2\u00a2\u00a3\7p\2\2\u00a3"+
		"\24\3\2\2\2\u00a4\u00a5\7e\2\2\u00a5\u00a6\7w\2\2\u00a6\u00a7\7t\2\2\u00a7"+
		"\u00a8\7t\2\2\u00a8\u00a9\7g\2\2\u00a9\u00aa\7p\2\2\u00aa\u00ab\7v\2\2"+
		"\u00ab\u00ac\7a\2\2\u00ac\u00ad\7f\2\2\u00ad\u00ae\7c\2\2\u00ae\u00af"+
		"\7v\2\2\u00af\u00b0\7g\2\2\u00b0\26\3\2\2\2\u00b1\u00b2\7o\2\2\u00b2\u00b3"+
		"\7k\2\2\u00b3\u00b4\7p\2\2\u00b4\u00b5\7w\2\2\u00b5\u00b6\7v\2\2\u00b6"+
		"\u00b7\7g\2\2\u00b7\u00b8\7u\2\2\u00b8\u00b9\7a\2\2\u00b9\u00ba\7d\2\2"+
		"\u00ba\u00bb\7g\2\2\u00bb\u00bc\7v\2\2\u00bc\u00bd\7y\2\2\u00bd\u00be"+
		"\7g\2\2\u00be\u00bf\7g\2\2\u00bf\u00c0\7p\2\2\u00c0\30\3\2\2\2\u00c1\u00c2"+
		"\7e\2\2\u00c2\u00c3\7q\2\2\u00c3\u00c4\7w\2\2\u00c4\u00c5\7p\2\2\u00c5"+
		"\u00c6\7v\2\2\u00c6\32\3\2\2\2\u00c7\u00c8\7u\2\2\u00c8\u00c9\7w\2\2\u00c9"+
		"\u00ca\7o\2\2\u00ca\34\3\2\2\2\u00cb\u00cc\7o\2\2\u00cc\u00cd\7k\2\2\u00cd"+
		"\u00ce\7p\2\2\u00ce\36\3\2\2\2\u00cf\u00d0\7o\2\2\u00d0\u00d1\7c\2\2\u00d1"+
		"\u00d2\7z\2\2\u00d2 \3\2\2\2\u00d3\u00d4\7c\2\2\u00d4\u00d5\7x\2\2\u00d5"+
		"\u00d6\7i\2\2\u00d6\"\3\2\2\2\u00d7\u00d8\7f\2\2\u00d8\u00d9\7k\2\2\u00d9"+
		"\u00da\7u\2\2\u00da\u00db\7v\2\2\u00db\u00dc\7k\2\2\u00dc\u00dd\7p\2\2"+
		"\u00dd\u00de\7e\2\2\u00de\u00df\7v\2\2\u00df$\3\2\2\2\u00e0\u00e1\7n\2"+
		"\2\u00e1\u00e2\7k\2\2\u00e2\u00e3\7o\2\2\u00e3\u00e4\7k\2\2\u00e4\u00e5"+
		"\7v\2\2\u00e5&\3\2\2\2\u00e6\u00e7\7e\2\2\u00e7\u00e8\7t\2\2\u00e8\u00e9"+
		"\7q\2\2\u00e9\u00ea\7u\2\2\u00ea\u00eb\7u\2\2\u00eb\u00ec\7v\2\2\u00ec"+
		"\u00ed\7c\2\2\u00ed\u00ee\7d\2\2\u00ee(\3\2\2\2\u00ef\u00f0\7q\2\2\u00f0"+
		"\u00f1\7t\2\2\u00f1*\3\2\2\2\u00f2\u00f3\7c\2\2\u00f3\u00f4\7p\2\2\u00f4"+
		"\u00f5\7f\2\2\u00f5,\3\2\2\2\u00f6\u00f7\7r\2\2\u00f7\u00f8\7c\2\2\u00f8"+
		"\u00f9\7p\2\2\u00f9\u00fa\7f\2\2\u00fa.\3\2\2\2\u00fb\u00fc\7p\2\2\u00fc"+
		"\u00fd\7q\2\2\u00fd\u00fe\7v\2\2\u00fe\60\3\2\2\2\u00ff\u0100\7t\2\2\u0100"+
		"\u0101\7q\2\2\u0101\u0102\7w\2\2\u0102\u0103\7p\2\2\u0103\u0104\7f\2\2"+
		"\u0104\62\3\2\2\2\u0105\u0106\7*\2\2\u0106\64\3\2\2\2\u0107\u0108\7+\2"+
		"\2\u0108\66\3\2\2\2\u0109\u010a\7k\2\2\u010a\u0112\7p\2\2\u010b\u010c"+
		"\7p\2\2\u010c\u010d\7q\2\2\u010d\u010e\7v\2\2\u010e\u010f\7\"\2\2\u010f"+
		"\u0110\7k\2\2\u0110\u0112\7p\2\2\u0111\u0109\3\2\2\2\u0111\u010b\3\2\2"+
		"\2\u01128\3\2\2\2\u0113\u0114\7e\2\2\u0114\u0115\7q\2\2\u0115\u0116\7"+
		"p\2\2\u0116\u0117\7v\2\2\u0117\u0118\7c\2\2\u0118\u0119\7k\2\2\u0119\u011a"+
		"\7p\2\2\u011a\u0130\7u\2\2\u011b\u011c\7u\2\2\u011c\u011d\7v\2\2\u011d"+
		"\u011e\7c\2\2\u011e\u011f\7t\2\2\u011f\u0120\7v\2\2\u0120\u0121\7u\2\2"+
		"\u0121\u0122\7\"\2\2\u0122\u0123\7y\2\2\u0123\u0124\7k\2\2\u0124\u0125"+
		"\7v\2\2\u0125\u0130\7j\2\2\u0126\u0127\7g\2\2\u0127\u0128\7p\2\2\u0128"+
		"\u0129\7f\2\2\u0129\u012a\7u\2\2\u012a\u012b\7\"\2\2\u012b\u012c\7y\2"+
		"\2\u012c\u012d\7k\2\2\u012d\u012e\7v\2\2\u012e\u0130\7j\2\2\u012f\u0113"+
		"\3\2\2\2\u012f\u011b\3\2\2\2\u012f\u0126\3\2\2\2\u0130:\3\2\2\2\u0131"+
		"\u0132\7g\2\2\u0132\u0133\7z\2\2\u0133\u0134\7k\2\2\u0134\u0135\7u\2\2"+
		"\u0135\u0136\7v\2\2\u0136\u0145\7u\2\2\u0137\u0138\7p\2\2\u0138\u0139"+
		"\7q\2\2\u0139\u013a\7v\2\2\u013a\u013b\7\"\2\2\u013b\u013c\7g\2\2\u013c"+
		"\u013d\7z\2\2\u013d\u013e\7k\2\2\u013e\u013f\7u\2\2\u013f\u0140\7v\2\2"+
		"\u0140\u0145\7u\2\2\u0141\u0142\7c\2\2\u0142\u0143\7p\2\2\u0143\u0145"+
		"\7{\2\2\u0144\u0131\3\2\2\2\u0144\u0137\3\2\2\2\u0144\u0141\3\2\2\2\u0145"+
		"<\3\2\2\2\u0146\u0147\7v\2\2\u0147\u0148\7t\2\2\u0148\u0149\7w\2\2\u0149"+
		"\u0150\7g\2\2\u014a\u014b\7h\2\2\u014b\u014c\7c\2\2\u014c\u014d\7n\2\2"+
		"\u014d\u014e\7u\2\2\u014e\u0150\7g\2\2\u014f\u0146\3\2\2\2\u014f\u014a"+
		"\3\2\2\2\u0150>\3\2\2\2\u0151\u015e\t\3\2\2\u0152\u0153\7@\2\2\u0153\u015e"+
		"\7?\2\2\u0154\u0155\7>\2\2\u0155\u015e\7?\2\2\u0156\u015e\7?\2\2\u0157"+
		"\u0158\7#\2\2\u0158\u015e\7?\2\2\u0159\u015a\7n\2\2\u015a\u015b\7k\2\2"+
		"\u015b\u015c\7m\2\2\u015c\u015e\7g\2\2\u015d\u0151\3\2\2\2\u015d\u0152"+
		"\3\2\2\2\u015d\u0154\3\2\2\2\u015d\u0156\3\2\2\2\u015d\u0157\3\2\2\2\u015d"+
		"\u0159\3\2\2\2\u015e@\3\2\2\2\u015f\u0161\7/\2\2\u0160\u015f\3\2\2\2\u0160"+
		"\u0161\3\2\2\2\u0161\u0163\3\2\2\2\u0162\u0164\5K&\2\u0163\u0162\3\2\2"+
		"\2\u0164\u0165\3\2\2\2\u0165\u0163\3\2\2\2\u0165\u0166\3\2\2\2\u0166B"+
		"\3\2\2\2\u0167\u0169\7/\2\2\u0168\u0167\3\2\2\2\u0168\u0169\3\2\2\2\u0169"+
		"\u016b\3\2\2\2\u016a\u016c\5K&\2\u016b\u016a\3\2\2\2\u016c\u016d\3\2\2"+
		"\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0171"+
		"\7\60\2\2\u0170\u0172\5K&\2\u0171\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173"+
		"\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174D\3\2\2\2\u0175\u0177\5K&\2\u0176"+
		"\u0175\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u0176\3\2\2\2\u0178\u0179\3\2"+
		"\2\2\u0179\u017a\3\2\2\2\u017a\u017b\t\4\2\2\u017bF\3\2\2\2\u017c\u017e"+
		"\5K&\2\u017d\u017c\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u017d\3\2\2\2\u017f"+
		"\u0180\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0182\t\5\2\2\u0182H\3\2\2\2"+
		"\u0183\u0185\5K&\2\u0184\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0184"+
		"\3\2\2\2\u0186\u0187\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u0189\t\6\2\2\u0189"+
		"J\3\2\2\2\u018a\u018b\4\62;\2\u018bL\3\2\2\2\u018c\u0190\t\7\2\2\u018d"+
		"\u018f\t\b\2\2\u018e\u018d\3\2\2\2\u018f\u0192\3\2\2\2\u0190\u018e\3\2"+
		"\2\2\u0190\u0191\3\2\2\2\u0191N\3\2\2\2\u0192\u0190\3\2\2\2\u0193\u0196"+
		"\5A!\2\u0194\u0196\5M\'\2\u0195\u0193\3\2\2\2\u0195\u0194\3\2\2\2\u0196"+
		"\u0197\3\2\2\2\u0197\u0198\7\60\2\2\u0198\u019d\5M\'\2\u0199\u019a\7\60"+
		"\2\2\u019a\u019c\5M\'\2\u019b\u0199\3\2\2\2\u019c\u019f\3\2\2\2\u019d"+
		"\u019b\3\2\2\2\u019d\u019e\3\2\2\2\u019eP\3\2\2\2\u019f\u019d\3\2\2\2"+
		"\u01a0\u01a1\7$\2\2\u01a1\u01a2\5Y-\2\u01a2\u01a3\7$\2\2\u01a3R\3\2\2"+
		"\2\u01a4\u01a5\7^\2\2\u01a5\u01a6\t\t\2\2\u01a6T\3\2\2\2\u01a7\u01a8\t"+
		"\n\2\2\u01a8V\3\2\2\2\u01a9\u01aa\13\2\2\2\u01aaX\3\2\2\2\u01ab\u01ae"+
		"\5S*\2\u01ac\u01ae\n\t\2\2\u01ad\u01ab\3\2\2\2\u01ad\u01ac\3\2\2\2\u01ae"+
		"\u01b1\3\2\2\2\u01af\u01ad\3\2\2\2\u01af\u01b0\3\2\2\2\u01b0Z\3\2\2\2"+
		"\u01b1\u01af\3\2\2\2\u01b2\u01b3\7$\2\2\u01b3\\\3\2\2\2\26\2e\u0111\u012f"+
		"\u0144\u014f\u015d\u0160\u0165\u0168\u016d\u0173\u0178\u017f\u0186\u0190"+
		"\u0195\u019d\u01ad\u01af\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}