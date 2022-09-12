package com.tanya.maslova.vehiclehire.service

import com.tanya.maslova.vehiclehire.dao.AdminFees
import com.tanya.maslova.vehiclehire.dao.VehicleHireRepository
import com.tanya.maslova.vehiclehire.model.CarCategory
import spock.lang.Specification

import java.time.LocalDate

class VehicleHireServiceSpockTest extends Specification {

    VehicleHireRepository vehicleHireRepository
    VehicleHireCostCalculator vehicleHireCostCalculator;
    AdminFees adminFees;
    VehicleHireService vehicleHireService;

    def setup(){
        vehicleHireRepository = Mock(VehicleHireRepository.class);
        vehicleHireCostCalculator = Mock(VehicleHireCostCalculator.class);
        adminFees = Mock(AdminFees.class);
        vehicleHireService = new VehicleHireService(vehicleHireRepository, vehicleHireCostCalculator, adminFees);
    }

    def "should return combination of vehicle hire cost and admin fees" () {
        given: "selected vehicles to hire for a correct period of time"
        List<CarCategory> listOfDifferentCars = Arrays.asList(CarCategory.values());
        LocalDate dateFrom = LocalDate.parse("2020-09-17");
        LocalDate dateTo = LocalDate.parse("2020-09-20");
        and: "vehicle hiring costs are 100 pounds"
        1 * vehicleHireCostCalculator
                .calculate(listOfDifferentCars, dateFrom, dateTo) >> 100;
        and: "admin fees are 50 pounds"
        1 * adminFees.getAdminFees() >> 50;
        when: "getting hire costs with admin fees"
        def result = vehicleHireService.getHireCostWithAdminFees(listOfDifferentCars, dateFrom, dateTo);
        then: "return sum of hiring cost and admin fees"
        result == 150;
    }

    def "should call vehicleHireRepository when asked to retrieve all available cars" () {
        given: "repository exists"

        when: "get all available cars"
        vehicleHireService.getAllAvailableCars();
        then: "should call repository once"
        1  * vehicleHireRepository.findCarsByHiredFalse();
    }

    def "should return part of hiring cost of all vehicles selected" () {
        given: "a list of different types of vehicles"
        List<CarCategory> listOfDifferentCars = Arrays.asList(CarCategory.values());
        and: "valid to and from dates"
        LocalDate dateFrom = LocalDate.parse("2020-09-17");
        LocalDate dateTo = LocalDate.parse("2020-09-20");

        when: "calculate hire cost"
        def actualResult = vehicleHireService.calculateHireCost(listOfDifferentCars, dateFrom, dateTo);

        then: "successfully return part of hiring cost"
        def expectedResult = 234;
        actualResult == expectedResult;
    }

    def "should return 0 when no vehicle list is provided for calculating hire cost" () {
        given:
        List<CarCategory> carCategoryList = Collections.emptyList();
        LocalDate dateFrom = LocalDate.parse("2020-09-17");
        LocalDate dateTo = LocalDate.parse("2020-09-20");
        when:
        def actualResult = vehicleHireService.calculateHireCost(carCategoryList, dateFrom, dateTo);
        then:
        actualResult == 0;
    }
}

