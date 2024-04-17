package ru.sgk.chatnoteddesktop.datastore.sqlite.actions;

import ru.sgk.chatnoteddesktop.datastore.AppDatasource;
import ru.sgk.chatnoteddesktop.datastore.action.DatasourceAction;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

public class CreateChatAction extends DatasourceAction<CreateChatAction.Res> {
    private final Req request;
    public CreateChatAction(AppDatasource datasource, Req request) {
        super(datasource);
        this.request = request;
    }

    @Override
    public Res doAction() throws SQLException {

        try (Connection connection = datasource().connection();
             PreparedStatement statement = connection.prepareStatement("""
                     insert into chat (uuid, title, description, icon, modified_datetime) VALUES (?, ?, ?, ?, ?)
                     returning *
                     """)) {
            Date now = new Date(Instant.now().atZone(ZoneId.of("UTC")).toInstant().toEpochMilli());

            UUID uuid = UUID.randomUUID();
            statement.setString(1, uuid.toString());
            statement.setString(2, request.title());
            statement.setString(3, request.description());
            statement.setString(4, request.icon());
            statement.setDate(5, now);
            try (ResultSet resultSet = statement.executeQuery()) {
                return new Res(resultSet);
            }
        }
    }

    /**
     * Request
     * @param title
     * @param description
     * @param icon
     */
    public record Req(String title, String description, String icon) {}

    /**
     * Response
     * @param uuid
     * @param title
     * @param description
     * @param icon
     * @param modifiedDate
     */
    public record Res(String uuid,
                      String title,
                      String description,
                      String icon,
                      Instant modifiedDate) {
        protected Res(ResultSet resultSet) throws SQLException {
                this(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        Instant.ofEpochMilli(resultSet.getDate(4).getTime()));
        }
    }
}
