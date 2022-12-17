package streamingplatform.page;

import java.util.List;
import static streamingplatform.StreamingPlatformConstants.PURCHASE_ACTION;
import static streamingplatform.StreamingPlatformConstants.WATCH_ACTION;
import static streamingplatform.StreamingPlatformConstants.LIKE_ACTION;
import static streamingplatform.StreamingPlatformConstants.RATE_ACTION;

public class SeeDetailsPage extends AuthenticatedPage {
    public SeeDetailsPage() {
        super();
        possibleActions.addAll(List.of(PURCHASE_ACTION, WATCH_ACTION, LIKE_ACTION, RATE_ACTION));
    }
}
