package streamingplatform.page;

import java.util.List;

public class UpgradesPage extends AuthenticatedPage{
    public UpgradesPage() {
        super();
        possibleActions.addAll(List.of("buy tokens", "buy premium account"));
    }
}
