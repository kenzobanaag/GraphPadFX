package sketchpad.commands.help;

import sketchpad.commands.Command;
import sketchpad.controller.ConsoleController;

public class Help implements Command {
    @Override
    public void execute() {
        String msg = "Type ?<command> to get more information. eg: ?algorithm";
        ConsoleController.consoleWrite(msg);
    }
}
