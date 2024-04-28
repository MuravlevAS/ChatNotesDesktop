package ru.sgk.chatnotesdesktop.backend.datastore.sqlite.actions;

import ru.sgk.chatnotesdesktop.backend.SQLiteChat;
import ru.sgk.chatnotesdesktop.backend.datastore.action.DatasourceAction;
import ru.sgk.chatnotesdesktop.backend.datastore.AppDatasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class FetchChatsAction extends DatasourceAction<Collection<SQLiteChat>> {
    public FetchChatsAction(AppDatasource datasource) {
        super(datasource);
    }

    @Override
    public Collection<SQLiteChat> doAction() throws SQLException {
        try (Connection connection = datasource().connection();
             PreparedStatement statement = connection.prepareStatement("""
                     select c.* from chat c join message m on c.uuid = m.chat_uuid
                     group by chat_uuid
                     order by m.modified_datetime desc
                     """)) {
            ArrayList<SQLiteChat> list = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new SQLiteChat(
                        datasource(),
                        UUID.fromString(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        LocalDateTime.ofInstant(Instant.ofEpochMilli(resultSet.getLong(4)), ZoneId.systemDefault())
                ));
            }

            return list;
        }
    }
}
