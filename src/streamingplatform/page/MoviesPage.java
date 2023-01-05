package streamingplatform.page;

import streamingplatform.StreamingPlatform;
import streamingplatform.database.Database;
import streamingplatform.movie.Movie;
import streamingplatform.user.User;
import static streamingplatform.StreamingPlatformConstants.SEARCH_ACTION;
import static streamingplatform.StreamingPlatformConstants.FILTER_ACTION;
import static streamingplatform.StreamingPlatformConstants.SEE_DETAILS_PAGE;

import java.util.List;

public class MoviesPage extends AuthenticatedPage {
    public MoviesPage() {
        super();
        this.possibleActions.addAll(List.of(SEARCH_ACTION, FILTER_ACTION));
        this.accessiblePages.add(SEE_DETAILS_PAGE);
        this.hasBackOutput = true;
        StreamingPlatform site = StreamingPlatform.getINSTANCE();
        Database<Movie> movieDatabase = site.getMovieDatabase();
        User currentUser = site.getCurrentUser();
        for (Movie movie: movieDatabase.getEntries()) {
            if (!movie.getCountriesBanned().contains(currentUser.getCountry())) {
                allAccessibleMovies.add(movie);
            }
        }
        visibleMovies.addAll(allAccessibleMovies);
    }
}
