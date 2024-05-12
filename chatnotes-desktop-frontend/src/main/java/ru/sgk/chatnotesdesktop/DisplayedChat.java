package ru.sgk.chatnotesdesktop;

import javafx.scene.layout.Pane;
import ru.sgk.chatnotesdesktop.backend.Chat;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public class DisplayedChat implements Chat<DisplayedMessage>, Displayable<Pane> {
    private final Chat<?> origin;

    public DisplayedChat(Chat<?> origin) {
        this.origin = origin;
    }

    @Override
    public String title() {
        return origin.title();
    }

    @Override
    public Chat<DisplayedMessage> withTitle(String title) {
        return new DisplayedChat(origin.withTitle(title));
    }

    @Override
    public String description() {
        return origin.description();
    }

    @Override
    public Chat<DisplayedMessage> withDescription(String description) {
        return new DisplayedChat(origin.withDescription(description));
    }

    @Override
    public LocalDateTime modifiedDatetime() {
        return origin.modifiedDatetime();
    }

    @Override
    public Collection<DisplayedMessage> messages() {
        // TODO: 01.05.2024 get messages from origin. Map them to DisplayedMessages.
        return Collections.emptyList();
    }

    @Override
    public void sendMessage(String text) {
        origin.sendMessage(text);
    }

    @Override
    public Pane displayableObject() {
        // TODO: 12.05.2024 create displayable object. Object should have id like 'chat_[chatId]'

        return null;
    }
}
