package streamingplatform.page;

import java.util.List;

public abstract class AuthenticatedPage extends Page{
    public AuthenticatedPage(){
        this.accessiblePages.addAll(List.of("movies", "upgrades", "logout", "authhome"));
    }
}
