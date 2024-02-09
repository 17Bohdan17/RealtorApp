package org.example.Entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Consultation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cons_id", nullable = false)
    private Integer consId;
    @Basic
    @Column(name = "client_id", nullable = true)
    private Integer clientId;
    @Basic
    @Column(name = "cons_date", nullable = false)
    private Timestamp consDate;
    @Basic
    @Column(name = "cons_status", nullable = false, length = -1)
    private String consStatus;

    public Integer getConsId() {
        return consId;
    }

    public void setConsId(Integer consId) {
        this.consId = consId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Timestamp getConsDate() {
        return consDate;
    }

    public void setConsDate(Timestamp consDate) {
        this.consDate = consDate;
    }

    public String getConsStatus() {
        return consStatus;
    }

    public void setConsStatus(String consStatus) {
        this.consStatus = consStatus;
    }


}
