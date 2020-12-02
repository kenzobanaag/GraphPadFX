package sketchpad.utils;

import javafx.geometry.Point2D;
import sketchpad.constants.Sizes;

import java.util.LinkedList;
import java.util.List;

public abstract class CircleTranslator {

    private static double EVEN_DISTANCE = .25; // .25 of radius
    private static double ODD_DISTANCE = .5; // .5 of radius
    private static double QUARTER_CIRCLE = 90.0;
    private static List<Point2D> pointList;

    public static List<Point2D> computePoints(double centerX, double centerY, double slope, int numPoints) {
        int radius = Sizes.Node.RADIUS;
        if(numPoints % 2 == 0) { //even
            return computeEvenPoints(centerX,centerY,radius,slope,numPoints);
        }
        else {
            return computeOddPoints(centerX,centerY,radius,slope,numPoints);
        }
    }

    // refactor: combine both even and odd points methods.
    private static List<Point2D> computeOddPoints(double centerX, double centerY, int radius, double slope, int numPoints) {
        pointList = new LinkedList<>();
        double rad = radius  * .5; // adjust for margin
        for(int i = 0; i < numPoints/2; i++) {
            pointList.add(computePositiveDegree(centerX, centerY, rad, slope));
            pointList.add(computeNegativeDegree(centerX, centerY, rad, slope));
            rad += (double)radius * .5; //
        }
        pointList.add(new Point2D(centerX,centerY));
        return pointList;
    }

    /*
    * We can combine the two algorithms and take in a ratioed radius and probably replace numPoints by some edgeDistance variable
    * */
    private static List<Point2D> computeEvenPoints(double centerX, double centerY, int radius, double slope, int numPoints) {
        pointList = new LinkedList<>();
        double rad = radius * .25;
        for(int i = 0; i < numPoints/2; i++) {
            pointList.add(computePositiveDegree(centerX, centerY, rad, slope));
            pointList.add(computeNegativeDegree(centerX, centerY, rad, slope));
            rad += radius * .5; //
        }
        return pointList;
    }

    // + QUARTER CIRCLE
    private static Point2D computePositiveDegree(double centerX, double centerY, double radius, double slope) {
        double x = centerX + radius * Math.cos(getAngle(slope) + Math.toRadians(QUARTER_CIRCLE));
        double y = centerY + radius * Math.sin(getAngle(slope) + Math.toRadians(QUARTER_CIRCLE));
        return new Point2D(x,y);
    }

    // - QUARTER CIRCLE
    private static Point2D computeNegativeDegree(double centerX, double centerY, double radius, double slope) {
        double x = centerX + radius * Math.cos(getAngle(slope) - Math.toRadians(QUARTER_CIRCLE));
        double y = centerY + radius * Math.sin(getAngle(slope) - Math.toRadians(QUARTER_CIRCLE));
        return new Point2D(x,y);
    }

    private static double getAngle(double slope) {
        return Math.atan(slope); // get angle using slope
    }

    public static double getSlope(double startX, double startY, double endX, double endY) {
        return (endY - startY) / (endX - startX);
    }
}
