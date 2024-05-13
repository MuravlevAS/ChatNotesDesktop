package ru.sgk.chatnotesdesktop.backend;

import ru.sgk.chatnotesdesktop.backend.datastore.AppDatasource;
import ru.sgk.chatnotesdesktop.backend.datastore.StoredChat;
import ru.sgk.chatnotesdesktop.backend.datastore.sqlite.actions.FetchMessagesAction;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

public class SQLiteChat implements StoredChat<SQLiteMessage> {
    private final AppDatasource datasource;
    private final UUID id;
    private final String title;
    private final String description;
    private final LocalDateTime modified;

    public SQLiteChat(AppDatasource datasource, UUID id, String title, String description) {
        this(datasource, id, title, description, LocalDateTime.now());
    }

    public SQLiteChat(AppDatasource datasource, UUID id, String title, String description, LocalDateTime modified) {
        this.datasource = datasource;
        this.id = id;
        this.title = title;
        this.description = description;
        this.modified = modified;
    }

    @Override
    public UUID id() {
        return id;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public Chat<SQLiteMessage> withTitle(String title) {
        return new SQLiteChat(datasource, id, title, description);
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public Chat<SQLiteMessage> withDescription(String description) {
        return new SQLiteChat(datasource, id, title, description);
    }

    @Override
    public LocalDateTime modifiedDatetime() {
        return modified;
    }

    @Override
    public Collection<SQLiteMessage> messages() {
        try {
            return new FetchMessagesAction(this.datasource, this).doAction();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<SQLiteMessage> messages(LocalDateTime startDate, LocalDateTime endDate) {
        throw new UnsupportedOperationException();
        // TODO: 27.04.2024 from sql?
    }

    @Override
    public void sendMessage(String text) {
        throw new UnsupportedOperationException();
        // TODO: 27.04.2024 run sql
    }
}
