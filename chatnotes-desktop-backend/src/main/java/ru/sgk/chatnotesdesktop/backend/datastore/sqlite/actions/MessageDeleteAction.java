package ru.sgk.chatnotesdesktop.backend.datastore.sqlite.actions;

import ru.sgk.chatnotesdesktop.backend.datastore.AppDatasource;
import ru.sgk.chatnotesdesktop.backend.datastore.action.DatasourceAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public final class MessageDeleteAction extends DatasourceAction<Void> {
    private final UUID uuid;
    private final UUID chatUuid;
    public MessageDeleteAction(AppDatasource datasource, UUID uuid, UUID chatUuid) {
        super(datasource);
        this.uuid = uuid;
        this.chatUuid = chatUuid;
    }

    @Override
    public Void doAction() throws SQLException {
        // TODO: 17.04.2024 mark message deleted without explicitly deleting it. This should be done for future synchronization
        try (Connection connection = datasource().connection();
             PreparedStatement statement = connection.prepareStatement("delete from message where uuid = ? and chat_uuid = ?")) {
            statement.setString(1, this.uuid.toString());
            statement.setString(2, this.chatUuid.toString());
            statement.executeUpdate();
        }
        return null;
    }
}
