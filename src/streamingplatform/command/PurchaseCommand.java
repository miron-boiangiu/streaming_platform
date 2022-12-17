package streamingplatform.command;

import input.ActionInput;
import streamingplatform.movie.Movie;
import streamingplatform.user.User;
import static streamingplatform.StreamingPlatformConstants.PURCHASE_ACTION;
import static streamingplatform.StreamingPlatformConstants.PREMIUM_USER_ATTRIBUTE;
import static streamingplatform.StreamingPlatformConstants.PURCHASE_MOVIE_FREE_TOKEN_PRICE;
import static streamingplatform.StreamingPlatformConstants.PURCHASE_MOVIE_PRICE;

public class PurchaseCommand extends Command{
    public PurchaseCommand(ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        if(!platform.getCurrentPage().getPossibleActions().contains(PURCHASE_ACTION)){
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
        if(accountType.equals(PREMIUM_USER_ATTRIBUTE) && currentFreeMoviesCount >= PURCHASE_MOVIE_FREE_TOKEN_PRICE){
            currentFreeMoviesCount-=PURCHASE_MOVIE_FREE_TOKEN_PRICE;
        }
        else if(currentTokens >= PURCHASE_MOVIE_PRICE){
            currentTokens-=PURCHASE_MOVIE_PRICE;
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
