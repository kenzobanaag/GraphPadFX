package sketchpad.commands.graph;

import sketchpad.commands.Command;
import sketchpad.model.canvaselement.vertex.Node;

import java.util.LinkedHashMap;

public class ToggleName implements Command {

    private LinkedHashMap<String, Node> nodeList;
    private boolean show;

    /*
    * TODO: Use enum to show, order, value or degree
    *
    * showName(TYPE) or showName(value, ei edges or just do search on canvasData for degree value)
    *
    * TODO: Display: Show what the numbers mean; ORDER(NAME), VALUE or DEGREE
    * */

    public ToggleName(LinkedHashMap<String, Node> nodeList, boolean show) {
        this.nodeList = nodeList;
        this.show = show;
    }

    @Override
    public void execute() {
        if(show) {
            for(Node node : nodeList.values())
                node.showName();
        }
        else {
            for(Node node : nodeList.values())
                node.hideName();
        }
    }
}
