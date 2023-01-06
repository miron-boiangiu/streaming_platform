package streamingplatform;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import input.Input;
import input.MovieInput;
import input.UserInput;
import lombok.Getter;
import lombok.Setter;
import streamingplatform.command.Command;
import streamingplatform.command.CommandFactory;
import streamingplatform.command.CommandParser;
import streamingplatform.database.Database;
import streamingplatform.database.MovieDatabase;
import streamingplatform.movie.Movie;
import streamingplatform.page.Page;
import streamingplatform.page.UnauthenticatedHomepage;
import static streamingplatform.StreamingPlatformConstants.ERROR_PROPERTY_VALUE;
import static streamingplatform.StreamingPlatformConstants.ERROR_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.CURRENT_MOVIES_LIST_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.CURRENT_USER_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.NOTIFICATION_DEFAULT_RECOMMENDATION;
import static streamingplatform.StreamingPlatformConstants.NOTIFICATION_RECOMMENDATION_MESSAGE;
import static streamingplatform.StreamingPlatformConstants.PREMIUM_USER_ATTRIBUTE;

import streamingplatform.user.Notification;
import streamingplatform.user.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public final class StreamingPlatform {

    // Lombok makes everything so much easier, it's great!
    @Getter
    private static final StreamingPlatform INSTANCE = new StreamingPlatform();
    @Getter
    private ArrayNode output;
    @Getter
    private Input inputData;
    @Getter
    @Setter
    private Page currentPage;
    @Getter
    private Database<User> userDatabase;
    @Getter
    private MovieDatabase movieDatabase;
    @Getter
    @Setter
    private User currentUser;
    @Getter
    private ArrayList<Command> executedCommandsForCurrentUser = new ArrayList<Command>();
    private StreamingPlatform() {

    }
    private CommandParser commandParser;

    /**
     * Initializes the platform and goes through the input.
     * @param inputData The input to be read.
     * @param output The ArrayNode where output data is put,
     *               to be turned the final JSON file.
     */
    public void bootUp(final Input inputData, final ArrayNode output) {
        this.inputData = inputData;
        this.output = output;
        this.userDatabase = new Database<User>();
        this.movieDatabase = new MovieDatabase();
        this.currentUser = null;
        this.commandParser = new CommandParser();
        this.currentPage = new UnauthenticatedHomepage();
        this.executedCommandsForCurrentUser = new ArrayList<>();

        for (UserInput userInput: inputData.getUsers()) {
            userDatabase.addEntry(new User(userInput));
        }

        for (MovieInput movieInput: inputData.getMovies()) {
            movieDatabase.addEntry(new Movie(movieInput));
        }

        for (ActionInput actionInput: inputData.getActions()) {
            commandParser.addCommand(CommandFactory.create(actionInput));
        }

        commandParser.executeAll();

        if (currentUser != null && currentUser.getAccountType().equals(PREMIUM_USER_ATTRIBUTE)) {
            recommendNewMovie();
        }
    }

    /**
     * In case of an error, adds an ObjectNode to the output to mark the error.
     */
    public void addErrorOutputNode() {
        ObjectNode newNode = output.addObject();
        newNode.put(ERROR_PROPERTY_NAME, ERROR_PROPERTY_VALUE);
        newNode.putNull(CURRENT_USER_PROPERTY_NAME);
        newNode.putArray(CURRENT_MOVIES_LIST_PROPERTY_NAME);
    }

    /**
     * In case normal output is required, goes through the current user
     * and the current page and writes all required information.
     */
    public void addOutputNode() {
        ObjectNode newNode = output.addObject();
        newNode.putNull(ERROR_PROPERTY_NAME);
        newNode.putNull(CURRENT_MOVIES_LIST_PROPERTY_NAME);
        if (currentUser != null) {
            ObjectNode userNode = newNode.putObject(CURRENT_USER_PROPERTY_NAME);
            currentUser.addOutput(userNode);
        } else {
            newNode.putNull(CURRENT_USER_PROPERTY_NAME);
        }
        currentPage.addOutput(newNode);
    }

    /**
     * Recommends a new movie to the connected premium user, based on his preferences.
     */
    public void recommendNewMovie() {
        ObjectNode newNode = output.addObject();
        // This default recommendation will only get overwritten if a movie is found.
        String recommendation = NOTIFICATION_DEFAULT_RECOMMENDATION;
        // Firstly, all genres are ordered by their number of likes.

        /**
         * This class is a wrapper for the genres, so they can be ordered by their likes.
         */
        @Getter
        @Setter
        class GenreRecommendation {
                private String genre;
                private Integer numberOfLikes = 0;


            GenreRecommendation(final String genre, final Integer numberOfLikes) {
                this.genre = genre;
                this.numberOfLikes = numberOfLikes;
            }
        }
        class GenreRecommendationComparator implements Comparator<GenreRecommendation> {
            public int compare(final GenreRecommendation a, final GenreRecommendation b) {
                if (!a.getNumberOfLikes().equals(b.getNumberOfLikes())) {
                    return b.getNumberOfLikes() - a.getNumberOfLikes();
                }
                return a.getGenre().compareTo(b.getGenre());
            }
        }

        ArrayList<GenreRecommendation> genres = new ArrayList<>();
        HashMap<String, Integer> genresAndLikes = new HashMap<>();

        for (Movie likedMovie: currentUser.getLikedMovies()) {
            for (String genre: likedMovie.getGenres()) {
                if (genresAndLikes.containsKey(genre)) {
                    genresAndLikes.put(genre, genresAndLikes.get(genre) + 1);
                } else {
                    genresAndLikes.put(genre, 1);
                }
            }
        }

        for (Map.Entry<String, Integer> entry: genresAndLikes.entrySet()) {
            genres.add(new GenreRecommendation(entry.getKey(), entry.getValue()));
        }

        genres.sort(new GenreRecommendationComparator());

        // Next, all genres get their movies sorted by their number of likes.
        for (GenreRecommendation genreRecommendation: genres) {
            ArrayList<Movie> moviesOfCurrentGenre = new ArrayList<>();
            for (Movie movie: StreamingPlatform.getINSTANCE().getMovieDatabase().getEntries()) {
                // Only unbanned, unwatched movies of the specified genre are picked.
                if (movie.getCountriesBanned().contains(currentUser.getCountry())) {
                    continue;
                }
                if (!movie.getGenres().contains(genreRecommendation.getGenre())) {
                    continue;
                }
                if (currentUser.getWatchedMovies().contains(movie)) {
                    continue;
                }
                moviesOfCurrentGenre.add(movie);
            }

            if (moviesOfCurrentGenre.size() == 0) {
                continue;
            }

            // Order the movies and pick the first one to recommend.
            moviesOfCurrentGenre.sort((m1, m2) -> m2.getNumLikes() - m1.getNumLikes());
            recommendation = moviesOfCurrentGenre.get(0).getName();
            /* Since the genres are ordered by their number of likes and only
             * unwatched movies are picked, if at least a movie was found, then
             * surely a movie of that genre will be recommended, so we break if
             * we reach this point.
             */
            break;
        }

        /* Here, the ObjectNode gets added for the JSON output, with either the
         * default notification or a movie's name.
         */
        currentUser.getNotifications().add(new Notification(recommendation,
                NOTIFICATION_RECOMMENDATION_MESSAGE));
        newNode.putNull(ERROR_PROPERTY_NAME);
        newNode.putNull(CURRENT_MOVIES_LIST_PROPERTY_NAME);
        ObjectNode userNode = newNode.putObject(CURRENT_USER_PROPERTY_NAME);
        currentUser.addOutput(userNode);
    }
}
