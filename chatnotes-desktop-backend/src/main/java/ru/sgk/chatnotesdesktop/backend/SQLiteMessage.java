package ru.sgk.chatnotesdesktop.backend;

import ru.sgk.chatnotesdesktop.backend.datastore.AppDatasource;

import java.time.LocalDateTime;
import java.util.UUID;

public class SQLiteMessage implements Message<SQLiteChat>, HasId<UUID> {
    private final AppDatasource datasource;
    private final SQLiteChat chat;
    private final UUID messageId;

    public SQLiteMessage(AppDatasource datasource, SQLiteChat chat, UUID messageId) {
        this.datasource = datasource;
        this.chat = chat;
        this.messageId = messageId;
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


}
