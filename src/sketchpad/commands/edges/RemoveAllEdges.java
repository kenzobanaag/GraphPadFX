package sketchpad.commands.edges;

import sketchpad.commands.Command;
import sketchpad.controller.canvas.CanvasController;

public class RemoveAllEdges implements Command {

    private boolean executable = false;

    public RemoveAllEdges(String command) {
        String[] cmd = command.split(" ");
        if(cmd[1] != null && cmd[1].equals("edge")) {
            executable = true;
        }
    }

    @Override
    public void execute() {
        if(executable)
            CanvasController.removeAllEdges();
    }
}
