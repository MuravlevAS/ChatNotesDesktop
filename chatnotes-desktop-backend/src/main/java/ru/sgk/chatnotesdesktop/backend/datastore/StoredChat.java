package ru.sgk.chatnotesdesktop.backend.datastore;

import ru.sgk.chatnotesdesktop.backend.Chat;
import ru.sgk.chatnotesdesktop.backend.HasId;
import ru.sgk.chatnotesdesktop.backend.Message;

import java.util.UUID;

public interface StoredChat<M extends Message<?>> extends Chat<M>, HasId<UUID> {
    StoredChat<M> withTitle(String title);

    StoredChat<M> withDescription(String description);
}
