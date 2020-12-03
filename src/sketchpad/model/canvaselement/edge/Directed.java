package sketchpad.model.canvaselement.edge;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import sketchpad.constants.ColorScheme;
import sketchpad.constants.Sizes;
import sketchpad.model.canvaselement.vertex.Node;
import sketchpad.utils.CircleTranslator;

import java.util.List;

import static sketchpad.constants.ColorScheme.Edge.EDGE;
import static sketchpad.constants.ColorScheme.Edge.SELECTED;

/*
* refactor: fix some layouts, fix naming conventions
* */

public class Directed extends Edge{

    private static final double ARROW_ANGLE = 135;

    // need to add arrows to the pane
    private Group directedEdge;
    private Polygon arrow;

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
        if(parentId.equals(nodeId)) {
            // either start or end
            edge.setStartX(x);
            edge.setStartY(y);
        }
        else if(childId.equals(nodeId)) {
            edge.setEndX(x);
            edge.setEndY(y);
        }
        computeArrowPosition();
    }

    private void computeArrowPosition() {
        arrow.getPoints().clear();
        double slope = CircleTranslator.getSlope(edge.getStartX(), edge.getStartY(), edge.getEndX(), edge.getEndY());
        List<Point2D> arrowPoints = CircleTranslator.computePoints((edge.getEndX()+edge.getStartX())/2,
                (edge.getEndY()+edge.getStartY())/2, slope,
                (int)(Sizes.Node.RADIUS*1.5), 2, ARROW_ANGLE, edge.getEndX() - edge.getStartX());
        arrow.getPoints().addAll((edge.getEndX()+edge.getStartX())/2,
                (edge.getEndY()+edge.getStartY())/2,arrowPoints.get(0).getX(),arrowPoints.get(0).getY(),
                arrowPoints.get(1).getX(), arrowPoints.get(1).getY());
    }

    private void initArrows() {
        arrow = new Polygon();
        arrow.setFill(ColorScheme.Edge.EDGE);
        arrow.setStroke(ColorScheme.Edge.EDGE);
        // build triangle arrow
        computeArrowPosition();
        directedEdge = new Group(edge, arrow);
    }

    @Override
    public EdgeTypes getType() {
        return EdgeTypes.DIRECTED;
    }

    @Override
    public Group getCanvasElement() {
        return directedEdge;
    }

    @Override
    public void highlight(Paint paintStyle) {
        edge.setStroke(paintStyle);
    }

    public void select() {
        edge.setStroke(SELECTED);
        arrow.setStroke(SELECTED);
        arrow.setFill(SELECTED);
    }

    public void deselect() {
        // todo: have a standard color for fill so we can also color edges
        edge.setStroke(EDGE);
        arrow.setStroke(EDGE);
        arrow.setFill(EDGE);
    }
}
