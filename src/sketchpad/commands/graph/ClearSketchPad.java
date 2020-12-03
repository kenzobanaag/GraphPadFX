package sketchpad.commands.graph;

import sketchpad.commands.Command;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.controller.ConsoleController;

/*
* todo: make console command for clearing edges only
* */
public class ClearSketchPad implements Command {

    private final String msg = "Cleared Graph Pad";
    
    @Override
    public void execute() {
        CanvasController.clearSketchPad();
        ConsoleController.consoleWrite(msg);
    }
}
