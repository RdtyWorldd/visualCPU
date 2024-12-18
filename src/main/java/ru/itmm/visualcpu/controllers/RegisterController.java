package ru.itmm.visualcpu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ru.itmm.visualcpu.models.BExecutorModel;
import ru.itmm.visualcpu.models.ExecutorModel;
import ru.itmm.visualcpu.models.memory.BMemory;
import ru.itmm.visualcpu.models.memory.Memory;

public class RegisterController implements IObserver<ExecutorModel> {

    private Memory memory = BMemory.memory();
    private ExecutorModel executor = BExecutorModel.model();
    @FXML
    private GridPane registerPane;

    @FXML
    void initialize() {
        executor.addObserver(this);
        for(int i = 0; i < memory.getRegisters().length; i++) {
            int pos = i % registerPane.getColumnCount();
            String value = i + " : " +memory.getRegisters()[i];
            registerPane.addColumn(pos, new Label(value));
        }
    }
    @Override
    public void event(ExecutorModel model) {
        for(int i = 0; i < memory.getRegisters().length; i++) {
            Label label = (Label) registerPane.getChildren().get(i);
            String value = i + " : " +memory.getRegisters()[i];
            label.setText(value);
        }
    }
}
