package streamingplatform.command;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.ActionInput;
import lombok.Getter;
import streamingplatform.StreamingPlatform;

/**
 * The base structure all commands must follow.
 */
public abstract class Command {
    @Getter
    protected ActionInput action;
    protected StreamingPlatform platform = StreamingPlatform.getINSTANCE();
    protected ArrayNode output = StreamingPlatform.getINSTANCE().getOutput();
    @Getter
    protected boolean hasSucceeded = false;
    @Getter
    protected boolean wasUndone = false;
    public Command(final ActionInput action) {
        this.action = action;
    }

    /**
     * The logic for each command has to overwrite this method.
     */
    public abstract void execute();

    public void undo(){}
}
