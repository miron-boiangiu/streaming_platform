package streamingplatform.command;

import input.ActionInput;
import streamingplatform.movie.Movie;
import streamingplatform.user.User;

public class LikeCommand extends Command{

    public LikeCommand(ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        if(!platform.getCurrentPage().getPossibleActions().contains("like")){
            platform.addErrorOutputNode();
            return;
        }
        if(platform.getCurrentPage().getVisibleMovies().size() == 0){
            platform.addErrorOutputNode();
            return;
        }
        Movie currentViewedMovie = platform.getCurrentPage().getVisibleMovies().get(0);
        User currentUser = platform.getCurrentUser();
        if(!currentUser.getWatchedMovies().contains(currentViewedMovie)){
            platform.addErrorOutputNode();
            return;
        }
        if(currentUser.getLikedMovies().contains(currentViewedMovie)){
            platform.addErrorOutputNode();
            return;
        }
        currentViewedMovie.addLike();
        currentUser.getLikedMovies().add(currentViewedMovie);
        platform.addOutputNode();
        return;
    }
}
