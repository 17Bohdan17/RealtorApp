package org.example.TableView;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter

public class ObjectViewModel {
    private final SimpleIntegerProperty objectId, street_num, roomCount;
    private final SimpleStringProperty street, status;
    private final SimpleDoubleProperty area;
    private final SimpleObjectProperty<BigDecimal> price;


    public ObjectViewModel(int objectId, String street, int street_num, Double area, BigDecimal price, String status,
                           Integer roomCount) {
        this.objectId = new SimpleIntegerProperty(objectId);
        this.street = new SimpleStringProperty(street);
        this.street_num = new SimpleIntegerProperty(street_num);
        this.area = new SimpleDoubleProperty(area);
        this.price = new SimpleObjectProperty<BigDecimal>(price);
        this.status = new SimpleStringProperty(status);
        this.roomCount = new SimpleIntegerProperty(roomCount);
    }
}

