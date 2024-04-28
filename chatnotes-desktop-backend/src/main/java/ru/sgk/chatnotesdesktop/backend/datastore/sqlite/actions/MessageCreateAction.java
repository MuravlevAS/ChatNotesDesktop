package ru.sgk.chatnotesdesktop.backend.datastore.sqlite.actions;

import ru.sgk.chatnotesdesktop.backend.datastore.action.DatasourceAction;
import ru.sgk.chatnotesdesktop.backend.datastore.AppDatasource;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class MessageCreateAction extends DatasourceAction<MessageCreateAction.Res> {
    private final Req request;

    public MessageCreateAction(AppDatasource datasource, Req request) {
        super(datasource);
        this.request = request;
    }

    @Override
    public Res doAction() throws SQLException {
        try (Connection connection = datasource().connection();
             PreparedStatement statement = connection.prepareStatement("""
                     insert into message (uuid, chat_uuid, message_text, sent_at, modified_datetime) VALUES (?, ?, ?, ?)
                     returning *
                     """)) {
            UUID uuid = UUID.randomUUID();
            Date now = new Date(Instant.now().atZone(ZoneId.of("UTC")).toInstant().toEpochMilli());
            statement.setString(1, uuid.toString());
            statement.setString(2, request.chatUUID().toString());
            statement.setString(3, request.messageText());
            statement.setDate(4, now);
            statement.setDate(5, now);
            try (ResultSet resultSet = statement.executeQuery()) {
                return new Res(resultSet);
            }
        }
    }

    public record Req(UUID chatUUID, String messageText) {
    }

    public record Res(UUID uuid,
                      UUID chatUUID,
                      String messageText,
                      LocalDateTime modifiedDate) {
        private Res(ResultSet resultSet) throws SQLException {
            this(UUID.fromString(resultSet.getString(1)),
                    UUID.fromString(resultSet.getString(2)),
                    resultSet.getString(3),
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(resultSet.getDate(4).getTime()), ZoneId.of("UTC")));
        }
    }
}
