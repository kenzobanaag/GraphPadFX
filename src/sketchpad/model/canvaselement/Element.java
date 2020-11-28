package sketchpad.model.canvaselement;

import javafx.scene.Node;
import javafx.scene.paint.Paint;

public interface Element {
    void select();
    void deselect();
    String getId();
    Node getCanvasElement();
    void highlight(Paint paintStyle);
    String getName();
    int getValue();
    void setValue(int newValue);
    void showLabel(DisplayTypes type);
    void hideLabel();
}
