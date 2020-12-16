package sketchpad.commands.draw;

import com.sun.glass.ui.Size;
import sketchpad.commands.edges.AddEdge;
import sketchpad.commands.nodes.AddNode;
import sketchpad.constants.Sizes;
import sketchpad.controller.ConsoleController;
import sketchpad.controller.canvas.CanvasController;

/*
* In the mathematical field of graph theory, a complete bipartite graph or biclique is a special kind of bipartite
* graph where every vertex of the first set is connected to every vertex of the second set.
* */
public class Bipartite implements IDrawable{

    private int m, n;
    private final int MAX_MN = 15; // we dont want to have too much nodes
    private final int MIN_MN = 1; // we dont want to have too much nodes
    private final int OUT_OF_CANVAS_OFFSET = 50;

    public Bipartite(int m, int n) {
        if(m < MIN_MN || m > MAX_MN) {
            m = MIN_MN;
            ConsoleController.consoleWrite("Max for m is currently set at " + MAX_MN);
        }

        if(n < MIN_MN || n > MAX_MN) {
            n = MIN_MN;
            ConsoleController.consoleWrite("Max for n is currently set at " + MAX_MN);
        }

        this.m = m;
        this.n = n;
    }

    @Override
    public void draw() {
        CanvasController.clearSketchPad();

        buildNodes();
        connectNodes();
    }

    private void buildNodes(){
        // refactor: we could extract this to some other method but lets move to other stuff for now
        double height_m = (double)Sizes.Canvas.HEIGHT / 2 - (double)Sizes.Canvas.HEIGHT / 4;
        double height_n = (double)Sizes.Canvas.HEIGHT / 2 + (double)Sizes.Canvas.HEIGHT / 4;

        double radius_m = ((double)Sizes.Canvas.WIDTH - OUT_OF_CANVAS_OFFSET) / m;
        double radius_n = ((double)Sizes.Canvas.WIDTH - OUT_OF_CANVAS_OFFSET) / n;
        double mid_m = ((double)Sizes.Canvas.WIDTH) /2;

        double x = radius_m;

        for(int i = 0; i < m/2; i++) {
            new AddNode(mid_m+x, height_m).execute();
            new AddNode(mid_m-x, height_m).execute();
            x += radius_m;
        }
        if(m % 2 != 0)
            new AddNode(mid_m, height_m).execute();

        x = radius_n;

        for(int i = 0; i < n/2; i++) {
            new AddNode(mid_m+x, height_n).execute();
            new AddNode(mid_m-x, height_n).execute();
            x += radius_n;
        }
        if(n % 2 != 0)
            new AddNode(mid_m, height_n).execute();
    }

    private void connectNodes() {
        for(int i = 0; i < m; i++) {    // each m
            for(int j = m; j < m+n; j++) { // connect each m to each n
                new AddEdge(String.format("!edge %d %d", i,j)).execute();
            }
        }
    }
}
