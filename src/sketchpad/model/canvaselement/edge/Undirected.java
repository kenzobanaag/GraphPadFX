package sketchpad.model.canvaselement.edge;

import javafx.scene.shape.Line;
import sketchpad.model.canvaselement.vertex.Node;

public class Undirected extends Edge{

    public Undirected(Node parent, Node child) {
        super(parent, child, 0);
    }

    public Undirected(Node parent, Node child, int value) {
        super(parent, child, value);
    }

    @Override
    public EdgeTypes getType() {
        return EdgeTypes.UNDIRECTED;
    }

    @Override
    public Line getCanvasElement() {
        return edge;
    }
}
