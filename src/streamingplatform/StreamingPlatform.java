package streamingplatform;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import input.Input;
import input.MovieInput;
import input.UserInput;
import lombok.Getter;
import lombok.Setter;
import streamingplatform.command.CommandFactory;
import streamingplatform.command.CommandParser;
import streamingplatform.database.Database;
import streamingplatform.movie.Movie;
import streamingplatform.page.Page;
import streamingplatform.page.UnauthenticatedHomepage;
import streamingplatform.page.UnauthenticatedPage;
import streamingplatform.user.User;

import java.util.stream.Stream;

public class StreamingPlatform {

    // Lombok makes everything so much easier, it's great!
    @Getter
    private static final StreamingPlatform instance = new StreamingPlatform();
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
    private Database<Movie> movieDatabase;
    @Getter
    @Setter
    private User currentUser;
    private StreamingPlatform(){}
    private CommandParser commandParser;

    public void bootUp(Input inputData, ArrayNode output) {
        this.inputData = inputData;
        this.output = output;
        this.userDatabase = new Database<User>();
        this.movieDatabase = new Database<Movie>();
        this.currentUser = null;
        this.commandParser = new CommandParser();
        this.currentPage = new UnauthenticatedHomepage();

        for(UserInput userInput: inputData.getUsers()){
            userDatabase.addEntry(new User(userInput));
        }

        for(MovieInput movieInput: inputData.getMovies()){
            movieDatabase.addEntry(new Movie(movieInput));
        }

        for(ActionInput actionInput: inputData.getActions()){
            commandParser.addCommand(CommandFactory.create(actionInput));
        }

        commandParser.executeAll();
    }

    public void addErrorOutputNode(){
        ObjectNode newNode = output.addObject();
        newNode.put("error", "Error");
        newNode.putNull("currentUser");
        newNode.putArray("currentMoviesList");
    }

    public void addOutputNode(){
        ObjectNode newNode = output.addObject();
        newNode.putNull("error");
        if(currentUser != null){
            ObjectNode userNode = newNode.putObject("currentUser");
            currentUser.addOutput(userNode);
        }
        else{
            newNode.putNull("currentUser");
        }
        currentPage.addOutput(newNode);
    }
}
