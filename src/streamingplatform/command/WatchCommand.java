package streamingplatform.command;

import input.ActionInput;
import streamingplatform.movie.Movie;
import streamingplatform.user.User;
import static streamingplatform.StreamingPlatformConstants.WATCH_ACTION;

public class WatchCommand extends Command{

    public WatchCommand(ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        if(!platform.getCurrentPage().getPossibleActions().contains(WATCH_ACTION)){
            platform.addErrorOutputNode();
            return;
        }
        if(platform.getCurrentPage().getVisibleMovies().size() == 0){
            platform.addErrorOutputNode();
            return;
        }

        Movie currentViewedMovie = platform.getCurrentPage().getVisibleMovies().get(0);
        User currentUser = platform.getCurrentUser();

        if(!currentUser.getPurchasedMovies().contains(currentViewedMovie)){
            platform.addErrorOutputNode();
            return;
        }
        if(!currentUser.getWatchedMovies().contains(currentViewedMovie)){
            currentUser.getWatchedMovies().add(currentViewedMovie);
        }
        platform.addOutputNode();

    }
}
