package streamingplatform.command;

import input.ActionInput;
import streamingplatform.movie.Movie;
import streamingplatform.user.User;

public class PurchaseCommand extends Command{
    public PurchaseCommand(ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        if(!platform.getCurrentPage().getPossibleActions().contains("purchase")){
            platform.addErrorOutputNode();
            return;
        }
        if(platform.getCurrentPage().getVisibleMovies().size() == 0){
            platform.addErrorOutputNode();
            return;
        }
        Movie currentViewedMovie = platform.getCurrentPage().getVisibleMovies().get(0);
        User currentUser = platform.getCurrentUser();
        int currentTokens = currentUser.getTokensCount();
        int currentFreeMoviesCount = currentUser.getNumFreePremiumMovies();
        String accountType = currentUser.getAccountType();

        if(currentUser.getPurchasedMovies().contains(currentViewedMovie)){
            platform.addErrorOutputNode();
            return;
        }
        if(accountType.equals("premium") && currentFreeMoviesCount > 1){
            currentFreeMoviesCount--;
        }
        else if(currentTokens > 2){
            currentTokens-=2;
        }
        else{
            platform.addErrorOutputNode();
            return;
        }

        currentUser.setTokensCount(currentTokens);
        currentUser.setNumFreePremiumMovies(currentFreeMoviesCount);
        currentUser.getPurchasedMovies().add(currentViewedMovie);
        platform.addOutputNode();
        return;
    }
}
