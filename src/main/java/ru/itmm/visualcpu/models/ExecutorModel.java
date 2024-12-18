package ru.itmm.visualcpu.models;

import ru.itmm.visualcpu.controllers.IObserver;
import ru.itmm.visualcpu.models.commands.Command;
import ru.itmm.visualcpu.models.cpu.Icpu;
import ru.itmm.visualcpu.models.memory.Memory;

import java.util.ArrayList;
import java.util.List;

public class ExecutorModel {
    private Icpu cpu;
    private List<IObserver> observers = new ArrayList<>();
    private boolean isDebug = false;
    private Integer currentCommand = 0;
    private Integer programLength = 0;

    public ExecutorModel(Icpu cpu) {
        this.cpu = cpu;
    }

    public void executeAll(ProgramModel programModel) {
        programModel.forEach(command -> {
            cpu.execute(command);
            eventCall();
        });
    }

    public void executeNext(ProgramModel programModel) throws RuntimeException {
        cpu.execute(programModel.getCommandList().get(currentCommand));
        currentCommand++;
        if(currentCommand.equals(programLength)) {
            currentCommand = 0;
            programLength = 0;
            eventCall();
            throw new RuntimeException();
        }
        eventCall();
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public Integer getCurrentCommand() {
        return currentCommand;
    }

    public void setProgramLength(Integer programLength) {
        this.programLength = programLength;
    }

    public void reset() {
        currentCommand = 0;
        programLength = 0;
        eventCall();
    }
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }
    public void removeObservers() {
        currentCommand = 0;
        programLength = 0;
        observers.clear();
    }
    public void eventCall() {
        observers.forEach(x -> x.event(this));
    }

}
