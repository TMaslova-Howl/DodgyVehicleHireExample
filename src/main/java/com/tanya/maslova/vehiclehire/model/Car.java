package com.tanya.maslova.vehiclehire.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "car_table")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String make;
    private String model;

    @Column(name = "fuel_type")
    private String fuelType;
    private boolean hired;

    @Column(name = "car_category")
    @Enumerated(EnumType.STRING)
    private CarCategory carCategory;

    @OneToOne(mappedBy = "car")
    private CarHireDetails carHireDetails;

}
