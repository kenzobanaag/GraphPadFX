package sketchpad.model.canvaselement.edge;

import javafx.scene.input.KeyCode;
import sketchpad.controller.ProgramEventController;
import sketchpad.model.canvaselement.Element;
import sketchpad.model.canvaselement.vertex.Node;

public abstract class EdgeBuilder {

    // accept a macro as well?
    public static Edge buildEdge(Node parent, Node child) {
        if(parent.getId().equals(child.getId())) {
            // loop
            // if ProgramEventController.shift is pressed, undirected, else directed
            return new UndirectedLoop(parent, child);
        }
        else {
            // line
        }
        // check program event macro
        if(ProgramEventController.getCurrentKey() != null) {
            if(ProgramEventController.getCurrentKey().equals(KeyCode.CONTROL))
                return new Directed(parent, child);
        }

        // this will do for now
        return new Undirected(parent, child);
    }
}
