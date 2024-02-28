package project.TableView;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import project.Entity.Client;

@Getter

public class ClientViewModel {

    private final SimpleIntegerProperty clientId;
    private final SimpleIntegerProperty reqId;
    private final SimpleLongProperty contactNum;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty secondName;
    private final Client originalClient;

    public ClientViewModel(int clientId, String firstName, String secondName,
                           Long contactNum, int reqId, Client originalClient) {

        this.clientId = new SimpleIntegerProperty(clientId);
        this.firstName = new SimpleStringProperty(firstName);
        this.secondName = new SimpleStringProperty(secondName);
        this.contactNum = new SimpleLongProperty(contactNum);
        this.reqId = new SimpleIntegerProperty(reqId);
        this.originalClient = originalClient;
    }
}

