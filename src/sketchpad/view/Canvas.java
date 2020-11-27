package sketchpad.view;

import javafx.scene.layout.Pane;
import sketchpad.constants.Sizes;
import sketchpad.controller.canvas.CanvasController;


import static sketchpad.constants.ColorScheme.Canvas.BACKGROUND_STR;

/*
* Hold all the items inside a canvas
* */
public class Canvas extends LayoutContainer {

    private final String CANVAS_STYLE = String.format("-fx-background-color: %s;", BACKGROUND_STR);
    private Pane layout;

    public Canvas() {
        layout = new Pane();
        layout.setStyle(CANVAS_STYLE);
        layout.setPrefSize(Sizes.Canvas.WIDTH, Sizes.Canvas.HEIGHT);
        CanvasController.init(this);
    }

    @Override
    public Pane getLayout() {
        return layout;
    }
}
