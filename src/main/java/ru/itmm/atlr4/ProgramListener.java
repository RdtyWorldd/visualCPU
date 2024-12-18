// Generated from E:/visualCPU/src/main/antlr4/Program.g4 by ANTLR 4.13.2
package ru.itmm.atlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ProgramParser}.
 */
public interface ProgramListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ProgramParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ProgramParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProgramParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ProgramParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProgramParser#simpleInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSimpleInstruction(ProgramParser.SimpleInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProgramParser#simpleInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSimpleInstruction(ProgramParser.SimpleInstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProgramParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(ProgramParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProgramParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(ProgramParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProgramParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(ProgramParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProgramParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(ProgramParser.ArgContext ctx);
}