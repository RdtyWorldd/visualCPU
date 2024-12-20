package ru.itmm.visualcpu.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.itmm.visualcpu.App;
import ru.itmm.visualcpu.SpringAppContext;
import ru.itmm.visualcpu.models.BExecutorModel;
import ru.itmm.visualcpu.models.BProgramModel;
import ru.itmm.visualcpu.models.ExecutorModel;
import ru.itmm.visualcpu.models.ProgramModel;
import ru.itmm.visualcpu.models.commands.Command;
import ru.itmm.visualcpu.parser.ProgramTextParser;

import java.io.File;
import java.io.IOException;

@Component("main_controller")
@Scope("singleton")
public class MainController implements IObserver<ProgramModel> {
    @Autowired
    private ExecutorModel executor;
    @Autowired
    private ProgramModel program;

    @FXML
    private GridPane programPanel;
    @FXML
    private GridPane rightPane;
    @FXML
    private Button fileButton;
    @FXML
    private Button addButton;
    @FXML
    private Button debugButton;
    @FXML
    private Button startButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button resetButton;


    @FXML
    void initialize()
    {
        program.addObserver(this);
        nextButton.setDisable(false);
        stopButton.setDisable(false);
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(
                App.class.getResource("memory-view.fxml"));
        fxmlLoader.setController(App.getContext().getBean("memory_controller"));

        FXMLLoader fxmlLoader1 = new FXMLLoader(
                App.class.getResource("register-view.fxml"));
        fxmlLoader1.setController(App.getContext().getBean("register_controller"));
            Pane pane = fxmlLoader.load();
            rightPane.addRow(1, pane);
            Pane pane1 = fxmlLoader1.load();
            rightPane.addRow(0, pane1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void onButtonFileClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL FILES", "*.*"),
                new FileChooser.ExtensionFilter("ZIP", "*.zip"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("TEXT", "*.txt"),
                new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
        );
        Pane dialogPane = new Pane();
        Scene scene = new Scene(dialogPane);
        File file = fileChooser.showOpenDialog(dialogPane.getScene().getWindow());
        System.out.println(file);
        if(file == null)
            return;

        ProgramTextParser parser = (ProgramTextParser) App.getContext().getBean("program_parser");
        try {
            parser.parseProgram(file);
            program.eventCall();
        } catch (IOException e) {
            //добавить диалоговое окно
            System.err.println(e);
        }
    }

    @FXML
    private void onButtonAddClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                App.class.getResource("addCommand-view.fxml"));
            fxmlLoader.setController(App.getContext().getBean("add_command_controller"));

            Pane dialogPane;
            dialogPane = fxmlLoader.load();
            Scene scene = new Scene(dialogPane);

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);
            dialogStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onButtonDebugClick() {
        if(executor.isDebug())
            executor.setDebug(false);
        else
            executor.setDebug(true);
    }

    @FXML
    private void onButtonStartClick() {
        if(executor.isDebug()){
            startButton.setDisable(true);
            nextButton.setDisable(false);
            stopButton.setDisable(false);
            executor.setProgramLength(program.getCommandCount());
            executor.eventCall();
        }
        else {
            executor.executeAll(program);
            nextButton.setDisable(true);
            stopButton.setDisable(true);
            startButton.setDisable(false);
        }
    }

    @FXML
    private void onButtonNextClick() {
        try {
            executor.executeNext(program);
        } catch (RuntimeException e) {
            nextButton.setDisable(true);
            stopButton.setDisable(true);
            startButton.setDisable(false);
        }
    }

    @FXML
    private void onButtonStopClick() {
        startButton.setDisable(false);
        nextButton.setDisable(true);
        stopButton.setDisable(true);
        executor.reset();
    }

    @FXML
    private void onButtonResetClick() {
        nextButton.setDisable(true);
        stopButton.setDisable(true);
        startButton.setDisable(false);
        executor.removeObservers();
        program.reset();
    }

    @Override
    public void event(ProgramModel model) {
        programPanel.getChildren().clear();
        int i = 0;
        for(Command c : model) {
            //CommandController cm = new CommandController(c, i);
            FXMLLoader fxmlLoader = new FXMLLoader(
                    App.class.getResource("command-view.fxml"));
            fxmlLoader.setController(App.getContext().getBean("command_controller", c, i));
            try {
                Pane pane = fxmlLoader.load();
                programPanel.addColumn(0, pane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
    }
}
