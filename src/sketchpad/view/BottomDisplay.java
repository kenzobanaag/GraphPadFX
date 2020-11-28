package sketchpad.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import sketchpad.constants.Sizes;
import sketchpad.controller.canvas.CanvasController;
import sketchpad.utils.InputValidator;
import sketchpad.utils.TryParse;

import static sketchpad.constants.ColorScheme.Display.BACKGROUND_STR;
import static sketchpad.constants.ColorScheme.Display;
import static sketchpad.constants.ColorScheme.Canvas;
import static sketchpad.constants.ColorScheme.Node.SELECTED_STR;

public class BottomDisplay extends LayoutContainer{

    private final Label edgesInputLabel;
    private final Label nodesInputLabel;
    private final Label selectionNameLabel;
    private final Label selectionTitleLabel;
    private final Label valueTitleLabel;
    private final TextField valueInputField;
    private final String TITLE_STYLE = String.format("-fx-font-size: %d; " +
            "-fx-font-family: Calibri;" +
            "-fx-font-weight: Bold;" +
            "-fx-text-fill: %s;", Sizes.Display.TEXT_SIZE, Canvas.TEXT_STR);
    private final String INPUT_STYLE = String.format("-fx-font-size: %d; " +
            "-fx-font-family: Calibri;" +
            "-fx-font-weight: Bold;" +
            "-fx-text-fill: %s;", Sizes.Display.TEXT_SIZE, Display.TEXT_STR);

    private final String SELECTION_STYLE = String.format("-fx-font-size: %d; " +
            "-fx-font-family: Calibri;" +
            "-fx-font-weight: Bold;" +
            "-fx-text-fill: %s;", Sizes.Display.TEXT_SIZE, SELECTED_STR);

    private final String BACKGROUND = String.format("-fx-background-color: %s;", BACKGROUND_STR);

    private Pane layout;

    public BottomDisplay() {
        layout = new HBox(10);
        layout.setStyle(BACKGROUND);

        Label edgesTitleLabel = new Label("m = ");
        edgesTitleLabel.setStyle(TITLE_STYLE);
        Label nodesTitleLabel = new Label("n = ");
        nodesTitleLabel.setStyle(TITLE_STYLE);

        edgesInputLabel = new Label("0");
        edgesInputLabel.setStyle(INPUT_STYLE);
        nodesInputLabel = new Label("0");
        nodesInputLabel.setStyle(INPUT_STYLE);

        selectionTitleLabel = new Label("Selection: ");
        selectionTitleLabel.setStyle(TITLE_STYLE);
        selectionTitleLabel.setVisible(false);
        selectionNameLabel = new Label();
        selectionNameLabel.setStyle(SELECTION_STYLE);

        valueTitleLabel = new Label("Value: ");
        valueTitleLabel.setStyle(TITLE_STYLE);
        valueTitleLabel.setVisible(false);
        valueInputField = new TextField();
        valueInputField.setPrefSize(35,35);
        valueInputField.setVisible(false);

        setValueChangedListener();

        layout.getChildren().addAll(edgesTitleLabel, edgesInputLabel, nodesTitleLabel, nodesInputLabel,
                selectionTitleLabel, selectionNameLabel, valueTitleLabel,valueInputField);
    }

    private void setValueChangedListener() {
        valueInputField.textProperty().addListener((observable, oldVal, newVal) -> {
            // fixme: not dependable or good design
            CanvasController.changeElementValue(TryParse.tryParseInt(newVal));
        });
    }

    public void setEdges(int numEdges) {
        edgesInputLabel.setText(String.valueOf(numEdges));
    }

    public void setNodes(int numNodes) {
        nodesInputLabel.setText(String.valueOf(numNodes));
    }

    public void showSelection(String selectionName, int value) {
        selectionNameLabel.setVisible(true);
        selectionTitleLabel.setVisible(true);
        valueInputField.setVisible(true);
        valueTitleLabel.setVisible(true);
        selectionNameLabel.setText(selectionName);
        valueInputField.setText(value+"");
    }

    public void hideSelection() {
        selectionNameLabel.setVisible(false);
        selectionTitleLabel.setVisible(false);
        valueInputField.setVisible(false);
        valueTitleLabel.setVisible(false);
    }

    @Override
    public Pane getLayout() {
        return layout;
    }
}
