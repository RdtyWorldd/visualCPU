package ru.itmm.visualcpu.models;

public class BProgramModel {
    private static ProgramModel model = new ProgramModel();
    public static ProgramModel model() { return model; }
}
