package org.example.Hibernate;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.TableView.AgreementModel;
import org.example.TableView.AgreementViewModel;
import org.example.TableView.ObjectModel;
import org.example.TableView.ObjectViewModel;

import java.math.BigDecimal;
import java.util.Date;

public class MainWindowController {
    @FXML
    private Pane titlePane;
    @FXML
    private ImageView btnClose, btnMinimize;
    @FXML
    private TableView mainTableView;


    private double x, y;

    public void init(Stage stage) {
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
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
    }

    private void setUpCloseButton(Stage stage) {
        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
    }

    private void setUpMinimizeButton(Stage stage) {
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }

    public void clickButtonAddObjectTable(ActionEvent actionEvent) {
        addObjectTable();
    }

    public void clickButtonAddAgreementTable(ActionEvent actionEvent){
        addAgreementTable();
    }


    public void addObjectTable() {
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

    public void addAgreementTable(){
        mainTableView.getColumns().clear();

        TableColumn<AgreementViewModel, Integer> agreementIdColumn = new TableColumn<>("agreement_id");
        TableColumn<AgreementViewModel, Integer> objectIdColumn = new TableColumn<>("object_id");
        TableColumn<AgreementViewModel, Integer> clientIdColumn = new TableColumn<>("client_id");
        TableColumn<AgreementViewModel, Date> agreementDateColumn = new TableColumn<>("agreement_date");
        TableColumn<AgreementViewModel, Integer> agreementPriceColumn = new TableColumn<>("agreement_price");
        TableColumn<AgreementViewModel, String> agreementStatusColumn = new TableColumn<>("agreement_status");

        agreementIdColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getAgreementId().getValue(),
                cellData.getValue().getAgreementId()));

        objectIdColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getObjectId().getValue(),
                cellData.getValue().getObjectId()));

        clientIdColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getClientId().getValue(),
                cellData.getValue().getClientId()));

        agreementDateColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getAgreementDate().getValue(),
                cellData.getValue().getAgreementDate()));

        agreementPriceColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getAgreementPrice().getValue(),
                cellData.getValue().getAgreementPrice()));

        agreementStatusColumn.setCellValueFactory(cellData -> cellData.getValue().getAgreementStatus());

        AgreementModel agreementModel = new AgreementModel();

        mainTableView.setItems(agreementModel.getAgreementViewModels());
        mainTableView.getColumns().addAll(agreementIdColumn, objectIdColumn, clientIdColumn, agreementDateColumn,
                agreementPriceColumn, agreementStatusColumn);











    }


}
