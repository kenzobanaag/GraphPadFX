package sketchpad.model.algorithms.graph;

import sketchpad.constants.ColorScheme;
import sketchpad.controller.ConsoleController;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.model.algorithms.Algorithm;
import sketchpad.model.algorithms.graph.componentinfo.StronglyConnectedComponents;
import sketchpad.model.canvaselement.Element;
import sketchpad.model.canvaselement.edge.Edge;
import sketchpad.model.canvaselement.vertex.Node;
import sketchpad.utils.DeepClone;

/*
 * note: Bridges in directed graphs are the edges that when removed, increases or decreases the size of strongly
 *       connected components
 *
 * note: Bridges in undirected graphs are edges that change the total component size when removed.
 * */
public class Bridges extends Algorithm {

    private StronglyConnectedComponents comp;
    private int originalCount;

    @Override
    public void setup() {
        comp = new StronglyConnectedComponents();
        performComponentAlgorithm();
        originalCount = comp.getComponentCount();
    }

    @Override
    public void execute() {
        startAlgorithm();
        deColorAllNodes();
        ConsoleController.consoleWrite("Bridges are highlighted");
    }

    private void deColorAllNodes() {
        for(Node node : nodeMap.values()) {
            node.highlight(ColorScheme.Node.NODE);
        }
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
