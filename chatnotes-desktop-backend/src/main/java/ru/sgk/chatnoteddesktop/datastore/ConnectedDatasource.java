package ru.sgk.chatnoteddesktop.datastore;

import java.sql.Connection;

/**
 * Already connected datasource. It will always give specified connection.
 */
public final class ConnectedDatasource implements AppDatasource {

    private final Connection connection;

    /**
     * ctor
     *
     * @param connection pre created connection.
     */
    public ConnectedDatasource(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection connection() {
        return connection;
    }
}
