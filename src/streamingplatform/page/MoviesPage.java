package streamingplatform.page;

import streamingplatform.StreamingPlatform;
import streamingplatform.database.Database;
import streamingplatform.movie.Movie;
import streamingplatform.user.User;

import java.util.List;

public class MoviesPage extends AuthenticatedPage{
    public MoviesPage(){
        super();
        this.possibleActions.addAll(List.of("search", "filter"));
        this.accessiblePages.add("see details");

        StreamingPlatform site = StreamingPlatform.getInstance();
        Database<Movie> movieDatabase = site.getMovieDatabase();
        User currentUser = site.getCurrentUser();
        for(Movie movie: movieDatabase.getEntries()){
            if(!movie.getCountriesBanned().contains(currentUser.getCountry())){
                allAccessibleMovies.add(movie);
            }
        }
        visibleMovies.addAll(allAccessibleMovies);
    }
}
