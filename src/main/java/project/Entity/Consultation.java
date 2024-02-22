package project.Entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cons_id", nullable = false)
    private Integer consId;

    @Basic
    @Column(name = "client_id")
    private Integer clientId;

    @Basic
    @Column(name = "cons_date", nullable = false)
    private Date consDate;

    @Basic
    @Column(name = "cons_status", nullable = false)
    private String consStatus;
}
