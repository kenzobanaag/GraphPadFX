package sketchpad.controller.canvas;

import sketchpad.constants.Sizes;
import sketchpad.model.canvaselement.edge.Edge;
import sketchpad.model.canvaselement.vertex.Node;
import sketchpad.model.collision.Collision;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class CanvasData {

    private HashMap<String, Edge> edgeMap; //adjacency map, string is nodeId
    private LinkedHashMap<String, Node> nodeMap;
    private static CanvasData instance;
    private int order = 0;
    private final int OFFSET = Sizes.Node.RADIUS;


    private CanvasData() {
        edgeMap = new HashMap<>();
        nodeMap = new LinkedHashMap<>();
    }

    protected static synchronized CanvasData getInstance() {
        if(instance == null)
            instance = new CanvasData();

        return instance;
    }

    protected void addNode(Node node) {
        node.setOrder(order++);
        nodeMap.put(node.getId(), node);
    }

    protected void removeNode(Node node) {
        // remove edges first
        List<String> edgeIds = node.getEdges().edgeListClone(); // we need to deep clone the list, because the reference gets deleted.
        for(String edgeId : edgeIds) {
            CanvasController.removeEdge(edgeMap.get(edgeId));
        }
        nodeMap.remove(node.getId());
        if(nodeMap.size() == 0)
            order = 0;
    }

    protected void addEdge(Edge edge) {
        edgeMap.put(edge.getId(), edge);
        // we need to add the edge on both the parent and child as well
        nodeMap.get(edge.getParentId()).getEdges().addEdge(edge);
        nodeMap.get(edge.getChildId()).getEdges().addEdge(edge);
    }

    protected void removeEdge(Edge edge) {
        // remove node references of this edge
        nodeMap.get(edge.getParentId()).getEdges().removeEdge(edge);
        nodeMap.get(edge.getChildId()).getEdges().removeEdge(edge);
        edgeMap.remove(edge.getId());
    }

    /*
    * At most, we will only be adjusting one end. unless its a loop?
    *
    * take in edge and the node that moved?
    *
    * This way we know where the edge is going to relocate
    * */
    protected void adjustEdge(Node node) {
        // is parent start xy? or end?

        // check for parallel edges first?
        for(String edgeId : node.getEdges().getEdgeList()) {
            edgeMap.get(edgeId).adjustEdge(node.getCanvasElement().getLayoutX() + OFFSET,
                    node.getCanvasElement().getLayoutY() + OFFSET, node.getId());
        }
    }

    protected int getEdgeCount() { return edgeMap.size(); }

    protected int getNodeCount() {
        return nodeMap.size();
    }

    protected void clearData() {
        edgeMap.clear();
        nodeMap.clear();
        order = 0;
    }

    protected HashMap<String, Edge> getEdgeMap() {
        return edgeMap;
    }

    protected LinkedHashMap<String, Node> getNodeMap() {
        return nodeMap;
    }
}
