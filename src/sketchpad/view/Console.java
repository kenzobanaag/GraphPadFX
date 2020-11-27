package sketchpad.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import sketchpad.constants.ColorScheme;
import sketchpad.constants.Sizes;
import sketchpad.controller.ConsoleController;

import static sketchpad.constants.ColorScheme.Canvas;
import static sketchpad.constants.Strings.Console.MINIMIZE;
import static sketchpad.constants.Strings.Console.TITLE;

/*
* We want a title, and a minimize button
* Should contain the layout of the console
*
* A text view on the top, a minimize button, then the rest is a text area.
* */
public class Console extends LayoutContainer {

    private final String CONSOLE_STYLE = String.format("-fx-background-color: %s;", ColorScheme.Console.BACKGROUND_STR);
    private final String TITLE_BAR_STYLE = String.format("-fx-background-color: %s;", Canvas.BACKGROUND_STR);
    private final String TEXT_AREA_STYLE = String.format("text-area-background: %s;", Canvas.BACKGROUND_STR);
    private BorderPane layout;


    public Console() {
        layout = new BorderPane();
        layout.setPrefSize(Sizes.Console.WIDTH, Sizes.Console.HEIGHT);

        BorderPane titleBar = new BorderPane();

        titleBar.setStyle(CONSOLE_STYLE);

        Label titleLabel = new Label(TITLE);
        Button minimizeButton = new Button(MINIMIZE);
        titleBar.setLeft(titleLabel);
        titleBar.setRight(minimizeButton);

        TextArea textArea = new TextArea();
        textArea.setStyle("text-area-background: green;");
        textArea.setWrapText(true);
        layout.setCenter(textArea);
        layout.setTop(titleBar);

        layout.setStyle(CONSOLE_STYLE);

        ConsoleController.init(textArea);
    }

    @Override
    public Pane getLayout() {
        return layout;
    }
}
