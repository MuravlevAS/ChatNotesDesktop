package ru.sgk.chatnotesdesktop.backend.datastore;

import ru.sgk.chatnotesdesktop.backend.Chat;
import ru.sgk.chatnotesdesktop.backend.HasId;
import ru.sgk.chatnotesdesktop.backend.Message;

import java.util.UUID;

public interface StoredMessage<C extends Chat<?>> extends Message<C>, HasId<UUID> {
}
