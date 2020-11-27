package sketchpad.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sketchpad.constants.Sizes;
import sketchpad.constants.Strings;
import sketchpad.view.BottomDisplay;
import sketchpad.view.Canvas;
import sketchpad.view.Console;

public class SketchPad extends Application {
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        Console console = new Console();
        Canvas canvas = new Canvas();
        BottomDisplay graphDisplay = new BottomDisplay();

        BottomDisplayController.init(graphDisplay);

        ProgramEventController.init(stage);

        root.setLeft(canvas.getLayout());
        root.setRight(console.getLayout());
        root.setBottom(graphDisplay.getLayout());

        Scene mainScene = new Scene(root, Sizes.Application.WIDTH, Sizes.Application.HEIGHT);

        stage.setTitle(Strings.Application.TITLE);
        stage.setScene(mainScene);
        stage.show();
    }
}