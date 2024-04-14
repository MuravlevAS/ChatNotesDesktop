package ru.sgk.chatnoteddesktop.datastore.action;

import ru.sgk.chatnoteddesktop.datastore.AppDatasource;

import java.util.List;
import java.util.function.Consumer;

public class DatasourceActionChain extends DatasourceAction<Void> {
    private final Iterable<Consumer<AppDatasource>> actions;

    protected DatasourceActionChain(AppDatasource datasource, Consumer<AppDatasource>... actions) {
        super(datasource);
        this.actions = List.of(actions);
    }
    protected DatasourceActionChain(AppDatasource datasource, Iterable<Consumer<AppDatasource>> actions) {
        super(datasource);
        this.actions = actions;
    }

    @Override
    public Void doAction() {
        for (Consumer<AppDatasource> action : actions) {
            action.accept(datasource());
        }
        return null;
    }
}
