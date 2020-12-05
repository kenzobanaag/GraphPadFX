package sketchpad.commands.draw;

import sketchpad.commands.edges.AddEdge;
import sketchpad.commands.nodes.AddNode;
import sketchpad.constants.Sizes;
import sketchpad.controller.ConsoleController;
import sketchpad.controller.canvas.CanvasController;

public class Cycle implements IDrawable{

    private int cycleCount;
    private final int MAX_CYCLE = 12; // we dont want to have too much nodes
    private final int ONE_CYCLE = 1; // we dont want to have too much nodes

    public Cycle(int cycles) {
        if(cycles <= 0 || cycles > MAX_CYCLE) {
            cycles = ONE_CYCLE;
            ConsoleController.consoleWrite("Max cycle count is currently set at " + MAX_CYCLE);
        }
        cycleCount = cycles;
    }

    @Override
    public void draw() {
        buildCycleGraph();
    }

    private void buildCycleGraph() {
        CanvasController.clearSketchPad(); // clear first so we have no problems

        if(cycleCount == 1) {
            new AddNode((double)Sizes.Canvas.WIDTH/2,(double)Sizes.Canvas.HEIGHT/2).execute();
            new AddEdge("!edge 0 0").execute();
        }
        else if(cycleCount == 2) {
            new AddNode((double)Sizes.Canvas.WIDTH/4,(double)Sizes.Canvas.HEIGHT/2).execute();
            new AddNode((double)Sizes.Canvas.WIDTH/2 + (double)Sizes.Canvas.WIDTH/4
                    ,(double)Sizes.Canvas.HEIGHT/2).execute();
            new AddEdge("!edge 0 1").execute();
            new AddEdge("!edge 1 0").execute();
        }
        else { // all other counts until MAX_CYCLE
            // use circle stuff
            circledNodes();
        }
    }

    // refactor: move to circle translator util?
    private void circledNodes() {
        // center x,y, radius, degree, numPoints.

        double degree = 360.0 / cycleCount;

        double currentDegree = -90;

        double canvasRadius = (double)Sizes.Canvas.HEIGHT/2 - Sizes.Node.RADIUS - 50;

        double centerX = (double)Sizes.Canvas.WIDTH/2;
        double centerY = (double)Sizes.Canvas.HEIGHT/2;

        for(int i = 0; i < cycleCount; i++) {
            double firstX = centerX + canvasRadius * Math.cos(Math.toRadians(currentDegree));
            double firstY = centerY + canvasRadius * Math.sin(Math.toRadians(currentDegree));
            currentDegree -= degree;
            new AddNode(firstX, firstY).execute();
        }

        for(int i = 0; i < cycleCount-1; i++) {
            new AddEdge(String.format("!edge %d %d", i, i+1)).execute();
        }
        new AddEdge(String.format("!edge %d %d", cycleCount-1, 0)).execute();
    }
}