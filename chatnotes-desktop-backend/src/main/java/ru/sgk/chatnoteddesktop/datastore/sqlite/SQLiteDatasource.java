package ru.sgk.chatnoteddesktop.datastore.sqlite;

import org.cactoos.Text;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;
import ru.sgk.chatnoteddesktop.datastore.AppDatasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatasource implements AppDatasource {

    private final UncheckedText dbLocation;

    public SQLiteDatasource(String dbLocation) {
        this(new TextOf(dbLocation));
    }

    public SQLiteDatasource(Text dbLocation) {
        this(new UncheckedText(dbLocation));
    }

    public SQLiteDatasource(UncheckedText dbLocation) {
        this.dbLocation = dbLocation;
    }

    @Override
    public Connection connection() throws SQLException {
        try {
            return DriverManager.getConnection("jdbc:sqlite:" + dbLocation.asString());
        } catch (SQLException e) {
            throw e;
        }
    }
}
