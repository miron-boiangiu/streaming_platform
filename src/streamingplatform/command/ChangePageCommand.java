package streamingplatform.command;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import streamingplatform.StreamingPlatform;
import streamingplatform.movie.Movie;
import streamingplatform.page.Page;
import streamingplatform.page.PageFactory;
import streamingplatform.user.User;
import static streamingplatform.StreamingPlatformConstants.SEE_DETAILS_PAGE;
import static streamingplatform.StreamingPlatformConstants.MOVIES_PAGE;
import static streamingplatform.StreamingPlatformConstants.LOGOUT_PAGE;
import static streamingplatform.StreamingPlatformConstants.UNAUTHENTICATED_HOMEPAGE_PAGE;

public class ChangePageCommand extends Command{
    public ChangePageCommand(ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        StreamingPlatform site = StreamingPlatform.getInstance();
        Page currentPage = site.getCurrentPage();

        if(!currentPage.getAccessiblePages().contains(action.getPage())){
            site.addErrorOutputNode();
            return;
        }
        if(action.getPage().equals(SEE_DETAILS_PAGE)){
            boolean foundMovie = false;
            Movie movieToAdd = null;
            for(Movie movie: currentPage.getVisibleMovies()){
                if(movie.getName().equals(action.getMovie())){
                    foundMovie = true;
                    movieToAdd = movie;
                    break;
                }
            }
            if(!foundMovie){
                site.addErrorOutputNode();
            }
            else{
                site.setCurrentPage(PageFactory.create(action.getPage()));
                site.getCurrentPage().getVisibleMovies().add(movieToAdd);
                site.addOutputNode();
            }
            return;
        }

        site.setCurrentPage(PageFactory.create(action.getPage()));
        if(action.getPage().equals(MOVIES_PAGE)){
            site.addOutputNode();
        }
        else if(action.getPage().equals(LOGOUT_PAGE)){
            site.setCurrentPage(PageFactory.create(UNAUTHENTICATED_HOMEPAGE_PAGE));
            site.setCurrentUser(null);
        }

    }
}
