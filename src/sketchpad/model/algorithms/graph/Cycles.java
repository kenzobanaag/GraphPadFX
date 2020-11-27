package sketchpad.model.algorithms.graph;

import sketchpad.model.algorithms.Algorithm;
import sketchpad.model.canvaselement.edge.Edges;
import sketchpad.model.canvaselement.vertex.Node;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Cycles extends Algorithm {
    protected Cycles(LinkedHashMap<String, Node> nodeMap, HashMap<String, Edges> edgeMap, Node selected, Node lastSelected) {
        super(nodeMap, edgeMap, selected, lastSelected);
    }

    @Override
    public void execute() {
        checkForCycles();
    }

    private void checkForCycles() {

    }
}
