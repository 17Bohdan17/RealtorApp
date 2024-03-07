/*
 * Facilities
 *
 * Version: 1.0
 * Author: Богдан Чирков
 *
 * Description: Клас, який представляє сутність зручностей в програмі RealtorApp.
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
public class Facilities {
    // Ідентифікатор зручностей
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "facility_id", nullable = false)
    private Integer facilityId;

    // Ідентифікатор посилання на об'єкт
    @Basic
    @Column(name = "object_reference_id")
    private Integer objectReferenceId;

    // Мінімальна кількість спалень
    @Basic
    @Column(name = "min_bedrooms")
    private Short minBedrooms;

    // Мінімальна кількість ванних кімнат
    @Basic
    @Column(name = "min_bathrooms")
    private Short minBathrooms;

    // Наявність гаражу
    @Basic
    @Column(name = "garage")
    private Boolean garage;

    // Наявність саду
    @Basic
    @Column(name = "garden")
    private Boolean garden;

    // Наявність басейну
    @Basic
    @Column(name = "pool")
    private Boolean pool;

    // Метод для отримання самої зручностей
    public Facilities getFacility() {
        return this;
    }
}
