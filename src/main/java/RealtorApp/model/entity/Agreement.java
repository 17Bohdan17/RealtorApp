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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "agreement_id", nullable = false)
    private Integer agreementId;

    @Basic
    @Column(name = "object_id")
    private Integer objectId;

    @Basic
    @Column(name = "client_id")
    private Integer clientId;

    @Basic
    @Column(name = "agreement_date", nullable = false)
    private Date agreementDate;

    @Basic
    @Column(name = "agreement_price", nullable = false)
    private Integer agreementPrice;

    @Basic
    @Column(name = "agreement_status", nullable = false)
    private String agreementStatus;

    public Agreement getAgreement() {
        return this;
    }
}
