package sketchpad.model.canvaselement.edge;

import javafx.scene.input.KeyCode;
import sketchpad.controller.ProgramEventController;
import sketchpad.model.canvaselement.vertex.Node;

public abstract class EdgeBuilder {

    public static Edge buildEdge(Node parent, Node child) {
        if(ProgramEventController.getCurrentKey() != null) {
            if(ProgramEventController.getCurrentKey().equals(KeyCode.CONTROL)) {
                if(parent.getId().equals(child.getId())) {
                    return new DirectedLoop(parent, child);
                }
                else {
                    return new Directed(parent, child);
                }
            }
            else if(ProgramEventController.getCurrentKey().equals(KeyCode.SHIFT)) {
                if(parent.getId().equals(child.getId())) {
                    return new UndirectedLoop(parent, child);
                }
                else {
                    return new Undirected(parent, child);
                }
            }
        }
        // this will never run, but for safety's sake
        return new Undirected(parent, child);
    }
}
