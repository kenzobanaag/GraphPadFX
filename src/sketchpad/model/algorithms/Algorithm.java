package sketchpad.model.algorithms;

import sketchpad.model.canvaselement.Element;
import sketchpad.model.canvaselement.edge.Edge;
import sketchpad.model.canvaselement.edge.Edges;
import sketchpad.model.canvaselement.vertex.Node;

import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class Algorithm {

    //take in node list and edge list, selected(end node), last selected(start node)
    protected LinkedHashMap<String, Node> nodeMap;
    protected HashMap<String, Edge> edgeMap;
    protected Element[] elementSelection;

    public void initVariables(LinkedHashMap<String, Node> nodeMap, HashMap<String, Edge> edgeMap, Element[] selection) {
        this.nodeMap = nodeMap;
        this.edgeMap = edgeMap;
        elementSelection = selection;
        setup();
    }

    // call to setup before executing
    public abstract void setup();

    // since we pretty much have global access on controllers, we dont need to return anything, but just send changes to
    // the canvas controller.
    public abstract void execute();
}
