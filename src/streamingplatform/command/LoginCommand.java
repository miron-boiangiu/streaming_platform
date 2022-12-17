package streamingplatform.command;

import input.ActionInput;
import streamingplatform.StreamingPlatform;
import streamingplatform.database.Database;
import streamingplatform.page.PageFactory;
import streamingplatform.user.User;
import static streamingplatform.StreamingPlatformConstants.LOGIN_ACTION;
import static streamingplatform.StreamingPlatformConstants.AUTHENTICATED_HOMEPAGE_PAGE;
import static streamingplatform.StreamingPlatformConstants.UNAUTHENTICATED_HOMEPAGE_PAGE;

public final class LoginCommand extends Command {

    public LoginCommand(final ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        String username = action.getCredentials().getName();
        String password = action.getCredentials().getPassword();

        Database<User> userDatabase = StreamingPlatform.getINSTANCE().getUserDatabase();
        StreamingPlatform site = StreamingPlatform.getINSTANCE();

        if (!StreamingPlatform.getINSTANCE().getCurrentPage().
                getPossibleActions().contains(LOGIN_ACTION)) {
            site.addErrorOutputNode();
            return;
        }

        for (User user: userDatabase.getEntries()) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                StreamingPlatform.getINSTANCE().setCurrentUser(user);
                StreamingPlatform.getINSTANCE().setCurrentPage(
                        PageFactory.create(AUTHENTICATED_HOMEPAGE_PAGE));
                site.addOutputNode();
                return;
            }
        }
        StreamingPlatform.getINSTANCE().setCurrentPage(
                PageFactory.create(UNAUTHENTICATED_HOMEPAGE_PAGE));
        site.addErrorOutputNode();
    }
}
