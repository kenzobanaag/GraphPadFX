package sketchpad.commands.nodes;

import sketchpad.commands.Command;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.utils.TryParse;

public class DeleteNodeByOrder implements Command {

    private int order;

    public DeleteNodeByOrder(String command) {
        String[] args = command.split(" ");
        if(args.length > 1)
            order = TryParse.tryParseInt(args[1]);
        else order = -1;
    }


    @Override
    public void execute() {
        CanvasController.removeNode(order);
    }
}
