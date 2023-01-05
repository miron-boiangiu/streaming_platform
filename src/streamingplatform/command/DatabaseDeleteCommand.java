package streamingplatform.command;

import input.ActionInput;
import streamingplatform.database.Database;

public class DatabaseDeleteCommand extends Command{
    public DatabaseDeleteCommand(final ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        platform.getMovieDatabase().removeEntry(action.getDeletedMovie());
    }
}
