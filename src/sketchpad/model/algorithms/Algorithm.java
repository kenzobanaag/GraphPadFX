package sketchpad.model.algorithms;

import sketchpad.model.canvaselement.edge.Edges;
import sketchpad.model.canvaselement.vertex.Node;

import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class Algorithm {

    //take in node list and edge list, selected(end node), last selected(start node)
    protected LinkedHashMap<String, Node> nodeMap;
    protected HashMap<String, Edges> edgeMap;
    protected Node startNode;
    protected Node endNode;

    protected Algorithm(LinkedHashMap<String, Node> nodeMap, HashMap<String, Edges> edgeMap, Node selected,
                        Node lastSelected) {
    }

    // since we pretty much have global access on controllers, we dont need to return anything, but just send changes to
    // the canvas controller.
    public abstract void execute();
}
