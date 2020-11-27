package sketchpad.commands.nodes;

import sketchpad.commands.Command;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.model.canvaselement.vertex.Node;

public class DeleteNode implements Command {

    private Node node;

    public DeleteNode(Node node) {
        this.node = node;
    }

    @Override
    public void execute() {
        CanvasController.removeNode(node);
    }
}
