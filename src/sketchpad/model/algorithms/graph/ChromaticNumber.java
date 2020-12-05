package sketchpad.model.algorithms.graph;

import sketchpad.model.algorithms.Algorithm;
import javafx.scene.paint.Color;


import static sketchpad.constants.ColorScheme.Node.*;

public class ChromaticNumber extends Algorithm {

    private Color[] colors;
    private int minColors;


    @Override
    public void setup() {
        colors = new Color[] {GREEN_NODE, GOLD_NODE, INVERTED_NODE, BLACK_NODE, ORANGE_NODE, BLUE_NODE, PURPLE_NODE,
            BROWN_NODE, LIGHT_NODE};
        minColors = Integer.MAX_VALUE;
    }

    @Override
    public void execute() {

    }
}
