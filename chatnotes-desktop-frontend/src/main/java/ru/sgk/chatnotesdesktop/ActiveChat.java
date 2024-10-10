package ru.sgk.chatnotesdesktop;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Collection;
import java.util.Comparator;

public class ActiveChat implements Displayable<BorderPane> {
    private final Collection<DisplayedMessage> messages;

    public ActiveChat(Collection<DisplayedMessage> messages) {
        this.messages = messages;
    }

    @Override
    public BorderPane displayableObject() {
        BorderPane activeChatContent = new BorderPane();
        Pane topPane = new Pane();
        topPane.getStyleClass().add("top-bar");
        activeChatContent.setTop(topPane);

        TextField textField = new TextField();
        textField.getStyleClass().add("text-input");
        textField.getStyleClass().add("message-input");

        Pane bottomPane = new Pane(new HBox(textField));
        bottomPane.getStyleClass().add("bottom-bar");
        bottomPane.setPrefHeight(45);
        activeChatContent.setBottom(bottomPane);
        new HBox(textField).getChildren().add(new Label("text"));
        VBox vBox = new VBox(
                this.messages.stream()
                        .sorted(Comparator.comparing(DisplayedMessage::modifiedDatetime))
                        .map(DisplayedMessage::displayableObject)
                        .toArray(Pane[]::new)
        );
        vBox.setAlignment(Pos.BOTTOM_LEFT);
        vBox.getStyleClass().add("messages-vbox");
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setVvalue(scrollPane.getVmax());
        vBox.minHeightProperty().bind(scrollPane.heightProperty());
        activeChatContent.setCenter(scrollPane);
        return activeChatContent;
    }
}
