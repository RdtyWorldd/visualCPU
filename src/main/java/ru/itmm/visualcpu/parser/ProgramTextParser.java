package ru.itmm.visualcpu.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import ru.itmm.atlr4.ProgramLexer;
import ru.itmm.atlr4.ProgramParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProgramTextParser {
    public void parseProgram(File file) throws IOException {
        String raw = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        ProgramLexer lexer = new ProgramLexer(CharStreams.fromString(raw));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ProgramParser parser = new ProgramParser(tokens);
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();

        ProgramListener listener = new ProgramListener();
        walker.walk(listener, tree);
    }
}
