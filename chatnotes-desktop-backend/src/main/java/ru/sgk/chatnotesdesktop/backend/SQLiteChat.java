package ru.sgk.chatnotesdesktop.backend;

import ru.sgk.chatnotesdesktop.backend.datastore.AppDatasource;
import ru.sgk.chatnotesdesktop.backend.datastore.StoredChat;

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

        return null;
        // TODO: 27.04.2024 from sql?
    }
    public Collection<SQLiteMessage> messages(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
        // TODO: 27.04.2024 from sql?
    }


    @Override
    public void sendMessage(String text) {
        // TODO: 27.04.2024 run sql
    }
}
