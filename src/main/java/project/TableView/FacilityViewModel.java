package project.TableView;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;
import project.Entity.Facilities;


@Getter
public class FacilityViewModel {
    private final SimpleIntegerProperty facilityId;
    private final SimpleIntegerProperty objectReferenceId;
    private final SimpleObjectProperty<Short> minBedrooms;
    private final SimpleObjectProperty<Short> minBathrooms;
    private final SimpleBooleanProperty garage;
    private final SimpleBooleanProperty garden;
    private final SimpleBooleanProperty pool;
    private final Facilities originalFacility;

    public FacilityViewModel(int facilityId, int objectReferenceId,
                             short minBedrooms, short minBathrooms,
                             boolean garage, boolean garden, boolean pool, Facilities originalFacility) {

        this.facilityId = new SimpleIntegerProperty(facilityId);
        this.objectReferenceId = new SimpleIntegerProperty(objectReferenceId);
        this.minBedrooms = new SimpleObjectProperty<Short>(minBedrooms);
        this.minBathrooms = new SimpleObjectProperty<Short>(minBathrooms);
        this.garage = new SimpleBooleanProperty(garage);
        this.garden = new SimpleBooleanProperty(garden);
        this.pool = new SimpleBooleanProperty(pool);
        this.originalFacility = originalFacility;
    }
}

