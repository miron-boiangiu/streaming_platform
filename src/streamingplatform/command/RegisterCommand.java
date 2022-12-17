package streamingplatform.command;

import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import streamingplatform.StreamingPlatform;
import streamingplatform.database.Database;
import streamingplatform.page.PageFactory;
import streamingplatform.user.User;
import static streamingplatform.StreamingPlatformConstants.REGISTER_ACTION;
import static streamingplatform.StreamingPlatformConstants.UNAUTHENTICATED_HOMEPAGE_PAGE;
import static streamingplatform.StreamingPlatformConstants.AUTHENTICATED_HOMEPAGE_PAGE;

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

        if(!StreamingPlatform.getInstance().getCurrentPage().getPossibleActions().contains(REGISTER_ACTION)){
            ObjectNode newNode = StreamingPlatform.getInstance().getOutput().addObject();
            site.addErrorOutputNode();
            return;
        }

        for(User user: userDatabase.getEntries()){
            if(user.getName().equals(username)){
                StreamingPlatform.getInstance().setCurrentPage(PageFactory.create(UNAUTHENTICATED_HOMEPAGE_PAGE));
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
        StreamingPlatform.getInstance().setCurrentPage(PageFactory.create(AUTHENTICATED_HOMEPAGE_PAGE));
        site.addOutputNode();
        return;

    }
}
