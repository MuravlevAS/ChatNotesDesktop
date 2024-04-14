package ru.sgk.chatnoteddesktop.datastore.sqlite.actions.schema;

import ru.sgk.chatnoteddesktop.datastore.AppDatasource;
import ru.sgk.chatnoteddesktop.datastore.ConnectedDatasource;
import ru.sgk.chatnoteddesktop.datastore.action.DatasourceAction;
import ru.sgk.chatnoteddesktop.datastore.NotClosableConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Action that initializes schema.
 */
public class InitSchemaAction extends DatasourceAction<Void> {
    public InitSchemaAction(AppDatasource datasource) {
        super(datasource);
    }

    @Override
    public Void doAction() throws SQLException {

        try (Connection connection = datasource().connection()) {
            connection.setAutoCommit(false);

            ConnectedDatasource connectedDatasource = new ConnectedDatasource(new NotClosableConnection(connection));
            new CreateChatTableAction(connectedDatasource).doAction();
            new CreateMessageTableAction(connectedDatasource).doAction();

            connection.commit();
            // TODO: 14.04.2024 Connect liquibase here for managing versions.
        }
        return null;
    }
}
