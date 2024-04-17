package ru.sgk.chatnoteddesktop.datastore.sqlite.actions;

import ru.sgk.chatnoteddesktop.datastore.AppDatasource;
import ru.sgk.chatnoteddesktop.datastore.action.DatasourceAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class ChatDeleteAction extends DatasourceAction<Void> {
    private final String uuid;
    public ChatDeleteAction(AppDatasource datasource, String uuid) {
        super(datasource);
        this.uuid = uuid;
    }

    @Override
    public Void doAction() throws SQLException {
        // TODO: 17.04.2024 mark chat deleted without explicitly deleting it. This should be done for future synchronization
        try (Connection connection = datasource().connection();
             PreparedStatement statement = connection.prepareStatement("delete from chat where uuid = ?")) {
            statement.setString(1, this.uuid);
            statement.executeUpdate();
        }
        return null;
    }
}
