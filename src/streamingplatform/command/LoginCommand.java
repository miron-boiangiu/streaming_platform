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
            site.addErrorOutputNode();
            return;
        }

        for(User user: userDatabase.getEntries()){
            if(user.getName().equals(username) && user.getPassword().equals(password)){
                StreamingPlatform.getInstance().setCurrentUser(user);
                StreamingPlatform.getInstance().setCurrentPage(PageFactory.create("authhome"));
                site.addOutputNode();
                return;
            }
        }
        StreamingPlatform.getInstance().setCurrentPage(PageFactory.create("unauthhome"));
        site.addErrorOutputNode();
        return;

    }
}
