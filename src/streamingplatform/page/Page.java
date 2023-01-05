package streamingplatform.page;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import streamingplatform.movie.Movie;
import java.util.ArrayList;
import static streamingplatform.StreamingPlatformConstants.CURRENT_MOVIES_LIST_PROPERTY_NAME;

/**
 *  Main class for pages, describing the structure all pages must follow.
 */
public abstract class Page {
    // TODO: Add pageName to all pages.
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
    @Getter
    protected boolean hasBackOutput = false;

    /**
     * Adds visible movies property to the given node.
     * @param outputNode The node to add the property to.
     */
    public final void addOutput(final ObjectNode outputNode) {
        ArrayNode outputArray = outputNode.putArray(CURRENT_MOVIES_LIST_PROPERTY_NAME);
        for (Movie movie: visibleMovies) {
            movie.addOutput(outputArray);
        }
    }
}
