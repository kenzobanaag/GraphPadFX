package sketchpad.model.canvaselement.edge;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import sketchpad.constants.ColorScheme;
import sketchpad.constants.Sizes;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.model.canvaselement.Element;
import sketchpad.model.canvaselement.vertex.Node;
import test.model.TestingSpecifics;

import java.util.UUID;

import static sketchpad.constants.ColorScheme.Edge.EDGE;
import static sketchpad.constants.ColorScheme.Edge.SELECTED;

/*
* For loop edges, we can maybe create UndirectedLoop and DirectedLoop
* */

public abstract class Edge implements Element {

    protected String parentId;
    protected String childId;
    protected String id;
    protected String edgeName = "";
    protected int value;
    protected boolean isBridge = false;

    protected Pane edgeLayout;
    protected Line edge;
    protected Label edgeValueLabel;
    protected Label edgeNameLabel;

    protected Edge(Node parent, Node child) {
        initVariables(parent, child, 0);
    }

    protected Edge(Node parent, Node child,int value) {
        initVariables(parent, child, value);
    }

    private void initVariables(Node parent, Node child,  int value) {
        this.parentId = parent.getId();
        this.childId = child.getId();
        this.value = value;
        setEdgeName(parent.getOrder(), child.getOrder());
        id = UUID.randomUUID().toString();
        if(!TestingSpecifics.isTesting()) // HAHAHAHAHAHAHAHA
            initWidgets(parent.getNode().getLayoutX(), parent.getNode().getLayoutY(),
                    child.getNode().getLayoutX(), child.getNode().getLayoutY()); // note: comment out when testing
        initListeners();
    }

    /*
    * Create one for circles.
    * */

    private void initWidgets(double x1, double y1, double x2, double y2) {
        // these need to be mocked for testing??
        // im not testing these tho.
        int radius = Sizes.Node.RADIUS;
        edge = new Line(x1+radius,y1+radius,x2+radius,y2+radius);
        edge.setStrokeWidth(Sizes.Edge.EDGE_STROKE);
        edge.setStroke(ColorScheme.Edge.EDGE);
        edge.toFront();
        edgeLayout = new Pane(edge);
        edgeValueLabel = new Label(); // there doesnt need to be a value label, just change name label to value
        edgeNameLabel = new Label(edgeName);
    }

    private void initListeners() {
        EventHandler<MouseEvent> clickHandler = event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
               CanvasController.select(this);
            }
            else if(event.getButton().equals(MouseButton.SECONDARY)) { // right click
                CanvasController.removeEdge(this);
            }
        };

        edge.addEventHandler(MouseEvent.MOUSE_PRESSED, clickHandler);
    }

    private void setEdgeName(int parentOrder, int childOrder) {
        edgeName = String.format("n%d-n%d", parentOrder, childOrder);
    }

    public void setValue(int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return edgeName;
    }

    public String getParentId() {
        return parentId;
    }

    public String getChildId() {
        return childId;
    }

    public String getId() {
        return id;
    }
    /*
    * Color edge and all thats connected to it?
    * */
    public void select() {
        edge.setStroke(SELECTED);
    }

    public void deselect() {
        edge.setStroke(EDGE);
    }

    public abstract void adjustEdge(double x, double y, String nodeId);

    public abstract EdgeTypes getType();
    public abstract javafx.scene.Node getCanvasElement();

    public enum EdgeTypes {
        DIRECTED, UNDIRECTED, DIRECTED_LOOP, UNDIRECTED_LOOP;
    }
}
