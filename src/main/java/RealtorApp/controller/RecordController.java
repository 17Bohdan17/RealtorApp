package RealtorApp.controller;

import RealtorApp.model.entity.*;
import RealtorApp.model.entity.Object;
import RealtorApp.model.modelView.*;
import RealtorApp.util.ChoiceBoxInitializer;
import RealtorApp.util.hibernate.HibernateUtil;
import javafx.animation.PauseTransition;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;


public class RecordController {

    @FXML
    private Pane addRecordPane;
    @FXML
    private ImageView addRecordWindowCloseBtn;
    @FXML
    private Label addRecordDoneLabel;

    @FXML
    private TextField idAgreementObjectTextField;
    @FXML
    private TextField idAgreementClientTextField;
    @FXML
    private TextField idAgreementPriceTextField;
    @FXML
    private DatePicker addAgreementRecordDatePicker;
    @FXML
    private ChoiceBox agreementStatusChoiceBox;

    @FXML
    private TextField objectStreetTextField;
    @FXML
    private TextField objectNumberTextField;
    @FXML
    private TextField objectAreaTextField;
    @FXML
    private TextField objectPriceTextField;
    @FXML
    private TextField objectRoomCountTextField;
    @FXML
    private ChoiceBox objectStatusChoiceBox;

    @FXML
    private TextField clientFirstNameTextField;
    @FXML
    private TextField clientSecondNameTextField;
    @FXML
    private TextField clientNumberTextField;
    @FXML
    private TextField clientIdRequirementTextField;

    @FXML
    private TextField consultationIdClientTextField;
    @FXML
    private DatePicker consultationDatePicker;
    @FXML
    private ChoiceBox consultationStatusChoiceBox;

    @FXML
    private TextField facilityIdObjectTextField;
    @FXML
    private TextField facilityMinBedroomsTextField;
    @FXML
    private TextField facilityMinBathroomsTextField;
    @FXML
    private ChoiceBox<Boolean> facilityGarageChoiceBox;
    @FXML
    private ChoiceBox<Boolean> facilityGardenChoiceBox;
    @FXML
    private ChoiceBox<Boolean> facilityPoolChoiceBox;

    @FXML
    private TextField requirementStreetTextField;
    @FXML
    private TextField requirementMaxPriceTextField;
    @FXML
    private TextField requirementMinAreaTextField;
    @FXML
    private TextField requirementMinBedroomsTextField;
    @FXML
    private TextField requirementMinBathroomsTextField;
    @FXML
    private ChoiceBox<Boolean> requirementGarageChoiceBox;
    @FXML
    private ChoiceBox<Boolean> requirementGardenChoiceBox;
    @FXML
    private ChoiceBox<Boolean> requirementPoolChoiceBox;

    @FXML
    private TableView agreementTableView;
    @FXML
    private TableView clientTableView;
    @FXML
    private TableView facilityTableView;
    @FXML
    private TableView consultationTableView;

    @FXML
    private TableView updateTableView;


    Session session;
    private double x;
    private double y;

    Agreement agreement = new Agreement();
    Object object = new Object();
    Client client = new Client();
    Consultation consultation = new Consultation();
    Facilities facility = new Facilities();
    Requirement requirement = new Requirement();
    MainWindowController mainWindowController = new MainWindowController();

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

    public void init(Stage stage) {
        setUpCloseButton(stage);
        setUpDraggableStage(stage);
        initializeChoiceBox();

        if (addRecordDoneLabel != null) {
            addRecordDoneLabel.setVisible(false);
        }
    }


    public void clickAddObjectRecordButton() {
        String objectStreet;
        int objectNumber;
        double objectArea;
        int objectPrice;
        String objectStatus;
        short objectRoomCount;

        startTransaction();

        objectStreet = objectStreetTextField.getText();
        objectNumber = Integer.parseInt(objectNumberTextField.getText());
        objectArea = Double.parseDouble(objectAreaTextField.getText());
        objectPrice = Integer.parseInt(objectPriceTextField.getText());
        objectStatus = (String) objectStatusChoiceBox.getValue();
        objectRoomCount = Short.parseShort(objectRoomCountTextField.getText());

        object.setStreet(objectStreet);
        object.setStreetNum(objectNumber);
        object.setArea(objectArea);
        object.setPrice(BigDecimal.valueOf(objectPrice));
        object.setStatus(objectStatus);
        object.setRoomCount(objectRoomCount);

        session.persist(object);
        session.getTransaction().commit();
        labelClose();

        objectStreetTextField.clear();
        objectNumberTextField.clear();
        objectAreaTextField.clear();
        objectPriceTextField.clear();
        objectStatusChoiceBox.setValue(null);
        objectRoomCountTextField.clear();
    }


    public void clickAddAgreementRecordButton() {
        System.out.println(idAgreementObjectTextField);
        int objectId;
        int clientId;
        int agreementPrice;
        Date agreementDate;
        String agreementStatus;

        startTransaction();

        objectId = Integer.parseInt(idAgreementObjectTextField.getText());
        clientId = Integer.parseInt(idAgreementClientTextField.getText());
        agreementDate = Date.valueOf(addAgreementRecordDatePicker.getValue());
        agreementPrice = Integer.parseInt(idAgreementPriceTextField.getText());
        agreementStatus = (String) agreementStatusChoiceBox.getValue();

        agreement.setObjectId(objectId);
        agreement.setClientId(clientId);
        agreement.setAgreementDate(agreementDate);
        agreement.setAgreementPrice(agreementPrice);
        agreement.setAgreementStatus(agreementStatus);

        session.persist(agreement);
        session.getTransaction().commit();
        labelClose();

        idAgreementObjectTextField.clear();
        idAgreementClientTextField.clear();
        addAgreementRecordDatePicker.setValue(null);
        idAgreementPriceTextField.clear();
        agreementStatusChoiceBox.setValue(null);

    }


    public void clickOpenObjectTableInAgreementButton() {
        agreementTableView.getColumns().clear();
        mainWindowController.initObjectTable(agreementTableView);
        initObjectTableInAgreementSelectId();
    }


    public void initObjectTableInAgreementSelectId() {
        agreementTableView.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                ObjectViewModel selectedObject =
                        (ObjectViewModel) agreementTableView
                                .getSelectionModel().getSelectedItem();
                SimpleIntegerProperty objectId = selectedObject.getObjectId();
                String parseObjectId = String.valueOf(objectId.get());
                idAgreementObjectTextField.setText(parseObjectId);
            }
        });
    }

    public void clickOpenClientTableInAgreementButton() {
        agreementTableView.getColumns().clear();
        mainWindowController.initClientTable(agreementTableView);
        initClientTableInAgreementSelectId();
    }


    public void initClientTableInAgreementSelectId() {
        agreementTableView.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                ClientViewModel selectedClient =
                        (ClientViewModel) agreementTableView
                                .getSelectionModel().getSelectedItem();
                SimpleIntegerProperty clientId = selectedClient.getClientId();
                String parseClientId = String.valueOf(clientId.get());
                idAgreementClientTextField.setText(parseClientId);
            }
        });
    }

    public void clickAddClientRecordButton(){
        String firstName;
        String secondName;
        long number;
        int idRequirement;

        startTransaction();

        firstName = clientFirstNameTextField.getText();
        secondName = clientSecondNameTextField.getText();
        number = Long.parseLong(clientNumberTextField.getText());
        idRequirement = Integer.parseInt(
                clientIdRequirementTextField.getText());

        client.setFirstName(firstName);
        client.setSecondName(secondName);
        client.setContactNum(number);
        client.setReqId(idRequirement);

        session.persist(client);
        session.getTransaction().commit();
        labelClose();

        clientFirstNameTextField.clear();
        clientSecondNameTextField.clear();
        clientNumberTextField.clear();
        clientIdRequirementTextField.clear();
    }

    public void clickOpenRequirementInClientTableButton() {
        clientTableView.getColumns().clear();
        mainWindowController.initRequirementTable(clientTableView);
        initRequirementTableInClientSelectId();
    }


    public void initRequirementTableInClientSelectId() {
        clientTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                RequirementViewModel selectedRequirement =
                        (RequirementViewModel) clientTableView
                                .getSelectionModel().getSelectedItem();
                SimpleIntegerProperty reqId = selectedRequirement.getReqId();
                String parseReqId = String.valueOf(reqId.get());
                clientIdRequirementTextField.setText(parseReqId);
            }
        });
    }



    public void clickAddConsultationRecordButton(){
        int clientId;
        Date consultationDate;
        String consultationStatus;

        startTransaction();

        clientId = Integer.parseInt(consultationIdClientTextField.getText());
        consultationDate = Date.valueOf(consultationDatePicker.getValue());
        consultationStatus = (String) consultationStatusChoiceBox.getValue();

        consultation.setClientId(clientId);
        consultation.setConsDate(consultationDate);
        consultation.setConsStatus(consultationStatus);

        session.persist(consultation);
        session.getTransaction().commit();
        labelClose();

        consultationIdClientTextField.clear();
        consultationDatePicker.setValue(null);
        consultationStatusChoiceBox.setValue(null);
    }

    public void clickOpenClientInConsultationTableButton() {
        consultationTableView.getColumns().clear();
        mainWindowController.initClientTable(consultationTableView);
        initClientTableInConsultationSelectId();
    }


    public void initClientTableInConsultationSelectId() {
        consultationTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                ClientViewModel selectedClient =
                        (ClientViewModel) consultationTableView
                                .getSelectionModel().getSelectedItem();
                SimpleIntegerProperty clientId = selectedClient.getClientId();
                String parseClientId = String.valueOf(clientId.get());
                consultationIdClientTextField.setText(parseClientId);
            }
        });
    }

    public void clickAddFacilityRecordButton(){
        int objectId;
        short minBedrooms;
        short minBathrooms;
        boolean garage;
        boolean garden;
        boolean pool;

        startTransaction();

        objectId = Integer.parseInt(facilityIdObjectTextField.getText());
        minBedrooms = Short.parseShort( facilityMinBedroomsTextField.getText());
        minBathrooms = Short.parseShort(
                facilityMinBathroomsTextField.getText());
        garage = facilityGarageChoiceBox.getValue();
        garden = facilityGardenChoiceBox.getValue();
        pool = facilityPoolChoiceBox.getValue();

        facility.setObjectReferenceId(objectId);
        facility.setMinBedrooms(minBedrooms);
        facility.setMinBathrooms(minBathrooms);
        facility.setGarage(garage);
        facility.setGarden(garden);
        facility.setPool(pool);

        session.persist(facility);
        session.getTransaction().commit();
        labelClose();

        facilityIdObjectTextField.clear();
        facilityMinBedroomsTextField.clear();
        facilityMinBathroomsTextField.clear();
        facilityGarageChoiceBox.setValue(null);
        facilityGardenChoiceBox.setValue(null);
        facilityPoolChoiceBox.setValue(null);
    }

    public void clickOpenObjectInFacilityTableButton() {
        facilityTableView.getColumns().clear();
        mainWindowController.initFacilityTable(facilityTableView);
        initObjectTableInFacilitySelectId();
    }


    public void initObjectTableInFacilitySelectId() {
        facilityTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                FacilityViewModel selectedObject =
                        (FacilityViewModel) facilityTableView
                                .getSelectionModel().getSelectedItem();
                SimpleIntegerProperty objectId =
                        selectedObject.getObjectReferenceId();
                String parseObjectId = String.valueOf(objectId.get());
                facilityIdObjectTextField.setText(parseObjectId);
            }
        });
    }

    public void clickAddRequirementRecordButton(){
        String street;
        int maxPrice;
        double minArea;
        short minBedrooms;
        short minBathrooms;
        boolean garage;
        boolean garden;
        boolean pool;

        startTransaction();

        street = requirementStreetTextField.getText();
        maxPrice = Integer.parseInt(requirementMaxPriceTextField.getText());
        minArea = Double.parseDouble(requirementMinAreaTextField.getText());
        minBedrooms = Short.parseShort(
                requirementMinBedroomsTextField.getText());
        minBathrooms = Short.parseShort(
                requirementMinBathroomsTextField.getText());
        garage = requirementGarageChoiceBox.getValue();
        garden = requirementGardenChoiceBox.getValue();
        pool = requirementPoolChoiceBox.getValue();

        requirement.setReqStreet(street);
        requirement.setReqMaxPrice(maxPrice);
        requirement.setReqMinimalArea(minArea);
        requirement.setReqMinBedrooms(minBedrooms);
        requirement.setReqMinBathrooms(minBathrooms);
        requirement.setReqGarage(garage);
        requirement.setReqGarden(garden);
        requirement.setReqPool(pool);

        session.persist(requirement);
        session.getTransaction().commit();
        labelClose();

        requirementStreetTextField.clear();
        requirementMaxPriceTextField.clear();
        requirementMinAreaTextField.clear();
        requirementMinBedroomsTextField.clear();
        requirementMinBathroomsTextField.clear();
        requirementGarageChoiceBox.setValue(null);
        requirementGardenChoiceBox.setValue(null);
        requirementPoolChoiceBox.setValue(null);
    }

    private void labelClose (){
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(3));

        visiblePause.setOnFinished(event ->
                addRecordDoneLabel.setVisible(false));
        visiblePause.play();
    }

    private void startTransaction(){
        addRecordDoneLabel.setVisible(true);
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
    }

    private void initializeChoiceBox() {
        ChoiceBoxInitializer.initializeChoiceBox(objectStatusChoiceBox,
                Arrays.asList("FOR_SALE", "AVAILABLE", "UNDER_CONTRACT"),
                """
                        1. "FOR_SALE" - об'єкт доступний тільки для продажу.
                        2. "AVAILABLE" - об'єкт доступний для продажу або оренди.
                        3. "UNDER_CONTRACT" - об'єкт вже під контрактом.""");

        ChoiceBoxInitializer.initializeChoiceBox(agreementStatusChoiceBox,
                Arrays.asList("PENDING", "COMPLETED", "ACTIVE"),
                """
                        1. "PENDING" - в очікуванні
                        2. "COMPLETED" - завершено
                        3. "ACTIVE" - активний""");

        ChoiceBoxInitializer.initializeChoiceBox(consultationStatusChoiceBox,
                Arrays.asList("COMPLETED", "SCHEDULED"),
                "1. COMPLETED - завершено,\n" +
                        "2. SCHEDULED - заплановано");

        ChoiceBoxInitializer.initializeChoiceBox(facilityGarageChoiceBox,
                Arrays.asList(true, false),
                "true - в наявності,\n" +
                        "false - Немає");

        ChoiceBoxInitializer.initializeChoiceBox(facilityGardenChoiceBox,
                Arrays.asList(true, false),
                "true - в наявності,\n" +
                        "false - Немає");

        ChoiceBoxInitializer.initializeChoiceBox(facilityPoolChoiceBox,
                Arrays.asList(true, false),
                "true - в наявності,\n" +
                        "false - Немає");

        ChoiceBoxInitializer.initializeChoiceBox(requirementGarageChoiceBox,
                Arrays.asList(true, false),
                "true - в наявності,\n" +
                        "false - Немає");

        ChoiceBoxInitializer.initializeChoiceBox(requirementGardenChoiceBox,
                Arrays.asList(true, false),
                "true - в наявності,\n" +
                        "false - Немає");

        ChoiceBoxInitializer.initializeChoiceBox(requirementPoolChoiceBox,
                Arrays.asList(true, false),
                "true - в наявності,\n" +
                        "false - Немає");
    }


    public void clickOpenObjectTableForUpdate() {
        updateTableView.getColumns().clear();
        mainWindowController.initObjectTable(updateTableView);
        clickObjectTableItem();
    }

    public void clickObjectTableItem() {
        updateTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
               ObjectViewModel selectedObject =
                        (ObjectViewModel) updateTableView
                                .getSelectionModel().getSelectedItem();

                object = selectedObject.getOriginalObject();

                SimpleStringProperty street =
                        selectedObject.getStreet();
                SimpleIntegerProperty streetNum =
                        selectedObject.getStreet_num();
                SimpleDoubleProperty area =
                        selectedObject.getArea();
                SimpleObjectProperty<BigDecimal> price =
                        selectedObject.getPrice();
                SimpleStringProperty status =
                        selectedObject.getStatus();
                SimpleIntegerProperty roomCount =
                        selectedObject.getRoomCount();

                objectStreetTextField.setText(String.valueOf(street.get()));
                objectNumberTextField.setText(String.valueOf(streetNum.get()));
                objectAreaTextField.setText(String.valueOf(area.get()));
                objectPriceTextField.setText(String.valueOf(price.get()));
                objectStatusChoiceBox.setValue(status.get());
                objectRoomCountTextField.setText(
                        String.valueOf(roomCount.get()));
            }
        });
    }

    public void clickUpdateObjectRecordButton() {
        object.setStreet(objectStreetTextField.getText());
        object.setStreetNum(Integer.parseInt(objectNumberTextField.getText()));
        object.setArea(Double.parseDouble(objectAreaTextField.getText()));
        object.setPrice(BigDecimal.valueOf(Integer.parseInt(
                objectPriceTextField.getText())));
        object.setStatus((String) objectStatusChoiceBox.getValue());
        object.setRoomCount(Short.parseShort(
                objectRoomCountTextField.getText()));

            startTransaction();
            session.update(object);
            session.getTransaction().commit();
            labelClose();
    }

    public void clickOpenAgreementTableForUpdate() {
        updateTableView.getColumns().clear();
        mainWindowController.initAgreementTable(updateTableView);
        clickAgreementTableItem();
    }

    public void clickAgreementTableItem() {
        updateTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                AgreementViewModel selectedAgreement =
                        (AgreementViewModel) updateTableView
                                .getSelectionModel().getSelectedItem();

                agreement = selectedAgreement.getOriginalAgreement();

                SimpleIntegerProperty objectId =
                        selectedAgreement.getObjectId();
                SimpleIntegerProperty clientId =
                        selectedAgreement.getClientId();
                SimpleObjectProperty<Date> agreementDate =
                        selectedAgreement.getAgreementDate();
                SimpleIntegerProperty price =
                        selectedAgreement.getAgreementPrice();
                SimpleStringProperty status =
                        selectedAgreement.getAgreementStatus();

                idAgreementObjectTextField.setText(
                        String.valueOf(objectId.get()));
                idAgreementClientTextField.setText(
                        String.valueOf(clientId.get()));
                addAgreementRecordDatePicker.setValue(
                        agreementDate.get().toLocalDate());
                idAgreementPriceTextField.setText(
                        String.valueOf(price.get()));
                agreementStatusChoiceBox.setValue(
                        status.get());
            }
        });
    }

    public void clickUpdateAgreementRecordButton() {
        agreement.setObjectId(Integer.valueOf(
                idAgreementObjectTextField.getText()));
        agreement.setClientId(Integer.valueOf(
                idAgreementClientTextField.getText()));
        agreement.setAgreementDate(Date.valueOf(
                addAgreementRecordDatePicker.getValue()));
        agreement.setAgreementPrice(Integer.valueOf(
                idAgreementPriceTextField.getText()));
        agreement.setAgreementStatus(
                (String) agreementStatusChoiceBox.getValue());

        startTransaction();
        session.update(agreement);
        session.getTransaction().commit();
        labelClose();
    }

    public void clickOpenClientTableForUpdate() {
        updateTableView.getColumns().clear();
        mainWindowController.initClientTable(updateTableView);
        clickClientTableItem();
    }

    public void clickClientTableItem() {
        updateTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                ClientViewModel selectedClient =
                        (ClientViewModel) updateTableView
                                .getSelectionModel().getSelectedItem();

                client = selectedClient.getOriginalClient();

                SimpleStringProperty name =
                        selectedClient.getFirstName();
                SimpleStringProperty surname =
                        selectedClient.getSecondName();
                SimpleLongProperty phoneNum =
                        selectedClient.getContactNum();
                SimpleIntegerProperty reqId =
                        selectedClient.getReqId();

                clientFirstNameTextField.setText(name.get());
                clientSecondNameTextField.setText(surname.get());
                clientNumberTextField.setText(String.valueOf(phoneNum.get()));
                clientIdRequirementTextField.setText(
                        String.valueOf(reqId.get()));

            }
        });
    }

    public void clickUpdateClientRecordButton() {
        client.setFirstName(clientFirstNameTextField.getText());
        client.setSecondName(clientSecondNameTextField.getText());
        client.setContactNum(Long.valueOf(clientNumberTextField.getText()));
        client.setReqId(Integer.valueOf(
                clientIdRequirementTextField.getText()));

        startTransaction();
        session.update(client);
        session.getTransaction().commit();
        labelClose();
    }

    public void clickOpenFacilityTableForUpdate() {
        updateTableView.getColumns().clear();
        mainWindowController.initFacilityTable(updateTableView);
        clickFacilityTableItem();
    }

    public void clickFacilityTableItem() {
        updateTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                FacilityViewModel selectedFacility =
                        (FacilityViewModel) updateTableView
                                .getSelectionModel().getSelectedItem();

                facility = selectedFacility.getOriginalFacility();

                SimpleIntegerProperty objectId =
                        selectedFacility.getObjectReferenceId();
                SimpleObjectProperty<Short> bedroomsNum =
                        selectedFacility.getMinBedrooms();
                SimpleObjectProperty<Short> bathroomsNum =
                        selectedFacility.getMinBathrooms();
                SimpleBooleanProperty garage =
                        selectedFacility.getGarage();
                SimpleBooleanProperty garden =
                        selectedFacility.getGarden();
                SimpleBooleanProperty pool =
                        selectedFacility.getPool();

                facilityIdObjectTextField.setText(
                        String.valueOf(objectId.get()));
                facilityMinBedroomsTextField.setText(
                        String.valueOf(bedroomsNum.get()));
                facilityMinBathroomsTextField.setText(
                        String.valueOf(bathroomsNum.get()));
                facilityGarageChoiceBox.setValue(garage.get());
                facilityGardenChoiceBox.setValue(garden.get());
                facilityPoolChoiceBox.setValue(pool.get());
            }
        });
    }

    public void clickUpdateFacilityRecordButton() {

        facility.setObjectReferenceId(
                Integer.valueOf(facilityIdObjectTextField.getText()));
        facility.setMinBedrooms(
                Short.valueOf(facilityMinBedroomsTextField.getText()));
        facility.setMinBathrooms(
                Short.valueOf(facilityMinBathroomsTextField.getText()));
        facility.setGarage(facilityGarageChoiceBox.getValue());
        facility.setGarden(facilityGardenChoiceBox.getValue());
        facility.setPool(facilityPoolChoiceBox.getValue());

        startTransaction();
        session.update(facility);
        session.getTransaction().commit();
        labelClose();
    }







    public void clickOpenRequirementTableForUpdate() {
        updateTableView.getColumns().clear();
        mainWindowController.initRequirementTable(updateTableView);
        clickRequirementTableItem();
    }

    public void clickRequirementTableItem() {
        updateTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                RequirementViewModel selectedRequirement =
                        (RequirementViewModel) updateTableView
                                .getSelectionModel().getSelectedItem();

                requirement = selectedRequirement.getOriginalRequirement();

                SimpleStringProperty reqStreet =
                        selectedRequirement.getReqStreet();
                SimpleIntegerProperty reqMaxPrice =
                        selectedRequirement.getReqMaxPrice();
                SimpleDoubleProperty reqMinArea =
                        selectedRequirement.getReqMinimalArea();
                SimpleIntegerProperty reqMinBedrooms =
                        selectedRequirement.getReqMinBedrooms();
                SimpleIntegerProperty reqMinBathrooms =
                        selectedRequirement.getReqMinBathrooms();
                SimpleBooleanProperty reqGarage =
                        selectedRequirement.getReqGarage();
                SimpleBooleanProperty reqGarden =
                        selectedRequirement.getReqGarden();
                SimpleBooleanProperty reqPool =
                        selectedRequirement.getReqPool();

                requirementStreetTextField.setText(reqStreet.get());
                requirementMaxPriceTextField.setText(
                        String.valueOf(reqMaxPrice.get()));
                requirementMinAreaTextField.setText(
                        String.valueOf(reqMinArea.get()));
                requirementMinBedroomsTextField.setText(
                        String.valueOf(reqMinBedrooms.get()));
                requirementMinBathroomsTextField.setText(
                        String.valueOf(reqMinBathrooms.get()));
                requirementGarageChoiceBox.setValue(reqGarage.get());
                requirementGardenChoiceBox.setValue(reqGarden.get());
                requirementPoolChoiceBox.setValue(reqPool.get());
            }
        });
    }

    public void clickUpdateRequirementRecordButton() {

        requirement.setReqStreet(requirementStreetTextField.getText());
        requirement.setReqMaxPrice(
                Integer.valueOf(requirementMaxPriceTextField.getText()));
        requirement.setReqMinimalArea(
                Double.valueOf(requirementMinAreaTextField.getText()));
        requirement.setReqMinBedrooms(
                Short.valueOf(requirementMinBedroomsTextField.getText()));
        requirement.setReqMinBathrooms(
                Short.valueOf(requirementMinBathroomsTextField.getText()));
        requirement.setReqGarage(requirementGarageChoiceBox.getValue());
        requirement.setReqGarden(requirementGardenChoiceBox.getValue());
        requirement.setReqPool(requirementPoolChoiceBox.getValue());

        startTransaction();
        session.update(requirement);
        session.getTransaction().commit();
        labelClose();
    }

    public void clickOpenConsultationTableForUpdate() {
        updateTableView.getColumns().clear();
        mainWindowController.initConsultationTable(updateTableView);
        clickConsultationTableItem();
    }

    public void clickConsultationTableItem() {
        updateTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                ConsultationViewModel selectedConsultation =
                        (ConsultationViewModel) updateTableView
                                .getSelectionModel().getSelectedItem();

                consultation = selectedConsultation.getOriginalConsultation();

                SimpleIntegerProperty clientId =
                        selectedConsultation.getClientId();
                SimpleObjectProperty<Date> date =
                        selectedConsultation.getConsDate();
                SimpleStringProperty status =
                        selectedConsultation.getConsStatus();

                consultationIdClientTextField.setText(
                        String.valueOf(clientId.get()));
                consultationDatePicker.setValue(date.get().toLocalDate());
                consultationStatusChoiceBox.setValue(status.get());

            }
        });
    }

    public void clickUpdateConsultationRecordButton() {

        consultation.setClientId(
                Integer.valueOf(consultationIdClientTextField.getText()));
        consultation.setConsDate(
                Date.valueOf(consultationDatePicker.getValue()));
        consultation.setConsStatus(
                (String) consultationStatusChoiceBox.getValue());

        startTransaction();
        session.update(consultation);
        session.getTransaction().commit();
        labelClose();
    }
}
