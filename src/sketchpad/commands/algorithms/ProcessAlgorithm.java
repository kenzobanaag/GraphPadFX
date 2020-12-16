package sketchpad.commands.algorithms;

import sketchpad.commands.Command;
import sketchpad.controller.ConsoleController;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.model.algorithms.graph.Bipartite;
import sketchpad.model.algorithms.graph.Bridges;
import sketchpad.model.algorithms.graph.componentinfo.Components;
import sketchpad.model.algorithms.graph.GreedyChromatic;
import sketchpad.model.algorithms.graph.componentinfo.StronglyConnectedComponents;

public class ProcessAlgorithm implements Command {

    private final int ALGORITHM_NAME = 1;
    private String[] commands;
    private final String COMPONENT = "component", EMPTY = "", BRIDGE = "bridge", SCC = "scc", CHROMATIC = "chromatic",
        BIPARTITE = "bipartite";

    public ProcessAlgorithm(String args) {
        commands = args.split(" ");

        // we want to make sure that length is > 1. Basically input is just not just !algorithm
        if(commands.length <= 1) {
            commands = new String[] {EMPTY, EMPTY}; // we want to run switch still even when there's not enough input
        }
    }

    @Override
    public void execute() {
        switch (commands[ALGORITHM_NAME]) {
            case COMPONENT: CanvasController.performAlgorithm(new Components());
                break;
            case BRIDGE: CanvasController.performAlgorithm(new Bridges());
                break;
            case SCC: CanvasController.performAlgorithm(new StronglyConnectedComponents());
                break;
            case CHROMATIC: CanvasController.performAlgorithm(new GreedyChromatic());
                break;
            case BIPARTITE: CanvasController.performAlgorithm(new Bipartite());
                break;
            case EMPTY:
            default:
                ConsoleController.consoleWrite("Please specify an algorithm or type ?algorithm for help");
                break;
        }
    }
}
