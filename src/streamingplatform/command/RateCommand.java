package streamingplatform.command;

import input.ActionInput;
import streamingplatform.movie.Movie;
import streamingplatform.user.User;
import static streamingplatform.StreamingPlatformConstants.RATE_ACTION;
import static streamingplatform.StreamingPlatformConstants.MAXIMUM_RATING_VALUE;
import static streamingplatform.StreamingPlatformConstants.MINIMUM_RATING_VALUE;

public final class RateCommand extends Command {
    public RateCommand(final ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        // TODO: Users can rate a movie twice.
        if (!platform.getCurrentPage().getPossibleActions().contains(RATE_ACTION)) {
            platform.addErrorOutputNode();
            return;
        }
        if (platform.getCurrentPage().getVisibleMovies().size() == 0) {
            platform.addErrorOutputNode();
            return;
        }
        Movie currentViewedMovie = platform.getCurrentPage().getVisibleMovies().get(0);
        User currentUser = platform.getCurrentUser();
        if (!currentUser.getWatchedMovies().contains(currentViewedMovie)) {
            platform.addErrorOutputNode();
            return;
        }
        if (currentUser.getRatedMovies().contains(currentViewedMovie)) {
            platform.addErrorOutputNode();
            return;
        }
        if (action.getRate() > MAXIMUM_RATING_VALUE || action.getRate() < MINIMUM_RATING_VALUE) {
            platform.addErrorOutputNode();
            return;
        }
        currentViewedMovie.addRating(action.getRate());
        currentUser.getRatedMovies().add(currentViewedMovie);
        platform.addOutputNode();
    }
}
