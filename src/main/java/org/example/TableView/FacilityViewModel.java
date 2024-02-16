package org.example.TableView;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Getter;


@Getter
public class FacilityViewModel {
    private final SimpleIntegerProperty facilityId, objectReferenceId, minBedrooms, minBathrooms;
    private final SimpleBooleanProperty garage, garden, pool;

    public FacilityViewModel(int facilityId, int objectReferenceId, int minBedrooms, int minBathrooms,
                             boolean garage, boolean garden, boolean pool) {

        this.facilityId = new SimpleIntegerProperty(facilityId);
        this.objectReferenceId = new SimpleIntegerProperty(objectReferenceId);
        this.minBedrooms = new SimpleIntegerProperty(minBedrooms);
        this.minBathrooms = new SimpleIntegerProperty(minBathrooms);
        this.garage = new SimpleBooleanProperty(garage);
        this.garden = new SimpleBooleanProperty(garden);
        this.pool = new SimpleBooleanProperty(pool);
    }
}

