package sketchpad.model.algorithms.graph.componentinfo;

import sketchpad.controller.ConsoleController;
import sketchpad.model.algorithms.Algorithm;
import sketchpad.model.canvaselement.vertex.Node;
import java.util.HashMap;
import java.util.LinkedHashMap;

/*
* note: only works for undirected
*
* Might need to add a 2d array to compute strongly connected and connected components for digraphs
* */
public class Components extends Algorithm {

    private HashMap<String, Boolean> visitedMap;
    private int componentCount;

    @Override
    public void setup() {
        visitedMap = new LinkedHashMap<>();
        for(String nodeId : nodeMap.keySet()) {
            visitedMap.put(nodeId, false);
        }
        componentCount = 0;
    }

    @Override
    public void execute() {
        startAlgorithm();
        ConsoleController.consoleWrite("Number Of Components: " + componentCount);
    }

    public void startAlgorithm() {
        for(String nodeId : visitedMap.keySet()) {
            if(!visitedMap.get(nodeId)) {
                performDFS(nodeMap.get(nodeId));
                ++componentCount;
            }
        }
    }

    private void performDFS(Node node) {
        visitedMap.put(node.getId(), true); // node now visited
        for(String adjacentNode : node.getEdges().getAdjacentNodes()) {
            if(!visitedMap.get(adjacentNode)) {
                performDFS(nodeMap.get(adjacentNode));
            }
        }
    }

    public int getComponentCount() {
        return componentCount;
    }
}
