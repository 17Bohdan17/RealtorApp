package org.example.Entity;

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
    private Integer streetNum;

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

}
