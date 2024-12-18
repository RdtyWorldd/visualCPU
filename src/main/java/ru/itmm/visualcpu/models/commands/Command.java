package ru.itmm.visualcpu.models.commands;

public class Command {
    Instruction instruction;
    int[] args;

    public Command(Instruction inst, int adr1, int adr2) {
        instruction = inst;
        args = new int[]{adr1, adr2};
    }

    public Command(Instruction inst){
        instruction = inst;
    }
    public Instruction getInstruction() {
        return instruction;
    }

    public int[] getArgs() {
        return args;
    }
}
