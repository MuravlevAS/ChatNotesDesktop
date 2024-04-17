package ru.sgk.chatnoteddesktop.datastore.sqlite.actions.schema;

import ru.sgk.chatnoteddesktop.datastore.AppDatasource;
import ru.sgk.chatnoteddesktop.datastore.action.DatasourceAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class CreateChatFtsTableAction extends DatasourceAction<Void> {
    public CreateChatFtsTableAction(AppDatasource datasource) {
        super(datasource);
    }

    @Override
    public Void doAction() throws SQLException {
        try (Connection connection = datasource().connection();
             PreparedStatement statement = connection.prepareStatement("""
                     create virtual table chat_fts using fts5 (
                         uuid,
                         title,
                         description
                     );
                     """)) {

            statement.executeUpdate();
        }
        return null;
    }
}