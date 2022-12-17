package streamingplatform.movie;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.MovieInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

import static streamingplatform.StreamingPlatformConstants.*;

@Getter
public class Movie {
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

    public Movie(MovieInput movieInput){
        name = movieInput.getName();
        year = movieInput.getYear();
        duration = movieInput.getDuration();
        genres = movieInput.getGenres();
        actors = movieInput.getActors();
        countriesBanned = movieInput.getCountriesBanned();
    }

    public void addOutput(ArrayNode output){
        ObjectNode outputNode = output.addObject();
        outputNode.put(MOVIE_NAME_PROPERTY_NAME, name);
        outputNode.put(YEAR_PROPERTY_NAME, year);
        outputNode.put(DURATION_PROPERTY_NAME, duration);
        ArrayNode genresArray = outputNode.putArray(GENRES_PROPERTY_NAME);
        for(String genre: genres){
            genresArray.add(genre);
        }
        ArrayNode actorsArray = outputNode.putArray(ACTORS_PROPERTY_NAME);
        for(String actor: actors){
            actorsArray.add(actor);
        }
        ArrayNode countriesBannedArray = outputNode.putArray(BANNED_COUNTRIES_PROPERTY_NAME);
        for(String country: countriesBanned){
            countriesBannedArray.add(country);
        }
        outputNode.put(NUMBER_OF_LIKES_PROPERTY_NAME, numLikes);
        outputNode.put(MOVIE_RATING_PROPERTY_NAME, rating);
        outputNode.put(NUMBER_OF_RATINGS_PROPERTY_NAME, numRatings);
    }

    public void addRating(int rating){
        allRatingStars += rating;
        numRatings++;
        this.rating = (double)allRatingStars / (double) numRatings;
    }

    public void addLike(){
        this.numLikes++;
    }
}
