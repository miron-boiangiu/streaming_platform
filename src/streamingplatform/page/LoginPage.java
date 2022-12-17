package streamingplatform.page;
import static streamingplatform.StreamingPlatformConstants.LOGIN_ACTION;

public class LoginPage extends UnauthenticatedPage {
    public LoginPage() {
        super();
        this.possibleActions.add(LOGIN_ACTION);
    }
}
