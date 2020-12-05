package sketchpad.commands.graph;

import sketchpad.commands.Command;
import sketchpad.commands.draw.Cycle;
import sketchpad.controller.ConsoleController;
import sketchpad.utils.TryParse;

public class DrawGraph implements Command {

    private final int GRAPH_NAME = 1;
    private int graphInput = 0;
    private String[] commands;
    private final String EMPTY = "", CYCLE = "cycle", COMPLETE = "k";

    public DrawGraph(String args) {
        commands = args.split(" ");

        // we want to make sure that length is > 1. Basically input is just not just !algorithm
        if(commands.length <= 1) {
            commands = new String[] {EMPTY, EMPTY}; // we want to run switch still even when there's not enough input
        }
        if(commands.length >= 3) {
            graphInput = TryParse.tryParseInt(commands[2]);
        }
    }

    @Override
    public void execute() {
        switch (commands[GRAPH_NAME]) {
            case CYCLE: new Cycle(graphInput).draw(); // take in a number
                break;
            case COMPLETE:
                break;
            case EMPTY:
            default:
                ConsoleController.consoleWrite("Please specify a graph type");
                break;
        }
    }
}
