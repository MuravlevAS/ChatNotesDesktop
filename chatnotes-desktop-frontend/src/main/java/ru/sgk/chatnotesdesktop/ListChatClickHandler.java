package ru.sgk.chatnotesdesktop;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sgk.chatnotesdesktop.backend.Chat;

public class ListChatClickHandler implements EventHandler<MouseEvent> {
    private static final Logger log = LogManager.getLogger(ListChatClickHandler.class);
    private final Chat<DisplayedMessage> chat;
    private final Node node;

    public ListChatClickHandler(Chat<DisplayedMessage> chat, Node node) {
        this.chat = chat;
        this.node = node;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            log.info(event.getSource());
            BorderPane activeChatPane = (BorderPane) node.getScene().lookup("#active_chat");
            activeChatPane.setCenter(new ActiveChat(this.chat.messages()).displayableObject());
        }
    }
}
