package ru.sgk.chatnotesdesktop;

import javafx.scene.layout.Pane;
import ru.sgk.chatnotesdesktop.backend.Message;

import java.time.LocalDateTime;

public class DisplayedMessage implements Message<DisplayedChat>, Displayable<Pane> {
    @Override
    public DisplayedChat chat() {
        return null;
    }

    @Override
    public String text() {
        return null;
    }

    @Override
    public Message withText(String text) {
        return null;
    }

    @Override
    public LocalDateTime modifiedDatetime() {
        return null;
    }

    @Override
    public Pane displayableObject() {
        // TODO: 12.05.2024 create displayable object. Object should have id like 'message_[chatId]_[messageId]'
        return null;
    }
}
