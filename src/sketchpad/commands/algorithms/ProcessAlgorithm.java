package sketchpad.commands.algorithms;

import sketchpad.commands.Command;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.model.algorithms.graph.Components;
import sketchpad.model.algorithms.graph.DepthFirstSearch;

public class ProcessAlgorithm implements Command {

    private String command;
    private Command cmd;

    public ProcessAlgorithm(String args) {
        // split by space
        command = args;
        //todo: parse args to run some algorithm

        // we want to make sure that 
    }

    @Override
    public void execute() {
        CanvasController.performAlgorithm(new Components());
    }
}
