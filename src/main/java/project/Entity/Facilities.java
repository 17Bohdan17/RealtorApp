package project.Entity;

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
    @Column(name = "object_reference_id")
    private Integer objectReferenceId;

    @Basic
    @Column(name = "min_bedrooms")
    private Integer minBedrooms;

    @Basic
    @Column(name = "min_bathrooms")
    private Integer minBathrooms;

    @Basic
    @Column(name = "garage")
    private Boolean garage;

    @Basic
    @Column(name = "garden")
    private Boolean garden;

    @Basic
    @Column(name = "pool")
    private Boolean pool;
}
