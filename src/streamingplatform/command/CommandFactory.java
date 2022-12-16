package streamingplatform.command;

import input.ActionInput;

import java.util.Objects;

public class CommandFactory {
    public static Command create(ActionInput input){
        if(Objects.equals(input.getType(), "change page")){
            return new ChangePageCommand(input);
        }
        else if(Objects.equals(input.getType(), "on page")){
            switch (input.getFeature()) {
                case "login": return new LoginCommand(input);
                case "register": return new RegisterCommand(input);
                case "search": return new SearchCommand(input);
                case "filter": return new FilterCommand(input);
                case "like": return new LikeCommand(input);
                case "rate": return new RateCommand(input);
                case "buy premium account": return new BuyPremiumCommand(input);
                case "purchase": return new PurchaseCommand(input);
                case "watch": return new WatchCommand(input);
                case "buy tokens": return new BuyTokensCommand(input);
            }
        }
        throw new IllegalArgumentException();
    }
}
