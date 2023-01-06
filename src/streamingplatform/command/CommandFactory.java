package streamingplatform.command;

import input.ActionInput;

import java.util.Objects;

import static streamingplatform.StreamingPlatformConstants.BACK_ACTION_TYPE;
import static streamingplatform.StreamingPlatformConstants.CHANGE_PAGE_ACTION_TYPE;
import static streamingplatform.StreamingPlatformConstants.DATABASE_ACTION_TYPE;
import static streamingplatform.StreamingPlatformConstants.DATABASE_ADD_ACTION;
import static streamingplatform.StreamingPlatformConstants.DATABASE_DELETE_ACTION;
import static streamingplatform.StreamingPlatformConstants.LOGIN_ACTION;
import static streamingplatform.StreamingPlatformConstants.ON_PAGE_ACTION_TYPE;
import static streamingplatform.StreamingPlatformConstants.REGISTER_ACTION;
import static streamingplatform.StreamingPlatformConstants.SEARCH_ACTION;
import static streamingplatform.StreamingPlatformConstants.FILTER_ACTION;
import static streamingplatform.StreamingPlatformConstants.LIKE_ACTION;
import static streamingplatform.StreamingPlatformConstants.RATE_ACTION;
import static streamingplatform.StreamingPlatformConstants.BUY_PREMIUM_ACTION;
import static streamingplatform.StreamingPlatformConstants.PURCHASE_ACTION;
import static streamingplatform.StreamingPlatformConstants.SUBSCRIBE_ACTION;
import static streamingplatform.StreamingPlatformConstants.WATCH_ACTION;
import static streamingplatform.StreamingPlatformConstants.BUY_TOKENS_ACTION;

/**
 * Factory used to create Commands.
 */
public final class CommandFactory {
    private CommandFactory() {

    }

    /**
     * Creates a specific Command object from its input.
     * @param input Information about the requested command.
     * @return A command that does what was requested, ready to be executed..
     */
    public static Command create(final ActionInput input) {
        if (Objects.equals(input.getType(), CHANGE_PAGE_ACTION_TYPE)) {
            return new ChangePageCommand(input);
        } else if (Objects.equals(input.getType(), BACK_ACTION_TYPE)) {
            return new BackCommand(input);
        } else if (Objects.equals(input.getType(), DATABASE_ACTION_TYPE)) {
            switch (input.getFeature()) {
                case DATABASE_ADD_ACTION: return new DatabaseAddCommand(input);
                case DATABASE_DELETE_ACTION: return new DatabaseDeleteCommand(input);
                default: throw new IllegalArgumentException();
            }
        } else if (Objects.equals(input.getType(), ON_PAGE_ACTION_TYPE)) {
            switch (input.getFeature()) {
                case LOGIN_ACTION: return new LoginCommand(input);
                case REGISTER_ACTION: return new RegisterCommand(input);
                case SEARCH_ACTION: return new SearchCommand(input);
                case FILTER_ACTION: return new FilterCommand(input);
                case LIKE_ACTION: return new LikeCommand(input);
                case RATE_ACTION: return new RateCommand(input);
                case BUY_PREMIUM_ACTION: return new BuyPremiumCommand(input);
                case PURCHASE_ACTION: return new PurchaseCommand(input);
                case WATCH_ACTION: return new WatchCommand(input);
                case BUY_TOKENS_ACTION: return new BuyTokensCommand(input);
                case SUBSCRIBE_ACTION: return new SubscribeCommand(input);
                default: throw new IllegalArgumentException();
            }
        }
        return null;
    }
}
