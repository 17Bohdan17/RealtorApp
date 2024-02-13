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
    private Integer roomCount;

    @Basic
    @Column(name = "area", nullable = false, precision = 0)
    private Double area;

    @Basic
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Basic
    @Column(name = "status", nullable = false, length = -1)
    private String status;

}
