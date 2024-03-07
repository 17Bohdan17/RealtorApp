/*
 * Consultation
 *
 * Version: 1.0
 * Author: Богдан Чирков
 *
 * Description: Клас, який представляє сутність консультації в програмі RealtorApp.
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
public class Consultation {
    // Ідентифікатор консультації
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cons_id", nullable = false)
    private Integer consId;

    // Ідентифікатор клієнта
    @Basic
    @Column(name = "client_id")
    private Integer clientId;

    // Дата консультації
    @Basic
    @Column(name = "cons_date", nullable = false)
    private Date consDate;

    // Статус консультації
    @Basic
    @Column(name = "cons_status", nullable = false)
    private String consStatus;

    // Метод для отримання самої консультації
    public Consultation getConsultation() {
        return this;
    }
}
