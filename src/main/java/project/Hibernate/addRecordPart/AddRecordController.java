package project.Hibernate.addRecordPart;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.hibernate.Session;
import project.Entity.Object;
import project.Entity.*;
import project.Hibernate.HibernateUtil;

import java.math.BigDecimal;
import java.sql.Date;


public class AddRecordController {

    @FXML
    private Pane addRecordPane;
    @FXML
    private ImageView addRecordWindowCloseBtn;
    @FXML
    private Label addRecordDoneLabel;

    @FXML
    private TextField  idAgreementObjectTextField;
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
    private TableView objectTable;



    Session session;
    private double x;
    private double y;


    Agreement agreement = new Agreement();
    Object object = new Object();
    Client client = new Client();
    Consultation consultation = new Consultation();
    Facilities facility = new Facilities();
    Requirement requirement = new Requirement();

    public void init (Stage stage){
        setUpCloseButton(stage);
        setUpDraggableStage(stage);
        addRecordDoneLabel.setVisible(false);
        initChoiceBoxes();
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


    public void clickAddAgreementRecordButton()  {
        int objectId;
        int clientId;
        int  agreementPrice;
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














    public void clickAddObjectRecordButton (){
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

    private void initChoiceBoxes(){
        try {
            objectStatusChoiceBox.getItems().addAll(
                    "FOR_SALE", "AVAILABLE", "UNDER_CONTRACT");
        }
        catch (NullPointerException ignored){
        }

        try {
            agreementStatusChoiceBox.getItems().addAll(
                    "PENDING", "COMPLETED", "ACTIVE");
        } catch (NullPointerException ignored){
        }

        try {
            consultationStatusChoiceBox.getItems().addAll(
                    "COMPLETED", "SCHEDULED");
        } catch (NullPointerException ignored){
        }

        try {
            facilityGarageChoiceBox.getItems().addAll(true, false);
        } catch (NullPointerException ignored){}

        try {
            facilityGardenChoiceBox.getItems().addAll(true, false);
        } catch (NullPointerException ignored){}

        try {
            facilityPoolChoiceBox.getItems().addAll(true, false);
        } catch (NullPointerException ignored){}

        try {
            requirementGarageChoiceBox.getItems().addAll(true, false);
        } catch (NullPointerException ignored){}

        try {
            requirementGardenChoiceBox.getItems().addAll(true, false);
        } catch (NullPointerException ignored){}

        try {
            requirementPoolChoiceBox.getItems().addAll(true, false);
        } catch (NullPointerException ignored){}

        Tooltip objectTooltip = new Tooltip(
                "1.\"FOR_SALE\" - Об'єкт доступний тільки для продажу.\n" +
                        "2.\"AVAILABLE\" - Об'єкт доступний для продажу" +
                        " або оренди.\n"+
                        "3.\"UNDER_CONTRACT\" - Об'єкт вже під контрактом.");
        Tooltip.install(objectStatusChoiceBox, objectTooltip);


    }
}
