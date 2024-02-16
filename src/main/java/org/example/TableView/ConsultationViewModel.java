package org.example.TableView;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;

import java.sql.Timestamp;

@Getter

public class ConsultationViewModel {
    private final SimpleIntegerProperty consId, clientId;
    private final SimpleObjectProperty<Timestamp> consDate;
    private final SimpleStringProperty consStatus;

    public ConsultationViewModel(int consId, int clientId, Timestamp consDate, String consStatus) {

        this.consId = new SimpleIntegerProperty(consId);
        this.clientId = new SimpleIntegerProperty(clientId);
        this.consDate = new SimpleObjectProperty<Timestamp>(consDate);
        this.consStatus = new SimpleStringProperty(consStatus);

    }
}

