package sketchpad.constants;

public final class Sizes {

    public static final class Application {
        public static final int WIDTH = Canvas.WIDTH + Console.WIDTH;
        public static final int HEIGHT = Canvas.HEIGHT;
    }

    public static final class Canvas {
        public static final int WIDTH = 750;
        public static final int HEIGHT = 600;
        public static final int SELECTION_COUNT = 2;
    }

    public static final class Node {
        public static final int LABEL_SIZE = 30;
        public static final int WIDTH = 50;
        public static final int HEIGHT = 50;
        public static final int RADIUS = 25;
        public static final int PADDING = 5;
    }

    public static final class Edge {
        public static final int EDGE_STROKE = 5;
    }

    public static final class Console {
        public static final int WIDTH = 200;
        public static final int HEIGHT = Canvas.HEIGHT;
    }

    public static final class Display {
        public static final int TEXT_SIZE = 30;
    }
}
