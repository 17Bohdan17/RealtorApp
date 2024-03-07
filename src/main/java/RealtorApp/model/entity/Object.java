/*
 * Object
 *
 * Version: 1.0
 * Author: Богдан Чирков
 *
 * Description: Клас, який представляє сутність об'єкта в програмі RealtorApp.
 */

package RealtorApp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Object {
    // Ідентифікатор об'єкта
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "object_id", nullable = false)
    private Integer objectId;

    // Вулиця
    @Basic
    @Column(name = "street", nullable = false, length = 30)
    private String street;

    // Номер будинку
    @Basic
    @Column(name = "street_num", nullable = false)
    private Integer streetNum;

    // Кількість кімнат
    @Basic
    @Column(name = "room_count", nullable = false)
    private Short roomCount;

    // Площа
    @Basic
    @Column(name = "area", nullable = false)
    private Double area;

    // Ціна
    @Basic
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    // Статус
    @Basic
    @Column(name = "status", nullable = false)
    private String status;

    // Метод для отримання самого об'єкта
    public Object getObject() {
        return this;
    }
}
