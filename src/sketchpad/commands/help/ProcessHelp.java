package sketchpad.commands.help;

import sketchpad.commands.Command;
import sketchpad.controller.ConsoleController;

public class ProcessHelp implements Command {

    private final String HELP_PREFIX = "?";
    private final String ALGORITHM = "algorithm", RESET = "reset", NODE = "node", EDGE = "edge", CLEAR_SCREEN = "cls",
        DRAW = "draw", REMOVE = "rm", SEARCH = "search";
    private String command;

    // algorithm
    private final String BIPARTITE = "<!algorithm bipartite> - Checks if a graph is bipartite";
    private final String COMPONENT = "<!algorithm component> - Counts the number of components of an undirected graph";
    private final String SCC = "<!algorithm scc> - Counts the number of strongly connected components of directed graph";
    private final String BRIDGE = "<!algorithm bridge> - Highlights the edges that are bridges";
    private final String CHROMATIC = "<!algorithm chromatic> - Finds the minimum graph coloring";

    // reset
    private final String RESET_ALL = "<!reset> - Removes all nodes and edges";
    private final String RESET_EDGE = "<!reset edge> - Removes edges only";

    // node
    private final String NODE_EMPTY = "<!node> - Inserts node at x : 30, y : 30";
    private final String NODE_XY = "<!node x y> - Inserts node at x, y";
    private final String NODE_XY_VAL = "<!node x y val> - Inserts node at x, y with value val";

    // edge
    private final String EDGE_EMPTY = "<!edge> - Creates 2 nodes and an edge";
    private final String EDGE_ORDER = "<!edge parent child> - Connects parent and child node";
    private final String EDGE_ORDER_VAL = "<!edge parent child val> - Connects parent and child node with edge value";
    private final String EDGE_ORDER_VAL_DIRECTION = "<!edge parent child val D|U> - Connects parent and child node with " +
            "direction D or U";

    // draw
    private final String DRAW_KMN = "<!draw kmn m n> - Draw a kmn graph with m n nodes";
    private final String DRAW_CYCLE = "<!draw c n> - Draw a a cycle graph with n nodes";
    private final String DRAW_K = "<!draw k n> - Draw a complete graph with n nodes";


    public ProcessHelp(String args) {
        // we are assuming that the prefix is already ?
        command = args;
    }

    @Override
    public void execute() {
        switch(command) {
            case HELP_PREFIX+ALGORITHM:
                printAlgorithm();
                break;
            case HELP_PREFIX+RESET:
                printReset();
                break;
            case HELP_PREFIX+NODE:
                printNode();
                break;
            case HELP_PREFIX+EDGE:
                printEdge();
                break;
            case HELP_PREFIX+CLEAR_SCREEN:
                ConsoleController.consoleWrite("Clear console text");
                break;
            case HELP_PREFIX+DRAW:
                printDraw();
                break;
            case HELP_PREFIX+REMOVE:
                ConsoleController.consoleWrite("<!rm x> - Remove a node with name x");
                break;
            case HELP_PREFIX+SEARCH:
                ConsoleController.consoleWrite("<!search x> - Search a node with name x");
                break;
            default:
                ConsoleController.consoleWrite("Help command not recognized, type !help");
        }
    }

    private void printAlgorithm() {
        String sb = "\n" + BIPARTITE + "\n\n" +
                COMPONENT + "\n\n" +
                SCC + "\n\n" +
                BRIDGE + "\n\n" +
                CHROMATIC + "\n\n";
        ConsoleController.consoleWrite(sb);
    }

    private void printReset() {
        String sb = "\n" + RESET_ALL + "\n\n" +
                RESET_EDGE + "\n\n";
        ConsoleController.consoleWrite(sb);
    }

    private void printNode() {
        String sb = "\n" + NODE_EMPTY + "\n\n" +
                NODE_XY + "\n\n" +
                NODE_XY_VAL + "\n\n";
        ConsoleController.consoleWrite(sb);
    }

    private void printEdge() {
        String sb = "\n" + EDGE_EMPTY + "\n\n" +
                EDGE_ORDER + "\n\n" +
                EDGE_ORDER_VAL + "\n\n" +
                EDGE_ORDER_VAL_DIRECTION + "\n\n";
        ConsoleController.consoleWrite(sb);
    }

    private void printDraw() {
        String sb = "\n" + DRAW_KMN + "\n\n" +
                DRAW_CYCLE + "\n\n" +
                DRAW_K + "\n\n";
        ConsoleController.consoleWrite(sb);
    }
}
