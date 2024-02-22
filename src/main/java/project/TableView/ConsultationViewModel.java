package project.TableView;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;

import java.sql.Date;


@Getter

public class ConsultationViewModel {
    private final SimpleIntegerProperty consId;
    private final SimpleIntegerProperty clientId;
    private final SimpleObjectProperty<Date> consDate;
    private final SimpleStringProperty consStatus;

    public ConsultationViewModel(int consId, int clientId, Date consDate,
                                 String consStatus) {

        this.consId = new SimpleIntegerProperty(consId);
        this.clientId = new SimpleIntegerProperty(clientId);
        this.consDate = new SimpleObjectProperty<>(consDate);
        this.consStatus = new SimpleStringProperty(consStatus);
    }
}

