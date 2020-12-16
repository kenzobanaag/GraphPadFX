package sketchpad.commands.console;

import sketchpad.commands.*;
import sketchpad.commands.algorithms.ProcessAlgorithm;
import sketchpad.commands.edges.AddEdge;
import sketchpad.commands.edges.RemoveAllEdges;
import sketchpad.commands.graph.ClearSketchPad;
import sketchpad.commands.graph.DrawGraph;
import sketchpad.commands.help.Help;
import sketchpad.commands.help.ProcessHelp;
import sketchpad.commands.nodes.AddNode;
import sketchpad.commands.nodes.DeleteNodeByOrder;
import sketchpad.commands.nodes.SearchNode;
import sketchpad.controller.ConsoleController;

public class ProcessText implements Command {

    private String command = "";
    public static final String PREFIX = "!", SHOW_HELP = "?";
    private static final String RESET_SKETCHPAD = "reset", CLEAR_SCREEN = "cls", COMMANDS = "cmd",
            ADD_NODE = "node", REMOVE_NODE = "rm", SEARCH_NODE = "search", XMAS = "xmas", ADD_EDGE="edge",
            ALGORITHM = "algorithm", DRAW = "draw", HELP = "help";
    private static final String[] COMMAND_LIST = {RESET_SKETCHPAD, CLEAR_SCREEN, ADD_NODE, ADD_EDGE,
            REMOVE_NODE, SEARCH_NODE, XMAS, ALGORITHM, DRAW, HELP};

    public ProcessText(String[] lines) {
        if(lines.length > 0) {
            command = lines[lines.length - 1]; // get last line from text area
            command = command.toLowerCase().trim();
        }
    }

    @Override
    public void execute() {
        switch (command) {
            case PREFIX+RESET_SKETCHPAD :
                new ClearSketchPad().execute();
                break;
            case PREFIX+CLEAR_SCREEN:
                new ClearConsole().execute();
                break;
            case PREFIX+COMMANDS:
                new ConsoleCommands(COMMAND_LIST).execute();
                break;
            case PREFIX+XMAS:
                new Count().execute();
                break;
            case PREFIX+HELP:
                new Help().execute();
                break;
            default:
                if(command.contains(PREFIX+ADD_NODE)) {
                    new AddNode(command).execute();
                    ConsoleController.focus();
                }
                else if(command.contains(PREFIX+REMOVE_NODE)) {
                    new DeleteNodeByOrder(command).execute();
                    ConsoleController.focus();
                }
                else if(command.contains(PREFIX+SEARCH_NODE)) {
                    new SearchNode(command).execute();
                    ConsoleController.focus();
                }
                else if(command.contains(PREFIX+ADD_EDGE)) {
                    new AddEdge(command).execute();
                }
                else if(command.contains(PREFIX+ALGORITHM)) {
                    new ProcessAlgorithm(command).execute();
                }
                else if(command.contains(PREFIX+RESET_SKETCHPAD)) {
                    new RemoveAllEdges(command).execute();
                }
                else if(command.contains(PREFIX+DRAW)) {
                    new DrawGraph(command).execute();
                }
                else if(command.contains(SHOW_HELP)) {
                    new ProcessHelp(command).execute();
                }
                else
                    ConsoleController.consoleWrite("Command not recognized");
            }
    }
}

