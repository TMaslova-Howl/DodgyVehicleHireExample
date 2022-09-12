package com.tanya.maslova.vehiclehire.controller;

import com.tanya.maslova.vehiclehire.model.Car;
import com.tanya.maslova.vehiclehire.service.VehicleHireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VehicleHireController {
    @Autowired
    private VehicleHireService vehicleHireService;

    @CrossOrigin
    @GetMapping("/availableCars")
    public List<Car> getAvailableCars(){
        return vehicleHireService.getAllAvailableCars();
    }

    @CrossOrigin
    @GetMapping("/hiredCars")
    public List<Car> getHiredCars(){
        return vehicleHireService.getAllHiredCars();
    }

    @CrossOrigin
    @PostMapping("/calculateHireCost")
    public int calculateHireCost(@RequestBody HiredCarsDto hiredCars){
        return vehicleHireService.calculateHireCost(hiredCars.getCarCategories(),hiredCars.getDateFrom(), hiredCars.getDateTo());
    }
}
