package ru.itmm.visualcpu.models.memory;

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
