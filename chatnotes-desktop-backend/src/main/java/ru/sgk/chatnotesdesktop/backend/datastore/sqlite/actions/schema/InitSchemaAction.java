package ru.sgk.chatnotesdesktop.backend.datastore.sqlite.actions.schema;

import liquibase.command.CommandScope;
import liquibase.command.core.UpdateCommandStep;
import liquibase.command.core.helpers.DbUrlConnectionArgumentsCommandStep;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import ru.sgk.chatnotesdesktop.backend.datastore.action.DatasourceAction;
import ru.sgk.chatnotesdesktop.backend.datastore.AppDatasource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Action that initializes schema.
 */
public final class InitSchemaAction extends DatasourceAction<Void> {
    public InitSchemaAction(AppDatasource datasource) {
        super(datasource);
    }

    @Override
    public Void doAction() throws SQLException {
        try (Connection connection = datasource().connection()) {
            CommandScope updateCommand = new CommandScope(UpdateCommandStep.COMMAND_NAME);
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            updateCommand.addArgumentValue(DbUrlConnectionArgumentsCommandStep.DATABASE_ARG, database);
            updateCommand.addArgumentValue(UpdateCommandStep.CHANGELOG_FILE_ARG, "/db/changelog/liquibase-changelog.yaml");
            updateCommand.execute();
        } catch (LiquibaseException e) {
            throw new SQLException(e);
        }
        return null;
    }
}
