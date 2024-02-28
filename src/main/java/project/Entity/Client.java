package project.Entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "client_id", nullable = false)
    private Integer clientId;

    @Basic
    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Basic
    @Column(name = "second_name", nullable = false, length = 30)
    private String secondName;

    @Basic
    @Column(name = "contact_num", nullable = false)
    private Long contactNum;

    @Basic
    @Column(name = "req_id")
    private Integer reqId;

    public Client getClient() {
        return this;
    }
}
