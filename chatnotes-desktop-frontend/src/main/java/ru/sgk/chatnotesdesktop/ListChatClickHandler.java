package ru.sgk.chatnotesdesktop;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Comparator;

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
            try {
                BorderPane activeChatPane = (BorderPane) node.getScene().lookup("#active_chat");
                FXMLLoader loader = new FXMLLoader(ListChatClickHandler.class.getResource("/ru/sgk/chatnotesdesktop/active-chat.fxml"));
                BorderPane activeChat = loader.load();


                VBox vBox = new VBox();
                this.chat.messages().stream()
                        .sorted(Comparator.comparing(DisplayedMessage::modifiedDatetime))
                        .map(DisplayedMessage::displayableObject)
                        .forEach(vBox.getChildren()::add);
                activeChat.setCenter(vBox);
                activeChatPane.setCenter(activeChat);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            // TODO: 12.05.2024 add chat event handler that will create chat view with messages.
            //  Pane messagePane = (Pane) chat.getScene().lookup("#messagesPane");
            //  messagePane.
//            node.getScene().lookup("#")
        }
    }
}
