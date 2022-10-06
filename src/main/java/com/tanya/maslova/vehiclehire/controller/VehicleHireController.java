package com.tanya.maslova.vehiclehire.controller;

import com.tanya.maslova.vehiclehire.model.Car;
import com.tanya.maslova.vehiclehire.service.VehicleHireService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VehicleHireController {

    private final VehicleHireService vehicleHireService;

    @GetMapping("/availableCars")
    public List<Car> getAvailableCars(){
        return vehicleHireService.getAllAvailableCars();
    }

    @GetMapping("/hiredCars")
    public List<Car> getHiredCars(){
        return vehicleHireService.getAllHiredCars();
    }

    @PostMapping("/calculateHireCost")
    public int calculateHireCost(@RequestBody HiredCarsDto hiredCars){
        return vehicleHireService.calculateHireCost(hiredCars.getCarCategories(),hiredCars.getDateFrom(), hiredCars.getDateTo());
    }

    @PostMapping("/finalCost")
    public int calculateFinalCost(@RequestBody HiredCarsDto hiredCars){
        return vehicleHireService.getHireCostWithAdminFees(hiredCars.getCarCategories(),hiredCars.getDateFrom(), hiredCars.getDateTo());
    }

}
