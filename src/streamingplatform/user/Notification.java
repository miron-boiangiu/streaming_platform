package streamingplatform.user;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static streamingplatform.StreamingPlatformConstants.NOTIFICATION_MESSAGE_PROPERTY_NAME;
import static streamingplatform.StreamingPlatformConstants.NOTIFICATION_MOVIE_PROPERTY_NAME;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notification {
    private String movieName;
    private String message;

    public Notification(String movieName, String message){
        this.movieName = movieName;
        this.message = message;
    }

    public void addOutput(final ArrayNode arrayNode) {
        ObjectNode newNotificationNode = arrayNode.addObject();
        newNotificationNode.put(NOTIFICATION_MOVIE_PROPERTY_NAME, movieName);
        newNotificationNode.put(NOTIFICATION_MESSAGE_PROPERTY_NAME, message);
    }
}
