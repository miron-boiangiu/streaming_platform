package streamingplatform.user;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
        newNotificationNode.put("movieName", movieName);
        newNotificationNode.put("message", message);
    }
}
