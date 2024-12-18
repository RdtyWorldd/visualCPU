package ru.itmm.visualcpu.models.memory;

public class BMemory {
    private final static Memory memory = new Memory(4, 1024);
    public static Memory memory() { return memory; }
}
