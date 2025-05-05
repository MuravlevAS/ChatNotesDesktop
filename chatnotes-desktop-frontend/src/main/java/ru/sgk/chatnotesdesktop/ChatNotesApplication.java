package ru.sgk.chatnotesdesktop;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

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

        BorderPane root = (BorderPane) scene.getRoot();
        VBox right = (VBox) root.getRight();
        AtomicBoolean resizing = new AtomicBoolean(false);

        AtomicLong sum = new AtomicLong(0);
        AtomicInteger counter = new AtomicInteger(0);


        DoubleProperty doubleProperty = right.prefWidthProperty();
        scene.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            if (resizing.get() && event.getX() != 0) {
                long time = System.nanoTime()/1000;
                double sceneX = event.getSceneX();
                doubleProperty.setValue(scene.getWidth() - sceneX);
                long s = sum.addAndGet((System.nanoTime() / 1000) - time);
                int c = counter.incrementAndGet();
                if (c % 10 == 0) {
                    System.out.println(s/c);
                }
            }

        });
        right.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            if (resizing.get() || event.getX() < 5) {
                right.setCursor(Cursor.H_RESIZE);
            } else {
                right.setCursor(Cursor.DEFAULT);
            }
        });

        right.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getX() < 5) {
                resizing.set(true);
//                System.out.println("resizing: " + resizing.get());
            }
        });
        right.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            if (resizing.get() && event.getButton() == MouseButton.PRIMARY) {
                resizing.set(false);
//                right.setCursor(Cursor.DEFAULT);
//                System.out.println("resizing: " + resizing.get());
                long s = sum.getAndSet(0);
                int c = counter.getAndSet(0);
//                System.out.println("total dragged events: "+ c);
            }
        });

        Pane lookup = (Pane) scene.getRoot().lookup("#chats_pane");
//
//        SQLiteDatasource datasource = new SQLiteDatasource(new ChatNotesDBPath());
//
        Collection<SQLiteChat> sqLiteChats = new FetchChatsAction(datasource).doAction();
        for (SQLiteChat sqLiteChat : sqLiteChats) {
            lookup.getChildren().add(new DisplayedChat(new CachedChat<>(sqLiteChat)).displayableObject());
        }
        stage.show();
    }
}