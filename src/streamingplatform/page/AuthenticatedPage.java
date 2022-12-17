package streamingplatform.page;

import java.util.List;
import static streamingplatform.StreamingPlatformConstants.MOVIES_PAGE;
import static streamingplatform.StreamingPlatformConstants.UPGRADES_PAGE;
import static streamingplatform.StreamingPlatformConstants.LOGOUT_PAGE;
import static streamingplatform.StreamingPlatformConstants.AUTHENTICATED_HOMEPAGE_PAGE;

public abstract class AuthenticatedPage extends Page{
    public AuthenticatedPage(){
        this.accessiblePages.addAll(List.of(MOVIES_PAGE, UPGRADES_PAGE, LOGOUT_PAGE, AUTHENTICATED_HOMEPAGE_PAGE));
    }
}
