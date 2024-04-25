package ru.sgk.chatnotesdesktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatNotesApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatNotesApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.hide();
                System.exit(0);
            }
        });
        stage.show();
    }
}