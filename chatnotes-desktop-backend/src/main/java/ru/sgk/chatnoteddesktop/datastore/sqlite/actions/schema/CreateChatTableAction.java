package ru.sgk.chatnoteddesktop.datastore.sqlite.actions.schema;

import ru.sgk.chatnoteddesktop.datastore.AppDatasource;
import ru.sgk.chatnoteddesktop.datastore.action.DatasourceAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class CreateChatTableAction extends DatasourceAction<Void> {
    public CreateChatTableAction(AppDatasource datasource) {
        super(datasource);
    }

    @Override
    public Void doAction() throws SQLException {
        try (Connection connection = datasource().connection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    create table if not exists chat (
                        uuid text primary key not null,
                        title text not null,
                        description text not null,
                        modified_datetime integer not null,
                        icon text
                    )
                    """)) {
                statement.execute();
            }
            try (PreparedStatement statement = connection.prepareStatement("""
                    create index if not EXISTS chat_modified_datetime_index on chat(modified_datetime)
                    """)) {
                statement.execute();
            }
        }
        return null;
    }
}
