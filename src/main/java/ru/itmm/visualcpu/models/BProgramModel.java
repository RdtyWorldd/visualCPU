package ru.itmm.visualcpu.models;

import ru.itmm.visualcpu.dao.BDAO;

public class BProgramModel {
    private static ProgramModel model = new ProgramModel();
    public static ProgramModel model() { return model; }
}
