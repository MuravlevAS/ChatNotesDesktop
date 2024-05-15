package ru.sgk.chatnotesdesktop.backend.datastore.action;

import ru.sgk.chatnotesdesktop.backend.datastore.AppDatasource;

import java.sql.SQLException;

/**
 * Datasource action
 * @param <R> Type of result that action produce.
 */
public abstract class DatasourceAction<R> {
    private final AppDatasource datasource;

    protected DatasourceAction(AppDatasource datasource) {
        this.datasource = datasource;
    }

    protected final AppDatasource datasource() {
        return datasource;
    }

    public abstract R doAction() throws SQLException;
}
