package ru.itmm.visualcpu.parser;


import ru.itmm.atlr4.ProgramBaseListener;
import ru.itmm.atlr4.ProgramParser;
import ru.itmm.visualcpu.models.BProgramModel;
import ru.itmm.visualcpu.models.ProgramModel;
import ru.itmm.visualcpu.models.commands.Command;
import ru.itmm.visualcpu.models.commands.Instruction;


import java.util.ArrayList;
import java.util.List;

public class ProgramListener extends ProgramBaseListener {
    private ProgramModel program = BProgramModel.model();
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
