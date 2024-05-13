package ru.sgk.chatnotesdesktop;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import ru.sgk.chatnotesdesktop.backend.Message;

import java.time.LocalDateTime;

public class DisplayedMessage implements Message<DisplayedChat>, Displayable<Pane> {
    private final DisplayedChat chat;
    private final Message<?> origin;

    public DisplayedMessage(DisplayedChat chat, Message<?> origin) {
        this.chat = chat;
        this.origin = origin;
    }

    @Override
    public DisplayedChat chat() {
        return chat;
    }

    @Override
    public String text() {
        return origin.text();
    }

    @Override
    public Message withText(String text) {
        return new DisplayedMessage(this.chat, origin.withText(text));
    }

    @Override
    public LocalDateTime modifiedDatetime() {
        return origin.modifiedDatetime();
    }

    @Override
    public Pane displayableObject() {
        Pane pane = new Pane();
        pane.setPrefWidth(300);
        pane.setPrefHeight(100);
        Label label = new Label(text());
        pane.getChildren().add(label);
        return pane;
    }
}
