package ru.sgk.chatnoteddesktop.datastore.sqlite.actions.schema;

import ru.sgk.chatnoteddesktop.datastore.AppDatasource;
import ru.sgk.chatnoteddesktop.datastore.action.DatasourceAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class CreateSyncTableAction extends DatasourceAction<Void> {
    public CreateSyncTableAction(AppDatasource datasource) {
        super(datasource);
    }

    @Override
    public Void doAction() throws SQLException {
        try (Connection connection = datasource().connection();
             PreparedStatement statement = connection.prepareStatement("""
                     create table if not exists sync (
                        sync_id integer primary key autoincrement,
                        sync_datetime integer
                    )
                    """)) {

            statement.executeUpdate();
        }
        return null;
    }
}
