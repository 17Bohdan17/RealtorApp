package org.example.Hibernate;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.TableView.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
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

    public void clickButtonAddClientTable(ActionEvent actionEvent){
        addClientTable();
    }

    public void clickButtonAddConsultationTable (ActionEvent actionEvent){
        addConsultationTable();
    }

    public void clickButtonAddFacilityTable (ActionEvent actionEvent){
        addFacilityTable();
    }

    public void clickButtonAddRequirementTable (ActionEvent actionEvent){
        addRequirementTable();
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

    public void addClientTable(){
        mainTableView.getColumns().clear();

        TableColumn<ClientViewModel, Integer> clientIdColumn = new TableColumn<>("client_id");
        TableColumn<ClientViewModel, String> firstNameColumn = new TableColumn<>("first_name");
        TableColumn<ClientViewModel, String> secondNameColumn = new TableColumn<>("second_name");
        TableColumn<ClientViewModel, Long> contactNumColumn = new TableColumn<>("contact_num");
        TableColumn<ClientViewModel, Integer> reqIdColumn = new TableColumn<>("req_id");

        clientIdColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getClientId().getValue(),
                cellData.getValue().getClientId()));

        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstName());

        secondNameColumn.setCellValueFactory(cellData -> cellData.getValue().getSecondName());

        contactNumColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getContactNum().getValue(),
                cellData.getValue().getContactNum()));

        reqIdColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getReqId().getValue(),
                cellData.getValue().getReqId()));

        ClientModel clientModel = new ClientModel();

        mainTableView.setItems(clientModel.getClientViewModels());
        mainTableView.getColumns().addAll(clientIdColumn, firstNameColumn, secondNameColumn, contactNumColumn, reqIdColumn);
    }

    public void addConsultationTable(){
        mainTableView.getColumns().clear();

        TableColumn<ConsultationViewModel, Integer> consIdColumn = new TableColumn<>("cons_id");
        TableColumn<ConsultationViewModel, Integer> clientIdColumn = new TableColumn<>("client_id");
        TableColumn<ConsultationViewModel, Timestamp> consDateColumn = new TableColumn<>("cons_date");
        TableColumn<ConsultationViewModel, String> consStatusColumn = new TableColumn<>("cons_status");


        consIdColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getConsId().getValue(),
                cellData.getValue().getConsId()));

        clientIdColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getClientId().getValue(),
                cellData.getValue().getClientId()));

        consDateColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getConsDate().getValue(),
                cellData.getValue().getConsDate()));

        consStatusColumn.setCellValueFactory(cellData -> cellData.getValue().getConsStatus());

        ConsultationModel consultationModel = new ConsultationModel();

        mainTableView.setItems(consultationModel.getConsultationViewModels());
        mainTableView.getColumns().addAll(consIdColumn, clientIdColumn, consDateColumn, consStatusColumn);

    }

    public void addFacilityTable(){
        mainTableView.getColumns().clear();

        TableColumn<FacilityViewModel, Integer> facilityIdColumn = new TableColumn<>("facility_id");
        TableColumn<FacilityViewModel, Integer> objectReferenceIdColumn = new TableColumn<>("object_reference_id");
        TableColumn<FacilityViewModel, Integer> minBedroomsColumn = new TableColumn<>("min_bedrooms");
        TableColumn<FacilityViewModel, Integer> minBathroomsColumn = new TableColumn<>("min_bathrooms");
        TableColumn<FacilityViewModel, Boolean> garageColumn = new TableColumn<>("garage");
        TableColumn<FacilityViewModel, Boolean> gardenColumn = new TableColumn<>("garden");
        TableColumn<FacilityViewModel, Boolean> poolColumn = new TableColumn<>("pool");



        facilityIdColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getFacilityId().getValue(),
                cellData.getValue().getFacilityId()));

        objectReferenceIdColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getObjectReferenceId().getValue(),
                cellData.getValue().getObjectReferenceId()));

        minBedroomsColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getMinBedrooms().getValue(),
                cellData.getValue().getMinBedrooms()));

        minBathroomsColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getMinBathrooms().getValue(),
                cellData.getValue().getMinBathrooms()));

        garageColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getGarage().getValue(),
                cellData.getValue().getGarage()));

        gardenColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getGarden().getValue(),
                cellData.getValue().getGarden()));

        poolColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getPool().getValue(),
                cellData.getValue().getPool()));



       FacilityModel facilityModel = new FacilityModel();

        mainTableView.setItems(facilityModel.getFacilityViewModels());
        mainTableView.getColumns().addAll(facilityIdColumn, objectReferenceIdColumn, minBedroomsColumn, minBathroomsColumn,
                garageColumn, gardenColumn, poolColumn);

    }

    public void addRequirementTable(){
        mainTableView.getColumns().clear();

        TableColumn<RequirementViewModel, Integer> reqIdColumn = new TableColumn<>("req_id");
        TableColumn<RequirementViewModel, Integer> reqMinBedroomsColumn = new TableColumn<>("req_min_bedrooms");
        TableColumn<RequirementViewModel, Integer> reqMinBathroomsColumn = new TableColumn<>("req_min_bathrooms");
        TableColumn<RequirementViewModel, Double> reqMinimalAreaColumn = new TableColumn<>("req_minimal_area");
        TableColumn<RequirementViewModel, BigDecimal> reqMaxPriceColumn = new TableColumn<>("req_max_price");
        TableColumn<RequirementViewModel, String> reqStreetColumn = new TableColumn<>("req_street");
        TableColumn<RequirementViewModel, Boolean> reqGarageColumn = new TableColumn<>("garage");
        TableColumn<RequirementViewModel, Boolean> reqGardenColumn = new TableColumn<>("garden");
        TableColumn<RequirementViewModel, Boolean> reqPoolColumn = new TableColumn<>("pool");



        reqIdColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getReqId().getValue(),
                cellData.getValue().getReqId()));

        reqMinBedroomsColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getReqMinBedrooms().getValue(),
                cellData.getValue().getReqMinBedrooms()));

        reqMinBathroomsColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getReqMinBathrooms().getValue(),
                cellData.getValue().getReqMinBathrooms()));

        reqMinimalAreaColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getReqMinimalArea().getValue(),
                cellData.getValue().getReqMinimalArea()));

        reqMaxPriceColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getReqMaxPrice().getValue(),
                cellData.getValue().getReqMaxPrice()));

        reqStreetColumn.setCellValueFactory(cellData -> cellData.getValue().getReqStreet());

        reqGarageColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getReqGarage().getValue(),
                cellData.getValue().getReqGarage()));

        reqGardenColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getReqGarden().getValue(),
                cellData.getValue().getReqGarden()));

        reqPoolColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getReqPool().getValue(),
                cellData.getValue().getReqPool()));



       RequirementModel requirementModel = new RequirementModel();

        mainTableView.setItems(requirementModel.getRequirementViewModels());
        mainTableView.getColumns().addAll(reqIdColumn, reqMinBedroomsColumn, reqMinBathroomsColumn, reqMinimalAreaColumn,
                reqMaxPriceColumn, reqStreetColumn, reqGarageColumn, reqGardenColumn, reqPoolColumn);

    }





}
