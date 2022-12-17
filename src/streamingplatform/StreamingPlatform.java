package streamingplatform;

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
import static streamingplatform.StreamingPlatformConstants.ERROR_PROPERTY_VALUE;
import static streamingplatform.StreamingPlatformConstants.ERROR_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.CURRENT_MOVIES_LIST_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.CURRENT_USER_PROPERTY_NAME;
import streamingplatform.user.User;

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
    private Database<Movie> movieDatabase;
    @Getter
    @Setter
    private User currentUser;
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
        this.movieDatabase = new Database<Movie>();
        this.currentUser = null;
        this.commandParser = new CommandParser();
        this.currentPage = new UnauthenticatedHomepage();

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
        if (currentUser != null) {
            ObjectNode userNode = newNode.putObject(CURRENT_USER_PROPERTY_NAME);
            currentUser.addOutput(userNode);
        } else {
            newNode.putNull(CURRENT_USER_PROPERTY_NAME);
        }
        currentPage.addOutput(newNode);
    }
}
