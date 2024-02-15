package org.example.Hibernate;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.TableView.ObjectModel;
import org.example.TableView.ObjectViewModel;

import java.math.BigDecimal;

public class MainWindowController {
    @FXML private Pane titlePane;
    @FXML private ImageView btnClose, btnMinimize;
    @FXML private TableView<ObjectViewModel> mainTableView;
    @FXML private Button objectButton;
    @FXML private Button clearButton;



    private double x, y;

    public void init(Stage stage){
        setUpDraggableStage(stage);
        setUpCloseButton(stage);
        setUpMinimizeButton(stage);
    }

    private void setUpDraggableStage(Stage stage) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });
    }

    private void setUpCloseButton(Stage stage) {
        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
    }

    private void setUpMinimizeButton(Stage stage) {
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }



    public void addObjectTable(){
        mainTableView.getColumns().clear();

        TableColumn<ObjectViewModel, Integer> idColumn = new TableColumn<>("object_id");
        TableColumn<ObjectViewModel, String> streetColumn = new TableColumn<>("street");
        TableColumn<ObjectViewModel, Integer> streetNumColumn = new TableColumn<>("street_num");
        TableColumn<ObjectViewModel, Double> areaColumn = new TableColumn<>("area");
        TableColumn<ObjectViewModel, BigDecimal> priceColumn = new TableColumn<>("price");
        TableColumn<ObjectViewModel, String> statusColumn = new TableColumn<>("status");
        TableColumn<ObjectViewModel, Integer> roomCountColumn = new TableColumn<>("room_count");

        idColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getObjectId().getValue(),
        cellData.getValue().getObjectId()));

        streetColumn.setCellValueFactory(cellData -> cellData.getValue().getStreet());

        streetNumColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getStreet_num().getValue(),
                cellData.getValue().getStreet_num()));

        areaColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getArea().getValue(),
                cellData.getValue().getArea()));

        priceColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getPrice().getValue(),
                cellData.getValue().getPrice()));

        statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatus());

        roomCountColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getRoomCount().getValue(),
                cellData.getValue().getRoomCount()));

        ObjectModel objectModel = new ObjectModel();
        mainTableView.setItems(objectModel.getObjectViewModels());
        mainTableView.getColumns().addAll(idColumn, streetColumn, streetNumColumn, areaColumn, priceColumn, statusColumn, roomCountColumn);
    }

    public void clickButtonAddObjectTable(ActionEvent actionEvent) {

        addObjectTable();
    }

    public void clearTableView (ActionEvent actionEvent){

    }


}
