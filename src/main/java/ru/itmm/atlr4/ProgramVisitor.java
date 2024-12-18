// Generated from E:/visualCPU/src/main/antlr4/Program.g4 by ANTLR 4.13.2
package ru.itmm.atlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ProgramParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ProgramVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ProgramParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ProgramParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProgramParser#simpleInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleInstruction(ProgramParser.SimpleInstructionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProgramParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(ProgramParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProgramParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(ProgramParser.ArgContext ctx);
}