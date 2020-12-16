package sketchpad.commands.draw;

import javafx.geometry.Point2D;
import sketchpad.commands.edges.AddEdge;
import sketchpad.commands.nodes.AddNode;
import sketchpad.constants.Sizes;
import sketchpad.controller.ConsoleController;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.utils.CircleTranslator;

import java.util.List;

public class KComplete implements IDrawable{

    private int kCount;
    private final int MAX_COUNT = 15; // we dont want to have too much nodes
    private final int ONE_K = 1; // we dont want to have too much nodes
    private final int OUT_OF_CANVAS_OFFSET = 40;

    public KComplete(int count) {
        kCount = count;
        if(count <= 0 || count > MAX_COUNT) {
            count = ONE_K;
            ConsoleController.consoleWrite("Max k count is currently set at " + MAX_COUNT);
        }
        kCount = count;
    }

    @Override
    public void draw() {
        buildKComplete();
    }

    private void buildKComplete() {
        CanvasController.clearSketchPad(); // clear first so we have no problems

        if(kCount == 1) {
            new AddNode((double) Sizes.Canvas.WIDTH/2,(double)Sizes.Canvas.HEIGHT/2).execute();
        }
        else if(kCount == 2) {
            new AddNode((double)Sizes.Canvas.WIDTH/4,(double)Sizes.Canvas.HEIGHT/2).execute();
            new AddNode((double)Sizes.Canvas.WIDTH/2 + (double)Sizes.Canvas.WIDTH/4
                    ,(double)Sizes.Canvas.HEIGHT/2).execute();
            new AddEdge("!edge 0 1").execute();
        }
        else { // all other counts until MAX_CYCLE
            createCircleNodes();
            connectNodes();
        }
    }

    private void createCircleNodes() {
        // center x,y, radius, degree, numPoints.
        double startDegree = -90;
        double canvasRadius = (double)Sizes.Canvas.HEIGHT/2 - Sizes.Node.RADIUS - OUT_OF_CANVAS_OFFSET;
        double centerX = (double)Sizes.Canvas.WIDTH/2;
        double centerY = (double)Sizes.Canvas.HEIGHT/2;

        List<Point2D> nodePoints = CircleTranslator.getNodesInACircle(centerX, centerY, canvasRadius,
                kCount, startDegree);

        for(Point2D point : nodePoints) // get nodes balancedly distanced?????
            new AddNode(point.getX(), point.getY()).execute();

    }

    private void connectNodes() {
        for(int i = 0; i < kCount-1; i++) {    // create edges for each node
            for(int j = i; j < kCount-1; j++) {
                if(i != j) {
                    new AddEdge(String.format("!edge %d %d", i, j)).execute();
                }
            }
            new AddEdge(String.format("!edge %d %d", kCount-1, i)).execute();
        }
    }
}
