/*
 * AgreementViewModel
 *
 * Version: 1.0
 * Author: Чирков Богдан
 *
 * Description: Клас, який представляє модель представлення угоди
 *              в інтерфейсі користувача. Використовується для відображення даних
 *              про угоду у таблиці або іншому візуальному елементі.
 */

package RealtorApp.model.modelView;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import RealtorApp.model.entity.Agreement;

import java.sql.Date;

@Getter
public class AgreementViewModel {

    // Властивості для зберігання даних про угоду
    private final SimpleIntegerProperty agreementId;
    private final SimpleIntegerProperty objectId;
    private final SimpleIntegerProperty clientId;
    private final SimpleObjectProperty<Date> agreementDate;
    private final SimpleStringProperty agreementStatus;
    private final SimpleIntegerProperty agreementPrice;

    // Посилання на оригінальний об'єкт угоди
    private final Agreement originalAgreement;

    // Конструктор класу AgreementViewModel
    public AgreementViewModel(int agreementId, int objectId, int clientId,
                              int agreementPrice, Date agreementDate,
                              String agreementStatus, Agreement originalAgreement) {

        // Ініціалізація властивостей
        this.agreementId = new SimpleIntegerProperty(agreementId);
        this.objectId = new SimpleIntegerProperty(objectId);
        this.clientId = new SimpleIntegerProperty(clientId);
        this.agreementPrice = new SimpleIntegerProperty(agreementPrice);
        this.agreementStatus = new SimpleStringProperty(agreementStatus);
        this.agreementDate = new SimpleObjectProperty<>(agreementDate);
        this.originalAgreement = originalAgreement;
    }
}
