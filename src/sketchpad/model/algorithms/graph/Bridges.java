package sketchpad.model.algorithms.graph;

import sketchpad.controller.ConsoleController;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.model.algorithms.Algorithm;
import sketchpad.model.canvaselement.Element;
import sketchpad.model.canvaselement.edge.Edge;
import sketchpad.utils.DeepClone;

/*
 * refactor: Refactor this implementation to something that doesnt rely on the component algorithm maybe?
 * */
public class Bridges extends Algorithm {

    private Components comp;
    private int originalCount;

    @Override
    public void setup() {
        comp = new Components();
        performComponentAlgorithm();
        originalCount = comp.getComponentCount();
    }

    @Override
    public void execute() {
        startAlgorithm();
        ConsoleController.consoleWrite("Bridges are highlighted");
    }

    private void startAlgorithm() {
        // we need a deep clone
        for(Edge edge : DeepClone.create(edgeMap)) {
            CanvasController.removeEdge(edge);
            performComponentAlgorithm();
            if(originalCount != comp.getComponentCount()) {
                edge.select();
            }
            CanvasController.addEdge(edge);
        }
    }

    /*
    * Did it this way because components print result to console
    * */
    private void performComponentAlgorithm() {
        comp.initVariables(CanvasController.getNodeMap(), CanvasController.getEdgeMap(), new Element[0]);
        comp.startAlgorithm();
    }
}
