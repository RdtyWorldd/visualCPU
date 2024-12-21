package ru.itmm.visualcpu.models.memory;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("memory")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Memory {
    int[] registers;
    int[] memory;

    public Memory(int registers_count, int memory_count) {
        registers = new int[registers_count];
        memory = new int[memory_count];
    }

    public int[] getRegisters() {
        return registers;
    }

    public int[] getMemory() {
        return memory;
    }
}
