package streamingplatform.page;
import static streamingplatform.StreamingPlatformConstants.LOGIN_PAGE;
import static streamingplatform.StreamingPlatformConstants.REGISTER_PAGE;
import static streamingplatform.StreamingPlatformConstants.LOGOUT_PAGE;
import static streamingplatform.StreamingPlatformConstants.UNAUTHENTICATED_HOMEPAGE_PAGE;
import static streamingplatform.StreamingPlatformConstants.AUTHENTICATED_HOMEPAGE_PAGE;
import static streamingplatform.StreamingPlatformConstants.MOVIES_PAGE;
import static streamingplatform.StreamingPlatformConstants.SEE_DETAILS_PAGE;
import static streamingplatform.StreamingPlatformConstants.UPGRADES_PAGE;

public class PageFactory {
    public static Page create(String pageName){
        switch (pageName) {
            case LOGIN_PAGE: return new LoginPage();
            case REGISTER_PAGE: return new RegisterPage();
            case LOGOUT_PAGE: return new LogoutPage();
            case UNAUTHENTICATED_HOMEPAGE_PAGE: return new UnauthenticatedHomepage();
            case AUTHENTICATED_HOMEPAGE_PAGE: return new AuthenticatedHomepage();
            case MOVIES_PAGE: return new MoviesPage();
            case SEE_DETAILS_PAGE: return new SeeDetailsPage();
            case UPGRADES_PAGE: return new UpgradesPage();
        }
        throw new IllegalArgumentException();
    }
}
