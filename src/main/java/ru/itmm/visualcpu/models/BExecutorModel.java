package ru.itmm.visualcpu.models;

import ru.itmm.visualcpu.models.cpu.LittleCpu;

public class BExecutorModel {
    private static ExecutorModel model = new ExecutorModel(new LittleCpu());
    public static ExecutorModel model() { return model; }
}
