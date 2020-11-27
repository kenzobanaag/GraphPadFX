package sketchpad.commands.edges;

import sketchpad.commands.Command;
import sketchpad.commands.nodes.AddNode;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.model.canvaselement.edge.Directed;
import sketchpad.model.canvaselement.edge.Edge;
import sketchpad.model.canvaselement.edge.Undirected;
import sketchpad.model.canvaselement.vertex.Node;
import sketchpad.utils.TryParse;

import java.util.LinkedList;
import java.util.List;

public class AddEdge implements Command {

    private Edge edge;
    private String parentId;

    public AddEdge(String cmd) {
        String[] args = cmd.split(" ");
        int parentOrder = 0;
        int childOrder = 0;
        int value = 0;
        String type = "";
        if(args.length > 2) { // !edge n1 n2
            parentOrder = TryParse.tryParseInt(args[1]);
            childOrder = TryParse.tryParseInt(args[2]);
            System.out.println("2 edges");
        }
        else { // no input so create nodes
            createNodes();
            System.out.println("less than 2 edges");
            return;
        }
        if(args.length > 3) { // !edge n1 n2 value
            value = TryParse.tryParseInt(args[3]);
            System.out.println("3 edges");
        }
        if(args.length > 4) {  // !edge n1 n2 value U/D // these nodes are the order of the nodes
            type = args[4];
            System.out.println("4 edges");
        }
        // if we reach this, we know that we have all the values we needed.
        handleAddEdgeCommand(parentOrder, childOrder, value, type);
    }

    private void handleAddEdgeCommand(int parentOrder, int childOrder, int value, String typeStr) {
        Node parent = CanvasController.findNode(parentOrder);
        Node child = CanvasController.findNode(childOrder);

        if(parentOrder == -1 || childOrder == -1 || parent == null || child == null) { // invalid input
            createNodes();
        }
        else { // valid
            Edge.EdgeTypes type = typeStr.equals("U") ? Edge.EdgeTypes.UNDIRECTED : Edge.EdgeTypes.DIRECTED;
            createEdge(parent, child, value, type);
        }

        System.out.println("handled add edge");
    }

    private void createNodes() {
        new AddNode(50,50).execute();
        new AddNode(175,50).execute();

        List<Node> nodeMap = new LinkedList<>(CanvasController.getNodeMap().values());
        Node parent = nodeMap.get(nodeMap.size() - 2); // second to the last
        Node child = nodeMap.get(nodeMap.size() - 1); // last

        createEdge(parent, child, 0, Edge.EdgeTypes.UNDIRECTED);

        System.out.println("created edge");
    }

    public AddEdge(Node parent, Node child, int value, Edge.EdgeTypes type) {
        createEdge(parent, child, value, type);
    }

    public AddEdge(Node parent, Node child, Edge.EdgeTypes type) {
        createEdge(parent, child, 0, type);
    }

    public AddEdge(Node parent, Node child) {
        createEdge(parent, child, 0, Edge.EdgeTypes.UNDIRECTED);
    }

    private void createEdge(Node parent, Node child, int value, Edge.EdgeTypes type) {
        if(CanvasController.findNode(parent.getId()) != null &&
                CanvasController.findNode(child.getId()) != null) { //both nodes exist in the
            parentId = parent.getId();
        }
        else {
            parentId = "";
            edge = null;
            return;
        }

        switch (type) {
            case DIRECTED: edge = new Directed(parent, child);
                break;
            case UNDIRECTED: edge = new Undirected(parent, child);
        }
    }

    @Override
    public void execute() {
        CanvasController.addEdge(parentId, edge);
    }
}