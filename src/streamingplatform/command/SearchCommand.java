package streamingplatform.command;

import input.ActionInput;
import streamingplatform.movie.Movie;

import java.util.ArrayList;
import static streamingplatform.StreamingPlatformConstants.SEARCH_ACTION;

public final class SearchCommand extends Command {
    public SearchCommand(final ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        if (!platform.getCurrentPage().getPossibleActions().contains(SEARCH_ACTION)) {
            platform.addErrorOutputNode();
            return;
        }

        String startsWith = action.getStartsWith();
        ArrayList<Movie> allMovies = platform.getCurrentPage().getAllAccessibleMovies();
        ArrayList<Movie> visibleMovies = platform.getCurrentPage().getVisibleMovies();
        visibleMovies.clear();

        for (Movie movie: allMovies) {
            if (movie.getName().startsWith(startsWith)) {
                visibleMovies.add(movie);
            }
        }

        platform.addOutputNode();
        hasSucceeded = true;
    }
}
