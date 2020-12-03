package test.utils;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;
import sketchpad.utils.CircleTranslator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CircleTranslatorTest {

    private static double QUARTER_CIRCLE = 90.0;
    private static double NO_DIRECTION = 1;

    @Test
    public void angleTest_radians() {
       // assertEquals(.87605, CircleTranslator.getAngle((double)6/5), 0.001);
       // assertEquals(-.87605, CircleTranslator.getAngle(-(double)6/5), 0.001);
    }

    @Test
    public void validPointsTest() {

    }

    @Test
    public void testOddPoints() {
        //create circle
        double centerX = 1;
        double centerY = 1;
        double slope = CircleTranslator.getSlope(1,1,6,7);
        int num = 3;
        int radius = 1;

        double x = .23;
        double y = 1.64;
        // get point to the right of the semi circle
        //assertEquals(x, CircleTranslator.computeOddPoints(centerX, centerY, radius,slope, num).getX(), .01);
        //assertEquals(y, CircleTranslator.computeOddPoints(centerX, centerY, radius,slope, num).getY(), .01);
    }

    @Test
    public void testSlope() {
        assertEquals((double)6/5, CircleTranslator.getSlope(1,1,6,7), 0.01);
    }

    @Test
    public void testSlope_zero() {
        // impossible for this to happen
        assertEquals(0, CircleTranslator.getSlope(100,100,100,100));
    }

    @Test
    public void testActualPoints() {
        double centerX = 1;
        double centerY = 1;
        double slope = CircleTranslator.getSlope(1,1,6,7);
        int num = 3;
        int radius = 1;

        List<Point2D> pointList = CircleTranslator.computePoints(centerX, centerY, slope, radius, num, QUARTER_CIRCLE, NO_DIRECTION);

        assertEquals(num, pointList.size());

        for(Point2D point : pointList) {
            System.out.println(point);
        }

        double centerX_node2 = 6;
        double centerY_node2 = 7;
        List<Point2D> pointList_node2 = CircleTranslator.computePoints(centerX_node2, centerY_node2, slope,radius, num, QUARTER_CIRCLE,NO_DIRECTION);

        assertEquals(num, pointList_node2.size());

        for(Point2D point : pointList_node2) {
            //System.out.println(point);
        }
    }

    @Test
    public void testActualPoints2() {
        double centerX = 1;
        double centerY = 1;
        double centerX_node2 = 10;
        double centerY_node2 = 1;
        double slope = CircleTranslator.getSlope(centerX,centerY,centerX_node2,centerY_node2);
        int num = 4;
        int radius = 1;

        List<Point2D> pointList = CircleTranslator.computePoints(centerX, centerY, slope, radius, num, QUARTER_CIRCLE,NO_DIRECTION);

        assertEquals(num, pointList.size());

        for(Point2D point : pointList) {
            System.out.println(point);
        }


        List<Point2D> pointList_node2 = CircleTranslator.computePoints(centerX_node2, centerY_node2, slope,radius, num, QUARTER_CIRCLE,NO_DIRECTION);

        assertEquals(num, pointList_node2.size());

//        for(Point2D point : pointList_node2) {
//           System.out.println(point);
//        }
    }

}