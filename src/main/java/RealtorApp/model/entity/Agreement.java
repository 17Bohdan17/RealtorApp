/*
 * Agreement
 *
 * Version: 1.0
 * Author: Богдан Чирков
 *
 * Description: Клас, що представляє сутність угоди в програмі RealtorApp.
 */

package RealtorApp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Agreement {

    // Ідентифікатор угоди
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "agreement_id", nullable = false)
    private Integer agreementId;

    // Ідентифікатор об'єкта
    @Basic
    @Column(name = "object_id")
    private Integer objectId;

    // Ідентифікатор клієнта
    @Basic
    @Column(name = "client_id")
    private Integer clientId;

    // Дата угоди
    @Basic
    @Column(name = "agreement_date", nullable = false)
    private Date agreementDate;

    // Ціна угоди
    @Basic
    @Column(name = "agreement_price", nullable = false)
    private Integer agreementPrice;

    // Статус угоди
    @Basic
    @Column(name = "agreement_status", nullable = false)
    private String agreementStatus;

    // Метод для отримання самої угоди
    public Agreement getAgreement() {
        return this;
    }
}
