package streamingplatform.page;

public class LoginPage extends UnauthenticatedPage{
    public LoginPage(){
        super();
        this.possibleActions.add("login");
    }
}
