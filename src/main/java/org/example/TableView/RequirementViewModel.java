package org.example.TableView;

import javafx.beans.property.*;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
public class RequirementViewModel {
    private final SimpleIntegerProperty reqId, reqMinBedrooms, reqMinBathrooms;
    private final SimpleDoubleProperty reqMinimalArea;
    private final SimpleObjectProperty<BigDecimal> reqMaxPrice;
    private final SimpleStringProperty reqStreet;
    private final SimpleBooleanProperty reqGarage, reqGarden, reqPool;

    public RequirementViewModel(int reqId, int reqMinBedrooms, int reqMinBathrooms,
                                double reqMinimalArea, BigDecimal reqMaxPrice,
                                String reqStreet, boolean reqGarage, boolean reqGarden,
                                boolean reqPool) {

        this.reqId = new SimpleIntegerProperty(reqId);
        this.reqMinBedrooms = new SimpleIntegerProperty(reqMinBedrooms);
        this.reqMinBathrooms = new SimpleIntegerProperty(reqMinBathrooms);
        this.reqMinimalArea = new SimpleDoubleProperty(reqMinimalArea);
        this.reqMaxPrice = new SimpleObjectProperty<BigDecimal>(reqMaxPrice);
        this.reqStreet = new SimpleStringProperty(reqStreet);
        this.reqGarage = new SimpleBooleanProperty(reqGarage);
        this.reqGarden = new SimpleBooleanProperty(reqGarden);
        this.reqPool = new SimpleBooleanProperty(reqPool);

    }
}

