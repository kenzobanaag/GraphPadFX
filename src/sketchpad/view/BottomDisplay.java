package sketchpad.view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import sketchpad.constants.ColorScheme;
import sketchpad.constants.Sizes;

import java.awt.*;

import static sketchpad.constants.ColorScheme.Display.BACKGROUND_STR;
import static sketchpad.constants.ColorScheme.Display;
import static sketchpad.constants.ColorScheme.Canvas;
import static sketchpad.constants.ColorScheme.Node.SELECTED_STR;

public class BottomDisplay extends LayoutContainer{

    private final Label edgesInputLabel;
    private final Label nodesInputLabel;
    private final Label nodeOrderLabel;
    private final Label nodeTitleLabel;
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

        nodeTitleLabel = new Label("Selection: ");
        nodeTitleLabel.setStyle(TITLE_STYLE);
        nodeTitleLabel.setVisible(false);
        nodeOrderLabel = new Label();
        nodeOrderLabel.setStyle(SELECTION_STYLE);

        layout.getChildren().addAll(edgesTitleLabel, edgesInputLabel, nodesTitleLabel, nodesInputLabel, nodeTitleLabel, nodeOrderLabel);
    }

    public void setEdges(int numEdges) {
        edgesInputLabel.setText(String.valueOf(numEdges));
    }

    public void setNodes(int numNodes) {
        nodesInputLabel.setText(String.valueOf(numNodes));
    }

    public void showSelection(int numNodes) {
        nodeOrderLabel.setVisible(true);
        nodeTitleLabel.setVisible(true);
        nodeOrderLabel.setText(numNodes+"");
    }

    public void hideSelection() {
        nodeOrderLabel.setVisible(false);
        nodeTitleLabel.setVisible(false);
    }

    @Override
    public Pane getLayout() {
        return layout;
    }
}
