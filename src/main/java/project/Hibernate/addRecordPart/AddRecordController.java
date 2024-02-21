package project.Hibernate.addRecordPart;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.hibernate.Session;
import project.Entity.Agreement;
import project.Entity.Object;
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

    private double x;
    private double y;

    Agreement agreement = new Agreement();
    Object object = new Object();

    public void init (Stage stage){
        setUpCloseButton(stage);
        setUpDraggableStage(stage);
        addRecordDoneLabel.setVisible(false);


        try {
            objectStatusChoiceBox.getItems().addAll(
                    "FOR_SALE", "AVAILABLE", "UNDER_CONTRACT");
        }
        catch (NullPointerException ex){
        }

        try {
            agreementStatusChoiceBox.getItems().addAll(
                    "PENDING", "COMPLETED", "ACTIVE");
        } catch (NullPointerException ex){
        }
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

        addRecordDoneLabel.setVisible(true);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

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
        session.close();
        HibernateUtil.close();
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

        addRecordDoneLabel.setVisible(true);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

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
        session.close();
        HibernateUtil.close();
        labelClose();

        objectStreetTextField.clear();
        objectNumberTextField.clear();
        objectAreaTextField.clear();
        objectPriceTextField.clear();
        objectStatusChoiceBox.setValue(null);
        objectRoomCountTextField.clear();
    }

    private void labelClose (){
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(3));

        visiblePause.setOnFinished(event -> {
            addRecordDoneLabel.setVisible(false);
        });
        visiblePause.play();
    }


}
