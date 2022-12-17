package streamingplatform.page;

import java.util.List;
import static streamingplatform.StreamingPlatformConstants.BUY_TOKENS_ACTION;
import static streamingplatform.StreamingPlatformConstants.BUY_PREMIUM_ACTION;

public class UpgradesPage extends AuthenticatedPage{
    public UpgradesPage() {
        super();
        possibleActions.addAll(List.of(BUY_TOKENS_ACTION, BUY_PREMIUM_ACTION));
    }
}
