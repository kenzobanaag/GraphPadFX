package sketchpad.model.canvaselement.vertex;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import sketchpad.commands.nodes.DeleteNode;
import sketchpad.constants.ColorScheme;
import sketchpad.constants.Sizes;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.controller.ProgramEventController;
import sketchpad.model.canvaselement.DisplayTypes;
import sketchpad.model.canvaselement.edge.Edges;

import java.util.UUID;

import static sketchpad.constants.ColorScheme.Edge.EDGE_COLOR;
import static sketchpad.constants.ColorScheme.Node.*;
import static sketchpad.constants.Sizes.Edge.EDGE_STROKE;

public class Vertex implements Node {

    private Shape shape;
    private final String id;
    private int order;
    private Label label;
    private double radius;
    private int value;
    private final StackPane node;
    private Line edgeGuide;
    private Edges edges;
    private Paint currentFill;

    private final String SELECTION_STYLE = String.format("-fx-font-size: %d; " +
            "-fx-font-family: Calibri;" +
            "-fx-font-weight: Bold;" +
            "-fx-text-fill: %s;", Sizes.Node.LABEL_SIZE, NODE_TEXT_STR);

    private final String EDGE_STYLE = String.format("-fx-stroke: %s;" +
            "-fx-stroke-width: %d", EDGE_COLOR, EDGE_STROKE);

    public Vertex(double x, double y) {
        currentFill = NODE;
        initShape();
        initLabel();
        value = 0;
        id = UUID.randomUUID().toString();
        node = new StackPane(shape, label);
        node.relocate(x - radius,y - radius);
        node.toFront();
        edgeGuide = new Line(x, y, x, y);
        edgeGuide.setStrokeWidth(EDGE_STROKE);
        edgeGuide.setStroke(SELECTED);
        setListeners();
        edges = new Edges(id);
    }

    private void initLabel() {
        label = new Label();
        label.setVisible(false);
        label.setStyle(SELECTION_STYLE);
    }

    private void initShape() {
        radius = Sizes.Node.RADIUS;
        shape = new Circle(radius, currentFill);
    }

    private void setListeners() {
        EventHandler<MouseEvent> clickHandler = event -> {
            if(event.getButton().equals(MouseButton.SECONDARY)) {  // right click
                new DeleteNode(this).execute();
            }
            else if(event.getButton().equals(MouseButton.PRIMARY)) {
                CanvasController.select(this);
            }
        };
        node.addEventFilter(MouseEvent.MOUSE_PRESSED, clickHandler);

        node.setOnMouseDragged(event -> {
            if(ProgramEventController.isMovable()) { // || or some other macro like ctrl would make directed edges.
                double x = node.getLayoutX() + event.getX() - radius;
                double y = node.getLayoutY() + event.getY() - radius;
                node.relocate(x, y);
                resetEdgeGuide(event.getSceneX(), event.getSceneY());
                CanvasController.adjustEdges(this);
            }
            else {
                // set line end x,y
                edgeGuide.setEndX(event.getSceneX());
                edgeGuide.setEndY(event.getSceneY());
            }
            node.toFront();
        });

        EventHandler<MouseEvent> onMouseReleased = event -> {
            if(ProgramEventController.isMovable()) {
                // todo: check for collision then do some correction, while not clear.
            }
            else {
                // find node with x,y location
                // bind selection and collided node
                CanvasController.addEdge(event.getSceneX(), event.getSceneY());
            }
            edgeGuide.setEndX(node.getLayoutX() + radius);
            edgeGuide.setEndY(node.getLayoutY() + radius);
        };

        node.addEventHandler(MouseEvent.MOUSE_RELEASED, onMouseReleased);

        EventHandler<KeyEvent> keyPressed = event -> {
            switch (event.getCode()) {
                case DIGIT1: currentFill = NODE;
                    break;
                case DIGIT2: currentFill = GREEN_NODE;
                    break;
                case DIGIT3: currentFill = GOLD_NODE;
                    break;
                case DIGIT4: currentFill = INVERTED_NODE;
                    break;
                case DIGIT5: currentFill = BLACK_NODE;
                    break;
                case DIGIT6: currentFill = ORANGE_NODE;
                    break;
                case DIGIT7: currentFill = BLUE_NODE;
                    break;
                case DIGIT8: currentFill = PURPLE_NODE;
                    break;
                case DIGIT9: currentFill = BROWN_NODE;
                    break;
                case DIGIT0: currentFill = LIGHT_NODE;
                    break;
                default: return;
            }
            deselect();
        };

        node.addEventHandler(KeyEvent.KEY_PRESSED, keyPressed);
    }

    private void resetEdgeGuide(double x, double y) {
        edgeGuide.setStartX(x);
        edgeGuide.setStartY(y);
        edgeGuide.setEndX(x);
        edgeGuide.setEndY(y);
    }

    public void setOrder(int num) {
        order = num;
    }

    public int getOrder() { return order; }

    public String getId() {
        return id;
    }

    @Override
    public javafx.scene.Node getCanvasElement() {
        return node;
    }

    @Override
    public void highlight(Paint paintStyle) {
        shape.setFill(paintStyle);
    }

    @Override
    public String getName() {
        return order+"";
    }

    public void select() {
        shape.setFill(ColorScheme.Node.SELECTED);
    }

    public void deselect() {
        shape.setFill(currentFill);
    }

    @Override
    public int getValue() {
        return value;
    }

    public Line getEdgeGuide() {
        return edgeGuide;
    }

    @Override
    public Edges getEdges() {
        return edges;
    }

    @Override
    public void setValue(int newValue) {
        value = newValue;
    }

    @Override
    public void showLabel(DisplayTypes type) {
        label.setVisible(true);
        String labelStr = "";
        switch (type) {
            case NAME: labelStr = order+"";
                break;
            case VALUE: labelStr = value+"";
                break;
            case DEGREE: labelStr = edges.getDegree()+"";
                break;
        }
        label.setText(labelStr);
    }

    @Override
    public void hideLabel() {
        label.setVisible(false);
    }
}