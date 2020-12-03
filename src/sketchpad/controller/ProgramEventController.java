package sketchpad.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/*
* Responsible for program scope events like, movable, toggled label.
*
* fixme: We could probably include selected and lastSelection here?
* */
public class ProgramEventController {

    private static ProgramEventController instance;
    private static volatile boolean movable = true;
    private static Stage stage;
    private static KeyCode currentKeyCode;

    private ProgramEventController(Stage mainStage) {
        stage = mainStage;
        setListeners();
    }

    public static void init(Stage mainStage) {
        if(instance == null)
            instance = new ProgramEventController(mainStage);
    }

    public static boolean isMovable() {
        return movable;
    }

    private void setListeners() {
        EventHandler<KeyEvent> keyPressHandler = event -> { //todo: Thing of way of differentiating directed and undirected, shift for undirected, x for directed.
            if(event.getCode().equals(KeyCode.SHIFT)) {
                movable = false;
                currentKeyCode = KeyCode.SHIFT;
            }
            else if(event.getCode().equals(KeyCode.CONTROL)) {
                movable = false;
                currentKeyCode = KeyCode.CONTROL;
            }

        };

        EventHandler<KeyEvent> keyReleaseHandler = event -> {
            if(event.getCode().equals(KeyCode.SHIFT)) {
                movable = true;
            }
            else if(event.getCode().equals(KeyCode.CONTROL)) {
                movable = true;
            }
            currentKeyCode = null;
        };

        stage.addEventFilter(KeyEvent.KEY_PRESSED, keyPressHandler);
        stage.addEventFilter(KeyEvent.KEY_RELEASED, keyReleaseHandler);
    }

    public static KeyCode getCurrentKey() {
        return currentKeyCode;
    }

    public static void move() {
        movable = true;
    }
}
