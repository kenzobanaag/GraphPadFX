package sketchpad.model.canvaselement.edge;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Polygon;
import sketchpad.constants.ColorScheme;
import sketchpad.constants.Sizes;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.model.canvaselement.vertex.Node;
import sketchpad.utils.CircleTranslator;

import java.util.List;

import static sketchpad.constants.ColorScheme.Edge.EDGE;
import static sketchpad.constants.ColorScheme.Edge.SELECTED;
import static sketchpad.model.canvaselement.edge.Edge.EdgeTypes.DIRECTED_LOOP;

public class DirectedLoop extends Edge{
    /*
     * todo: remove line from edges object
     * */
    private Arc loop;
    private final int RADIUS = Sizes.Node.RADIUS;
    private Polygon arrow;
    private Group directedEdge;
    private static final double ARROW_ANGLE = 135;

    public DirectedLoop(Node parent, Node child) {
        super(parent, child);
        initArc(parent.getCanvasElement().getLayoutX() + RADIUS, parent.getCanvasElement().getLayoutY() + RADIUS);
        initListeners();
        initGroup();
    }

    public DirectedLoop(Node parent, Node child, int value) {
        super(parent, child, value);
        initArc(parent.getCanvasElement().getLayoutX() + RADIUS, parent.getCanvasElement().getLayoutY() + RADIUS); // 25 is the node raidius
        initListeners();
        initGroup();
    }

    public void initArc(double x, double y) {
        loop = new Arc(x, y, RADIUS-3,80,0,180);
        loop.setFill(Color.TRANSPARENT);
        loop.setStroke(ColorScheme.Edge.EDGE);
        loop.setStrokeWidth(Sizes.Edge.EDGE_STROKE);
        loop.toFront();
    }

    public void initGroup() {
        initArrows();
        directedEdge = new Group(loop, arrow);
    }

    private void initArrows() {
        arrow = new Polygon();
        arrow.setFill(ColorScheme.Edge.EDGE);
        arrow.setStroke(ColorScheme.Edge.EDGE);
        // build triangle arrow
        drawArrow();
    }

    // this could be abstract, since undirected and loop have different implementations. Super can then call initListeners
    private void initListeners() {
        EventHandler<MouseEvent> clickHandler = event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                CanvasController.select(this);
            }
            else if(event.getButton().equals(MouseButton.SECONDARY)) { // right click
                CanvasController.removeEdge(this);
            }
        };

        loop.addEventHandler(MouseEvent.MOUSE_PRESSED, clickHandler);
    }

    @Override
    public Edge.EdgeTypes getType() {
        return DIRECTED_LOOP;
    }

    @Override
    public javafx.scene.Node getCanvasElement() {
        return directedEdge;
    }

    @Override
    public void highlight(Paint paintStyle) {
        loop.setStroke(paintStyle);
        arrow.setStroke(paintStyle);
        arrow.setFill(paintStyle);
    }

    @Override
    public void adjustEdge(double x, double y, String nodeId) {
        loop.setCenterX(x);
        loop.setCenterY(y);
        drawArrow();
    }

    private void drawArrow() {
        arrow.getPoints().clear();
        List<Point2D> arrowPoints = CircleTranslator.computePoints(loop.getCenterX()+15,
                loop.getCenterY()-40, 10,
                (int)(Sizes.Node.RADIUS*1.5), 2, ARROW_ANGLE, 1);
        arrow.getPoints().addAll(loop.getCenterX()+15,
                loop.getCenterY()-40,arrowPoints.get(0).getX(),arrowPoints.get(0).getY(),
                arrowPoints.get(1).getX(), arrowPoints.get(1).getY());
    }

    public void select() {
        loop.setStroke(ColorScheme.Edge.SELECTED);
        arrow.setStroke(SELECTED);
        arrow.setFill(SELECTED);
    }

    public void deselect() {
        loop.setStroke(ColorScheme.Edge.EDGE);
        arrow.setStroke(EDGE);
        arrow.setFill(EDGE);
    }
}
