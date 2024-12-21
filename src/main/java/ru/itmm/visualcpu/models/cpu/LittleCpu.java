package ru.itmm.visualcpu.models.cpu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.itmm.visualcpu.models.commands.Command;
import ru.itmm.visualcpu.models.memory.BMemory;
import ru.itmm.visualcpu.models.memory.Memory;

@Component("cpu")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LittleCpu implements Icpu {
    private Memory memory;

    @Autowired
    public LittleCpu(Memory memory) {
        this.memory = memory;
    }

    private void ld(int register, int mem_id) {
        memory.getRegisters()[register] = memory.getMemory()[mem_id];
    }

    private void st(int register, int mem_id) {
        memory.getMemory()[mem_id] = memory.getRegisters()[register];
    }

    private void mv(int register1, int register2) {
        memory.getRegisters()[register1] = memory.getRegisters()[register2];
    }

    private void init(int mem_id, int value) {
        memory.getMemory()[mem_id] = value;
    }

    private void print() {
        for(int reg : memory.getRegisters())
            System.out.println(reg);
    }

    private void add() {
        memory.getRegisters()[3] = memory.getRegisters()[0] + memory.getRegisters()[1];
    }

    private void sub() {
        memory.getRegisters()[3] = memory.getRegisters()[0] - memory.getRegisters()[1];
    }

    private void div() {
        memory.getRegisters()[3] = memory.getRegisters()[0] / memory.getRegisters()[1];
    }

    private void mult() {
        memory.getRegisters()[3] = memory.getRegisters()[0] * memory.getRegisters()[1];
    }

    @Override
    public void execute(Command command) {
        int[] args = command.getArgs();
        switch (command.getInstruction()) {
            case LD -> ld(args[0], args[1]);
            case ST -> st(args[0], args[1]);
            case MV -> mv(args[0], args[1]);
            case INIT -> init(args[0], args[1]);
            case ADD -> add();
            case SUB -> sub();
            case DIV -> div();
            case MULT -> mult();
            case PRINT -> print();
        }
    }

}


