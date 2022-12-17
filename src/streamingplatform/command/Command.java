package streamingplatform.command;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.ActionInput;
import streamingplatform.StreamingPlatform;

public abstract class Command {
    protected ActionInput action;
    protected StreamingPlatform platform = StreamingPlatform.getInstance();
    protected ArrayNode output = StreamingPlatform.getInstance().getOutput();

    public Command(ActionInput action){
        this.action = action;
    }
    public abstract void execute();

}
