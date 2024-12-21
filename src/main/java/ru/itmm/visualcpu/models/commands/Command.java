package ru.itmm.visualcpu.models.commands;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component("command")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Command {
    private int id = -1;
    private Instruction instruction;
    private int[] args;

    public Command() {

    }

    public Command(Instruction inst, int adr1, int adr2) {
        instruction = inst;
        args = new int[]{adr1, adr2};
    }
    public Command(Instruction inst, int adr1, int adr2, int id) {
        instruction = inst;
        args = new int[]{adr1, adr2};
        this.id = id;
    }

    public Command(Instruction inst){
        instruction = inst;
    }
    public Command(Instruction inst, int id){
        instruction = inst;
        this.id = id;
    }



    public Instruction getInstruction() {
        return instruction;
    }

    public int[] getArgs() {
        return args;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
