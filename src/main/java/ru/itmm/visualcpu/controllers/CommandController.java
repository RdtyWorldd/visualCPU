package ru.itmm.visualcpu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import ru.itmm.visualcpu.models.BExecutorModel;
import ru.itmm.visualcpu.models.BProgramModel;
import ru.itmm.visualcpu.models.ExecutorModel;
import ru.itmm.visualcpu.models.ProgramModel;
import ru.itmm.visualcpu.models.commands.Command;
import ru.itmm.visualcpu.models.commands.Instruction;

public class CommandController implements IObserver<ExecutorModel> {
    private ExecutorModel executor = BExecutorModel.model();
    private ProgramModel program = BProgramModel.model();
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
        if(commandPosition == 0)
            return; //можно кидать исключения

        Command tmp = program.getCommandList().get(commandPosition - 1);
        program.getCommandList().set(commandPosition - 1, command);
        program.getCommandList().set(commandPosition, tmp);
        program.eventCall();
    }

    @FXML
    private void onButtonDownClick() {
        if(commandPosition == program.getCommandList().size())
            return; //можно кидать исключения

        Command tmp = program.getCommandList().get(commandPosition + 1);
        program.getCommandList().set(commandPosition + 1, command);
        program.getCommandList().set(commandPosition, tmp);
        program.eventCall();
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
