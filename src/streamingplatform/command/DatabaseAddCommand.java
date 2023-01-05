package streamingplatform.command;

import input.ActionInput;
import streamingplatform.movie.Movie;

public class DatabaseAddCommand extends Command{

    public DatabaseAddCommand(final ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        platform.getMovieDatabase().addEntry(new Movie(action.getAddedMovie()));
    }
}
