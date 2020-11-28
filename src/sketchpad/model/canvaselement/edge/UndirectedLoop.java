package sketchpad.model.canvaselement.edge;

import javafx.scene.Node;
import javafx.scene.paint.Paint;

public class UndirectedLoop extends Edge{
    /*
    * todo: create an arc and return that arc
    * */

    public UndirectedLoop(sketchpad.model.canvaselement.vertex.Node parent, sketchpad.model.canvaselement.vertex.Node child) {
        super(parent, child, 0);
    }

    public UndirectedLoop(sketchpad.model.canvaselement.vertex.Node parent, sketchpad.model.canvaselement.vertex.Node child, int value) {
        super(parent, child, value);
    }

    @Override
    public EdgeTypes getType() {
        return null;
    }

    @Override
    public Node getCanvasElement() {
        return null;
    }

    @Override
    public void highlight(Paint paintStyle) {

    }

    @Override
    public void adjustEdge(double x, double y, String nodeId) {

    }
}
