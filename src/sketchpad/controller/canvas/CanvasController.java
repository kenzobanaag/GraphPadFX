package sketchpad.controller.canvas;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.paint.Paint;
import sketchpad.commands.nodes.AddNode;
import sketchpad.commands.graph.ToggleName;
import sketchpad.constants.ColorScheme;
import sketchpad.controller.BottomDisplayController;
import sketchpad.controller.ConsoleController;
import sketchpad.model.canvaselement.Element;
import sketchpad.model.canvaselement.edge.EdgeBuilder;
import sketchpad.model.collision.Collision;
import sketchpad.model.canvaselement.edge.Edge;
import sketchpad.model.canvaselement.vertex.Node;
import sketchpad.view.BottomDisplay;
import sketchpad.view.Canvas;

import java.util.LinkedHashMap;

import static sketchpad.constants.Sizes.Canvas.SELECTION_COUNT;

/*
* todo: Break this class to a smaller class called CanvasData
*
*       That class will hold all data, and all methods that manipulate the data
*       This class should handle communications between the data class, and printing to the canvas view.
* */
public class CanvasController { // todo: CanvasController has a class CanvasData, this holds all the data, and will do data computations
                                // canvasController will only be responsible for communications with other controllers.

    /*
    * fixme: Should we have selectionElements?
    *
    * Do we even need to select an edge?
    *
    * We can use select to change values of elements. Basically node and edge both can have values.
    *
    * Todo: On bottom display, show value and have an option to change it.
    *
    * Canvas instructions go here.
    *
    * Anything to do with data will be on CanvasData
    *
    * */

    //private static int elements = 0;
    //private static LinkedHashMap<String, Node> nodeMap;
    //private static HashMap<String, Edge> edgeMap; //adjacency map, string is nodeId
    private static Canvas canvas;
    private static CanvasController instance;
    private static CanvasData data;
    private static boolean showLabel = false;
    private static Element[] elementSelection;
    private static final int CURRENT = 0, LAST = 1;
    private static Collision collision;

    public static void init(Canvas canvas) {
        if(instance == null)
            instance = new CanvasController(canvas);
    }

    private CanvasController(Canvas canvas) {
        CanvasController.canvas = canvas;
        setEvents(canvas);
        collision = new Collision();
        data = CanvasData.getInstance();
        elementSelection = new Element[SELECTION_COUNT];
        focus();
    }

    private static void setEvents(Canvas canvas) {
        EventHandler<MouseEvent> clickHandler = event -> {
            double x = event.getX();
            double y = event.getY();
            if(event.getButton().equals(MouseButton.PRIMARY)) { // left click
                if(isCanvasClear(x,y)) {// check collision
                    new AddNode(x, y).execute();
                    deselect(elementSelection[CURRENT]);
                    event.consume();
                }
            }
            else if(event.getButton().equals(MouseButton.SECONDARY)) {  // right click
                if(isCanvasClear(x,y)) {// check if on top of node
                    deselect(elementSelection[CURRENT]);
                    event.consume();
                }
            }
        };

        EventHandler<KeyEvent> keyHandler = event -> {
          if(event.getCode().equals(KeyCode.ALT)) {
              toggleShow();
              //toggleName();
          }
        };

        canvas.getLayout().addEventFilter(MouseEvent.MOUSE_PRESSED, clickHandler);
        canvas.getLayout().addEventFilter(KeyEvent.KEY_PRESSED, keyHandler);
    }

    /*
     * Highlight vs select
     *
     * Select will put it on the canvas' selection
     *
     * Highlight will only change the nodes' color
     * */
    public static void highlight(String elementId) {
        highlight(elementId, ColorScheme.Node.SELECTED);
    }

    public static void highlight(String elementId, Paint paintStyle) {
        if(data.getNodeMap().containsKey(elementId))
            data.getNodeMap().get(elementId).highlight(paintStyle);
        else if(data.getEdgeMap().containsKey(elementId))
            data.getEdgeMap().get(elementId).highlight(paintStyle);
    }

    public static void addNode(Node node) {
        // todo: Should also handle collisions. We know that these are console adds so we need to find a place for new nodes
        data.addNode(node);
        canvas.getLayout().getChildren().addAll(node.getEdgeGuide(), node.getNode()); // add to canvas
        BottomDisplayController.setNodes(data.getNodeCount());
        //toggleName(); // refactor this. Basically, this computes the whole map again and again, when it should just toggle the new node
        focus();
    }

    public static void removeNode(Node node) {
        data.removeNode(node);
        deselect(node);
        canvas.getLayout().getChildren().removeAll(node.getEdgeGuide(), node.getNode());
        BottomDisplayController.setNodes(data.getNodeCount());
        focus();
    }

    /*
    * This is called on mouse released on vertex
    * */
    public static void addEdge(double x, double y) {
        if(elementSelection[CURRENT] != null && elementSelection[CURRENT] instanceof Node) { // fixme: bad design, could be solved by visitor pattern
            Node child = collision.getNodeAt(data.getNodeMap(), x,y);
            if(child != null) { // both source and target are valid
                Node parent = (Node)elementSelection[CURRENT];
                Edge newEdge = EdgeBuilder.buildEdge(parent, child); // edge builder
                data.addEdge(newEdge);
                canvas.getLayout().getChildren().add(newEdge.getCanvasElement());
                repaintNodes();
                BottomDisplayController.setEdges(data.getEdgeCount());
                focus();
                deselect(elementSelection[CURRENT]); // fixme: why do we want to deselect this?
            }
        }
    }

    /*
    * note: WE NEED TO DO THIS. Because when an operation is done, everything else is pushed back to the bottom of the
    *       drawing, which means that it would be receiving the least collision possibility since everything else is on
    *       top of it
    *
    * fixme: We need a better way to do this.
    * */
    private static void repaintNodes() {
        for(Node node : data.getNodeMap().values())
            canvas.getLayout().getChildren().remove(node.getNode());
        for(Node node :data.getNodeMap().values())
            canvas.getLayout().getChildren().add(node.getNode());
    }

    public static void addEdge(String nodeId, Edge edge) {
        if(findNode(nodeId) != null) { // catch if invalid node
           // CanvasData.addEdge(nodeId, edge); // add to data
            // do some other stuff
            canvas.getLayout().getChildren().add(edge.getCanvasElement()); // draw it on canvas
            BottomDisplayController.setEdges(data.getEdgeCount());
            //toggleName(); // refactor this. Basically, this computes the whole map again and again, when it should just toggle the new node
            focus();
        }
    }

    public static void removeEdge(Edge edge) {
        canvas.getLayout().getChildren().remove(edge.getCanvasElement());
        data.removeEdge(edge);
        deselect(edge);
        BottomDisplayController.setEdges(data.getEdgeCount());
    }

    /*
    * note: used by command
    * */
    public static void removeNode(int order) {
        Node node = findNode(order);
        if(node != null)
            removeNode(node);
    }

    /*
     * note: used by command
     * */
    public static void searchNode(int order) {
        Node node = findNode(order);
        if(node != null) {
            select(node);
            return;
        }

        deselect(elementSelection[LAST]);
        ConsoleController.consoleWrite("Node with order " + order + " not found.");
    }

    /*
     * note: used by command
     * */
    public static Node findNode(int order) {
        for (Node node : data.getNodeMap().values()) {
            if(node.getOrder() == order) {
                return node;
            }
        }
        return null;
    }

    /*
     * note: used by command
     * */
    public static Node findNode(String id) {
        return data.getNodeMap().get(id);
    }


    public static void changeElementValue(int value) {
        if(elementSelection[CURRENT] != null) {
            elementSelection[CURRENT].setValue(value);
        }
    }

    public static void clearSketchPad() {
        for(Node node : data.getNodeMap().values()) {
            // note: dont call removeNode because there will be concurrency issues. Modifying a list while accessing it
            canvas.getLayout().getChildren().removeAll(node.getEdgeGuide(), node.getNode());
        }

        for(Edge edge : data.getEdgeMap().values()) {
            canvas.getLayout().getChildren().remove(edge.getCanvasElement());
        }

        data.clearData();
        BottomDisplayController.setEdges(0);
        BottomDisplayController.setNodes(0);
        BottomDisplayController.hideSelection();
        focus();
    }

    public static LinkedHashMap<String, Node> getNodeMap() {
        return data.getNodeMap();
    }

    public static void adjustEdges(Node node) {
        data.adjustEdge(node);
    }

    private static void findPlace() {
        //todo: Basically, find a place near this node that isnt filled yet so we can place it there.
        // make this a different class, this will just return a x and y
        // do last
        // hint: Could use golden ratio, like how sunflowers position its seeds
    }

    /*
    * Refactor: remove identify node, either make it edit element or select node
    *
    * select node basically just highlights the node, then makes it editable.
    * */

    public static void select(Element element) {
        changeSelection(element);
        if(element != null) {
            highlight(element.getId());
            BottomDisplayController.showSelection(element.getName(), element.getValue()); // show the bottom display tings
            element.getCanvasElement().requestFocus();
        }
    }

    private static void deselect(Element element) {
        if(element != null) {
            element.deselect();
            BottomDisplayController.hideSelection();
        }
    }

    private static void changeSelection(Element element) {
        elementSelection[LAST] = elementSelection[CURRENT];
        elementSelection[CURRENT] = element;
        deselect(elementSelection[LAST]);
    }

    // fixme:
//    private static void toggleName() {
//        new ToggleName(nodeMap, showLabel).execute();
//    }

    private static void toggleShow() {
        showLabel = !showLabel;
    }

    private static void focus() {
        canvas.getLayout().requestFocus();
    }

    private static boolean isCanvasClear(double x, double y) {
        return collision.nodeClear(data.getNodeMap(), x,y)
                && collision.edgeClear(data.getEdgeMap(), x,y);
    }
}