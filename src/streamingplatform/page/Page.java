package streamingplatform.page;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import streamingplatform.movie.Movie;
import java.util.ArrayList;

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

    public Page(){
        //TODO: Update the movies list for each page!
    }

    public void addOutput(ObjectNode outputNode){
        ArrayNode outputArray = outputNode.putArray("currentMoviesList");
        for(Movie movie: visibleMovies){
            movie.addOutput(outputArray);
        }
    }
}
