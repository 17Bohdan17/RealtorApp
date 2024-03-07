/*
 * Client
 *
 * Version: 1.0
 * Author: Богдан Чирков
 *
 * Description: Клас, який представляє сутність клієнта в програмі RealtorApp.
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
public class Client {
    // Ідентифікатор клієнта
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "client_id", nullable = false)
    private Integer clientId;

    // Ім'я клієнта
    @Basic
    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    // Прізвище клієнта
    @Basic
    @Column(name = "second_name", nullable = false, length = 30)
    private String secondName;

    // Контактний номер клієнта
    @Basic
    @Column(name = "contact_num", nullable = false)
    private Long contactNum;

    // Ідентифікатор запиту
    @Basic
    @Column(name = "req_id")
    private Integer reqId;

    // Метод для отримання самого клієнта
    public Client getClient() {
        return this;
    }
}
