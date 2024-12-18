// Generated from E:/visualCPU/src/main/antlr4/Program.g4 by ANTLR 4.13.2
package ru.itmm.atlr4;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ProgramLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NAME=1, LD=2, ST=3, INIT=4, COMM=5, EOI=6, VALID_ARG=7, WS=8;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"NAME", "LD", "ST", "INIT", "COMM", "EOI", "VALID_ARG", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "','", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "NAME", "LD", "ST", "INIT", "COMM", "EOI", "VALID_ARG", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public ProgramLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Program.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\bP\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000\u0015\b\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001#\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00023\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003=\b\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0005\u0006E\b\u0006"+
		"\n\u0006\f\u0006H\t\u0006\u0001\u0007\u0004\u0007K\b\u0007\u000b\u0007"+
		"\f\u0007L\u0001\u0007\u0001\u0007\u0000\u0000\b\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0001\u0000"+
		"\u0002\u0001\u000009\u0003\u0000\t\n\r\r  Z\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0001\u0014\u0001\u0000\u0000\u0000\u0003"+
		"\"\u0001\u0000\u0000\u0000\u00052\u0001\u0000\u0000\u0000\u0007<\u0001"+
		"\u0000\u0000\u0000\t>\u0001\u0000\u0000\u0000\u000b@\u0001\u0000\u0000"+
		"\u0000\rB\u0001\u0000\u0000\u0000\u000fJ\u0001\u0000\u0000\u0000\u0011"+
		"\u0015\u0003\u0003\u0001\u0000\u0012\u0015\u0003\u0005\u0002\u0000\u0013"+
		"\u0015\u0003\u0007\u0003\u0000\u0014\u0011\u0001\u0000\u0000\u0000\u0014"+
		"\u0012\u0001\u0000\u0000\u0000\u0014\u0013\u0001\u0000\u0000\u0000\u0015"+
		"\u0002\u0001\u0000\u0000\u0000\u0016\u0017\u0005l\u0000\u0000\u0017\u0018"+
		"\u0005o\u0000\u0000\u0018\u0019\u0005a\u0000\u0000\u0019#\u0005d\u0000"+
		"\u0000\u001a\u001b\u0005L\u0000\u0000\u001b\u001c\u0005O\u0000\u0000\u001c"+
		"\u001d\u0005A\u0000\u0000\u001d#\u0005D\u0000\u0000\u001e\u001f\u0005"+
		"L\u0000\u0000\u001f#\u0005D\u0000\u0000 !\u0005l\u0000\u0000!#\u0005d"+
		"\u0000\u0000\"\u0016\u0001\u0000\u0000\u0000\"\u001a\u0001\u0000\u0000"+
		"\u0000\"\u001e\u0001\u0000\u0000\u0000\" \u0001\u0000\u0000\u0000#\u0004"+
		"\u0001\u0000\u0000\u0000$%\u0005s\u0000\u0000%&\u0005t\u0000\u0000&\'"+
		"\u0005o\u0000\u0000\'(\u0005r\u0000\u0000(3\u0005e\u0000\u0000)*\u0005"+
		"S\u0000\u0000*+\u0005T\u0000\u0000+,\u0005O\u0000\u0000,-\u0005R\u0000"+
		"\u0000-3\u0005E\u0000\u0000./\u0005S\u0000\u0000/3\u0005T\u0000\u0000"+
		"01\u0005s\u0000\u000013\u0005t\u0000\u00002$\u0001\u0000\u0000\u00002"+
		")\u0001\u0000\u0000\u00002.\u0001\u0000\u0000\u000020\u0001\u0000\u0000"+
		"\u00003\u0006\u0001\u0000\u0000\u000045\u0005i\u0000\u000056\u0005n\u0000"+
		"\u000067\u0005i\u0000\u00007=\u0005t\u0000\u000089\u0005I\u0000\u0000"+
		"9:\u0005N\u0000\u0000:;\u0005I\u0000\u0000;=\u0005T\u0000\u0000<4\u0001"+
		"\u0000\u0000\u0000<8\u0001\u0000\u0000\u0000=\b\u0001\u0000\u0000\u0000"+
		">?\u0005,\u0000\u0000?\n\u0001\u0000\u0000\u0000@A\u0005;\u0000\u0000"+
		"A\f\u0001\u0000\u0000\u0000BF\u0007\u0000\u0000\u0000CE\u0007\u0000\u0000"+
		"\u0000DC\u0001\u0000\u0000\u0000EH\u0001\u0000\u0000\u0000FD\u0001\u0000"+
		"\u0000\u0000FG\u0001\u0000\u0000\u0000G\u000e\u0001\u0000\u0000\u0000"+
		"HF\u0001\u0000\u0000\u0000IK\u0007\u0001\u0000\u0000JI\u0001\u0000\u0000"+
		"\u0000KL\u0001\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000LM\u0001\u0000"+
		"\u0000\u0000MN\u0001\u0000\u0000\u0000NO\u0006\u0007\u0000\u0000O\u0010"+
		"\u0001\u0000\u0000\u0000\u0007\u0000\u0014\"2<FL\u0001\u0000\u0001\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}