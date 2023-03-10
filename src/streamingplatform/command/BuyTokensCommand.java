package streamingplatform.command;

import input.ActionInput;
import streamingplatform.StreamingPlatform;
import streamingplatform.user.User;
import static streamingplatform.StreamingPlatformConstants.BUY_TOKENS_ACTION;

public final class BuyTokensCommand extends Command {

    public BuyTokensCommand(final ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        if (!platform.getCurrentPage().getPossibleActions().contains(BUY_TOKENS_ACTION)) {
            platform.addErrorOutputNode();
            return;
        }
        User currentUser = StreamingPlatform.getINSTANCE().getCurrentUser();
        int currentBalance = currentUser.getBalance();
        int wantedAmount = action.getCount();
        int currentTokens = currentUser.getTokensCount();

        if (wantedAmount > currentBalance) {
            platform.addErrorOutputNode();
            return;
        }
        currentBalance -= wantedAmount;
        currentTokens += wantedAmount;

        currentUser.setBalance(currentBalance);
        currentUser.setTokensCount(currentTokens);
    }
}
