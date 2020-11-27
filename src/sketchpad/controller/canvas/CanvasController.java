package sketchpad.controller.canvas;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.paint.Paint;
import sketchpad.commands.nodes.AddNode;
import sketchpad.commands.graph.ToggleName;
import sketchpad.controller.BottomDisplayController;
import sketchpad.controller.ConsoleController;
import sketchpad.model.canvaselement.Element;
import sketchpad.model.collision.Collision;
import sketchpad.model.canvaselement.edge.Edge;
import sketchpad.model.canvaselement.edge.Undirected;
import sketchpad.model.canvaselement.vertex.Node;
import sketchpad.view.Canvas;

import java.util.HashMap;
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
    * */

    private static int elements = 0;
    private static LinkedHashMap<String, Node> nodeMap;
    private static HashMap<String, Edge> edgeMap; //adjacency map, string is nodeId
    private static Canvas canvas;
    private static CanvasController instance;
    private static Node selected;
    private static Node lastSelection;
    private static boolean show = false;
    private static Node[] selectionList;
    private static Element[] elementSelection;
    private static int CURRENT = 0, LAST = 1;

    private static Collision collision;

    public static void init(Canvas canvas) {
        if(instance == null)
            instance = new CanvasController(canvas);
    }

    private CanvasController(Canvas canvas) {
        nodeMap = new LinkedHashMap<>();
        edgeMap = new HashMap<>();
        CanvasController.canvas = canvas;
        setEvents(canvas);
        collision = new Collision();
        focus();
        selectionList = new Node[SELECTION_COUNT];
        elementSelection = new Element[SELECTION_COUNT];
    }

    private void setEvents(Canvas canvas) {
        EventHandler<MouseEvent> clickHandler = event -> {
            double x = event.getX();
            double y = event.getY();
            if(event.getButton().equals(MouseButton.PRIMARY)) { // left click
                if(collision.isClear(nodeMap, x, y) && collision.edgeClear(edgeMap, x,y)) {// check collision
                    new AddNode(x, y).execute();
                    deselect(selected);
                    event.consume();
                }
            }
            else if(event.getButton().equals(MouseButton.SECONDARY)) {  // right click
                if(collision.isClear(nodeMap, x, y)) {// check if on top of node
                    deselect(selected);
                    deselect(elementSelection[CURRENT]);
                }
            }
        };

        EventHandler<KeyEvent> keyHandler = event -> {
          if(event.getCode().equals(KeyCode.ALT)) {
              toggleShow();
              toggleName();
          }
        };

        canvas.getLayout().addEventFilter(MouseEvent.MOUSE_PRESSED, clickHandler);
        canvas.getLayout().addEventFilter(KeyEvent.KEY_PRESSED, keyHandler);
    }

    public static void addNode(Node node) { // todo: Should also handle collisions. We know that these are console adds so we need to find a place for new nodes
        canvas.getLayout().getChildren().addAll(node.getEdgeGuide(), node.getNode());
        node.setOrder(elements);
        nodeMap.put(node.getId(), node);
        BottomDisplayController.setNodes(nodeMap.size());
        incrementNodeOrder();
        toggleName(); // todo: refactor this. Basically, this computes the whole map again and again, when it should just toggle the new node
        focus();
    }

    public static void addEdge(double x, double y) {
        // get current selected
        // check for collisions at the given x,y
        // if exists, add edge. If not. Dont do anything.
        if(selected != null) { // we need to have a selection
            Node child = collision.getNodeAt(nodeMap, x, y);
            if(child != null) { // we have 2 values. Create edge on these two values
                buildEdge(child);
                // add edge to data
            }
        }
    }

    private static void buildEdge(Node child) {
        //if(!ProgramEventController.isMovable()) { // shift is pressed, create undirected edge
            buildUndirected(child);
       //}
        // if(.isPressed) // some other macro, then create directed.
    }

    private static void buildUndirected(Node child) {
        Edge undirectedEdge = new Undirected(selected, child);
        edgeMap.put(undirectedEdge.getId(), undirectedEdge);
        selected.getEdges().addEdge(undirectedEdge);
        child.getEdges().addEdge(undirectedEdge);
        addEdge(undirectedEdge.getId(), undirectedEdge);
        CanvasData.addEdge(undirectedEdge);
        canvas.getLayout().getChildren().add(undirectedEdge.getCanvasElement()); // draw it on canvas
        repaintNodes(); // put all nodes
        BottomDisplayController.setEdges(CanvasData.getEdgeCount());
        toggleName(); // todo: refactor this. Basically, this computes the whole map again and again, when it should just toggle the new node
        focus();
        deselect(selected);
    }

    public static void setStartNode(Node start) {
        //startNode = start;
        selectionList[0] = start; //note: selection list might be a nicer approach?, it doesnt bind it to a selected, last selected, or parent and child
        // we can also set, selection list size...... so we can chooose different number of selections
    }

    /*
    * note: WE NEED TO DO THIS. Because when an operation is done, everything else is pushed back to the bottom of the drawing, which means that it would be receiving the least collision possibility since everything else is on top of it
    *
    * fixme: We need a better way to do this.
    *
    * */
    private static void repaintNodes() {
        for(Node node : nodeMap.values())
            canvas.getLayout().getChildren().remove(node.getNode());
        for(Node node : nodeMap.values())
            canvas.getLayout().getChildren().add(node.getNode());
    }

    public static void addEdge(String nodeId, Edge edge) {
        if(findNode(nodeId) != null) { // catch if invalid node
           // CanvasData.addEdge(nodeId, edge); // add to data
            // do some other stuff
            canvas.getLayout().getChildren().add(edge.getCanvasElement()); // draw it on canvas
            BottomDisplayController.setEdges(CanvasData.getEdgeCount());
            toggleName(); // todo: refactor this. Basically, this computes the whole map again and again, when it should just toggle the new node
            focus();
        }
    }

    public static void removeEdge(String nodeId, Edge edge) {
        CanvasData.removeEdge(nodeId,edge);

        canvas.getLayout().getChildren().remove(edge.getCanvasElement());
        BottomDisplayController.setEdges(CanvasData.getEdgeCount());
    }

    public static void removeNode(Node node) {
        deselect(node);
        canvas.getLayout().getChildren().removeAll(node.getEdgeGuide(), node.getNode());
        handleListRemove(node.getId());
        BottomDisplayController.setNodes(nodeMap.size());
        focus();
    }

    public static void removeNode(int order) {
        Node node = findNode(order);
        if(node != null)
            removeNode(node);
    }

    public static void searchNode(int order) {
        Node node = findNode(order);
        if(node != null) {
            node.select();
            identifyNode(node);
            return;
        }

        deselect(lastSelection);
        ConsoleController.consoleWrite("Node with order " + order + " not found.");
    }

    public static Node findNode(int order) {
        for (Node node : nodeMap.values()) {
            if(node.getOrder() == order) {
                return node;
            }
        }
        return null;
    }

    public static Node findNode(String id) {
        return nodeMap.get(id);
    }

    /*
    * Highlight a node in the graph while changing selection
    *
    * fixme: I think identify node should be the same as select node.
    * */
    public static void identifyNode(Node node) {
        changeSelection(node);
        select(node);
    }

    public static void clearSketchPad() {
        for(Node node : nodeMap.values()) {
            // note: dont call removeNode because there will be concurrency issues. Modifying a list while accessing it
            canvas.getLayout().getChildren().removeAll(node.getEdgeGuide(), node.getNode());
        }
        nodeMap.clear();
        elements = 0;
        BottomDisplayController.hideSelection();
        BottomDisplayController.setNodes(nodeMap.size());
        focus();
    }

    /*
     * Highlight vs select
     *
     * Select will put it on the canvas' selection
     *
     * Highlight will only change the nodes' color
     * */
    public static void highlight(String nodeId) {
        if(nodeMap.containsKey(nodeId))
            nodeMap.get(nodeId).select();
    }

    public static void highlight(String nodeId, Paint paintStyle) {
        if(nodeMap.containsKey(nodeId))
            nodeMap.get(nodeId).color(paintStyle);
    }

    public static void highlightElement(String elementId) {
        if(nodeMap.containsKey(elementId))
            nodeMap.get(elementId).select();
        else if(edgeMap.containsKey(elementId))
            edgeMap.get(elementId).select();
    }

    public static LinkedHashMap<String, Node> getNodeMap() {
        return nodeMap;
    }

    private static void findPlace() {
        //todo: Basically, find a place near this node that isnt filled yet.
        // make this a different class, this will just return a x and y
        // do last
        // hint: Could use golden ratio, like how sunflowers position its seeds
    }

    private static void incrementNodeOrder() {
        ++elements;
    }

    private static void handleListRemove(String id) {
        nodeMap.remove(id);
        if(nodeMap.size() == 0)
            elements = 0;
    }

    private static void deselect(Node node) {
        if(node != null) {
            node.deselect();
            BottomDisplayController.hideSelection();
        }
    }

    /*
    * Refactor
    * */
    private static void select(Node node) {
        if(node != null) {
            highlight(node.getId());
            BottomDisplayController.showSelection(node.getOrder());
            node.getNode().requestFocus();
        }
    }

    private static void changeSelection(Node node) {
        lastSelection = selected;
        selected = node;
        deselect(lastSelection);
    }

    /*
    * We have a different function for selecting edges tho...
    *
    * That means we also need a different deselect
    * */
    public static void select(Element element) {
        changeSelection(element);
        if(element != null) {
            highlightElement(element.getId());
            //BottomDisplayController.showSelection(); // show the bottom display tings
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
        // deselect
        deselect(elementSelection[LAST]);
    }

    private static void toggleName() {
        new ToggleName(nodeMap, show).execute();
    }

    private static void toggleShow() {
        show = !show;
    }

    private static void focus() {
        canvas.getLayout().requestFocus();
    }
}