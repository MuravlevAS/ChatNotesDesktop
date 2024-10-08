package ru.sgk.chatnotesdesktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.sgk.chatnotesdesktop.backend.CachedChat;
import ru.sgk.chatnotesdesktop.backend.SQLiteChat;
import ru.sgk.chatnotesdesktop.backend.datastore.AppDatasource;
import ru.sgk.chatnotesdesktop.backend.datastore.sqlite.ChatNotesDBPath;
import ru.sgk.chatnotesdesktop.backend.datastore.sqlite.SQLiteDatasource;
import ru.sgk.chatnotesdesktop.backend.datastore.sqlite.actions.FetchChatsAction;
import ru.sgk.chatnotesdesktop.backend.datastore.sqlite.actions.schema.InitSchemaAction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class ChatNotesApplication extends Application {
    private final AppDatasource datasource = new SQLiteDatasource(new ChatNotesDBPath());

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        new InitSchemaAction(datasource).doAction();

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
        Pane lookup = (Pane) scene.getRoot().lookup("#chats_pane");
//
        Collection<SQLiteChat> sqLiteChats = new FetchChatsAction(datasource).doAction();
        for (SQLiteChat sqLiteChat : sqLiteChats) {
            lookup.getChildren().add(new DisplayedChat(new CachedChat<>(sqLiteChat)).displayableObject());
        }
        stage.show();
    }
}