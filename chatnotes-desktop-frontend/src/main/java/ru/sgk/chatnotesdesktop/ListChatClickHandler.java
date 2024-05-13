package ru.sgk.chatnotesdesktop;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListChatClickHandler implements EventHandler<MouseEvent> {
    private static final Logger log = LogManager.getLogger(ListChatClickHandler.class);
    private final DisplayedChat chat;
    private final Node node;

    public ListChatClickHandler(DisplayedChat chat, Node node) {
        this.chat = chat;
        this.node = node;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {

            log.info(event.getSource());
            // TODO: 12.05.2024 add chat event handler that will create chat view with messages.
            //  Pane messagePane = (Pane) chat.getScene().lookup("#messagesPane");
            //  messagePane.
//            node.getScene().lookup("#")
        }
    }
}
