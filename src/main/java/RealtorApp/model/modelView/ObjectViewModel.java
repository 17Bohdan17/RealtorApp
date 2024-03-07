/*
 * ObjectViewModel
 *
 * Version: 1.0
 * Author: Чирков Богдан
 *
 * Description: Клас, що представляє модель представлення об'єктів нерухомості в програмі RealtorApp.
 *              Використовується для відображення даних про об'єкти у таблиці або іншому візуальному елементі.
 */

package RealtorApp.model.modelView;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import RealtorApp.model.entity.Object;

import java.math.BigDecimal;

@Getter
public class ObjectViewModel {

    // Властивості для зберігання даних про об'єкт
    private final SimpleIntegerProperty objectId;
    private final SimpleIntegerProperty street_num;
    private final SimpleIntegerProperty roomCount;
    private final SimpleStringProperty street;
    private final SimpleStringProperty status;
    private final SimpleDoubleProperty area;
    private final SimpleObjectProperty<BigDecimal> price;
    private final Object originalObject;

    // Конструктор класу ObjectViewModel
    public ObjectViewModel(int objectId, String street, int street_num,
                           Double area, BigDecimal price, String status,
                           Short roomCount, Object originalObject) {
        // Ініціалізація властивостей
        this.objectId = new SimpleIntegerProperty(objectId);
        this.street = new SimpleStringProperty(street);
        this.street_num = new SimpleIntegerProperty(street_num);
        this.area = new SimpleDoubleProperty(area);
        this.price = new SimpleObjectProperty<>(price);
        this.status = new SimpleStringProperty(status);
        this.roomCount = new SimpleIntegerProperty(roomCount);
        this.originalObject = originalObject;
    }
}
