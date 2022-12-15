package streamingplatform.page;

import java.util.List;

public class SeeDetailsPage extends AuthenticatedPage{
    public SeeDetailsPage() {
        super();
        possibleActions.addAll(List.of("purchase", "watch", "like", "rate"));
    }
}
