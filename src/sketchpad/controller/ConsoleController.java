package sketchpad.controller;

import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sketchpad.commands.console.ProcessText;

/*
* Handle all events in the console
* */
public class ConsoleController {

    private static TextArea textArea;

    private static ConsoleController instance;

    public static void init(TextArea area) {
        if(instance == null)
            instance = new ConsoleController(area);
    }

    private ConsoleController(TextArea area) {
        textArea = area;
        setListeners();
        initTextArea();
    }

    private static void setListeners() {
        EventHandler<KeyEvent> keyHandler = event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                new ProcessText(textArea.getText().split("\n")).execute();
            }
        };

        textArea.addEventFilter(KeyEvent.KEY_PRESSED, keyHandler);
    }

    public static void consoleWrite(String msg) {
        textArea.appendText("\n"+msg);
        focus();
    }

    public static void clearConsole() {
        textArea.clear();
        initTextArea();
        focus();
    }

    public static void focus() {
        textArea.requestFocus();
    }

    private static void initTextArea() {
        textArea.appendText(String.format("Type %scmd to see commands\n", ProcessText.PREFIX));
    }
}
