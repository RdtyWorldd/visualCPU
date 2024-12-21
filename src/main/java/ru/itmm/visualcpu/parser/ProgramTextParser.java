package ru.itmm.visualcpu.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.itmm.atlr4.ProgramLexer;
import ru.itmm.atlr4.ProgramParser;
import ru.itmm.visualcpu.App;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Component("program_parser")
@Scope("prototype")
public class ProgramTextParser {
    public void parseProgram(File file) throws IOException {
        String raw = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        ProgramLexer lexer = new ProgramLexer(CharStreams.fromString(raw));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ProgramParser parser = new ProgramParser(tokens);
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();


        ProgramListener listener = (ProgramListener) App.getContext().getBean("program_listener");
        walker.walk(listener, tree);
    }
}
