/*
 * ClientViewModel
 *
 * Version: 1.0
 * Author: Чирков Богдан
 *
 * Description: Клас, що представляє модель представлення клієнта в програмі RealtorApp.
 *              Використовується для відображення даних про клієнта у таблиці або іншому
 *              візуальному елементі.
 */

package RealtorApp.model.modelView;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import RealtorApp.model.entity.Client;

@Getter
public class ClientViewModel {

    // Властивості для зберігання даних про клієнта
    private final SimpleIntegerProperty clientId;
    private final SimpleIntegerProperty reqId;
    private final SimpleLongProperty contactNum;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty secondName;
    private final Client originalClient;

    // Конструктор класу ClientViewModel
    public ClientViewModel(int clientId, String firstName, String secondName,
                           Long contactNum, int reqId, Client originalClient) {

        // Ініціалізація властивостей
        this.clientId = new SimpleIntegerProperty(clientId);
        this.firstName = new SimpleStringProperty(firstName);
        this.secondName = new SimpleStringProperty(secondName);
        this.contactNum = new SimpleLongProperty(contactNum);
        this.reqId = new SimpleIntegerProperty(reqId);
        this.originalClient = originalClient;
    }
}
