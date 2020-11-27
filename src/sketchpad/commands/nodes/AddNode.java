package sketchpad.commands.nodes;

import sketchpad.commands.Command;
import sketchpad.constants.Sizes;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.model.canvaselement.vertex.Node;
import sketchpad.model.canvaselement.vertex.Vertex;
import sketchpad.utils.InputValidator;
import sketchpad.utils.TryParse;

import static sketchpad.constants.Sizes.Node.PADDING;

public class AddNode implements Command {

    private Node node;

    public AddNode(double x, double y) {
        node = new Vertex(x,y);
    }

    public AddNode(String cmd) {
        String[] args = cmd.split(" ");
        if(args.length > 2) {
            double x = InputValidator.validateAdd(TryParse.tryParse(args[1]));
            double y = InputValidator.validateAdd(TryParse.tryParse(args[2]));
            node = new Vertex(x,y);
        }
        else node = new Vertex(Sizes.Node.RADIUS + PADDING,Sizes.Node.RADIUS + PADDING);
    }


    @Override
    public void execute() {
        CanvasController.addNode(node);
    }
}
