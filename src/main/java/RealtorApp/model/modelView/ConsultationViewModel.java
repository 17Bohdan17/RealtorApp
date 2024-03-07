/*
 * ConsultationViewModel
 *
 * Version: 1.0
 * Author: Чирков Богдан
 *
 * Description: Клас, що представляє модель представлення консультації в програмі RealtorApp.
 *              Використовується для відображення даних про консультацію у таблиці або іншому візуальному елементі.
 */

package RealtorApp.model.modelView;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import RealtorApp.model.entity.Consultation;

import java.sql.Date;

@Getter
public class ConsultationViewModel {

    // Властивості для зберігання даних про консультацію
    private final SimpleIntegerProperty consId;
    private final SimpleIntegerProperty clientId;
    private final SimpleObjectProperty<Date> consDate;
    private final SimpleStringProperty consStatus;
    private final Consultation originalConsultation;

    // Конструктор класу ConsultationViewModel
    public ConsultationViewModel(int consId, int clientId, Date consDate,
                                 String consStatus, Consultation originalConsultation) {

        // Ініціалізація властивостей
        this.consId = new SimpleIntegerProperty(consId);
        this.clientId = new SimpleIntegerProperty(clientId);
        this.consDate = new SimpleObjectProperty<>(consDate);
        this.consStatus = new SimpleStringProperty(consStatus);
        this.originalConsultation = originalConsultation;
    }
}
