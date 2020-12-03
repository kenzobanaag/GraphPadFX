package sketchpad.utils;

import javafx.geometry.Point2D;
import sketchpad.constants.Sizes;

import java.util.LinkedList;
import java.util.List;

/*
* refactor: NEED TO CLEAN THESE METHODS FOR CODE SMELLS
*
* its very smelly
*
* need to add direction, positive or negative number , 1 or -1.,
* */
public abstract class CircleTranslator {

    // direction = positive or negative, positive is from left to right, and negative is right to left.
    private static List<Point2D> pointList;

    public static List<Point2D> computePoints(double centerX, double centerY, double slope, int radius, int numPoints,
                                              double degree, double direction) {
        if(numPoints % 2 == 0)  //even
            return computeEvenPoints(centerX,centerY,radius,slope,numPoints,degree, direction);
        else
            return computeOddPoints(centerX,centerY,radius,slope,numPoints,degree, direction);
    }

    // refactor: combine both even and odd points methods.
    private static List<Point2D> computeOddPoints(double centerX, double centerY, int radius, double slope,
                                                  int numPoints, double degree, double direction) {
        pointList = new LinkedList<>();
        double rad = radius  * .75; // adjust for margin, 2 margins should be the same
        for(int i = 0; i < numPoints/2; i++) {
            pointList.add(computePositiveDegree(centerX, centerY, rad, slope, degree, direction));
            pointList.add(computeNegativeDegree(centerX, centerY, rad, slope, degree, direction));
            rad += (double)radius * .75; // distance between points
        }
        pointList.add(new Point2D(centerX,centerY));
        return pointList;
    }

    /*
    * We can combine the two algorithms and take in a ratioed radius and probably replace numPoints by some edgeDistance variable
    * */
    private static List<Point2D> computeEvenPoints(double centerX, double centerY, int radius, double slope,
                                                   int numPoints, double degree, double direction) {
        pointList = new LinkedList<>();
        double rad = radius * .38; // must be half of distance to be equal
        for(int i = 0; i < numPoints/2; i++) {
            pointList.add(computePositiveDegree(centerX, centerY, rad, slope, degree, direction));
            pointList.add(computeNegativeDegree(centerX, centerY, rad, slope, degree, direction));
            rad += radius * .75; // distance between points
        }
        return pointList;
    }

    // + QUARTER CIRCLE
    private static Point2D computePositiveDegree(double centerX, double centerY, double radius, double slope,
                                                 double degree, double direction) {
        double x;
        double y;
        if(direction > 0) { // positive
            x = centerX + radius * Math.cos(getAngle(slope) + Math.toRadians(degree));
            y = centerY + radius * Math.sin(getAngle(slope) + Math.toRadians(degree));
        }
        else {
            x = centerX - radius * Math.cos(getAngle(slope) + Math.toRadians(degree));
            y = centerY - radius * Math.sin(getAngle(slope) + Math.toRadians(degree));
        }
        return new Point2D(x,y);
    }

    // - QUARTER CIRCLE
    private static Point2D computeNegativeDegree(double centerX, double centerY, double radius, double slope,
                                                 double degree, double direction) {
        double x;
        double y;
        if(direction > 0) { // positive
            x = centerX + radius * Math.cos(getAngle(slope) - Math.toRadians(degree));
            y = centerY + radius * Math.sin(getAngle(slope) - Math.toRadians(degree));
        }
        else {
            x = centerX - radius * Math.cos(getAngle(slope) - Math.toRadians(degree));
            y = centerY - radius * Math.sin(getAngle(slope) - Math.toRadians(degree));
        }

        return new Point2D(x,y);
    }

    private static double getAngle(double slope) {
        return Math.atan(slope); // get angle using slope
    }

    public static double getSlope(double startX, double startY, double endX, double endY) {
        return (endY - startY) / (endX - startX);
    }
}
