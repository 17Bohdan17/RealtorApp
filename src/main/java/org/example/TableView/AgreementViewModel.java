package org.example.TableView;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;

import java.sql.Timestamp;

@Getter

public class AgreementViewModel {

    private final SimpleIntegerProperty agreementId, objectId, clientId;
    private final SimpleObjectProperty<Timestamp> agreementDate;
    private final SimpleStringProperty agreementStatus;
    private final SimpleIntegerProperty agreementPrice;


    public AgreementViewModel(int agreementId, int objectId, int clientId, int agreementPrice,
                              Timestamp agreementDate, String agreementStatus) {
        this.agreementId = new SimpleIntegerProperty(agreementId);
        this.objectId = new SimpleIntegerProperty(objectId);
        this.clientId = new SimpleIntegerProperty(clientId);
        this.agreementPrice = new SimpleIntegerProperty(agreementPrice);
        this.agreementStatus = new SimpleStringProperty(agreementStatus);
        this.agreementDate = new SimpleObjectProperty<Timestamp>(agreementDate);
    }
}

