package test.model.edge;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import test.model.TestingSpecifics;


public class EdgeObjectUnitTest {

    /*
    * This we want to test:
    *   Types
    *   The 2 nodes on each edge
    *   Which is parent
    *   Which is child
    *   Shouldnt matter on undirected
    *   directed should not be able to move to parent, this will be handled by the Edges class
    * */
//    private Edge directedEdge = new Directed("node1", "node2", 0,1);
//    private Edge undirectedEdge = new Undirected("node1", "node2",2,3);
//    private Edge directedValueEdge = new Directed("node1", "node2",14,5, 21);
//    private Edge undirectedValueEdge = new Undirected("node1", "node2",19,10, 15);

    @BeforeAll
    public static void init() {
        TestingSpecifics.setTesting(true);
    }

    @AfterAll
    public static void tearDown() {
        TestingSpecifics.setTesting(false);
    }

    @Test
    public void edgeName() {
//        assertEquals("n0-n1", directedEdge.getEdgeName());
//        assertEquals("n2-n3", undirectedEdge.getEdgeName());
//        assertEquals("n14-n5", directedValueEdge.getEdgeName());
//        assertEquals("n19-n10", undirectedValueEdge.getEdgeName());
    }

    @Test
    public void getEdgeValue() {
//        assertEquals(0, directedEdge.getValue());
//        assertEquals(0, undirectedEdge.getValue());
//        assertEquals(21, directedValueEdge.getValue());
//        assertEquals(15, undirectedValueEdge.getValue());
    }

    @Test
    public void parent() {
//        assertEquals("node1", directedEdge.getParentId());
//        assertEquals("node1", undirectedEdge.getParentId());
//        assertEquals("node1", directedValueEdge.getParentId());
//        assertEquals("node1", undirectedValueEdge.getParentId());
    }

    @Test
    public void child() {
//        assertEquals("node2", directedEdge.getChildId());
//        assertEquals("node2", undirectedEdge.getChildId());
//        assertEquals("node2", directedValueEdge.getChildId());
//        assertEquals("node2", undirectedValueEdge.getChildId());
    }


    @Test
    public void testType() {
//        assertEquals(Edge.EdgeTypes.DIRECTED, directedEdge.getType());
//        assertEquals(Edge.EdgeTypes.UNDIRECTED, undirectedEdge.getType());
//        assertEquals(Edge.EdgeTypes.DIRECTED, directedValueEdge.getType());
//        assertEquals(Edge.EdgeTypes.UNDIRECTED, undirectedValueEdge.getType());
    }
}
