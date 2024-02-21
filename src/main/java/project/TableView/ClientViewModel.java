package project.TableView;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;

@Getter

public class ClientViewModel {

    private final SimpleIntegerProperty clientId;
    private final SimpleIntegerProperty reqId;
    private final SimpleLongProperty contactNum;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty secondName;

    public ClientViewModel(int clientId, String firstName, String secondName,
                           Long contactNum, int reqId) {

        this.clientId = new SimpleIntegerProperty(clientId);
        this.firstName = new SimpleStringProperty(firstName);
        this.secondName = new SimpleStringProperty(secondName);
        this.contactNum = new SimpleLongProperty(contactNum);
        this.reqId = new SimpleIntegerProperty(reqId);
    }
}

