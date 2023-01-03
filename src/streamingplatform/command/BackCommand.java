package streamingplatform.command;

import input.ActionInput;

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

        for(Command command: platform.getExecutedCommandsForCurrentUser()){
            if(command.hasSucceeded && command.getAction().getType().equals("change page")){
                hasGoneBack = true;
                command.undo();
            }
        }

        if(hasGoneBack) {
            if(platform.getCurrentPage().isHasOutput()){ // TODO: Can you change Lombok getter prefix?
                platform.addOutputNode();
            }
        } else {
            platform.addErrorOutputNode();
        }
    }
}
