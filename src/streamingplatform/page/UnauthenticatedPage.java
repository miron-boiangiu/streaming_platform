package streamingplatform.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static streamingplatform.StreamingPlatformConstants.LOGIN_PAGE;
import static streamingplatform.StreamingPlatformConstants.REGISTER_PAGE;
import static streamingplatform.StreamingPlatformConstants.UNAUTHENTICATED_HOMEPAGE_PAGE;

public abstract class UnauthenticatedPage extends Page{
    public UnauthenticatedPage(){
        this.accessiblePages.addAll(List.of(LOGIN_PAGE, REGISTER_PAGE, UNAUTHENTICATED_HOMEPAGE_PAGE));
    }
}
