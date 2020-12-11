package sketchpad.commands.help;

import sketchpad.commands.Command;
import sketchpad.controller.ConsoleController;

public class ProcessHelp implements Command {

    private final String HELP_PREFIX = "?";
    private final String ALGORITHM = "algorithm";
    private String command;

    private final String BIPARTITE = "<!algorithm bipartite> - Checks if a graph is bipartite";

    private final String COMPONENT = "<!algorithm component> - Counts the number of components of an undirected graph";

    private final String SCC = "<!algorithm scc> - Counts the number of strongly connected components of directed graph";

    private final String BRIDGE = "<!algorithm bridge> - Highlights the edges that are bridges";

    private final String CHROMATIC = "<!algorithm chromatic> - Finds the minimum graph coloring";

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
            default:
                ConsoleController.consoleWrite("Help command not recognized, type !help");
        }
    }

    private void printAlgorithm() {
        String sb = BIPARTITE + "\n\n" +
                COMPONENT + "\n\n" +
                SCC + "\n\n" +
                BRIDGE + "\n\n" +
                CHROMATIC + "\n\n";
        ConsoleController.consoleWrite(sb);
    }
}
