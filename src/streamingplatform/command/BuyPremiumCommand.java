package streamingplatform.command;

import input.ActionInput;
import streamingplatform.StreamingPlatform;
import streamingplatform.user.User;
import static streamingplatform.StreamingPlatformConstants.PREMIUM_ACCOUNT_PRICE;
import static streamingplatform.StreamingPlatformConstants.BUY_PREMIUM_ACTION;
import static streamingplatform.StreamingPlatformConstants.PREMIUM_USER_ATTRIBUTE;

public class BuyPremiumCommand extends Command{
    public BuyPremiumCommand(ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        if (!platform.getCurrentPage().getPossibleActions().contains(BUY_PREMIUM_ACTION)) {
            platform.addErrorOutputNode();
            return;
        }

        User currentUser = StreamingPlatform.getInstance().getCurrentUser();
        int currentTokens = currentUser.getTokensCount();

        if(currentTokens < PREMIUM_ACCOUNT_PRICE){
            platform.addErrorOutputNode();
            return;
        }
        if(currentUser.getAccountType().equals(PREMIUM_USER_ATTRIBUTE)){
            platform.addErrorOutputNode();
            return;
        }

        currentTokens -= PREMIUM_ACCOUNT_PRICE;
        currentUser.setTokensCount(currentTokens);
        currentUser.setAccountType(PREMIUM_USER_ATTRIBUTE);

    }
}
