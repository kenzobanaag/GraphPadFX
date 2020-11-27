package sketchpad.commands.console;

import sketchpad.commands.Command;
import sketchpad.controller.ConsoleController;

public class ClearConsole implements Command {
    @Override
    public void execute() {
        ConsoleController.clearConsole();
    }
}
