package streamingplatform.command;

import input.ActionInput;
import streamingplatform.StreamingPlatform;
import streamingplatform.user.User;

public class BuyPremiumCommand extends Command{
    public BuyPremiumCommand(ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        if (!platform.getCurrentPage().getPossibleActions().contains("buy premium account")) {
            platform.addErrorOutputNode();
            return;
        }

        User currentUser = StreamingPlatform.getInstance().getCurrentUser();
        int currentTokens = currentUser.getTokensCount();

        if(currentTokens < 10){
            platform.addErrorOutputNode();
            return;
        }
        if(currentUser.getAccountType().equals("premium")){
            platform.addErrorOutputNode();
            return;
        }

        currentTokens -= 10;
        currentUser.setTokensCount(currentTokens);
        currentUser.setAccountType("premium");

    }
}
