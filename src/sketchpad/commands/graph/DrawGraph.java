package sketchpad.commands.graph;

import sketchpad.commands.Command;
import sketchpad.commands.draw.Bipartite;
import sketchpad.commands.draw.Cycle;
import sketchpad.commands.draw.KComplete;
import sketchpad.commands.draw.Petersen;
import sketchpad.controller.ConsoleController;
import sketchpad.utils.TryParse;

public class DrawGraph implements Command {

    private final int GRAPH_NAME = 1;
    private int graphInput = 0;
    private int graphInput2 = 0;
    private String[] commands;
    private final String EMPTY = "", CYCLE = "c", COMPLETE = "k", BIPARTITE = "kmn", PETERSEN = "petersen";

    public DrawGraph(String args) {
        commands = args.split(" ");

        // we want to make sure that length is > 1. Basically input is just not just !algorithm
        if(commands.length <= 1) {
            commands = new String[] {EMPTY, EMPTY}; // we want to run switch still even when there's not enough input
        }
        if(commands.length >= 3) {
            graphInput = TryParse.tryParseInt(commands[2]);
        }
        if(commands.length >= 4) {
            graphInput2 = TryParse.tryParseInt(commands[3]);
        }
    }

    @Override
    public void execute() {
        switch (commands[GRAPH_NAME]) {
            case CYCLE: new Cycle(graphInput).draw(); // take in a number
                break;
            case COMPLETE: new KComplete(graphInput).draw();
                break;
            case BIPARTITE: new Bipartite(graphInput, graphInput2).draw();
                break;
            case PETERSEN: new Petersen().draw();
                break;
            case EMPTY:
            default:
                ConsoleController.consoleWrite("Please specify a graph type");
                break;
        }
    }
}
