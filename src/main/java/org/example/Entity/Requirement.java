package org.example.Entity;

import jakarta.persistence.*;

@Entity
public class Requirement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "req_id", nullable = false)
    private Integer reqId;
    @Basic
    @Column(name = "req_min_bedrooms", nullable = false)
    private Short reqMinBedrooms;
    @Basic
    @Column(name = "req_min_bathrooms", nullable = false)
    private Short reqMinBathrooms;
    @Basic
    @Column(name = "req_minimal_area", nullable = false, precision = 0)
    private Double reqMinimalArea;
    @Basic
    @Column(name = "req_max_price", nullable = false)
    private java.lang.Object reqMaxPrice;
    @Basic
    @Column(name = "req_street", nullable = false, length = 30)
    private String reqStreet;
    @Basic
    @Column(name = "req_garage", nullable = false)
    private Boolean reqGarage;
    @Basic
    @Column(name = "req_garden", nullable = false)
    private Boolean reqGarden;
    @Basic
    @Column(name = "req_pool", nullable = false)
    private Boolean reqPool;

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public Short getReqMinBedrooms() {
        return reqMinBedrooms;
    }

    public void setReqMinBedrooms(Short reqMinBedrooms) {
        this.reqMinBedrooms = reqMinBedrooms;
    }

    public Short getReqMinBathrooms() {
        return reqMinBathrooms;
    }

    public void setReqMinBathrooms(Short reqMinBathrooms) {
        this.reqMinBathrooms = reqMinBathrooms;
    }

    public Double getReqMinimalArea() {
        return reqMinimalArea;
    }

    public void setReqMinimalArea(Double reqMinimalArea) {
        this.reqMinimalArea = reqMinimalArea;
    }

    public java.lang.Object getReqMaxPrice() {
        return reqMaxPrice;
    }

    public void setReqMaxPrice(java.lang.Object reqMaxPrice) {
        this.reqMaxPrice = reqMaxPrice;
    }

    public String getReqStreet() {
        return reqStreet;
    }

    public void setReqStreet(String reqStreet) {
        this.reqStreet = reqStreet;
    }

    public Boolean getReqGarage() {
        return reqGarage;
    }

    public void setReqGarage(Boolean reqGarage) {
        this.reqGarage = reqGarage;
    }

    public Boolean getReqGarden() {
        return reqGarden;
    }

    public void setReqGarden(Boolean reqGarden) {
        this.reqGarden = reqGarden;
    }

    public Boolean getReqPool() {
        return reqPool;
    }

    public void setReqPool(Boolean reqPool) {
        this.reqPool = reqPool;
    }


}
