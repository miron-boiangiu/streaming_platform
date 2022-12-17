package streamingplatform.command;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.ActionInput;
import streamingplatform.StreamingPlatform;

public abstract class Command {
    protected ActionInput action;
    protected StreamingPlatform platform = StreamingPlatform.getINSTANCE();
    protected ArrayNode output = StreamingPlatform.getINSTANCE().getOutput();

    public Command(final ActionInput action) {
        this.action = action;
    }
    public abstract void execute();
}
