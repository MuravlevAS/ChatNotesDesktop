package ru.sgk.chatnotesdesktop.backend.datastore.sqlite.actions;

import ru.sgk.chatnotesdesktop.backend.datastore.AppDatasource;
import ru.sgk.chatnotesdesktop.backend.datastore.action.DatasourceAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public final class ChatDeleteAction extends DatasourceAction<Void> {
    private final UUID uuid;
    public ChatDeleteAction(AppDatasource datasource, UUID uuid) {
        super(datasource);
        this.uuid = uuid;
    }

    @Override
    public Void doAction() throws SQLException {
        // TODO: 17.04.2024 mark chat deleted without explicitly deleting it. This should be done for future synchronization
        try (Connection connection = datasource().connection();
             PreparedStatement statement = connection.prepareStatement("delete from chat where uuid = ?")) {
            statement.setString(1, this.uuid.toString());
            statement.executeUpdate();
        }
        return null;
    }
}
