package streamingplatform.page;

public class PageFactory {
    public static Page create(String pageName){
        switch (pageName) {
            case "login" : return new LoginPage();
            case "register" : return new RegisterPage();
            case "logout" : return new LogoutPage();
            case "unauthhome" : return new UnauthenticatedHomepage();
            case "authhome" : return new AuthenticatedHomepage();
            case "movies": return new MoviesPage();
            case "see details": return new SeeDetailsPage();
        }
        throw new IllegalArgumentException();
    }
}
