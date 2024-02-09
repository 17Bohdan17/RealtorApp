package org.example.Entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
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

    public Integer getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Timestamp getAgreementDate() {
        return agreementDate;
    }

    public void setAgreementDate(Timestamp agreementDate) {
        this.agreementDate = agreementDate;
    }

    public Integer getAgreementPrice() {
        return agreementPrice;
    }

    public void setAgreementPrice(Integer agreementPrice) {
        this.agreementPrice = agreementPrice;
    }

    public String getAgreementStatus() {
        return agreementStatus;
    }

    public void setAgreementStatus(String agreementStatus) {
        this.agreementStatus = agreementStatus;
    }


}
