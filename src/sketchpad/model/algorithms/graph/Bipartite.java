package sketchpad.model.algorithms.graph;

import sketchpad.controller.ConsoleController;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.model.algorithms.Algorithm;
import sketchpad.model.canvaselement.Element;

public class Bipartite extends Algorithm {

    private GreedyChromatic chromatic;
    private final int BIPARTITE = 2;

    @Override
    public void setup() {
        chromatic = new GreedyChromatic();
        chromatic.initVariables(CanvasController.getNodeMap(), CanvasController.getEdgeMap(), new Element[0]);
    }

    @Override
    public void execute() {
        chromatic.startAlgorithm();
        String consoleMsg = "";
        if(chromatic.getMinColors() == BIPARTITE) {
            chromatic.highlightNodes();
            consoleMsg = "Graph is bipartite";
        }
        else
            consoleMsg = "Graph is not bipartite";

        ConsoleController.consoleWrite(consoleMsg);
    }
}
