package streamingplatform.command;

import java.util.ArrayList;

public class CommandParser {
    private final ArrayList<Command> commandsToBeExecuted = new ArrayList<Command>();

    public void addCommand(Command command){
        commandsToBeExecuted.add(command);
    }

    public void executeAll(){
        ArrayList<Command> executedCommands = new ArrayList<Command>();
        for(Command command: commandsToBeExecuted){
            command.execute();
            executedCommands.add(command);
        }
        commandsToBeExecuted.removeAll(executedCommands);
    }

}
