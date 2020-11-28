package sketchpad.model.canvaselement.edge;

import java.util.LinkedList;
import java.util.List;

/*
* Container for edge objects
*
* This container only belongs to one node
*
*
* note: Proposed Changes:
*       edgeList is a list of string id's
*
* */
public class Edges {

    private List<String> edgeList;
    private List<String> adjacentNodes;
    private int inDegree;
    private int outDegree;
    private String parentNode;
    private int degree; // degrees still belong to the node
    private boolean isAllUndirected;

    public Edges(String nodeId) {
        parentNode = nodeId;
        edgeList = new LinkedList<>();
        adjacentNodes = new LinkedList<>();
        inDegree = 0;
        outDegree = 0;
        degree = 0;
        isAllUndirected = true;
    }

    public void addEdge(Edge edge) {
        // handle assigning in and out for this edge
        handleAdd(edge);
    }

    public void removeEdge(Edge edge) {
        handleDelete(edge);
    }

    // Will most likely be used by traversing algorithms that would need to know connections.
    // Since those algorithms have access to the map of nodes, then we just need to store the id of those nodes.
    public List<String> getAdjacentNodes() {
        return adjacentNodes;
    }

    public List<String> getEdgeList() {
        return edgeList;
    }

    public List<String> edgeListClone() {
        LinkedList<String> deepCloneArray = new LinkedList<>();
        for(String edgeId : edgeList)
            if(!deepCloneArray.contains(edgeId))
                deepCloneArray.add(edgeId);

        return deepCloneArray;
    }

    public boolean isUndirected() {
        return isAllUndirected;
    }

    public int getDegree() {
        return degree;
    }

    public int getOutDegree() { // arrows pointing out
        return outDegree;
    }  // arrows pointing out
    public int getInDegree() { return inDegree; } // arrows pointing in

    public int getEdgeCount() {
        return edgeList.size();
    }

    private void handleAdd(Edge edge) {
        switch (edge.getType()) {
            case DIRECTED:
                // set in and out
                handleAddDirected(edge);
                isAllUndirected = false;
                break;
            case UNDIRECTED:
                adjacentNodes.add(edge.childId);
                ++outDegree;
                ++inDegree;
        }
        ++degree;
        edgeList.add(edge.getId());
    }

    private void handleDelete(Edge edge) {
        switch (edge.getType()) {
            case DIRECTED:
                // set in and out
                handleRemoveDirected(edge);
                //edgeList.remove(edge.getId()); // note: order matters for this removal
                //recalculateIsUndirected();
                break;
            case UNDIRECTED:
                --outDegree;
                --inDegree;
                //edgeList.remove(edge.getId()); // note: order doesnt matter for this remove
                adjacentNodes.remove(edge.childId); // question is, is it always the child id that is added.
        }
        --degree;
        edgeList.remove(edge.getId());
    }

    /*
    * check all edges if undirected still
    *
    * note: Might not be needed
    * */
//    private void recalculateIsUndirected() {
//        for(Edge edge : edgeList) {
//            if(edge.getType().equals(Edge.EdgeTypes.DIRECTED)) {
//                isAllUndirected = false;
//                return;
//            }
//        }
//        isAllUndirected = true;
//    }

    private void handleAddDirected(Edge edge) {
        if(parentNode.equals(edge.parentId)) {
            ++outDegree;
            adjacentNodes.add(edge.childId);
        }
        else
            ++inDegree;
    }

    private void handleRemoveDirected(Edge edge) {
        if(parentNode.equals(edge.parentId)) {
            adjacentNodes.remove(edge.childId);
            --outDegree;
        }
        else
            --inDegree;
    }
}
