package sketchpad.commands.console;

import javafx.application.Platform;
import sketchpad.commands.Command;
import sketchpad.constants.ColorScheme;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.model.canvaselement.vertex.Node;

import java.util.*;

/*
* Uses a new thread to simulate a node traversal
* */
public class Count implements Command {

    private LinkedHashMap<String, Node> canvList;

    public Count() {
        canvList =  CanvasController.getNodeMap();
    }

    @Override
    public void execute() {
        // fixme: use timeline or something. Make it better. This kinda sucks
        List<Node> nodes = new LinkedList(canvList.values());
        if(nodes.size() > 0) {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                int count = 0;
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        if(nodes.size()-1 == count)
                            this.cancel();
                        // some flashing lights
                        // call this xmas
                        if(count % 2 == 0) {
                            for(int i = 0; i < canvList.size(); i++) {
                                if(i % 2 == 0) {
                                    CanvasController.highlight(nodes.get(i).getId(), ColorScheme.Node.GREEN_NODE);
                                }
                                else
                                    CanvasController.highlight(nodes.get(i).getId(), ColorScheme.Node.SELECTED);
                            }
                        }
                        else {
                            for(int i = 0; i < canvList.size(); i++) {
                                if(i % 2 == 0) {
                                    CanvasController.highlight(nodes.get(i).getId(), ColorScheme.Node.SELECTED);
                                }
                                else
                                    CanvasController.highlight(nodes.get(i).getId(), ColorScheme.Node.GREEN_NODE);
                            }
                        }

                        //CanvasController.searchNode(nodes.get(count).getOrder());
                        //CanvasController.highlight(nodes.get(count).getId(), ColorScheme.Node.GOLD_NODE);
                        ++count;
                    });
                }
            }, 0, 100);
        }
    }
}
