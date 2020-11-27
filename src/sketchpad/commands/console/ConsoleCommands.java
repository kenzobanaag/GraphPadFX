package sketchpad.commands.console;

import sketchpad.commands.Command;
import sketchpad.controller.ConsoleController;

public class ConsoleCommands implements Command {

    private String[] commands;

    public ConsoleCommands(String[] cmd) {
        commands = cmd;
    }

    @Override
    public void execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("Commands are: [");
        for(String command : commands) {
            sb.append(command + ", ");
        }
        sb.setLength(sb.length() - 2); // remove last comma
        sb.append("]");
        ConsoleController.consoleWrite(sb.toString());
    }
}
