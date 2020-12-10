package sketchpad.model.algorithms.graph.componentinfo;

import javafx.scene.paint.Color;
import sketchpad.controller.ConsoleController;
import sketchpad.model.algorithms.Algorithm;
import sketchpad.model.canvaselement.vertex.Node;

import java.util.LinkedHashMap;
import java.util.Stack;

import static sketchpad.constants.ColorScheme.Node.*;

public class StronglyConnectedComponents extends Algorithm {

    private int id;
    private int sccCount;
    private static final int UNVISITED = -1;
    private Color[] colors;
    private int[] low;
    private Stack<Integer> sccStack;
    private LinkedHashMap<String, Integer> visitedMap;

    @Override
    public void setup() {
        id = 0;
        sccCount = 0;
        sccStack = new Stack<>();
        colors = new Color[] {GREEN_NODE, PURPLE_NODE, BLUE_NODE, GOLD_NODE, BLACK_NODE, INVERTED_NODE, BROWN_NODE,
                LIGHT_NODE, ORANGE_NODE};
        visitedMap = new LinkedHashMap<>();
        for(String nodeId : nodeMap.keySet()) {
            visitedMap.put(nodeId, UNVISITED);
        }

        low = new int[nodeMap.size()];
    }

    @Override
    public void execute() {
        startAlgorithm();
        ConsoleController.consoleWrite("Strongly Connected Component Count: " + sccCount);
    }

    // note: problem was count will not always be in sync with id. we need them to be in sync
    public void startAlgorithm() {
        for(String nodeId : visitedMap.keySet()) {
            if(visitedMap.get(nodeId) == UNVISITED) {
                performDFS(nodeMap.get(nodeId), id);
            }
        }
    }

    private void performDFS(Node node, int at) {
        visitedMap.put(node.getId(), id);
        sccStack.push(id);
        low[at] = id++;
        for(String adjacentId : node.getEdges().getAdjacentNodes()) {
            if(visitedMap.get(adjacentId) == UNVISITED)
                performDFS(nodeMap.get(adjacentId), id);
            if(sccStack.contains(visitedMap.get(adjacentId))) {
                low[at] = Math.min(low[at], low[visitedMap.get(adjacentId)]);
            }
        }

        if(low[at] == visitedMap.get(getNVal(at))) {
            for(Integer x = sccStack.pop(); ; x = sccStack.pop()) {
                Node poppedNode = nodeMap.get(getNVal(x));
                if(poppedNode != null)
                    poppedNode.highlight(colors[sccCount % colors.length]); // don't go out of bounds
                if(x == at) break;
            }
            ++sccCount;
        }
    }

    /*
    * We need to do this because we need a specific id bound to the index.
    * */
    private String getNVal(int ith) {
        for(String nodeId : visitedMap.keySet()) {
            if((visitedMap.get(nodeId)) == ith)
                return nodeId;
        }
        return null;
    }

    public int getComponentCount() {
        return sccCount;
    }
}
