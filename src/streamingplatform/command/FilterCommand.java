package streamingplatform.command;

import input.ActionInput;
import streamingplatform.movie.Movie;

import java.util.ArrayList;
import java.util.Comparator;
import static streamingplatform.StreamingPlatformConstants.FILTER_ACTION;
import static streamingplatform.StreamingPlatformConstants.INCREASING_FILTER_OPTION;

public final class FilterCommand extends Command {
    public FilterCommand(final ActionInput action) {
        super(action);
    }
    @Override
    public void execute() {
        if (!platform.getCurrentPage().getPossibleActions().contains(FILTER_ACTION)) {
            platform.addErrorOutputNode();
            return;
        }
        ArrayList<Movie> allMovies = platform.getCurrentPage().getAllAccessibleMovies();
        ArrayList<Movie> visibleMovies = platform.getCurrentPage().getVisibleMovies();
        visibleMovies.clear();
        visibleMovies.addAll(allMovies);
        filterMoviesByContains(visibleMovies);
        sortMovies(visibleMovies);
        platform.addOutputNode();
        hasSucceeded = true;
    }
    private void sortMovies(final ArrayList<Movie> movies) {
        if (action.getFilters().getSort() == null) {
            return;
        }
        String rating = action.getFilters().getSort().getRating();
        String duration = action.getFilters().getSort().getDuration();

        class SortMoviesComparator implements Comparator<Movie> {
            public int compare(final Movie a, final Movie b) {
                if (rating != null && duration != null) {
                    if (!(a.getDuration() == b.getDuration())) {
                        if (duration.equals(INCREASING_FILTER_OPTION)) {
                            return a.getDuration() - b.getDuration();
                        } else {
                            return b.getDuration() - a.getDuration();
                        }
                    } else {
                        if (rating.equals(INCREASING_FILTER_OPTION)) {
                            return a.getRating().compareTo(b.getRating());
                        } else {
                            return b.getRating().compareTo(a.getRating());
                        }
                    }
                } else if (rating == null && duration == null) {
                    throw new IllegalArgumentException();
                } else if (rating == null) {
                    if (duration.equals(INCREASING_FILTER_OPTION)) {
                        return a.getDuration() - b.getDuration();
                    } else {
                        return b.getDuration() - a.getDuration();
                    }
                } else {
                    if (rating.equals(INCREASING_FILTER_OPTION)) {
                        return a.getRating().compareTo(b.getRating());
                    } else {
                        return b.getRating().compareTo(a.getRating());
                    }
                }
            }
        }

        movies.sort(new SortMoviesComparator());
    }



    private void filterMoviesByContains(final ArrayList<Movie> movies) {
        if (action.getFilters().getContains() == null) {
            return;
        }
        ArrayList<String> actors = action.getFilters().getContains().getActors();
        ArrayList<String> genres = action.getFilters().getContains().getGenre();
        ArrayList<Movie> moviesCopy = new ArrayList<>(movies);

        for (Movie movie: moviesCopy) {
            if (actors != null) {
                boolean hasAllActors = true;
                for (String actor : actors) {
                    if (!movie.getActors().contains(actor)) {
                        hasAllActors = false;
                        break;
                    }
                }
                if (!hasAllActors) {
                    movies.remove(movie);
                    continue;
                }
            }
            if (genres != null) {
                boolean hasAllGenres = true;
                for (String genre : genres) {
                    if (!movie.getGenres().contains(genre)) {
                        hasAllGenres = false;
                        break;
                    }
                }
                if (!hasAllGenres) {
                    movies.remove(movie);
                }
            }
        }
    }
}


