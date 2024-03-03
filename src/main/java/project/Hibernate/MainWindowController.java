package project.Hibernate;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.hibernate.Session;
import project.Entity.Object;
import project.Entity.*;
import project.Hibernate.RecordPart.RecordWindow;
import project.Hibernate.RecordPart.TableEnum;
import project.TableView.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;


public class MainWindowController {
    @FXML
    private Pane titlePane;
    @FXML
    private ImageView btnClose;
    @FXML
    private ImageView btnMinimize;
    @FXML
    private TableView mainTableView;

    private double x;
    private double y;

    RecordWindow recordWindow = new RecordWindow();

    TableEnum tableEnum;

    public void init(Stage stage) {

        setUpDraggableStage(stage);
        setUpCloseButton(stage);
        setUpMinimizeButton(stage);
        mainTableView.setColumnResizePolicy(
                TableView.UNCONSTRAINED_RESIZE_POLICY);
        mainTableView.autosize();

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

    public void addObjectRecord() throws IOException{
        recordWindow.recordStatusEnum = RecordStatusEnum.ADD_OBJECT;
        recordWindow.init();
    }

    public void updateObjectRecord() throws IOException{
        recordWindow.recordStatusEnum = RecordStatusEnum.UPDATE_OBJECT;
        recordWindow.init();
    }


    public void addAgreementRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.ADD_AGREEMENT;
        recordWindow.init();

    }

    public void updateAgreementRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.UPDATE_AGREEMENT;
        recordWindow.init();

    }

    public void addClientRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.ADD_CLIENT;
        recordWindow.init();
    }

    public void updateClientRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.UPDATE_CLIENT;
        recordWindow.init();
    }

    public void addConsultationRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.ADD_CONSULTATION;
        recordWindow.init();
    }

    public void updateConsultationRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.UPDATE_CONSULTATION;
        recordWindow.init();
    }

    public void addFacilityRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.ADD_FACILITY;
        recordWindow.init();
    }

    public void updateFacilityRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.UPDATE_FACILITY;
        recordWindow.init();
    }

    public void addRequirementRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.ADD_REQUIREMENT;
        recordWindow.init();
    }

    public void updateRequirementRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.UPDATE_REQUIREMENT;
        recordWindow.init();
    }


    public void clickButtonAddObjectTable() {
        initObjectTable(mainTableView);
    }

    public void clickButtonAddAgreementTable() {
        initAgreementTable(mainTableView);
    }

    public void clickButtonAddClientTable() {
        initClientTable(mainTableView);
    }

    public void clickButtonAddConsultationTable() {
        initConsultationTable(mainTableView);
    }

    public void clickButtonAddFacilityTable() {
        initFacilityTable(mainTableView);
    }

    public void clickButtonAddRequirementTable() {
        initRequirementTable(mainTableView);
    }

    public void clickDeleteRecordButton (){
        deleteSelectedRecord(mainTableView);
    }


    public void deleteSelectedRecord(TableView<?> tableView) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        switch (tableEnum){
            case OBJECT:
                ObjectViewModel selectedObject = (ObjectViewModel)
                        tableView.getSelectionModel().getSelectedItem();

                Object object = selectedObject.getOriginalObject();


                session.beginTransaction();
                session.delete(object);
                session.getTransaction().commit();
                session.close();

                tableView.getItems().remove(selectedObject);
                break;

            case AGREEMENT:
                AgreementViewModel selectedAgreement = (AgreementViewModel)
                        tableView.getSelectionModel().getSelectedItem();

                Agreement agreement = selectedAgreement.getOriginalAgreement();
                session.beginTransaction();
                session.delete(agreement);
                session.getTransaction().commit();
                session.close();

                tableView.getItems().remove(selectedAgreement);
                break;

            case CLIENT:
                ClientViewModel selectedClient = (ClientViewModel)
                        tableView.getSelectionModel().getSelectedItem();

                Client client = selectedClient.getOriginalClient();
                session.beginTransaction();
                session.delete(client);
                session.getTransaction().commit();
                session.close();

                tableView.getItems().remove(selectedClient);
                break;

            case REQUIREMENT:
                RequirementViewModel selectedRequirement =
                        (RequirementViewModel)
                        tableView.getSelectionModel().getSelectedItem();

                Requirement requirement =
                        selectedRequirement.getOriginalRequirement();
                session.beginTransaction();
                session.delete(requirement);
                session.getTransaction().commit();
                session.close();

                tableView.getItems().remove(selectedRequirement);
                break;

            case FACILITY:
                FacilityViewModel selectedFacility = (FacilityViewModel)
                        tableView.getSelectionModel().getSelectedItem();

                Facilities facility = selectedFacility.getOriginalFacility();
                session.beginTransaction();
                session.delete(facility);
                session.getTransaction().commit();
                session.close();

                tableView.getItems().remove(selectedFacility);
                break;

            case CONSULTATION:
                ConsultationViewModel selectedConsultation =
                        (ConsultationViewModel)
                        tableView.getSelectionModel().getSelectedItem();

                Consultation consultation =
                        selectedConsultation.getOriginalConsultation();
                session.beginTransaction();
                session.delete(consultation);
                session.getTransaction().commit();
                session.close();

                tableView.getItems().remove(selectedConsultation);
                break;

        }
    }


    public void initObjectTable(TableView tableView){
        ObjectModel objectModel = new ObjectModel();
        tableEnum = TableEnum.OBJECT;

        tableView.getColumns().clear();

        TableColumn<ObjectViewModel, Integer> idColumn =
                new TableColumn<>("ID об'єкту");
        TableColumn<ObjectViewModel, String> streetColumn =
                new TableColumn<>("Вулиця");
        TableColumn<ObjectViewModel, Integer> streetNumColumn =
                new TableColumn<>("Номер будинку");
        TableColumn<ObjectViewModel, Double> areaColumn =
                new TableColumn<>("Площа");
        TableColumn<ObjectViewModel, BigDecimal> priceColumn =
                new TableColumn<>("Ціна");
        TableColumn<ObjectViewModel, String> statusColumn =
                new TableColumn<>("Статус");
        TableColumn<ObjectViewModel, Integer> roomCountColumn =
                new TableColumn<>("Кількість кімнат");

        idColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(
                () -> cellData.getValue().getObjectId().getValue()));

        streetColumn.setCellValueFactory(
                cellData -> cellData.getValue().getStreet());

        streetNumColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getStreet_num().getValue()));

        areaColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getArea().getValue()));

        priceColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getPrice().getValue()));

        statusColumn.setCellValueFactory(
                cellData -> cellData.getValue().getStatus());

        roomCountColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getRoomCount().getValue()));

        tableView.setItems(objectModel.getObjectViewModels());
        tableView.getColumns().addAll(idColumn, streetColumn,
                streetNumColumn, areaColumn, priceColumn, statusColumn,
                roomCountColumn);
    }

    public void initAgreementTable(TableView tableView){
        AgreementModel agreementModel = new AgreementModel();
        tableView.getColumns().clear();
        tableEnum = TableEnum.AGREEMENT;

        TableColumn<AgreementViewModel, Integer> agreementIdColumn =
                new TableColumn<>("ID угоди");
        TableColumn<AgreementViewModel, Integer> objectIdColumn =
                new TableColumn<>("ID об'єкту");
        TableColumn<AgreementViewModel, Integer> clientIdColumn =
                new TableColumn<>("ID клієнта");
        TableColumn<AgreementViewModel, Date> agreementDateColumn =
                new TableColumn<>("Дата укладання угоди");
        TableColumn<AgreementViewModel, Integer> agreementPriceColumn =
                new TableColumn<>("Ціна послуги укладання");
        TableColumn<AgreementViewModel, String> agreementStatusColumn =
                new TableColumn<>("Статус угоди");

        agreementIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getAgreementId()
                                .getValue()));

        objectIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getObjectId().getValue()));

        clientIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getClientId().getValue()));

        agreementDateColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getAgreementDate()
                                .getValue()));

        agreementPriceColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getAgreementPrice()
                                .getValue()));

        agreementStatusColumn.setCellValueFactory(
                cellData -> cellData.getValue().getAgreementStatus());

        tableView.setItems(agreementModel.getAgreementViewModels());
        tableView.getColumns().addAll(agreementIdColumn, objectIdColumn,
                clientIdColumn, agreementDateColumn, agreementPriceColumn,
                agreementStatusColumn);
    }

    public void initClientTable(TableView tableView){
        ClientModel clientModel = new ClientModel();
        tableView.getColumns().clear();
        tableEnum = TableEnum.CLIENT;

        TableColumn<ClientViewModel, Integer> clientIdColumn =
                new TableColumn<>("ID клієнта");
        TableColumn<ClientViewModel, String> firstNameColumn =
                new TableColumn<>("Ім'я");
        TableColumn<ClientViewModel, String> secondNameColumn =
                new TableColumn<>("Прізвище ");
        TableColumn<ClientViewModel, Long> contactNumColumn =
                new TableColumn<>("Номер телефону");
        TableColumn<ClientViewModel, Integer> reqIdColumn =
                new TableColumn<>("ID вимоги");

        clientIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getClientId().getValue()));

        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getFirstName());

        secondNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getSecondName());

        contactNumColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getContactNum().getValue()));

        reqIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqId().getValue()));

        tableView.setItems(clientModel.getClientViewModels());
        tableView.getColumns().addAll(clientIdColumn, firstNameColumn,
                secondNameColumn, contactNumColumn, reqIdColumn);
    }

    public void initConsultationTable(TableView tableView){
        ConsultationModel consultationModel = new ConsultationModel();
        tableView.getColumns().clear();
        tableEnum = TableEnum.CONSULTATION;

        TableColumn<ConsultationViewModel, Integer> consIdColumn =
                new TableColumn<>("ID консультації");
        TableColumn<ConsultationViewModel, Integer> clientIdColumn =
                new TableColumn<>("ID клієнта");
        TableColumn<ConsultationViewModel, Date> consDateColumn =
                new TableColumn<>("Дата консультації");
        TableColumn<ConsultationViewModel, String> consStatusColumn =
                new TableColumn<>("Статус консультації");

        consIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getConsId().getValue()));

        clientIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getClientId().getValue()));

        consDateColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getConsDate().getValue()));

        consStatusColumn.setCellValueFactory(
                cellData -> cellData.getValue().getConsStatus());

        tableView.setItems(consultationModel.getConsultationViewModels());
        tableView.getColumns().addAll(consIdColumn, clientIdColumn,
                consDateColumn, consStatusColumn);
    }

    public void initFacilityTable(TableView tableView){
        FacilityModel facilityModel = new FacilityModel();
        tableView.getColumns().clear();
        tableEnum = TableEnum.FACILITY;

        TableColumn<FacilityViewModel, Integer> facilityIdColumn =
                new TableColumn<>("ID зручностей ");
        TableColumn<FacilityViewModel, Integer> objectReferenceIdColumn =
                new TableColumn<>("ID об'єкту");
        TableColumn<FacilityViewModel, Short> minBedroomsColumn =
                new TableColumn<>("Спальні кімнати (min)");
        TableColumn<FacilityViewModel, Short> minBathroomsColumn =
                new TableColumn<>("Ванні кімнати (min)");
        TableColumn<FacilityViewModel, Boolean> garageColumn =
                new TableColumn<>("Гараж");
        TableColumn<FacilityViewModel, Boolean> gardenColumn =
                new TableColumn<>("Сад");
        TableColumn<FacilityViewModel, Boolean> poolColumn =
                new TableColumn<>("Басейн");

        facilityIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getFacilityId().getValue()));

        objectReferenceIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getObjectReferenceId()
                                .getValue()));

        minBedroomsColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getMinBedrooms()
                                .getValue()));

        minBathroomsColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getMinBathrooms()
                                .getValue()));

        garageColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getGarage().getValue()));

        gardenColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getGarden().getValue()));

        poolColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getPool().getValue()));

        tableView.setItems(facilityModel.getFacilityViewModels());
        tableView.getColumns().addAll(facilityIdColumn,
                objectReferenceIdColumn, minBedroomsColumn, minBathroomsColumn,
                garageColumn, gardenColumn, poolColumn);
    }


    public void initRequirementTable(TableView tableView){
        RequirementModel requirementModel = new RequirementModel();
        tableView.getColumns().clear();
        tableEnum = TableEnum.REQUIREMENT;

        TableColumn<RequirementViewModel, Integer> reqIdColumn =
                new TableColumn<>("ID вимоги");
        TableColumn<RequirementViewModel, Integer> reqMinBedroomsColumn =
                new TableColumn<>("Вимога кількості спален");
        TableColumn<RequirementViewModel, Integer> reqMinBathroomsColumn =
                new TableColumn<>("Вимога кількості ванних ");
        TableColumn<RequirementViewModel, Double> reqMinimalAreaColumn =
                new TableColumn<>("Мінімальна площа ");
        TableColumn<RequirementViewModel, Integer> reqMaxPriceColumn =
                new TableColumn<>("Максимальна ціна");
        TableColumn<RequirementViewModel, String> reqStreetColumn =
                new TableColumn<>("Вулиця");
        TableColumn<RequirementViewModel, Boolean> reqGarageColumn =
                new TableColumn<>("Гараж");
        TableColumn<RequirementViewModel, Boolean> reqGardenColumn =
                new TableColumn<>("Сад");
        TableColumn<RequirementViewModel, Boolean> reqPoolColumn =
                new TableColumn<>("Басейн");

        reqIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqId().getValue()));

        reqMinBedroomsColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqMinBedrooms()
                                .getValue()));

        reqMinBathroomsColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqMinBathrooms()
                                .getValue()));

        reqMinimalAreaColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqMinimalArea().
                                getValue()));

        reqMaxPriceColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqMaxPrice()
                                .getValue()));

        reqStreetColumn.setCellValueFactory(
                cellData -> cellData.getValue().getReqStreet());

        reqGarageColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqGarage().getValue()));

        reqGardenColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqGarden().getValue()));

        reqPoolColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqPool().getValue()));

        tableView.setItems(requirementModel.getRequirementViewModels());
        tableView.getColumns().addAll(reqIdColumn, reqMinBedroomsColumn,
                reqMinBathroomsColumn, reqMinimalAreaColumn, reqMaxPriceColumn,
                reqStreetColumn, reqGarageColumn, reqGardenColumn,
                reqPoolColumn);
    }








 





}
