package sketchpad.commands.draw;

import sketchpad.commands.nodes.AddNode;
import sketchpad.constants.Sizes;
import sketchpad.controller.canvas.CanvasController;

/*
* Draw a bst
*
*
* 2^h nodes where h is the level?
*
* 2^0 for root
* 2^1 for root children
* then 2^h
* */
public class Tree implements IDrawable{

    private int height;
    private final int HEIGHT_OFFSET = 50;

    public Tree(int height) {
        this.height = height;
        // if height = -1. we have yet to set bounds.
        // 10 is max width
    }

    @Override
    public void draw() {
        CanvasController.clearSketchPad();
        // we need to compute the distance between each node
        // distance could be x2 node radius,
        // max height of 2^4 and max leaf nodes of 8
        drawNodes();
    }

    /*
    * we might want to know the max number of nodes first so we can compute the needed width and height distance between nodes.
    * */
    private void drawNodes()  {
        for(int i = 0; i < height; i++) {
            drawLevel(i);
        }
    }

    // fixme: doesnt run on 0
    private void drawLevel(int level) {
        double levelY = ((double) Sizes.Canvas.HEIGHT / height) * level + HEIGHT_OFFSET;
        double levelX;
        if(level != 0)
            levelX = (double) Sizes.Canvas.WIDTH / ((Math.pow(2, level) - (Math.pow(2, level-1))));
        else
            levelX = (double) Sizes.Canvas.WIDTH / 2;
        for(int i = 0; i < level; i++) {
            //System.out.println(levelX);
            // create nodes in this level.
            new AddNode(i * levelX + 50, levelY).execute();
            System.out.println("X : " + i * levelX + 50 + " i : " +i);
            //levelX += 0; // recalculate
        }
    }
}

/*
* int width = 25;
        for(int i = 0; i < 8; i++) {
            new AddNode(width, 100).execute();
            width += 100;
        }
* */
