package test.model.edge;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import test.model.TestingSpecifics;

/*   Graph G3(BOTH):
 *       n1 -> n2
 *       n1 -> n3
 *       n2 <-> n3
 * */

public class EdgesUnitTest {

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
