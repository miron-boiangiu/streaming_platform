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
            }
        }
        throw new IllegalArgumentException();
    }
}
