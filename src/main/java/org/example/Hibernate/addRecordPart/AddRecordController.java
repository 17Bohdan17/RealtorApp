package org.example.Hibernate.addRecordPart;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.Entity.*;
import org.example.Entity.Object;
import org.example.Hibernate.HibernateUtil;
import org.hibernate.Session;

import java.sql.Date;


public class AddRecordController {

    @FXML
    private Pane addRecordPane;
    @FXML
    private ImageView addRecordWindowCloseBtn;
    @FXML
    private TextField  idAgreementObjectTextField, idAgreementClientTextField,
            idAgreementPriceTextField, idAgreementStatusTextField;
    @FXML
    private DatePicker addAgreementRecordDatePicker;
    @FXML
    private Label addRecordDoneLabel;

    private double x, y;
    private int objectId, clientId, agreementPrice;
    private Date agreementDate;
    private String agreementStatus;

    Agreement agreement = new Agreement();
    Object object = new Object();
    Client client = new Client();
    Facilities facilities = new Facilities();
    Consultation consultation = new Consultation();
    Requirement requirement = new Requirement();







    public void init (Stage stage){
        setUpCloseButton(stage);
        setUpDraggableStage(stage);
        addRecordDoneLabel.setVisible(false);
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

    public void clickAddAgreementRecordButton(){
        addAgreementRecord();
    }



    public void addAgreementRecord()  {

        addRecordDoneLabel.setVisible(true);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        objectId = Integer.parseInt(idAgreementObjectTextField.getText());
        clientId = Integer.parseInt(idAgreementClientTextField.getText());
        agreementDate = Date.valueOf(addAgreementRecordDatePicker.getValue());
        agreementPrice = Integer.parseInt(idAgreementPriceTextField.getText());
        agreementStatus = idAgreementStatusTextField.getText();

        agreement.setObjectId(objectId);
        agreement.setClientId(clientId);
        agreement.setAgreementDate(agreementDate);
        agreement.setAgreementPrice(agreementPrice);
        agreement.setAgreementStatus(agreementStatus);

        session.save(agreement);
        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();

        PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
        visiblePause.setOnFinished(event -> addRecordDoneLabel.setVisible(false));
        visiblePause.play();

        idAgreementObjectTextField.clear();
        idAgreementClientTextField.clear();
        addAgreementRecordDatePicker.setValue(null);
        idAgreementPriceTextField.clear();
        idAgreementStatusTextField.clear();
    }











}
