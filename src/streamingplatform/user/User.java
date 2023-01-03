package streamingplatform.user;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.UserInput;
import lombok.Getter;
import lombok.Setter;
import streamingplatform.movie.Movie;

import java.util.ArrayList;

import static streamingplatform.StreamingPlatformConstants.ACCOUNT_TYPE_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.BALANCE_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.COUNTRY_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.CREDENTIALS_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.FREE_PREMIUM_MOVIES_COUNT_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.INITIAL_FREE_MOVIES_COUNT;
import static streamingplatform.StreamingPlatformConstants.LIKED_MOVIES_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.PASSWORD_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.PURCHASED_MOVIES_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.RATED_MOVIES_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.TOKENS_COUNT_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.USERNAME_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.WATCHED_MOVIES_PROPERTY_NAME;

/**
 * User objects contain information about a specific user: his name,
 * his password, etc.
 */
@Getter
@Setter
public final class User {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private int balance;
    private int tokensCount = 0;
    private int numFreePremiumMovies = INITIAL_FREE_MOVIES_COUNT;
    private ArrayList<Movie> purchasedMovies =  new ArrayList<Movie>();
    private ArrayList<Movie> watchedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> likedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> ratedMovies = new ArrayList<Movie>();

    private ArrayList<Notification> notifications = new ArrayList<Notification>();

    public User(final String name, final String password,
                final String accountType, final String country, final int balance) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;
    }
    public User(final UserInput input) {
        this.name = input.getCredentials().getName();
        this.password = input.getCredentials().getPassword();
        this.accountType = input.getCredentials().getAccountType();
        this.country = input.getCredentials().getCountry();
        this.balance = input.getCredentials().getBalance();
    }

    /**
     * Adds user information properties to the given node.
     * @param outputNode The node to add the property to.
     */
    public void addOutput(final ObjectNode outputNode) {
        ObjectNode credentialsNode = outputNode.putObject(CREDENTIALS_PROPERTY_NAME);
        credentialsNode.put(USERNAME_PROPERTY_NAME, name);
        credentialsNode.put(PASSWORD_PROPERTY_NAME, password);
        credentialsNode.put(ACCOUNT_TYPE_PROPERTY_NAME, accountType);
        credentialsNode.put(COUNTRY_PROPERTY_NAME, country);
        credentialsNode.put(BALANCE_PROPERTY_NAME, String.valueOf(balance));
        outputNode.put(TOKENS_COUNT_PROPERTY_NAME, tokensCount);
        outputNode.put(FREE_PREMIUM_MOVIES_COUNT_PROPERTY_NAME, numFreePremiumMovies);
        ArrayNode purchasedMoviesNode = outputNode.putArray(PURCHASED_MOVIES_PROPERTY_NAME);
        ArrayNode watchedMoviesNode = outputNode.putArray(WATCHED_MOVIES_PROPERTY_NAME);
        ArrayNode likedMoviesNode = outputNode.putArray(LIKED_MOVIES_PROPERTY_NAME);
        ArrayNode ratedMoviesNode = outputNode.putArray(RATED_MOVIES_PROPERTY_NAME);
        // TODO: Add notifications support.
        ArrayNode notifications = outputNode.putArray("notifications");

        for (Movie movie: purchasedMovies) {
            movie.addOutput(purchasedMoviesNode);
        }
        for (Movie movie: watchedMovies) {
            movie.addOutput(watchedMoviesNode);
        }
        for (Movie movie: likedMovies) {
            movie.addOutput(likedMoviesNode);
        }
        for (Movie movie: ratedMovies) {
            movie.addOutput(ratedMoviesNode);
        }
    }
}
