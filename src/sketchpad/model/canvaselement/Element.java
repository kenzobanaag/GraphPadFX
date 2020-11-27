package sketchpad.model.canvaselement;

import javafx.scene.Node;

public interface Element {
    void select();
    void deselect();
    String getId();
    Node getCanvasElement();
}
