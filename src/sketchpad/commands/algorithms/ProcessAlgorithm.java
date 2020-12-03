package sketchpad.commands.algorithms;

import sketchpad.commands.Command;
import sketchpad.controller.ConsoleController;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.model.algorithms.graph.Components;
import sketchpad.model.algorithms.graph.DepthFirstSearch;

public class ProcessAlgorithm implements Command {

    private final int ALGORITHM_NAME = 1;
    private String[] commands;
    private final String COMPONENT = "component", EMPTY = "";

    public ProcessAlgorithm(String args) {
        commands = args.split(" ");

        // we want to make sure that length is > 1. Basically input is just not just !algorithm
        if(commands.length <= 1) {
            commands = new String[] {"", EMPTY}; // we want to just run EMPTY if not enough is given
        }
    }

    @Override
    public void execute() {
        switch (commands[ALGORITHM_NAME]) {
            case COMPONENT: CanvasController.performAlgorithm(new Components());
            break;

            case EMPTY:
            default:
                ConsoleController.consoleWrite("Please specify an algorithm or type ?algorithm for help");
                break;
        }
    }
}
