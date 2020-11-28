package sketchpad.controller;

import sketchpad.model.canvaselement.DisplayTypes;
import sketchpad.view.BottomDisplay;

public class BottomDisplayController {

    private static BottomDisplayController instance;
    private static BottomDisplay display;

    private BottomDisplayController(BottomDisplay display) {
        BottomDisplayController.display = display;
    }

    public static void init(BottomDisplay display) {
        if(instance == null)
            instance = new BottomDisplayController(display);
    }

    public static void setNodes(int numNodes) {
        if(instance != null) {
            display.setNodes(numNodes);
        }
    }

    public static void setEdges(int numEdges) {
        display.setEdges(numEdges);
    }

    // refactored to accept string instead
    public static void showSelection(String label, int value) {
        display.showSelection(label, value);
    }

    public static void hideSelection() {
        display.hideSelection();
    }

    public static void showLabel(DisplayTypes type) {
        display.showLabel(type);
    }

    public static void hideLabel() {
        display.hideLabel();
    }
}
