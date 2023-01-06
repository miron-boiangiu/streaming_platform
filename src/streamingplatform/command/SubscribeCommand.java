package streamingplatform.command;

import input.ActionInput;
import streamingplatform.movie.Movie;
import streamingplatform.user.User;

import static streamingplatform.StreamingPlatformConstants.SUBSCRIBE_ACTION;

public final class SubscribeCommand extends Command {
    public SubscribeCommand(final ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        if (!platform.getCurrentPage().getPossibleActions().contains(SUBSCRIBE_ACTION)) {
            platform.addErrorOutputNode();
            return;
        }

        Movie currentMovie = platform.getCurrentPage().getVisibleMovies().get(0);
        User currentUser = platform.getCurrentUser();

        if (!currentMovie.getGenres().contains(action.getSubscribedGenre())) {
            platform.addErrorOutputNode();
            return;
        }

        if (!platform.getMovieDatabase().addSubscriber(currentUser, action.getSubscribedGenre())) {
            platform.addErrorOutputNode();
        }
    }
}
