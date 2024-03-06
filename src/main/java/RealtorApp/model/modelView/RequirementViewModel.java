package RealtorApp.model.modelView;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import RealtorApp.model.entity.Requirement;


@Getter
public class RequirementViewModel {
    private final SimpleIntegerProperty reqId;
    private final SimpleIntegerProperty reqMinBedrooms;
    private final SimpleIntegerProperty reqMinBathrooms;
    private final SimpleDoubleProperty reqMinimalArea;
    private final SimpleIntegerProperty reqMaxPrice;
    private final SimpleStringProperty reqStreet;
    private final SimpleBooleanProperty reqGarage;
    private final SimpleBooleanProperty reqGarden;
    private final SimpleBooleanProperty reqPool;
    private final Requirement originalRequirement;

    public RequirementViewModel(int reqId, int reqMinBedrooms,
                                int reqMinBathrooms, double reqMinimalArea,
                                Integer reqMaxPrice, String reqStreet,
                                boolean reqGarage, boolean reqGarden,
                                boolean reqPool, Requirement originalRequirement) {

        this.reqId = new SimpleIntegerProperty(reqId);
        this.reqMinBedrooms = new SimpleIntegerProperty(reqMinBedrooms);
        this.reqMinBathrooms = new SimpleIntegerProperty(reqMinBathrooms);
        this.reqMinimalArea = new SimpleDoubleProperty(reqMinimalArea);
        this.reqMaxPrice = new SimpleIntegerProperty(reqMaxPrice);
        this.reqStreet = new SimpleStringProperty(reqStreet);
        this.reqGarage = new SimpleBooleanProperty(reqGarage);
        this.reqGarden = new SimpleBooleanProperty(reqGarden);
        this.reqPool = new SimpleBooleanProperty(reqPool);
        this.originalRequirement = originalRequirement;
    }
}

