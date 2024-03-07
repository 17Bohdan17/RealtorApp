/*
 * Requirement
 *
 * Version: 1.0
 * Author: Богдан Чирков
 *
 * Description: Клас, який представляє сутність вимог до об'єктів в програмі RealtorApp.
 */

package RealtorApp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Requirement {
    // Ідентифікатор вимоги
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "req_id", nullable = false)
    private Integer reqId;

    // Мінімальна кількість спалень у вимогах
    @Basic
    @Column(name = "req_min_bedrooms", nullable = false)
    private Short reqMinBedrooms;

    // Мінімальна кількість ванних кімнат у вимогах
    @Basic
    @Column(name = "req_min_bathrooms", nullable = false)
    private Short reqMinBathrooms;

    // Мінімальна площа у вимогах
    @Basic
    @Column(name = "req_minimal_area", nullable = false)
    private Double reqMinimalArea;

    // Максимальна ціна у вимогах
    @Basic
    @Column(name = "req_max_price", nullable = false)
    private Integer reqMaxPrice;

    // Вулиця у вимогах
    @Basic
    @Column(name = "req_street", nullable = false, length = 30)
    private String reqStreet;

    // Наявність гаражу у вимогах
    @Basic
    @Column(name = "req_garage", nullable = false)
    private Boolean reqGarage;

    // Наявність саду у вимогах
    @Basic
    @Column(name = "req_garden", nullable = false)
    private Boolean reqGarden;

    // Наявність басейну у вимогах
    @Basic
    @Column(name = "req_pool", nullable = false)
    private Boolean reqPool;

    // Метод для отримання самої вимоги
    public Requirement getRequirement() {
        return this;
    }
}
