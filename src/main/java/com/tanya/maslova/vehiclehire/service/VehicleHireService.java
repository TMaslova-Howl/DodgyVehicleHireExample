package com.tanya.maslova.vehiclehire.service;

import com.tanya.maslova.vehiclehire.dao.AdminFees;
import com.tanya.maslova.vehiclehire.dao.VehicleHireRepository;
import com.tanya.maslova.vehiclehire.model.Car;
import com.tanya.maslova.vehiclehire.model.CarCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleHireService {

    private final VehicleHireRepository vehicleHireRepository;

    private final VehicleHireCostCalculator vehicleHireCostCalculator;

    private final AdminFees adminFees;

    public List<Car> getAllAvailableCars(){
       return vehicleHireRepository.findCarsByHiredFalse();
    }

    public List<Car> getAllHiredCars(){
        return vehicleHireRepository.findCarsByHiredTrue();
    }

    public int calculateHireCost(List<CarCategory> carsToHire, LocalDate from, LocalDate to){
        return vehicleHireCostCalculator.calculate(carsToHire, from, to);
    }

    public int getHireCostWithAdminFees(List<CarCategory> carsToHire, LocalDate from, LocalDate to){
        return vehicleHireCostCalculator.calculate(carsToHire, from, to) + adminFees.getAdminFees();
    }

}





