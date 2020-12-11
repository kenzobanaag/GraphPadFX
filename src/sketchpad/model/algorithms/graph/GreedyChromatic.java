package sketchpad.model.algorithms.graph;

import javafx.scene.paint.Color;
import sketchpad.controller.ConsoleController;
import sketchpad.model.algorithms.Algorithm;
import sketchpad.model.canvaselement.vertex.Node;

import java.util.Arrays;

import static sketchpad.constants.ColorScheme.Node.*;

/*
* note: This algorithm works but, order matters.
* */
public class GreedyChromatic extends Algorithm {

    private int[] nodeColors;
    private int[] lastSolution;
    private boolean[] available;
    private final int UNASSIGNED = -1;
    private Color[] colors;
    private int maxColors, lastSolutionColors;

    @Override
    public void setup() {
        maxColors = 0;
        lastSolutionColors = nodeMap.size() > 0 ? Integer.MAX_VALUE : -1;
        nodeColors = lastSolution = new int[nodeMap.size()];
        available = new boolean[nodeMap.size()];
        colors = new Color[] {GREEN_NODE, GOLD_NODE, INVERTED_NODE, BLACK_NODE, ORANGE_NODE, BLUE_NODE, PURPLE_NODE,
                BROWN_NODE, LIGHT_NODE};
    }

    @Override
    public void execute() {
        startAlgorithm();
        highlightNodes();
        ConsoleController.consoleWrite("Minimum colors: " + (lastSolutionColors+1));
    }

    public void startAlgorithm() {
        for(int i = 0; i < nodeMap.size(); i++) { // brute force: we want to do this to assure that were getting the min colors.
            Arrays.fill(nodeColors, UNASSIGNED);
            Arrays.fill(available, true);

            nodeColors[i] = 0; // replace with i later

            for(int j = 0; j < nodeMap.size(); j++) {
                if(i != j && getNVal(j) != null) {
                    for(String nodeId : getNVal(j).getEdges().getAdjacentNodes()) {
                        if(nodeColors[getOrder(nodeId)] != UNASSIGNED)
                            available[nodeColors[getOrder(nodeId)]] = false;
                    }

                    int colorNumber = 0;
                    for(;colorNumber < nodeMap.size(); colorNumber++) {
                        if(available[colorNumber])
                            break;
                    }
                    nodeColors[j] = colorNumber;
                    maxColors = Math.max(maxColors, colorNumber);

                    Arrays.fill(available, true);
                }
            }

            if(lastSolutionColors > maxColors) {
                lastSolutionColors = maxColors;
                lastSolution = Arrays.copyOf(nodeColors, nodeColors.length);
            }
            maxColors = 0;
        }
    }

    public void highlightNodes() {
        for (int j = 0; j < nodeMap.size(); j++) {
            if(getNVal(j) != null)
                getNVal(j).highlight(colors[lastSolution[j] % colors.length]);
        }
    }

    public int getMinColors() {
        return lastSolutionColors + 1; // + 1 because we start at 0;
    }

    // get node at n. Since this is a linkedHashMap, we can assure that the order doesnt change
    private Node getNVal(int n) {
        int count = 0;
        for(Node node : nodeMap.values()) {
            if(count == n)
                return node;
            ++count;
        }
        return null;
    }

    private int getOrder(String nodeId) {
        int count = 0;
        for(Node node : nodeMap.values()) {
            if(nodeId.equals(node.getId()))
                return count;
            ++count;
        }
        return -1; // this should never fire
    }
}
