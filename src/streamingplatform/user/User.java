package streamingplatform.user;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.UserInput;
import lombok.Getter;
import lombok.Setter;
import streamingplatform.movie.Movie;

import java.util.ArrayList;

@Getter
@Setter
public class User {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private int balance;
    private int tokensCount = 0;
    private int numFreePremiumMovies = 15;
    private ArrayList<Movie> purchasedMovies =  new ArrayList<Movie>();
    private ArrayList<Movie> watchedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> likedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> ratedMovies = new ArrayList<Movie>();

    public User(String name, String password, String accountType, String country, int balance){
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;
    }
    public User(UserInput input){
        this.name = input.getCredentials().getName();
        this.password = input.getCredentials().getPassword();
        this.accountType = input.getCredentials().getAccountType();
        this.country = input.getCredentials().getCountry();
        this.balance = input.getCredentials().getBalance();
    }

    public void addOutput(ObjectNode outputNode){
        ObjectNode credentialsNode = outputNode.putObject("credentials");
        credentialsNode.put("name", name);
        credentialsNode.put("password", password);
        credentialsNode.put("accountType", accountType);
        credentialsNode.put("country", country);
        credentialsNode.put("balance", String.valueOf(balance));
        outputNode.put("tokensCount", tokensCount);
        outputNode.put("numFreePremiumMovies", numFreePremiumMovies);
        ArrayNode purchasedMovies = outputNode.putArray("purchasedMovies");
        ArrayNode watchedMovies = outputNode.putArray("watchedMovies");
        ArrayNode likedMovies = outputNode.putArray("likedMovies");
        ArrayNode ratedMovies = outputNode.putArray("ratedMovies");
    }
}
