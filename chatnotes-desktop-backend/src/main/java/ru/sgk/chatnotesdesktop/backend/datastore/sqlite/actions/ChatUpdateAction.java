package ru.sgk.chatnotesdesktop.backend.datastore.sqlite.actions;

import ru.sgk.chatnotesdesktop.backend.datastore.AppDatasource;
import ru.sgk.chatnotesdesktop.backend.datastore.action.DatasourceAction;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

public class ChatUpdateAction  extends DatasourceAction<Void> {
    private final Req  request;

    public ChatUpdateAction(AppDatasource datasource, Req request) {
        super(datasource);
        this.request = request;
    }

    @Override
    public Void doAction() throws SQLException {

        try (Connection connection = datasource().connection();
             PreparedStatement statement = connection.prepareStatement("""
                     update chat set title = ?, description = ?, icon = ?, modified_datetime = ?
                     where uuid  = ?
                     returning *
                     """)) {
            Date now = new Date(Instant.now().atZone(ZoneId.of("UTC")).toInstant().toEpochMilli());
            statement.setString(1,request.title());
            statement.setString(2,request.description());
            statement.setString(3,request.icon());
            statement.setDate(4, now);
            statement.setString(5, request.uuid().toString());
            statement.executeUpdate();
        }
        return null;
    }

    /**
     * Request
     *
     * @param title
     * @param description
     * @param icon
     */
    public record Req(UUID uuid, String title, String description, String icon) {
    }
}