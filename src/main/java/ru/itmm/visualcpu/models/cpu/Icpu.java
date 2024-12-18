package ru.itmm.visualcpu.models.cpu;


import ru.itmm.visualcpu.models.commands.Command;

public interface Icpu {
    void execute(Command command);
}
