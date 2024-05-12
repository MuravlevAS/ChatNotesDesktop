package ru.sgk.chatnotesdesktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.sgk.chatnotesdesktop.backend.SQLiteChat;
import ru.sgk.chatnotesdesktop.backend.datastore.sqlite.ChatNotesDBPath;
import ru.sgk.chatnotesdesktop.backend.datastore.sqlite.SQLiteDatasource;
import ru.sgk.chatnotesdesktop.backend.datastore.sqlite.actions.FetchChatsAction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

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

        Pane lookup = (Pane)scene.getRoot().lookup("#chats_pane");
//
        SQLiteDatasource datasource = new SQLiteDatasource(new ChatNotesDBPath());

        Collection<SQLiteChat> sqLiteChats = null;
        try {
            sqLiteChats = new FetchChatsAction(datasource).doAction();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (SQLiteChat sqLiteChat : sqLiteChats) {
            lookup.getChildren().add(new DisplayedChat(sqLiteChat).displayableObject());
        }
        stage.show();
    }
}