package streamingplatform.command;

import input.ActionInput;

import java.util.ArrayList;

public class BackCommand extends Command{
    public BackCommand(final ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        boolean hasGoneBack = false;
        if(platform.getCurrentUser() == null){
            platform.addErrorOutputNode();
            return;
        }

        ArrayList<Command> untouchedCommands = new ArrayList<>(platform.getExecutedCommandsForCurrentUser());
        for (int i = untouchedCommands.size()-1; i>=0; i--) {
            Command command = untouchedCommands.get(i);
            if(command.hasSucceeded && command.getAction().getType().equals("change page")){
                hasGoneBack = true;
                hasSucceeded = true;
                command.undo();
                platform.getExecutedCommandsForCurrentUser().remove(command);
                break;
            }
        }

        if(hasGoneBack) {
            if(platform.getCurrentPage().isHasBackOutput()){ // TODO: Can you change Lombok getter prefix?
                platform.addOutputNode();
            }
        } else {
            platform.addErrorOutputNode();
        }
    }
}
