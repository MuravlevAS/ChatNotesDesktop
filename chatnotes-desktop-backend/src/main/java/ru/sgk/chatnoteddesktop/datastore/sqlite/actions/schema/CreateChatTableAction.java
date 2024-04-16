package ru.sgk.chatnoteddesktop.datastore.sqlite.actions.schema;

import ru.sgk.chatnoteddesktop.datastore.AppDatasource;
import ru.sgk.chatnoteddesktop.datastore.action.DatasourceAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CreateChatTableAction extends DatasourceAction<Void> {
    public CreateChatTableAction(AppDatasource datasource) {
        super(datasource);
    }

    @Override
    public Void doAction() throws SQLException {
        try (Connection connection = datasource().connection();
             PreparedStatement statement = connection.prepareStatement("""
                     create table if not exists chat (
                         uuid text primary key,
                         title text,
                         description text,
                         icon text
                     );
                     """)) {

            statement.executeUpdate();
        }
        return null;
    }
}
