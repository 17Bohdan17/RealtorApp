package RealtorApp.util;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StageManager {
    private double x, y;

    public void setUpDraggableStage(Stage stage, Pane titlePane) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
    }

    public void setUpCloseButton(Stage stage, ImageView btnClose) {
        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
    }

    public void setUpMinimizeButton(Stage stage, ImageView btnMinimize) {
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }
}
