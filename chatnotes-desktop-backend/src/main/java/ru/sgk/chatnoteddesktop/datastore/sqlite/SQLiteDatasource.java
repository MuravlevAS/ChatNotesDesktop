package ru.sgk.chatnoteddesktop.datastore.sqlite;

import org.cactoos.Scalar;
import ru.sgk.chatnoteddesktop.datastore.AppDatasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatasource implements AppDatasource {

    private final Scalar<String> dbLocation;

    public SQLiteDatasource(String dbLocation) {
        this(() -> dbLocation);
    }

    public SQLiteDatasource(Scalar<String> dbLocation) {
        this.dbLocation = dbLocation;
    }

    @Override
    public Connection connection() throws SQLException {
        try {
            return DriverManager.getConnection("jdbc:sqlite:" + dbLocation.value());
        } catch (SQLException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
