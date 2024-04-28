package ru.sgk.chatnotesdesktop.backend.datastore;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Application datasource.
 */
public interface AppDatasource {

    /**
     * gets connection from datasource.
     *
     * @return created connection.
     * @throws SQLException when db error arises
     */
    Connection connection() throws SQLException;
}
