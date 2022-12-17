package streamingplatform.command;

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
        ArrayList<Command> executedCommands = new ArrayList<Command>();
        for (Command command: commandsToBeExecuted) {
            command.execute();
            executedCommands.add(command);
        }
        commandsToBeExecuted.removeAll(executedCommands);
    }

}
