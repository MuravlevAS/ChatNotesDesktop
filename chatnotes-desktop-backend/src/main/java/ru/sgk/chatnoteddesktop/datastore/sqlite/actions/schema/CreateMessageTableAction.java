package ru.sgk.chatnoteddesktop.datastore.sqlite.actions.schema;

import ru.sgk.chatnoteddesktop.datastore.AppDatasource;
import ru.sgk.chatnoteddesktop.datastore.action.DatasourceAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class CreateMessageTableAction extends DatasourceAction<Void> {
    public CreateMessageTableAction(AppDatasource datasource) {
        super(datasource);
    }

    @Override
    public Void doAction() throws SQLException {
        try (Connection connection = datasource().connection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    create table if not exists message (
                        uuid text not null,
                        chat_uuid text not null,
                        message_text text not null,
                        modified_datetime integer not null,
                        primary key (uuid, chat_uuid)
                    )
                    """)) {
                statement.executeUpdate();
            }
            try (PreparedStatement statement = connection.prepareStatement("""
                    create index if not EXISTS message_modified_datetime_index on message(modified_datetime)
                    """)) {
                statement.execute();
            }
        }
        return null;
    }
}
