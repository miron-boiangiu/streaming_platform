package streamingplatform.page;
import static streamingplatform.StreamingPlatformConstants.LOGIN_PAGE;
import static streamingplatform.StreamingPlatformConstants.REGISTER_PAGE;
import static streamingplatform.StreamingPlatformConstants.LOGOUT_PAGE;
import static streamingplatform.StreamingPlatformConstants.UNAUTHENTICATED_HOMEPAGE_PAGE;
import static streamingplatform.StreamingPlatformConstants.AUTHENTICATED_HOMEPAGE_PAGE;
import static streamingplatform.StreamingPlatformConstants.MOVIES_PAGE;
import static streamingplatform.StreamingPlatformConstants.SEE_DETAILS_PAGE;
import static streamingplatform.StreamingPlatformConstants.UPGRADES_PAGE;

/**
 * Factory used to create Pages.
 */
public final class PageFactory {
    private PageFactory() {

    }

    /**
     * Creates a specific Page object from its name.
     * @param pageName The name of the required page.
     * @return The Page that corresponds to the page name.
     */
    public static Page create(final String pageName) {
        switch (pageName) {
            case LOGIN_PAGE: return new LoginPage();
            case REGISTER_PAGE: return new RegisterPage();
            case LOGOUT_PAGE: return new LogoutPage();
            case UNAUTHENTICATED_HOMEPAGE_PAGE: return new UnauthenticatedHomepage();
            case AUTHENTICATED_HOMEPAGE_PAGE: return new AuthenticatedHomepage();
            case MOVIES_PAGE: return new MoviesPage();
            case SEE_DETAILS_PAGE: return new SeeDetailsPage();
            case UPGRADES_PAGE: return new UpgradesPage();
            default: throw new IllegalArgumentException();
        }
    }
}
