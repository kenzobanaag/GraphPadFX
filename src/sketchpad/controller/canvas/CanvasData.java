package sketchpad.controller.canvas;

import javafx.geometry.Point2D;
import sketchpad.constants.Sizes;
import sketchpad.model.canvaselement.edge.Edge;
import sketchpad.model.canvaselement.vertex.Node;
import sketchpad.utils.CircleTranslator;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

        adjustIfParallel(edge);
    }

    protected void removeEdge(Edge edge) {
        // remove node references of this edge
        nodeMap.get(edge.getParentId()).getEdges().removeEdge(edge);
        nodeMap.get(edge.getChildId()).getEdges().removeEdge(edge);
        edgeMap.remove(edge.getId());
        adjustIfParallel(edge);
    }

    /*
    * At most, we will only be adjusting one end. unless its a loop?
    *
    * take in edge and the node that moved?
    *
    * This way we know where the edge is going to relocate
    * */
    protected void adjustEdge(Node node) {
        // note: This is O(n^2), but edgeIds that are not parallel will most likely be flat.
        // using this will allow us to remove edgeList. But, edge list could be useful later so well see
        for(List<String> edgeIds : node.getEdges().getEdgeMap().values()) {
            if(edgeIds.size() == 1) {
                adjustEdgeOnNode(node, edgeIds.get(0));
            }
            else if(edgeIds.size() > 1 && (edgeMap.get(edgeIds.get(0)).getType() == Edge.EdgeTypes.UNDIRECTED_LOOP ||
                    edgeMap.get(edgeIds.get(0)).getType() == Edge.EdgeTypes.DIRECTED_LOOP)) {
                for(String edgeId : edgeIds) {
                    adjustEdgeOnNode(node, edgeId);
                }
            }
            else if(edgeIds.size() > 1) { // need to account for arcs.
                adjustIfParallel(edgeMap.get(edgeIds.get(0))); // grab one
            }
        }
    }

    private void adjustEdgeOnNode(Node node, String edgeId) {
        edgeMap.get(edgeId).adjustEdge(node.getCanvasElement().getLayoutX() + OFFSET,
                node.getCanvasElement().getLayoutY() + OFFSET, node.getId());
    }

    /*
    * Helper function to remove duplicate code.
    * */
    private void adjustIfParallel(Edge edge) {
        if(edge.getType() != Edge.EdgeTypes.DIRECTED_LOOP && edge.getType() != Edge.EdgeTypes.UNDIRECTED_LOOP
            && nodeMap.get(edge.getParentId()).getEdges().containsParallel()) {
            adjustParallel(nodeMap.get(edge.getParentId()), edge);
        }
    }


    /*
    * All we need here is the current node, the current edge, the list of other possible parallel edges
    *
    * We should be able to grab the edge just from passing in the node.
    *
    * For now, this will only deal with adding and removing, NOT moving
    * */
    private void adjustParallel(Node node, Edge edge) {
        // compute parallel lines and adjust their start and end lines
        // get start x,y and end x,y
        double parentX = nodeMap.get(edge.getParentId()).getCanvasElement().getLayoutX();
        double parentY = nodeMap.get(edge.getParentId()).getCanvasElement().getLayoutY();
        double childX = nodeMap.get(edge.getChildId()).getCanvasElement().getLayoutX();
        double childY = nodeMap.get(edge.getChildId()).getCanvasElement().getLayoutY();

        int radius = Sizes.Node.RADIUS; // node radius

        // line slope, needed to get angle
        double slope = CircleTranslator.getSlope(parentX,parentY,childX,childY);

        // get possible parallel edges of this edge
        List<String> edges = node.getEdges().getParallelEdges(edge);

        List<Point2D> startPoints = CircleTranslator.computePoints(parentX + radius,
                parentY + radius, slope, edges.size());
        List<Point2D> endPoints = CircleTranslator.computePoints(childX + radius,
                childY + radius, slope, edges.size());

        for(int i = 0; i < edges.size(); i++) {
            edgeMap.get(edges.get(i)).adjustEdge(startPoints.get(i).getX(), // adjust start line
                    startPoints.get(i).getY(), edge.getParentId());
            edgeMap.get(edges.get(i)).adjustEdge(endPoints.get(i).getX(), // adjust end line
                    endPoints.get(i).getY(), edge.getChildId());
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
