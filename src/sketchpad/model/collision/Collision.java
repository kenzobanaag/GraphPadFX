package sketchpad.model.collision;

import javafx.scene.shape.Rectangle;
import sketchpad.constants.Sizes;
import sketchpad.model.canvaselement.edge.Edge;
import sketchpad.model.canvaselement.vertex.Node;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Collision {
    private Rectangle hitBox;
    private Rectangle hitLine;

    public Collision() {
        hitBox = new Rectangle(Sizes.Node.RADIUS*2, Sizes.Node.RADIUS*2);
        hitLine = new Rectangle(Sizes.Edge.EDGE_STROKE*2, Sizes.Edge.EDGE_STROKE*2);
    }

    public boolean nodeClear(LinkedHashMap<String, Node> nodes, double x, double y) {
        hitBox.setX(x - Sizes.Node.RADIUS);
        hitBox.setY(y - Sizes.Node.RADIUS);

        for (Node node : nodes.values()) {
            if(hitBox.intersects(node.getCanvasElement().getBoundsInParent()))
                return false;
        }
        return true;
    }

    public boolean edgeClear(HashMap<String, Edge> edges, double x, double y) { // use line instead
        hitBox.setX(x - Sizes.Edge.EDGE_STROKE);
        hitBox.setY(y - Sizes.Edge.EDGE_STROKE);

        for (Edge edge : edges.values()) {
            if(hitBox.intersects(edge.getCanvasElement().getBoundsInParent())) // Refactor to intersect rectangle instead of bounds. This might be a better approach since we dont really know whats happening with bounds
                return false;
        }
        return true;
    }

    public Node getNodeAt(LinkedHashMap<String, Node> nodes, double x, double y) {
        hitBox.setX(x - Sizes.Node.RADIUS);
        hitBox.setY(y - Sizes.Node.RADIUS);

        for (Node node : nodes.values()) {
            if(hitBox.intersects(node.getCanvasElement().getBoundsInParent()))
                return node;
        }
        return null;
    }
}
