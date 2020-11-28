package sketchpad.model.canvaselement.vertex;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import sketchpad.model.canvaselement.Element;
import sketchpad.model.canvaselement.edge.Edges;


public interface Node extends Element {
    Pane getNode();
    void showName();
    void hideName();
    int getOrder();
    String getId();
    void setOrder(int order);
    void deselect();
    void select();
    int getValue();
    void showValue();
    Line getEdgeGuide();
    Edges getEdges();
    void setValue(int newValue);
}
