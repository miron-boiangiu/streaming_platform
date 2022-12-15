package streamingplatform.command;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.ActionInput;
import streamingplatform.StreamingPlatform;

public abstract class Command {
    ActionInput action;
    StreamingPlatform platform = StreamingPlatform.getInstance();
    ArrayNode output = StreamingPlatform.getInstance().getOutput();

    public Command(ActionInput action){
        this.action = action;
    }
    public abstract void execute();

}
