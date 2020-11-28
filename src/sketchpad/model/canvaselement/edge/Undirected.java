package sketchpad.model.canvaselement.edge;

import javafx.scene.paint.Paint;
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

    @Override
    public void highlight(Paint paintStyle) {
        edge.setStroke(paintStyle);
    }

    @Override
    public void adjustEdge(double x, double y, String nodeId) {
        if(parentId.equals(nodeId)) {
            // either start or end
            edge.setStartX(x);
            edge.setStartY(y);
        }
        else if(childId.equals(nodeId)) {
            edge.setEndX(x);
            edge.setEndY(y);
        }
    }
}
