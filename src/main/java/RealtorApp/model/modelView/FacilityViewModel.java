/*
 * FacilityViewModel
 *
 * Version: 1.0
 * Author: Чирков Богдан
 *
 * Description: Клас, що представляє модель представлення зручностей в програмі RealtorApp.
 *              Використовується для відображення даних про зручності у таблиці або іншому візуальному елементі.
 */

package RealtorApp.model.modelView;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;
import RealtorApp.model.entity.Facilities;

@Getter
public class FacilityViewModel {

    // Властивості для зберігання даних про умовності
    private final SimpleIntegerProperty facilityId;
    private final SimpleIntegerProperty objectReferenceId;
    private final SimpleObjectProperty<Short> minBedrooms;
    private final SimpleObjectProperty<Short> minBathrooms;
    private final SimpleBooleanProperty garage;
    private final SimpleBooleanProperty garden;
    private final SimpleBooleanProperty pool;
    private final Facilities originalFacility;

    // Конструктор класу FacilityViewModel
    public FacilityViewModel(int facilityId, int objectReferenceId,
                             short minBedrooms, short minBathrooms,
                             boolean garage, boolean garden, boolean pool, Facilities originalFacility) {

        // Ініціалізація властивостей
        this.facilityId = new SimpleIntegerProperty(facilityId);
        this.objectReferenceId = new SimpleIntegerProperty(objectReferenceId);
        this.minBedrooms = new SimpleObjectProperty<>(minBedrooms);
        this.minBathrooms = new SimpleObjectProperty<>(minBathrooms);
        this.garage = new SimpleBooleanProperty(garage);
        this.garden = new SimpleBooleanProperty(garden);
        this.pool = new SimpleBooleanProperty(pool);
        this.originalFacility = originalFacility;
    }
}
