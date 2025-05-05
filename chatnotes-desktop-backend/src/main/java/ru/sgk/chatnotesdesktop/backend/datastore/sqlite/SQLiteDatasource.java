package ru.sgk.chatnotesdesktop.backend.datastore.sqlite;

import org.cactoos.Text;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;
import ru.sgk.chatnotesdesktop.backend.datastore.AppDatasource;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SQLiteDatasource implements AppDatasource {

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
            Files.createDirectories(Path.of(dbLocation.asString()).getParent());
            return DriverManager.getConnection("jdbc:sqlite:" + dbLocation.asString());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public String toString() {
        return SQLiteDatasource.class.getName() + ' ' + dbLocation.toString();
    }
}
