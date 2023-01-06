package streamingplatform.command;

import input.ActionInput;

public final class DatabaseDeleteCommand extends Command {
    public DatabaseDeleteCommand(final ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        platform.getMovieDatabase().removeEntry(action.getDeletedMovie());
    }
}
