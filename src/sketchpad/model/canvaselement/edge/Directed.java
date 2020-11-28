package sketchpad.model.canvaselement.edge;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import sketchpad.model.canvaselement.vertex.Node;

public class Directed extends Edge{

    // need to add arrows to the pane

    public Directed(Node parent, Node child) {
        super(parent, child,0);
        initArrows();
    }

    public Directed(Node parent, Node child, int value) {
        super(parent, child,value);
        initArrows();
    }

    @Override
    public void adjustEdge(double x, double y, String nodeId) {

    }

    private void initArrows() {

    }

    @Override
    public EdgeTypes getType() {
        return EdgeTypes.DIRECTED;
    }

    @Override
    public Line getCanvasElement() {
        return edge;
    }

    @Override
    public void highlight(Paint paintStyle) {
        edge.setStroke(paintStyle);
    }
}
