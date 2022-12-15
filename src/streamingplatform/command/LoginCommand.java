package streamingplatform.command;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import streamingplatform.StreamingPlatform;
import streamingplatform.database.Database;
import streamingplatform.page.PageFactory;
import streamingplatform.user.User;

public class LoginCommand extends Command{

    public LoginCommand(ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        String username = action.getCredentials().getName();
        String password = action.getCredentials().getPassword();

        Database<User> userDatabase = StreamingPlatform.getInstance().getUserDatabase();
        StreamingPlatform site = StreamingPlatform.getInstance();

        if(!StreamingPlatform.getInstance().getCurrentPage().getPossibleActions().contains("login")){
            ObjectNode newNode = StreamingPlatform.getInstance().getOutput().addObject();
            newNode.put("error", "Error");
            newNode.putNull("currentUser");
            newNode.putArray("currentMoviesList");
            return;
        }

        for(User user: userDatabase.getEntries()){
            if(user.getName().equals(username) && user.getPassword().equals(password)){
                StreamingPlatform.getInstance().setCurrentUser(user);
                StreamingPlatform.getInstance().setCurrentPage(PageFactory.create("authhome"));

                ObjectNode newNode = StreamingPlatform.getInstance().getOutput().addObject();
                newNode.putNull("error");
                User currentUser = StreamingPlatform.getInstance().getCurrentUser();
                if(currentUser != null){
                    ObjectNode userNode = newNode.putObject("currentUser");
                    site.getCurrentUser().addOutput(userNode);
                }
                else{
                    newNode.putNull("currentUser");
                }
                site.getCurrentPage().addOutput(newNode);
                return;
            }
        }
        StreamingPlatform.getInstance().setCurrentPage(PageFactory.create("unauthhome"));
        ObjectNode newNode = StreamingPlatform.getInstance().getOutput().addObject();
        newNode.put("error", "Error");
        newNode.putNull("currentUser");
        newNode.putArray("currentMoviesList");
        return;

    }
}
