package ru.itmm.visualcpu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.itmm.visualcpu.models.BExecutorModel;
import ru.itmm.visualcpu.models.ExecutorModel;
import ru.itmm.visualcpu.models.memory.BMemory;
import ru.itmm.visualcpu.models.memory.Memory;

@Component("register_controller")
@Scope("singleton")
public class RegisterController implements IObserver<ExecutorModel> {
    @Autowired
    private Memory memory = BMemory.memory();
    @Autowired
    private ExecutorModel executor;
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
