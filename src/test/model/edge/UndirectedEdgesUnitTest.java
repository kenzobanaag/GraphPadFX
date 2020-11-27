package test.model.edge;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import test.model.TestingSpecifics;

/*   Graph G2(UNDIRECTED):
*       n1 <-> n2
*       n1 <-> n3
*       n1 <-> n4
*       n2 <-> n3
*       n2 <-> n5
*       n3 <-> n4
*       n3 <-> n5
*/

public class UndirectedEdgesUnitTest {

    @BeforeAll
    public static void setup() {
        TestingSpecifics.setTesting(true);
    }

    @AfterAll
    public static void tearDown() {
        TestingSpecifics.setTesting(false);
    }

    @Test
    public void test() {

    }
}
