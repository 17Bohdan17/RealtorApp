package org.example.Hibernate.addRecordPart;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddRecordController {
    @FXML
    private Pane addRecordPane;
    @FXML
    private ImageView addRecordWindowCloseBtn;

    private double x, y;

    public void init (Stage stage){
        setUpCloseButton(stage);
        setUpDraggableStage(stage);
    }


    private void setUpDraggableStage(Stage stage) {
        addRecordPane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        addRecordPane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
    }

    private void setUpCloseButton(Stage stage) {
        addRecordWindowCloseBtn.setOnMouseClicked(mouseEvent -> stage.close());
    }
}
