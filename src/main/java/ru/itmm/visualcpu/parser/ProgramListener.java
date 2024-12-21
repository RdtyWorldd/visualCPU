package ru.itmm.visualcpu.parser;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.itmm.atlr4.ProgramBaseListener;
import ru.itmm.atlr4.ProgramParser;
import ru.itmm.visualcpu.models.BProgramModel;
import ru.itmm.visualcpu.models.ProgramModel;
import ru.itmm.visualcpu.models.commands.Command;
import ru.itmm.visualcpu.models.commands.Instruction;


import java.util.ArrayList;
import java.util.List;

@Component("program_listener")
@Scope("prototype")
public class ProgramListener extends ProgramBaseListener {
    @Autowired
    private ProgramModel program;
    @Override
    public void enterProgram(ProgramParser.ProgramContext ctx) {
        program.reset();
        super.enterProgram(ctx);
    }

    @Override
    public void enterSimpleInstruction(ProgramParser.SimpleInstructionContext ctx) {
        List<Integer> args = new ArrayList<>();
        ctx.args().arg().forEach(arg -> {
            Integer value = Integer.valueOf(arg.VALID_ARG().toString());
            args.add(value);
        });
        Instruction inst = Instruction.valueOf(ctx.NAME().toString().toUpperCase());
        program.addCommand(new Command(inst, args.get(0), args.get(1)));
    }
}
