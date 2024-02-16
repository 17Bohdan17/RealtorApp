package org.example.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

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
    @Column(name = "req_max_price", nullable = false, precision = 0)
    private BigDecimal reqMaxPrice;

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


}
