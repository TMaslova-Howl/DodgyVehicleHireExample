package com.tanya.maslova.vehiclehire.dao;

import com.tanya.maslova.vehiclehire.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleHireRepository extends CrudRepository<Car, Integer> {
    List<Car> findCarsByHiredFalse();
    List<Car> findCarsByHiredTrue();

}
