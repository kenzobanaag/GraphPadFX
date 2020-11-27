package test.model.edge;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sketchpad.model.canvaselement.edge.Edges;
import test.model.TestingSpecifics;

import static org.junit.jupiter.api.Assertions.*;

/*
* Things we need to test:
*   DIRECTED:
*       getAdjacent
*
*   UNDIRECTED:
*       getAdjacent
*
*
*   Graph G1(DIRECTED):
*       n1 -> n2
*       n1 -> n3
*       n2 -> n4
*       n2 -> n5
*       n2 <- n1
*       n4 <- n2
*       n5 <- n2
* */

public class DirectedEdgesUnitTest {
    // think about this, and if this is the way we want to go
    // fixme: COMPARE THIS APPROACH VS ADJACENCY MATRIX/MAP
    private final Edges node1_graph1 = new Edges("n1");
    private final Edges node2_graph1 = new Edges("n2");
//    private final Edge n1n2_g1 = new Directed("n1","n2",1,2);
//    private final Edge n1n3_g1 = new Directed("n1","n3",1,3);
//    private final Edge n2n4_g1 = new Directed("n2","n4",2,4);
//    private final Edge n2n5_g1 = new Directed("n2","n5",2,5);
//    private final Edge n2n1_g1 = new Directed("n1","n2",1,2);
//    //private final Edge n4n2_g1 = new Directed("n2","n4",1,2);
    //private final Edge n5n2_g1 = new Directed("n2","n5",1,2);

    // fixme: Do we need the reference of the other edge?, yes.

    @BeforeAll
    public static void init() {
        TestingSpecifics.setTesting(true);
    }

    @AfterAll
    public static void tearDown() {
        TestingSpecifics.setTesting(false);
    }

    @Test
    public void addEdge_graph1_directed() {
//        node1_graph1.addEdge(n1n2_g1);
//        node1_graph1.addEdge(n1n3_g1);
//        node2_graph1.addEdge(n2n4_g1);
//        node2_graph1.addEdge(n2n5_g1);
//        node2_graph1.addEdge(n2n1_g1);

        assertEquals(2, node1_graph1.getEdgeCount());
        assertEquals(3, node2_graph1.getEdgeCount());

        assertEquals(2, node1_graph1.getOutDegree());
        assertEquals(0, node1_graph1.getInDegree());
        assertEquals(2, node2_graph1.getOutDegree());
        assertEquals(1, node2_graph1.getInDegree());
    }

    @Test
    public void removeEdge_directed() {
        // add first
//        node1_graph1.addEdge(n1n2_g1);
//        node1_graph1.addEdge(n1n3_g1);
//        node2_graph1.addEdge(n2n4_g1);
//        node2_graph1.addEdge(n2n5_g1);
//        node2_graph1.addEdge(n2n1_g1);
//
//        node1_graph1.removeEdge(n1n2_g1);
//        node1_graph1.removeEdge(n1n3_g1);
//        node2_graph1.removeEdge(n2n4_g1);
//        node2_graph1.removeEdge(n2n5_g1);
//        node2_graph1.removeEdge(n2n1_g1);

        assertEquals(0, node1_graph1.getEdgeCount());
        assertEquals(0, node2_graph1.getEdgeCount());

        assertEquals(0,node1_graph1.getOutDegree());
        assertEquals(0,node1_graph1.getInDegree());
        assertEquals(0,node2_graph1.getInDegree());
        assertEquals(0,node2_graph1.getOutDegree());
    }

    @Test
    public void isUndirected_directed() {
//        node1_graph1.addEdge(n1n2_g1);
//        node1_graph1.addEdge(n1n3_g1);
//        node2_graph1.addEdge(n2n4_g1);
//        node2_graph1.addEdge(n2n5_g1);
//        node2_graph1.addEdge(n2n1_g1);
//
//        assertFalse(node1_graph1.isUndirected());
//        assertFalse(node2_graph1.isUndirected());
//
//        node1_graph1.removeEdge(n1n2_g1);
//        node1_graph1.removeEdge(n1n3_g1);
//        node2_graph1.removeEdge(n2n4_g1);
//        node2_graph1.removeEdge(n2n5_g1);
//        node2_graph1.removeEdge(n2n1_g1);

        assertTrue(node1_graph1.isUndirected());
        assertTrue(node2_graph1.isUndirected());
    }

    @Test
    public void edit() {
        // what is this for?
        // todo: are changing of edge values allowed? THINK OF THIS
        // problem with this is that, we need to change all instances of this edge, from the parent and child.

        // We want the canvas to have all the edges?
        // fixme: Finalize how we model edges.

        /*
        * note: What were doing is pretty stupid, We basically redid each node reference but with edges.
        * note: consider running algorithms and see which on will be more efficient in handling those algorithms. eg. BFS, DFS, MST, CYCLES, COMPONENTS.
        *
        * 1. We could put the edges object inside the node.
        *
        *       Node1 <-> Node2
        *
        *       Node1 = {
        *           Edges = {
        *               edgeList = {{edgeId1}}
        *           }
        *       }
        *
        *       Node2 = {
        *           Edges = {
        *               edgeList = {{edgeId1}}
        *           }
        *       }
        *
        *       edgeId1 = {
        *           parent = "Node1"
        *           child = "Node2"
        *           value = ?
        *       }
        *
        *       Pros:
        *          This approach will be easier to implement since we only need to add it to the already existing node class.
        *
        *       Cons:
        *          Mutating values of an edge will require some deep query
        *
        *
        * 2. Or go with the other approach which is:
        *       Map<EdgeId, Edge>
        *       Map<EdgeId1, Edge{parent: "node1", child: "node2"}>
        *
        *       node1.setEdges("EdgeId1", "EdgeId")
        *       node1.getEdges({edgeid1, edgeid2})
        *
        *       Nodes, do not know about their edges.
        *
        *
        *
        *
        *       Q's: Undirected nodes will have to reference each other still.
        *
        *       Pros:
        *           Easy access to each edge
        *
        *
        *       Cons:
        *
        *       What are the most important functions that we need to consider?
        *           a. Adding/Removing a node
        *           b. Adding/Removing an edge
        *           c. Accessing the edge
        *           d. Traversing the nodes in the graph using the edges to check which is connected. -> BFS, DFS
        *
        *        1a. Nodes by default will have an edges object that doesnt contain any edges yet.
        *            When nodes are removed, we must also remove associated edges.
        *               Before removing a node, we get all edges, then for each edge, we want to remove all those instances
        *               in the other nodes so that the reference to that edge is completely gone.
        *               CnavasController needs a removeEdge function that will go through all the nodes affected by the former node deletion.
        *
        *               This is somewhat complex.
        *
        *        2a. Creating nodes will also have an Edges object that is empty but their edges will just be a list of string edge id's
        *
        *            Removing a node, we will go through each edge id in the node to be removed, then access the nodes that have references of this edge
        *            , remove that, then remove it from the Map. Pretty much the same approach as 1, in removing.
        *
        *        A. Which is easier?
        *               Difficulty would be the same, but, having the actual edge object inside of node seems to make more sense rather than just its reference id.
        *
        *        1b.
        *
        *        2b.
        *
        *        B. Again, these 2 will have the same implementation of delete.
        *
        *        C. So for approach 2, to access the edge/node, to know stuff about the node, like degree and stuff, wed need to reference the map of edges, and not the actual node
        *               which doesnt make sense. The node should have all the information we need about that node.
        *
        *        D. Approach 2 also will run into problems with referencing. Basically, the node will reference the edge, but not the node as a whole. Wed need to have an edges object
        *           inside here anyways, which would take up more space if we also had an edges map. Like whats the use of that map if it cannot hold edges.
        *
        *        ALRIGHT. APPROACH 1 MAKES MORE SENSE TO ME.
        *
        *        fixme: APPROACH 1 NEW PROBLEM
        *               Basically since edges are now bound inside nodes, how do we get its layout??
        *
        *               We might need the physical layout of the edge to be a reference somewhere... Just its layout.
        *
        *       New/Idea approach
        *       *This might be approach 2*
        *
        *       Map for the actual layout of edges and its object
        *
        *       Node has edges object
        *
        *       Edges object contains all information about the edges, traversibility, degree. so it doesnt need to reference the edge object?
        *
        *       // think about what the node needs to know first so we can put the needs of the node to an edges object.
        *          Or only put what is specific to that node.
        *
        *          ei. In and out degree, degree, adjacent nodes, traversable nodes, whos child and whos parent.
        *
        *          Edge object has
        *           value
        *           id
        *           parent
        *           child
        *           line
        *           label - (name, label)
        *           color
        *
        *      What im thinking
        *
        *           Map of Edge objects will be on canvasData
        *
        *           Object would contain things that are mutable, start, end xy. value, label, name, id
        *
        *           This would make mutating the edge easier,
        *
        *           "Move edge", we need to access the edge map.
        *
        *           When doing operations on the node. The only time they need to be notified is when the edge is removed, added, or direction has been changed.
        *
        *           Direction change will probably just be, deleting the current node, then putting it back again.
        *
        *           Also we need to repaint nodes to ensure they are on top???
        * */
    }

    @Test
    public void getAdjacent_directed() {
//        node1_graph1.addEdge(n1n2_g1);
//        node1_graph1.addEdge(n1n3_g1);
//        node2_graph1.addEdge(n2n4_g1);
//        node2_graph1.addEdge(n2n5_g1);
//        node2_graph1.addEdge(n2n1_g1);

        String[] adjacent_to_node1 = new String[] {"n2","n3"};
        String[] actualList = new String[2];

        String[] adjacent_to_node2 = new String[] {"n4","n5"};
        String[] actualList2 = new String[2];

        for(int i = 0; i < node1_graph1.getAdjacentNodes().size(); i++) {
            actualList[i] = node1_graph1.getAdjacentNodes().get(i);
        }

        for(int i = 0; i < node2_graph1.getAdjacentNodes().size(); i++) {
            actualList2[i] = node2_graph1.getAdjacentNodes().get(i);
        }

        assertArrayEquals(adjacent_to_node1, actualList);
        assertArrayEquals(adjacent_to_node2, actualList2);
    }


    @Test
    public void getDegree() {
//        node1_graph1.addEdge(n1n2_g1);
//        node1_graph1.addEdge(n1n3_g1);
//        node2_graph1.addEdge(n2n4_g1);
//        node2_graph1.addEdge(n2n5_g1);
//        node2_graph1.addEdge(n2n1_g1);

        assertEquals(2, node1_graph1.getDegree());
        assertEquals(3, node2_graph1.getDegree());
    }
}
