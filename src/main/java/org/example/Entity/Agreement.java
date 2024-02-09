package org.example.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


public class Agreement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "agreement_id", nullable = false)
    private Integer agreementId;

    @Basic
    @Column(name = "object_id", nullable = true)
    private Integer objectId;

    @Basic
    @Column(name = "client_id", nullable = true)
    private Integer clientId;

    @Basic
    @Column(name = "agreement_date", nullable = false)
    private Timestamp agreementDate;

    @Basic
    @Column(name = "agreement_price", nullable = false)
    private Integer agreementPrice;

    @Basic
    @Column(name = "agreement_status", nullable = false, length = -1)
    private String agreementStatus;


}
