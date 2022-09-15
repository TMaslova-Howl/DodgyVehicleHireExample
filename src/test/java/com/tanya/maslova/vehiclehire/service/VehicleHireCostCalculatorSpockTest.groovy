package com.tanya.maslova.vehiclehire.service

import com.tanya.maslova.vehiclehire.model.CarCategory
import com.tanya.maslova.vehiclehire.service.exception.InvalidHirePeriodException
import spock.lang.Specification

import java.time.LocalDate

class VehicleHireCostCalculatorSpockTest extends Specification{
    def vehicleHireCostCalculator = new VehicleHireCostCalculator()

    def "should return 0 when no vehicle list is provided for calculating hire cost" () {
        given: "empty list of cars"
        List<CarCategory> carCategoryList = Collections.emptyList()
        and: "valid hire period"
        LocalDate dateFrom = LocalDate.parse("2020-09-17")
        LocalDate dateTo = LocalDate.parse("2020-09-20")
        when: "calculate hire cost"
        def actualResult = vehicleHireCostCalculator.calculate(carCategoryList, dateFrom, dateTo)
        then: "should return 0"
        actualResult == 0
    }

    def "should throw exception when no hire period is provided for calculating hire cost" () {
        given: "a list of cars"
        List<CarCategory> carCategoryList = Arrays.asList(CarCategory.values())
        and: "no hire period provided"
        LocalDate dateFrom = null
        LocalDate dateTo = null
        when: "calculate hire cost"
        vehicleHireCostCalculator.calculate(carCategoryList, dateFrom, dateTo)
        then: "throw exception"
        thrown(InvalidHirePeriodException)
    }

    def "should return hiring cost of all vehicles selected" () {
        given: "a list of different types of vehicles"
        List<CarCategory> listOfDifferentCars = Arrays.asList(CarCategory.values())
        and: "valid to and from dates"
        LocalDate dateFrom = LocalDate.parse("2020-09-17")
        LocalDate dateTo = LocalDate.parse("2020-09-20")
        when: "calculate hire cost"
        def actualResult = vehicleHireCostCalculator.calculate(listOfDifferentCars, dateFrom, dateTo)
        then: "successfully return part of hiring cost"
        def expectedResult = 330
        actualResult == expectedResult
    }
}
