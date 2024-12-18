package ru.itmm.visualcpu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ru.itmm.visualcpu.models.ExecutorModel;
import ru.itmm.visualcpu.models.BExecutorModel;
import ru.itmm.visualcpu.models.BProgramModel;
import ru.itmm.visualcpu.models.ProgramModel;
import ru.itmm.visualcpu.models.memory.BMemory;
import ru.itmm.visualcpu.models.memory.Memory;

import java.util.Set;

public class MemoryController implements IObserver<ExecutorModel>{
    Memory memory = BMemory.memory();
    ProgramModel program = BProgramModel.model();
    ExecutorModel executor = BExecutorModel.model();

    @FXML
    GridPane addPane;

    @FXML
    void initialize()
    {
       executor.addObserver(this);
       for(int i = 0; i < memory.getMemory().length; i++) {
           int pos = i % addPane.getColumnCount();
           String value = i + " : " +memory.getMemory()[i];
           addPane.addColumn(pos, new Label(value));
       }
    }


    @Override
    public void event(ExecutorModel model) {
        Set<Integer> usedMemory = program.getMemoryAdresses();
        for(Integer i : usedMemory) {
            Label label = (Label) addPane.getChildren().get(i);
            String value = i + " : " +memory.getMemory()[i];
            label.setText(value);
        }
    }
}
