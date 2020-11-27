package sketchpad.commands.graph;

import sketchpad.commands.Command;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.controller.ConsoleController;

public class ClearSketchPad implements Command {

    private final String msg = "Cleared Graph Pad";

    @Override
    public void execute() {
        CanvasController.clearSketchPad();
        ConsoleController.consoleWrite(msg);
    }
}
