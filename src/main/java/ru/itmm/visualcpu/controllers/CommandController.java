package ru.itmm.visualcpu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itmm.visualcpu.models.BExecutorModel;
import ru.itmm.visualcpu.models.BProgramModel;
import ru.itmm.visualcpu.models.ExecutorModel;
import ru.itmm.visualcpu.models.ProgramModel;
import ru.itmm.visualcpu.models.commands.Command;
import ru.itmm.visualcpu.models.commands.Instruction;

public class CommandController implements IObserver<ExecutorModel> {
    //@Autowired
    private ExecutorModel executor;
    //@Autowired
    private ProgramModel program;

    private int commandPosition;
    private Command command;

    public CommandController(Command command, int commandPosition) {
        this.command = command;
        this.commandPosition = commandPosition;
    }

    @FXML
    Label commandName;
    @FXML
    Label labelArgFirst;
    @FXML
    Label labelArgSecond;

    @FXML
    private void initialize() {
        executor.addObserver(this);

        commandName.setText(command.getInstruction().name());
        if(command.getInstruction().equals(Instruction.LD) || command.getInstruction().equals(Instruction.ST)
            || command.getInstruction().equals(Instruction.INIT) || command.getInstruction().equals(Instruction.MV))
        {
            labelArgFirst.setText(String.valueOf(command.getArgs()[0]));
            labelArgSecond.setText(String.valueOf(command.getArgs()[1]));
        }
    }


    @FXML
    private void onButtonDeleteClick() {
        program.removeCommand(commandPosition);
    }

    @FXML
    private void onButtonUpClick() {
        try {
            program.upCommand(commandPosition);
        }catch (Exception e) {

        }
    }

    @FXML
    private void onButtonDownClick() {
        try {
            program.downCommand(commandPosition);
        }catch (Exception e) {

        }
    }

    @Override
    public void event(ExecutorModel model) {
        if(executor.getCurrentCommand().equals(commandPosition)) {
            commandName.setTextFill(Color.RED);
        } else {
            commandName.setTextFill(Color.BLACK);
        }
    }
}
