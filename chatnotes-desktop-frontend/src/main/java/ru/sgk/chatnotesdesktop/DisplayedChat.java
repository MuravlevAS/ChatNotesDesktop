package ru.sgk.chatnotesdesktop;

import javafx.scene.layout.Pane;
import ru.sgk.chatnotesdesktop.backend.Chat;
import ru.sgk.chatnotesdesktop.backend.HasId;
import ru.sgk.chatnotesdesktop.backend.datastore.StoredChat;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class DisplayedChat implements Chat<DisplayedMessage>, Displayable<Pane>, HasId<UUID> {
    private final Chat<?> origin;
    private final UUID id;

    /**
     * ctor
     *
     * @param origin chat which this will map on.
     * @param id     id for javafx nodes.
     */
    public DisplayedChat(Chat<?> origin, UUID id) {
        this.origin = origin;
        this.id = id;
    }

    /**
     * ctor. Id will be taken from
     *
     * @param origin chat which this will map on.
     */
    public DisplayedChat(StoredChat<?> origin) {
        this(origin, origin.id());
    }

    /**
     * ctor. Id will be taken ether from origin, if it is an instance of StoredChat or randomUUID will be generated.
     *
     * @param origin
     */
    public DisplayedChat(Chat<?> origin) {
        this(origin, origin instanceof StoredChat<?> i ? i.id() : UUID.randomUUID());
    }

    /**
     * id for javafx nodes.
     *
     * @return id
     */
    @Override
    public UUID id() {
        return this.id;
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
