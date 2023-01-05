package streamingplatform.movie;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.MovieInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

import static streamingplatform.StreamingPlatformConstants.ACTORS_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.BANNED_COUNTRIES_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.DURATION_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.GENRES_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.MOVIE_NAME_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.MOVIE_RATING_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.NUMBER_OF_LIKES_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.NUMBER_OF_RATINGS_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.YEAR_PROPERTY_NAME;

/**
 * Movie objects contain information about a specific movie: its name,
 * the year it was filmed in, etc.
 */
@Getter
public final class Movie {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    @Setter
    private Double rating = 0d;
    @Setter
    private int allRatingStars = 0;
    @Setter
    private int numLikes = 0;
    @Setter
    private int numRatings = 0;

    public Movie(final MovieInput movieInput) {
        name = movieInput.getName();
        year = movieInput.getYear();
        duration = movieInput.getDuration();
        genres = movieInput.getGenres();
        actors = movieInput.getActors();
        countriesBanned = movieInput.getCountriesBanned();
    }

    /**
     * Adds movie details properties to the given node.
     * @param output The node to add the property to.
     */
    public void addOutput(final ArrayNode output) {
        ObjectNode outputNode = output.addObject();
        outputNode.put(MOVIE_NAME_PROPERTY_NAME, name);
        outputNode.put(YEAR_PROPERTY_NAME, Integer.toString(year));
        outputNode.put(DURATION_PROPERTY_NAME, duration);
        ArrayNode genresArray = outputNode.putArray(GENRES_PROPERTY_NAME);
        for (String genre: genres) {
            genresArray.add(genre);
        }
        ArrayNode actorsArray = outputNode.putArray(ACTORS_PROPERTY_NAME);
        for (String actor: actors) {
            actorsArray.add(actor);
        }
        ArrayNode countriesBannedArray = outputNode.putArray(BANNED_COUNTRIES_PROPERTY_NAME);
        for (String country: countriesBanned) {
            countriesBannedArray.add(country);
        }
        outputNode.put(NUMBER_OF_LIKES_PROPERTY_NAME, numLikes);
        outputNode.put(MOVIE_RATING_PROPERTY_NAME, rating);
        outputNode.put(NUMBER_OF_RATINGS_PROPERTY_NAME, numRatings);
    }

    /**
     * Changes the rating of the movie.
     * @param newRating The new rating to be taken into consideration.
     */
    public void addRating(final int newRating) {
        allRatingStars += newRating;
        numRatings++;
        this.rating = (double) allRatingStars / (double) numRatings;
    }

    /**
     * Adds a like to the movie.
     */
    public void addLike() {
        this.numLikes++;
    }
}
