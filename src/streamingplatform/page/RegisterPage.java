package streamingplatform.page;
import static streamingplatform.StreamingPlatformConstants.REGISTER_ACTION;

public class RegisterPage extends UnauthenticatedPage {
    public RegisterPage() {
        super();
        possibleActions.add(REGISTER_ACTION);
    }
}
