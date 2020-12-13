package sketchpad.commands.draw;

/*
* Draw a petersen graph
*
* C5 Out
* C5 Inside but different edges
* */

import javafx.geometry.Point2D;
import sketchpad.commands.edges.AddEdge;
import sketchpad.commands.nodes.AddNode;
import sketchpad.constants.Sizes;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.utils.CircleTranslator;

import java.util.List;

public class Petersen implements IDrawable{

    private final int OUT_OF_CANVAS_OFFSET = 40;
    private final int NODE_COUNT = 5;

    @Override
    public void draw() {
        CanvasController.clearSketchPad(); // clear first so we have no problems
        drawOuterCircle();
        connectOuterCircle();
        drawInnerStar();
        connectInnerStar();
    }

    private void drawOuterCircle() {
        double startDegree = -90;
        double canvasRadius = (double) Sizes.Canvas.HEIGHT/2 - Sizes.Node.RADIUS - OUT_OF_CANVAS_OFFSET;
        double centerX = (double)Sizes.Canvas.WIDTH/2;
        double centerY = (double)Sizes.Canvas.HEIGHT/2;

        List<Point2D> nodePoints = CircleTranslator.getNodesInACircle(centerX, centerY, canvasRadius,
                NODE_COUNT, startDegree);

        for(Point2D point : nodePoints) // get nodes balancedly distanced?????
            new AddNode(point.getX(), point.getY()).execute();
    }

    private void connectOuterCircle() {
        for(int i = 0; i < NODE_COUNT-1; i++) // create edges for each node
            new AddEdge(String.format("!edge %d %d", i, i+1)).execute();
        // add last edge that connects from last node to first node
        new AddEdge(String.format("!edge %d %d", NODE_COUNT-1, 0)).execute();
    }

    private void drawInnerStar() {
        double startDegree = -90;
        double canvasRadius = (double) Sizes.Canvas.HEIGHT/3 - Sizes.Node.RADIUS - OUT_OF_CANVAS_OFFSET;
        double centerX = (double)Sizes.Canvas.WIDTH/2;
        double centerY = (double)Sizes.Canvas.HEIGHT/2;

        List<Point2D> nodePoints = CircleTranslator.getNodesInACircle(centerX, centerY, canvasRadius,
                5, startDegree);

        for(Point2D point : nodePoints) // get nodes balancedly distanced?????
            new AddNode(point.getX(), point.getY()).execute();
    }

    private void connectInnerStar() {
        for(int i = 0; i < NODE_COUNT; i++) {
            new AddEdge(String.format("!edge %d %d", i, i+NODE_COUNT)).execute();
        }

        new AddEdge(String.format("!edge %d %d", 5, 8)).execute();
        new AddEdge(String.format("!edge %d %d", 9, 6)).execute();
        new AddEdge(String.format("!edge %d %d", 5, 7)).execute();
        new AddEdge(String.format("!edge %d %d", 6, 8)).execute();
        new AddEdge(String.format("!edge %d %d", 9, 7)).execute();
    }
}
