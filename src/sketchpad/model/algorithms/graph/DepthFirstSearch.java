package sketchpad.model.algorithms.graph;

import sketchpad.constants.ColorScheme;
import sketchpad.controller.ConsoleController;
import sketchpad.model.algorithms.Algorithm;
import sketchpad.model.canvaselement.vertex.Node;

import java.util.LinkedList;
import java.util.List;

/*
* First algorithm:
*   Check if second selection is child of root
* */
public class DepthFirstSearch extends Algorithm {

    private List<String> visitedList;

    @Override
    public void setup() {

    }

    @Override
    public void execute() {
        if(elementSelection[0] != null && elementSelection[0] instanceof Node) {
            beforeSearch();
            search();
        }
        else
            ConsoleController.consoleWrite("Please select a root node");
    }

    private void search() {
        Node rootNode = (Node)elementSelection[0];
        Node endNode = (Node)elementSelection[1];

        someRecursiveStuff(rootNode, 0);
    }

    private void beforeSearch() {
        visitedList = new LinkedList<>();
    }

    private void someRecursiveStuff(Node node, int value) {
        if(node.getOrder() == value) {
            node.highlight(ColorScheme.Node.GREEN_NODE);
        }
        else {
            ConsoleController.consoleWrite("Element not found");
        }
    }
}
