package streamingplatform.command;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import streamingplatform.StreamingPlatform;
import streamingplatform.database.Database;
import streamingplatform.page.PageFactory;
import streamingplatform.user.User;

public class RegisterCommand extends Command{
    public RegisterCommand(ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        String username = action.getCredentials().getName();
        String password = action.getCredentials().getPassword();

        Database<User> userDatabase = StreamingPlatform.getInstance().getUserDatabase();
        StreamingPlatform site = StreamingPlatform.getInstance();

        if(!StreamingPlatform.getInstance().getCurrentPage().getPossibleActions().contains("register")){
            ObjectNode newNode = StreamingPlatform.getInstance().getOutput().addObject();
            site.addErrorOutputNode();
            return;
        }

        for(User user: userDatabase.getEntries()){
            if(user.getName().equals(username)){
                StreamingPlatform.getInstance().setCurrentPage(PageFactory.create("unauthhome"));
                site.addErrorOutputNode();
                return;
            }
        }

        String country = action.getCredentials().getCountry();
        int balance = action.getCredentials().getBalance();
        String type = action.getCredentials().getAccountType();
        User newUser = new User(username, password, type, country, balance);
        userDatabase.addEntry(newUser);

        site.setCurrentUser(newUser);
        StreamingPlatform.getInstance().setCurrentPage(PageFactory.create("authhome"));
        site.addOutputNode();
        return;

    }
}
