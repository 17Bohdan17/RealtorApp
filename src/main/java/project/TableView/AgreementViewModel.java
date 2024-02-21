package project.TableView;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;

import java.sql.Date;

@Getter

public class AgreementViewModel {

    private final SimpleIntegerProperty agreementId;
    private final SimpleIntegerProperty objectId;
    private final SimpleIntegerProperty clientId;
    private final SimpleObjectProperty<Date> agreementDate;
    private final SimpleStringProperty agreementStatus;
    private final SimpleIntegerProperty agreementPrice;


    public AgreementViewModel(int agreementId, int objectId, int clientId,
                              int agreementPrice, Date agreementDate,
                              String agreementStatus) {

        this.agreementId = new SimpleIntegerProperty(agreementId);
        this.objectId = new SimpleIntegerProperty(objectId);
        this.clientId = new SimpleIntegerProperty(clientId);
        this.agreementPrice = new SimpleIntegerProperty(agreementPrice);
        this.agreementStatus = new SimpleStringProperty(agreementStatus);
        this.agreementDate = new SimpleObjectProperty<>(agreementDate);
    }
}

