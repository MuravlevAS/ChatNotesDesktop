package ru.sgk.chatnoteddesktop.datastore.sqlite.actions.schema;

import ru.sgk.chatnoteddesktop.datastore.AppDatasource;
import ru.sgk.chatnoteddesktop.datastore.action.DatasourceAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateMessageTableAction extends DatasourceAction<Void> {
    public CreateMessageTableAction(AppDatasource datasource) {
        super(datasource);
    }

    @Override
    public Void doAction() throws SQLException {
        try (Connection connection = datasource().connection();
             PreparedStatement statement = connection.prepareStatement("""
                     create table if not exists message (
                         uuid text,
                         chat_uuid text,
                         message_text text,
                         created_datetime integer,
                         primary key (uuid, chat_uuid)
                    )
                     """)) {

            statement.executeUpdate();
        }
        return null;
    }
}
