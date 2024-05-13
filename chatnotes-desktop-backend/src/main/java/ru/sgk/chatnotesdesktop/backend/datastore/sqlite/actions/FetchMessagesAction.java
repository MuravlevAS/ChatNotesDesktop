package ru.sgk.chatnotesdesktop.backend.datastore.sqlite.actions;

import ru.sgk.chatnotesdesktop.backend.SQLiteChat;
import ru.sgk.chatnotesdesktop.backend.SQLiteMessage;
import ru.sgk.chatnotesdesktop.backend.datastore.AppDatasource;
import ru.sgk.chatnotesdesktop.backend.datastore.action.DatasourceAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FetchMessagesAction extends DatasourceAction<List<SQLiteMessage>> {
    private final SQLiteChat chat;

    public FetchMessagesAction(AppDatasource datasource, SQLiteChat chat) {
        super(datasource);
        this.chat = chat;
    }

    @Override
    public List<SQLiteMessage> doAction() throws SQLException {
        try (Connection connection = datasource().connection();
             PreparedStatement statement = connection.prepareStatement("""
                     select * from message where chat_uuid = ? order by modified_datetime asc
                     """)) {
            statement.setString(1, chat.id().toString());
            ArrayList<SQLiteMessage> list = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new SQLiteMessage(
                        datasource(),
                        chat,
                        UUID.fromString(resultSet.getString(1)),
                        resultSet.getString(3),
                        LocalDateTime.ofInstant(Instant.ofEpochMilli(resultSet.getLong(4)), ZoneId.systemDefault())
                ));
            }

            return list;
        }
    }
}
