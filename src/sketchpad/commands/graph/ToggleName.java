package sketchpad.commands.graph;

import sketchpad.commands.Command;
import sketchpad.model.canvaselement.DisplayTypes;
import sketchpad.model.canvaselement.edge.Edge;
import sketchpad.model.canvaselement.vertex.Node;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ToggleName implements Command {

    private LinkedHashMap<String, Node> nodeMap;
    private HashMap<String, Edge> edgeMap;
    private DisplayTypes type;
    private boolean show;

    /*
    * TODO: Use enum to show, order, value or degree
    *
    * showName(TYPE) or showName(value, ei edges or just do search on canvasData for degree value)
    *
    * TODO: Display: Show what the numbers mean; ORDER(NAME), VALUE or DEGREE
    * */

    public ToggleName(LinkedHashMap<String, Node> nodeList, HashMap<String, Edge> edgeList, DisplayTypes type, boolean show) {
        this.nodeMap = nodeList;
        this.show = show;
        this.type = type;
        edgeMap = edgeList;
    }

    @Override
    public void execute() {
        if(show) {
            for(Node node : nodeMap.values())
                node.showLabel(type);
            for(Edge edge : edgeMap.values())
                edge.showLabel(type);
        }
        else {
            for(Node node : nodeMap.values())
                node.hideLabel();
            for(Edge edge : edgeMap.values())
                edge.hideLabel();
        }
    }
}
