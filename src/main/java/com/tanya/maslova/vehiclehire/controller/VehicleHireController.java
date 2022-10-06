package com.tanya.maslova.vehiclehire.controller;

import com.tanya.maslova.vehiclehire.model.Car;
import com.tanya.maslova.vehiclehire.service.VehicleHireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
@RequiredArgsConstructor
public class VehicleHireController {

    private final VehicleHireService vehicleHireService;

    @GetMapping("/availableCars")
    @ApiOperation("Gets available cars for hire")
    public List<Car> getAvailableCars(){
        return vehicleHireService.getAllAvailableCars();
    }

    @GetMapping("/hiredCars")
    @ApiOperation("Gets already hired cars")
    public List<Car> getHiredCars(){
        return vehicleHireService.getAllHiredCars();
    }

    @PostMapping("/calculateHireCost")
    @ApiOperation("Calculates hire costs based on selected car types and date period")
    public int calculateHireCost(@RequestBody HiredCarsDto hiredCars){
        return vehicleHireService.calculateHireCost(hiredCars.getCarCategories(),hiredCars.getDateFrom(), hiredCars.getDateTo());
    }

    @PostMapping("/finalCost")
    @ApiOperation("Calculates final cost")
    public int calculateFinalCost(@RequestBody HiredCarsDto hiredCars){
        return vehicleHireService.getHireCostWithAdminFees(hiredCars.getCarCategories(),hiredCars.getDateFrom(), hiredCars.getDateTo());
    }

}
