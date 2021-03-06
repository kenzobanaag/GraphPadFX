package sketchpad.constants;

import javafx.scene.paint.Color;

public final class ColorScheme {
    public static final class Canvas {
        public static final String BACKGROUND_STR = "#1C1A18";
        public static final Color BACKGROUND = Color.valueOf(BACKGROUND_STR);
        public static final String TEXT_STR = "#808080";
        public static final Color TEXT = Color.valueOf(TEXT_STR);
    }

    public static final class Console {
        public static final String BACKGROUND_STR = "#808080";
        public static final Color BACKGROUND = Color.valueOf(BACKGROUND_STR);
        public static final Color TEXT = Color.valueOf("#FF3A00");
    }

    public static final class Node {
        public static final String NODE_STR = "#028f81";
        public static final String NODE_STR2 = "#00FF00"; //green
        public static final String NODE_STR3 = "#8D0DF1";
        public static final String NODE_STR4 = "#FF5500";
        public static final String NODE_STR5 = "#DA0318";
        public static final String NODE_STR6 = "#FD707E";
        public static final String NODE_STR7 = "#1919FF";
        public static final String NODE_STR8 = "#372C85";
        public static final String NODE_STR9 = "#65452C";
        public static final String NODE_STR0 = "#4A474C";
        public static final String GOLD_STR = "#FFBA00";
        // todo: Create 9 different colors of nodes. 1 is NODE_STR
        public static final String SELECTED_STR = NODE_STR5;
        public static final String NODE_TEXT_STR = "#1C1A18";
        public static final Color NODE = Color.valueOf(NODE_STR);
        public static final Color NODE_HIT_BOX = Color.valueOf("#da5a03");
        public static final Color SELECTED = Color.valueOf(SELECTED_STR);
        public static final Color GOLD_NODE = Color.valueOf(GOLD_STR);
        public static final Color GREEN_NODE = Color.valueOf(NODE_STR2);
        public static final Color INVERTED_NODE = Color.valueOf(NODE_STR6);
        public static final Color BLACK_NODE = Color.valueOf(NODE_STR3);
        public static final Color ORANGE_NODE = Color.valueOf(NODE_STR4);
        public static final Color BLUE_NODE = Color.valueOf(NODE_STR7);
        public static final Color PURPLE_NODE = Color.valueOf(NODE_STR8);
        public static final Color BROWN_NODE = Color.valueOf(NODE_STR9);
        public static final Color LIGHT_NODE = Color.valueOf(NODE_STR0);
    }

    public static final class Edge {
        public static final String EDGE_COLOR = "#da5a03";
        public static final Color EDGE = Color.valueOf(Node.NODE_STR);
        public static final Color SELECTED = Color.valueOf(Node.SELECTED_STR);
    }

    public static final class Display {
        public static final String BACKGROUND_STR = Canvas.BACKGROUND_STR;
        public static final String TEXT_STR = "#018786";
    }
}