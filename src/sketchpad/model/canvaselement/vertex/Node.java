package sketchpad.model.canvaselement.vertex;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import sketchpad.model.canvaselement.edge.Edges;


public interface Node {
    Pane getNode();
    void showName();
    void hideName();
    int getOrder();
    String getId();
    void setOrder(int order);
    void deselect();
    void select();
    void color(Paint paintStyle);
    int getValue();
    void showValue();
    Line getEdgeGuide();
    Edges getEdges();
}
