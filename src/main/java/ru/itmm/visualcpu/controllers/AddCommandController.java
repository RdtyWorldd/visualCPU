package ru.itmm.visualcpu.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itmm.visualcpu.models.BProgramModel;
import ru.itmm.visualcpu.models.ProgramModel;
import ru.itmm.visualcpu.models.commands.Command;
import ru.itmm.visualcpu.models.commands.Instruction;

public class AddCommandController {
    //@Autowired
    private ProgramModel program;
    private Instruction inst;
    @FXML
    ComboBox<Instruction> commandsBox;
    @FXML
    TextField argFirst;
    @FXML
    TextField argSecond;

    @FXML
    void initialize() {
        commandsBox.setItems(FXCollections.observableArrayList(Instruction.values()));
        argFirst.setDisable(true);
        argSecond.setDisable(true);
    }

    @FXML
    private void onButtonCancelClick() {
        Stage stage = (Stage) commandsBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onButtonAddClick() {
        Command command;
        if(inst.equals(Instruction.LD) || inst.equals(Instruction.ST)
                || inst.equals(Instruction.INIT) || inst.equals(Instruction.MV))
        {
            try {
                Integer arg1 = Integer.valueOf(argFirst.getText());
                Integer arg2 = Integer.valueOf(argSecond.getText());
                command = new Command(inst, arg1, arg2);
            } catch (NumberFormatException e) {
                Alert allert = new Alert(Alert.AlertType.ERROR, "Arguments isn't valid!", ButtonType.OK);
                allert.show();
                return;
            }
        } else {
            command = new Command(inst);
        }

        program.addCommand(command);
        Stage stage = (Stage) commandsBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void setComboBox() {
        inst = commandsBox.getValue();
        if(inst.equals(Instruction.LD) || inst.equals(Instruction.ST)
                || inst.equals(Instruction.INIT) || inst.equals(Instruction.MV)) {
            argFirst.setDisable(false);
            argSecond.setDisable(false);
        } else {
            argFirst.setDisable(true);
            argSecond.setDisable(true);
        }
    }
}
