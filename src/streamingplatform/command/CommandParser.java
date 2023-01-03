package streamingplatform.command;

import lombok.Getter;
import streamingplatform.StreamingPlatform;

import java.util.ArrayList;

/**
 * Holds all commands to be executed and those that were executed.
 */
public final class CommandParser {
    private final ArrayList<Command> commandsToBeExecuted = new ArrayList<Command>();
    /**
     * Adds a command to the "to-be-executed" list.
     * @param command The command to add.
     */
    public void addCommand(final Command command) {
        commandsToBeExecuted.add(command);
    }

    /**
     * Executes all commands left.
     */
    public void executeAll() {
        StreamingPlatform platform = StreamingPlatform.getINSTANCE();
        ArrayList<Command> executedCommands = platform.getExecutedCommandsForCurrentUser();
        for (Command command: new ArrayList<Command>(commandsToBeExecuted)) {
            command.execute();
            executedCommands.add(command);
            commandsToBeExecuted.remove(command);
        }
    }

}
