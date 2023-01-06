package streamingplatform.command;

import input.ActionInput;

import static streamingplatform.StreamingPlatformConstants.CHANGE_PAGE_ACTION_TYPE;

public final class BackCommand extends Command {
    public BackCommand(final ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        boolean hasGoneBack = false;
        if (platform.getCurrentUser() == null) {
            platform.addErrorOutputNode();
            return;
        }

        for (int i = platform.getExecutedCommandsForCurrentUser().size() - 1; i >= 0; i--) {
            Command command = platform.getExecutedCommandsForCurrentUser().get(i);
            if (command.hasSucceeded
                    && command.getAction().getType().equals(CHANGE_PAGE_ACTION_TYPE)
                    && !command.getWasUndone()) {
                hasGoneBack = true;
                hasSucceeded = true;
                command.undo();
                break;
            }
        }

        if (hasGoneBack) {
            if (platform.getCurrentPage().getHasBackOutput()) {
                platform.addOutputNode();
            }
        } else {
            platform.addErrorOutputNode();
        }
    }
}
