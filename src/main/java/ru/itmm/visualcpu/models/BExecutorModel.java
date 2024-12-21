package ru.itmm.visualcpu.models;

import ru.itmm.visualcpu.models.cpu.LittleCpu;
import ru.itmm.visualcpu.models.memory.BMemory;

public class BExecutorModel {
    private static ExecutorModel model = new ExecutorModel(new LittleCpu(BMemory.memory()));
    public static ExecutorModel model() { return model; }
}
