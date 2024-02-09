package org.example.Entity;

import jakarta.persistence.*;

@Entity
public class Object {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "object_id", nullable = false)
    private Integer objectId;
    @Basic
    @Column(name = "street", nullable = false, length = 30)
    private String street;
    @Basic
    @Column(name = "street_num", nullable = false)
    private Short streetNum;
    @Basic
    @Column(name = "room_count", nullable = false)
    private Short roomCount;
    @Basic
    @Column(name = "area", nullable = false, precision = 0)
    private Double area;
    @Basic
    @Column(name = "price", nullable = false)
    private java.lang.Object price;
    @Basic
    @Column(name = "status", nullable = false, length = -1)
    private String status;
    @Basic
    @Column(name = "min_bedrooms", nullable = false)
    private Short minBedrooms;
    @Basic
    @Column(name = "min_bathrooms", nullable = false)
    private Short minBathrooms;
    @Basic
    @Column(name = "garage", nullable = false)
    private Boolean garage;
    @Basic
    @Column(name = "garden", nullable = false)
    private Boolean garden;
    @Basic
    @Column(name = "pool", nullable = false)
    private Boolean pool;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Short getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(Short streetNum) {
        this.streetNum = streetNum;
    }

    public Short getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Short roomCount) {
        this.roomCount = roomCount;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public java.lang.Object getPrice() {
        return price;
    }

    public void setPrice(java.lang.Object price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Short getMinBedrooms() {
        return minBedrooms;
    }

    public void setMinBedrooms(Short minBedrooms) {
        this.minBedrooms = minBedrooms;
    }

    public Short getMinBathrooms() {
        return minBathrooms;
    }

    public void setMinBathrooms(Short minBathrooms) {
        this.minBathrooms = minBathrooms;
    }

    public Boolean getGarage() {
        return garage;
    }

    public void setGarage(Boolean garage) {
        this.garage = garage;
    }

    public Boolean getGarden() {
        return garden;
    }

    public void setGarden(Boolean garden) {
        this.garden = garden;
    }

    public Boolean getPool() {
        return pool;
    }

    public void setPool(Boolean pool) {
        this.pool = pool;
    }


}
