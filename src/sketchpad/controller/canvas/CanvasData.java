package sketchpad.controller.canvas;

import sketchpad.model.canvaselement.edge.Edge;

import java.util.HashMap;

public class CanvasData {

    private static HashMap<String, Edge> edgeMap; //adjacency map, string is nodeId
    private static int edgeCount = 0;

    private static CanvasData instance = new CanvasData();

    private CanvasData() {
        edgeMap = new HashMap<>();
    }

    protected static void addEdge(Edge edge) {
        //Edges edges = edgeMap.getOrDefault(edge.getId(), new Edge()); // get value of edges, or create new one
        //edges.addEdge(edge);    // add edge to edges associated with that node
        //edgeMap.put(edgeId, edges);  // create or replace the value of that node id
        // how do we know adding was successful, meaning the edge added was a new one?
        ++edgeCount;
    }

    protected static void removeEdge(String edgeId, Edge edge) {
        //Edges edges = edgeMap.getOrDefault(edgeId, new Edges(edgeId)); // get value of edges, or create new one
        //edges.removeEdge(edge);    // rm edge to edges associated with that node
        //edgeMap.put(edgeId, edges);  // replace the value of that node id
        // how do we know op was successful?
        --edgeCount;
    }

    /*
    * At most, we will only be adjusting one end. unless its a loop?
    * */
    protected static void adjustEdge(double x, double y) {

    }

    protected static int getEdgeCount() { return edgeCount; }
}
