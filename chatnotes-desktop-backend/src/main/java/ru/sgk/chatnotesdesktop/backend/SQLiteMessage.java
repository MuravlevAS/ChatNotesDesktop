package ru.sgk.chatnotesdesktop.backend;

import ru.sgk.chatnotesdesktop.backend.datastore.AppDatasource;
import ru.sgk.chatnotesdesktop.backend.datastore.StoredMessage;

import java.time.LocalDateTime;
import java.util.UUID;

public class SQLiteMessage implements StoredMessage<SQLiteChat> {
    private final AppDatasource datasource;
    private final SQLiteChat chat;
    private final UUID messageId;
    private final String text;
    private final LocalDateTime modifiedDatetime;

    public SQLiteMessage(AppDatasource datasource, SQLiteChat chat, UUID messageId, String text, LocalDateTime modifiedDatetime) {
        this.datasource = datasource;
        this.chat = chat;
        this.messageId = messageId;
        this.text = text;
        this.modifiedDatetime = modifiedDatetime;
    }

    @Override
    public UUID id() {
        return messageId;
    }

    @Override
    public SQLiteChat chat() {
        return this.chat;
    }

    @Override
    public String text() {
        return text;
    }

    @Override
    public Message withText(String text) {
        throw new UnsupportedOperationException(); // TODO: 13.05.2024
    }

    @Override
    public LocalDateTime modifiedDatetime() {
        return modifiedDatetime;
    }
}
