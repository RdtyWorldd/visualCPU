package ru.itmm.visualcpu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class App extends Application {

        private static ApplicationContext context;

        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
            fxmlLoader.setController(context.getBean("main_controller"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            context = new AnnotationConfigApplicationContext(SpringAppContext.class);
            launch();
        }

        public static ApplicationContext getContext() {
            return context;
        }
}
