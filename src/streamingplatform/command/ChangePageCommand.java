package streamingplatform.command;

import input.ActionInput;
import streamingplatform.StreamingPlatform;
import streamingplatform.movie.Movie;
import streamingplatform.page.Page;
import streamingplatform.page.PageFactory;

import java.util.ArrayList;

import static streamingplatform.StreamingPlatformConstants.SEE_DETAILS_PAGE;
import static streamingplatform.StreamingPlatformConstants.MOVIES_PAGE;
import static streamingplatform.StreamingPlatformConstants.LOGOUT_PAGE;
import static streamingplatform.StreamingPlatformConstants.UNAUTHENTICATED_HOMEPAGE_PAGE;

public final class ChangePageCommand extends Command {
    private Page previousPage;

    public ChangePageCommand(final ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        StreamingPlatform site = StreamingPlatform.getINSTANCE();
        Page currentPage = site.getCurrentPage();

        if (!currentPage.getAccessiblePages().contains(action.getPage())) {
            site.addErrorOutputNode();
            return;
        }
        if (action.getPage().equals(SEE_DETAILS_PAGE)) {
            boolean foundMovie = false;
            Movie movieToAdd = null;
            for (Movie movie: currentPage.getVisibleMovies()) {
                if (movie.getName().equals(action.getMovie())) {
                    foundMovie = true;
                    movieToAdd = movie;
                    break;
                }
            }
            if (!foundMovie) {
                site.addErrorOutputNode();
            } else {
                ArrayList<Movie> allMovies = platform.getCurrentPage().getAllAccessibleMovies();
                ArrayList<Movie> visibleMovies = platform.getCurrentPage().getVisibleMovies();
                visibleMovies.clear();
                visibleMovies.addAll(allMovies);
                previousPage = site.getCurrentPage();

                site.setCurrentPage(PageFactory.create(action.getPage()));
                site.getCurrentPage().getAllAccessibleMovies().add(movieToAdd);
                site.getCurrentPage().getVisibleMovies().add(movieToAdd);
                site.addOutputNode();
                hasSucceeded = true;
            }
            return;
        }
        ArrayList<Movie> allMovies = platform.getCurrentPage().getAllAccessibleMovies();
        ArrayList<Movie> visibleMovies = platform.getCurrentPage().getVisibleMovies();
        visibleMovies.clear();
        visibleMovies.addAll(allMovies);
        previousPage = site.getCurrentPage();

        site.setCurrentPage(PageFactory.create(action.getPage()));
        if (action.getPage().equals(MOVIES_PAGE)) {
            site.addOutputNode();
        } else if (action.getPage().equals(LOGOUT_PAGE)) {
            site.setCurrentPage(PageFactory.create(UNAUTHENTICATED_HOMEPAGE_PAGE));
            site.setCurrentUser(null);
        }
        hasSucceeded = true;
    }

    @Override
    public void undo() {
        if(hasSucceeded)
            platform.setCurrentPage(previousPage);
    }
}
