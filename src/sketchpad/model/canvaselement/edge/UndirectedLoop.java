package sketchpad.model.canvaselement.edge;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import sketchpad.constants.ColorScheme;
import sketchpad.constants.Sizes;
import sketchpad.controller.canvas.CanvasController;

import static sketchpad.model.canvaselement.edge.Edge.EdgeTypes.UNDIRECTED_LOOP;

public class UndirectedLoop extends Edge{
    /*
    * todo: create an arc and return that arc
    *
    * todo: remove line from edges object
    * */
    private Arc loop;

    public UndirectedLoop(sketchpad.model.canvaselement.vertex.Node parent, sketchpad.model.canvaselement.vertex.Node child) {
        super(parent, child, 0);
        initArc(parent.getCanvasElement().getLayoutX()+25, parent.getCanvasElement().getLayoutY()+25);
        initListeners();
    }

    public UndirectedLoop(sketchpad.model.canvaselement.vertex.Node parent, sketchpad.model.canvaselement.vertex.Node child, int value) {
        super(parent, child, value);
        initArc(parent.getCanvasElement().getLayoutX()+25, parent.getCanvasElement().getLayoutY()+25); // 25 is the node raidius
        initListeners();
    }

    public void initArc(double x, double y) {
        loop = new Arc(x, y, 20,80,0,180);
        loop.setFill(Color.TRANSPARENT);
        loop.setStroke(ColorScheme.Edge.EDGE);
        loop.setStrokeWidth(Sizes.Edge.EDGE_STROKE);
        loop.toFront();
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
    public EdgeTypes getType() {
        return UNDIRECTED_LOOP;
    }

    @Override
    public Node getCanvasElement() {
        return loop;
    }

    @Override
    public void highlight(Paint paintStyle) {

    }

    @Override
    public void adjustEdge(double x, double y, String nodeId) {
        loop.setCenterX(x);
        loop.setCenterY(y);
    }

    public void select() {
        loop.setStroke(ColorScheme.Edge.SELECTED);
    }

    public void deselect() {
        loop.setStroke(ColorScheme.Edge.EDGE);
    }
}
