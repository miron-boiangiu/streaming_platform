package streamingplatform.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class UnauthenticatedPage extends Page{
    public UnauthenticatedPage(){
        this.accessiblePages.addAll(List.of("login", "register", "unauthhome"));
    }
}
