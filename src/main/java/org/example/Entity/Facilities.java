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

public class Facilities {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "facility_id", nullable = false)
    private Integer facilityId;

    @Basic
    @Column(name = "object_reference_id", nullable = true)
    private Integer objectReferenceId;

    @Basic
    @Column(name = "min_bedrooms", nullable = true)
    private Integer minBedrooms;

    @Basic
    @Column(name = "min_bathrooms", nullable = true)
    private Integer minBathrooms;

    @Basic
    @Column(name = "garage", nullable = true)
    private Boolean garage;

    @Basic
    @Column(name = "garden", nullable = true)
    private Boolean garden;

    @Basic
    @Column(name = "pool", nullable = true)
    private Boolean pool;


}
