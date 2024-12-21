package ru.itmm.visualcpu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.itmm.visualcpu.models.ExecutorModel;
import ru.itmm.visualcpu.models.BExecutorModel;
import ru.itmm.visualcpu.models.BProgramModel;
import ru.itmm.visualcpu.models.ProgramModel;
import ru.itmm.visualcpu.models.memory.BMemory;
import ru.itmm.visualcpu.models.memory.Memory;

import java.util.Set;

@Component("memory_controller")
@Scope("singleton")
public class MemoryController implements IObserver<ExecutorModel>{
    @Autowired
    Memory memory = BMemory.memory();
    @Autowired
    ProgramModel program;
    @Autowired
    ExecutorModel executor;

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
