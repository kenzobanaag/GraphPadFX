package sketchpad.model.canvaselement.edge;

import java.util.HashMap;
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

    private List<String> edgeList; // refactor: see if we could remove this
    private List<String> adjacentNodes;
    private HashMap<String, List<String>> edgeMap;
    private int inDegree;
    private int outDegree;
    private String parentNode;
    private int degree; // degrees still belong to the node
    private boolean isAllUndirected;

    public Edges(String nodeId) {
        parentNode = nodeId;
        edgeList = new LinkedList<>();
        adjacentNodes = new LinkedList<>();
        edgeMap = new HashMap<>();
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
    }  // refactor: see if we could remove this

    private void handleAdd(Edge edge) {
        switch (edge.getType()) {
            case DIRECTED:
                // set in and out
                handleAddDirected(edge);
                isAllUndirected = false;
                break;
            case UNDIRECTED:
                handleAddUndirected(edge); // fixme: doesnt really mean that this node is always the child
                ++outDegree;
                ++inDegree;
        }
        ++degree;
        edgeList.add(edge.getId());  // refactor: see if we could remove this
        // we want to know if there are parallel edges.
        handleParallelEdgesAdd(edge);
    }

    private void handleDelete(Edge edge) {
        switch (edge.getType()) {
            case DIRECTED:
                // set in and out
                handleRemoveDirected(edge);
                break;
            case UNDIRECTED:
                --outDegree;
                --inDegree;
                handleRemoveUndirected(edge); // question is, is it always the child id that is added.
        }
        --degree;
        edgeList.remove(edge.getId());  // refactor: see if we could remove this
        // handle removing
        handleParallelEdgesRemove(edge);
    }

    private void handleAddUndirected(Edge edge) {
        if(!edge.getParentId().equals(parentNode))
            adjacentNodes.add(edge.getParentId());
        else if (!edge.getChildId().equals(parentNode))
            adjacentNodes.add(edge.getChildId());
    }

    private void handleRemoveUndirected(Edge edge) {
        if(!edge.getParentId().equals(parentNode))
            adjacentNodes.remove(edge.getParentId());
        else if (!edge.getChildId().equals(parentNode))
            adjacentNodes.remove(edge.getChildId());
    }

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

    private String calculateKey(Edge edge) {

        String[] edgePairId = edge.edgeName.split("-");

        if(edgePairId[0].compareTo(edgePairId[1]) > 0) //[0] > [1]
            return edgePairId[1] + edgePairId[0];
        else // [0] < [1] || [0] == [1]
            return edgePairId[0] + edgePairId[1];
    }

    private void handleParallelEdgesAdd(Edge edge) {
        String key = calculateKey(edge);
        List<String> edgeList = edgeMap.getOrDefault(key, new LinkedList<>());
        edgeList.add(edge.getId());
        edgeMap.put(key, edgeList);
    }

    private void handleParallelEdgesRemove(Edge edge) {
        String key = calculateKey(edge);
        List<String> edgeList = edgeMap.getOrDefault(key, new LinkedList<>());
        edgeList.remove(edge.getId());
        edgeMap.put(key, edgeList);
    }

    public HashMap<String, List<String>> getEdgeMap() {
        return edgeMap;
    }

    public boolean containsParallel() {
        for (List<String> edges : edgeMap.values()) {
            if(edges.size() > 1) // more than one edge
                return true;
        }
        return false;
    }

    public List<String> getParallelEdges(Edge edge) {
        return edgeMap.get(calculateKey(edge));
    }
}