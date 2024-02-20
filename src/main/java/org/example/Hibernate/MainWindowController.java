package org.example.Hibernate;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.Hibernate.addRecordPart.AddRecordWindow;
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

    public void clickButtonAddAgreementTable(ActionEvent actionEvent) {
        addAgreementTable();
    }

    public void clickButtonAddClientTable(ActionEvent actionEvent) {
        addClientTable();
    }

    public void clickButtonAddConsultationTable(ActionEvent actionEvent) {
        addConsultationTable();
    }

    public void clickButtonAddFacilityTable(ActionEvent actionEvent) {
        addFacilityTable();
    }

    public void clickButtonAddRequirementTable(ActionEvent actionEvent) {
        addRequirementTable();
    }

//    public void clickAddRecordButton (ActionEvent actionEvent) throws Exception {
//        AddRecordWindow addRecordWindow = new AddRecordWindow();
//        addRecordWindow.init();
//    }

    public void clickAddAgreementRecord(ActionEvent actionEvent) throws Exception{
        AddRecordWindow addRecordWindow = new AddRecordWindow();
        addRecordWindow.addRecordStatus = 1;




        addRecordWindow.init();

    }








    public void addObjectTable() {
        ObjectModel objectModel = new ObjectModel();
        mainTableView.getColumns().clear();

        TableColumn<ObjectViewModel, Integer> idColumn = new TableColumn<>("ID об'єкту");
        TableColumn<ObjectViewModel, String> streetColumn = new TableColumn<>("Вулиця");
        TableColumn<ObjectViewModel, Integer> streetNumColumn = new TableColumn<>("Номер будинку");
        TableColumn<ObjectViewModel, Double> areaColumn = new TableColumn<>("Площа");
        TableColumn<ObjectViewModel, BigDecimal> priceColumn = new TableColumn<>("Ціна");
        TableColumn<ObjectViewModel, String> statusColumn = new TableColumn<>("Статус");
        TableColumn<ObjectViewModel, Integer> roomCountColumn = new TableColumn<>("Кількість кімнат");

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

        mainTableView.setItems(objectModel.getObjectViewModels());
        mainTableView.getColumns().addAll(idColumn, streetColumn, streetNumColumn, areaColumn, priceColumn, statusColumn, roomCountColumn);
    }

    public void addAgreementTable(){
        AgreementModel agreementModel = new AgreementModel();
        mainTableView.getColumns().clear();

        TableColumn<AgreementViewModel, Integer> agreementIdColumn = new TableColumn<>("ID угоди");
        TableColumn<AgreementViewModel, Integer> objectIdColumn = new TableColumn<>("ID об'єкту");
        TableColumn<AgreementViewModel, Integer> clientIdColumn = new TableColumn<>("ID клієнта");
        TableColumn<AgreementViewModel, Date> agreementDateColumn = new TableColumn<>("Дата укладання угоди");
        TableColumn<AgreementViewModel, Integer> agreementPriceColumn = new TableColumn<>("Ціна послуги укладання");
        TableColumn<AgreementViewModel, String> agreementStatusColumn = new TableColumn<>("Статус угоди");

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

        mainTableView.setItems(agreementModel.getAgreementViewModels());
        mainTableView.getColumns().addAll(agreementIdColumn, objectIdColumn, clientIdColumn, agreementDateColumn,
                agreementPriceColumn, agreementStatusColumn);
    }

    public void addClientTable(){
        ClientModel clientModel = new ClientModel();
        mainTableView.getColumns().clear();

        TableColumn<ClientViewModel, Integer> clientIdColumn = new TableColumn<>("ID клієнта");
        TableColumn<ClientViewModel, String> firstNameColumn = new TableColumn<>("Ім'я");
        TableColumn<ClientViewModel, String> secondNameColumn = new TableColumn<>("Прізвище ");
        TableColumn<ClientViewModel, Long> contactNumColumn = new TableColumn<>("Номер телефону");
        TableColumn<ClientViewModel, Integer> reqIdColumn = new TableColumn<>("ID вимоги");

        clientIdColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getClientId().getValue(),
                cellData.getValue().getClientId()));

        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstName());

        secondNameColumn.setCellValueFactory(cellData -> cellData.getValue().getSecondName());

        contactNumColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getContactNum().getValue(),
                cellData.getValue().getContactNum()));

        reqIdColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getReqId().getValue(),
                cellData.getValue().getReqId()));

        mainTableView.setItems(clientModel.getClientViewModels());
        mainTableView.getColumns().addAll(clientIdColumn, firstNameColumn, secondNameColumn, contactNumColumn, reqIdColumn);
    }

    public void addConsultationTable(){
        ConsultationModel consultationModel = new ConsultationModel();
        mainTableView.getColumns().clear();

        TableColumn<ConsultationViewModel, Integer> consIdColumn = new TableColumn<>("ID консультації");
        TableColumn<ConsultationViewModel, Integer> clientIdColumn = new TableColumn<>("ID клієнта");
        TableColumn<ConsultationViewModel, Timestamp> consDateColumn = new TableColumn<>("Дата консультації");
        TableColumn<ConsultationViewModel, String> consStatusColumn = new TableColumn<>("Статус консультації");

        consIdColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getConsId().getValue(),
                cellData.getValue().getConsId()));

        clientIdColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getClientId().getValue(),
                cellData.getValue().getClientId()));

        consDateColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getConsDate().getValue(),
                cellData.getValue().getConsDate()));

        consStatusColumn.setCellValueFactory(cellData -> cellData.getValue().getConsStatus());

        mainTableView.setItems(consultationModel.getConsultationViewModels());
        mainTableView.getColumns().addAll(consIdColumn, clientIdColumn, consDateColumn, consStatusColumn);

    }

    public void addFacilityTable(){
        FacilityModel facilityModel = new FacilityModel();
        mainTableView.getColumns().clear();

        TableColumn<FacilityViewModel, Integer> facilityIdColumn = new TableColumn<>("ID зручностей ");
        TableColumn<FacilityViewModel, Integer> objectReferenceIdColumn = new TableColumn<>("ID об'єкту");
        TableColumn<FacilityViewModel, Integer> minBedroomsColumn = new TableColumn<>("Спальні кімнати (min)");
        TableColumn<FacilityViewModel, Integer> minBathroomsColumn = new TableColumn<>("Ванні кімнати (min)");
        TableColumn<FacilityViewModel, Boolean> garageColumn = new TableColumn<>("Гараж");
        TableColumn<FacilityViewModel, Boolean> gardenColumn = new TableColumn<>("Сад");
        TableColumn<FacilityViewModel, Boolean> poolColumn = new TableColumn<>("Басейн");

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

        mainTableView.setItems(facilityModel.getFacilityViewModels());
        mainTableView.getColumns().addAll(facilityIdColumn, objectReferenceIdColumn, minBedroomsColumn, minBathroomsColumn,
                garageColumn, gardenColumn, poolColumn);

    }

    public void addRequirementTable(){
        RequirementModel requirementModel = new RequirementModel();
        mainTableView.getColumns().clear();

        TableColumn<RequirementViewModel, Integer> reqIdColumn = new TableColumn<>("ID вимоги");
        TableColumn<RequirementViewModel, Integer> reqMinBedroomsColumn = new TableColumn<>("Вимога кількості спален");
        TableColumn<RequirementViewModel, Integer> reqMinBathroomsColumn = new TableColumn<>("Вимога кількості ванних ");
        TableColumn<RequirementViewModel, Double> reqMinimalAreaColumn = new TableColumn<>("Мінімальна площа ");
        TableColumn<RequirementViewModel, BigDecimal> reqMaxPriceColumn = new TableColumn<>("Максимальна ціна");
        TableColumn<RequirementViewModel, String> reqStreetColumn = new TableColumn<>("Вулиця");
        TableColumn<RequirementViewModel, Boolean> reqGarageColumn = new TableColumn<>("Гараж");
        TableColumn<RequirementViewModel, Boolean> reqGardenColumn = new TableColumn<>("Сад");
        TableColumn<RequirementViewModel, Boolean> reqPoolColumn = new TableColumn<>("Басейн");

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

        mainTableView.setItems(requirementModel.getRequirementViewModels());
        mainTableView.getColumns().addAll(reqIdColumn, reqMinBedroomsColumn, reqMinBathroomsColumn, reqMinimalAreaColumn,
                reqMaxPriceColumn, reqStreetColumn, reqGarageColumn, reqGardenColumn, reqPoolColumn);

    }





}
