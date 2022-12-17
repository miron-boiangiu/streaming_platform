package streamingplatform.page;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import streamingplatform.movie.Movie;
import java.util.ArrayList;
import static streamingplatform.StreamingPlatformConstants.CURRENT_MOVIES_LIST_PROPERTY_NAME;

public abstract class Page {
    @Getter
    protected String pageName;
    @Getter
    protected final ArrayList<Movie> allAccessibleMovies = new ArrayList<Movie>();
    @Getter
    protected final ArrayList<Movie> visibleMovies = new ArrayList<Movie>();
    @Getter
    protected final ArrayList<String> accessiblePages = new ArrayList<String>();
    @Getter
    protected final ArrayList<String> possibleActions = new ArrayList<String>();

    public void addOutput(ObjectNode outputNode){
        ArrayNode outputArray = outputNode.putArray(CURRENT_MOVIES_LIST_PROPERTY_NAME);
        for(Movie movie: visibleMovies){
            movie.addOutput(outputArray);
        }
    }
}
