package streamingplatform.command;

import input.ActionInput;
import streamingplatform.StreamingPlatform;
import streamingplatform.user.User;

public class BuyTokensCommand extends Command{

    public BuyTokensCommand(ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        if (!platform.getCurrentPage().getPossibleActions().contains("buy tokens")) {
            platform.addErrorOutputNode();
            return;
        }
        User currentUser = StreamingPlatform.getInstance().getCurrentUser();
        int currentBalance = currentUser.getBalance();
        int wantedAmount = action.getCount();
        int currentTokens = currentUser.getTokensCount();

        if(wantedAmount > currentBalance){
            platform.addErrorOutputNode();
            return;
        }
        currentBalance -= wantedAmount;
        currentTokens += wantedAmount;

        currentUser.setBalance(currentBalance);
        currentUser.setTokensCount(currentTokens);
    }
}
