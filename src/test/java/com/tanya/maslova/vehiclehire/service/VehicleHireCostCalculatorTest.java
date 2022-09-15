package com.tanya.maslova.vehiclehire.service;

import com.tanya.maslova.vehiclehire.model.CarCategory;
import com.tanya.maslova.vehiclehire.service.exception.InvalidHirePeriodException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class VehicleHireCostCalculatorTest {

    @InjectMocks
    private VehicleHireCostCalculator vehicleHireCostCalculator;

    @Test
    void givenEmptyListOfCars_whenCalculateHireCost_thenReturnZero() {
        //Given
        List<CarCategory> carCategoryList = Collections.emptyList();
        LocalDate dateFrom = LocalDate.parse("2020-09-17");
        LocalDate dateTo = LocalDate.parse("2020-09-20");
        //When
        int actualResult = vehicleHireCostCalculator.calculate(carCategoryList, dateFrom, dateTo);
        //Then
        assertThat(actualResult).isEqualTo(0);
    }

    @Test
    void givenNoHirePeriodProvided_whenCalculateHireCost_thenThrowException() {
        //Given
        List<CarCategory> carCategoryList = Arrays.asList(CarCategory.values());
        //Then
       assertThrows(InvalidHirePeriodException.class, () -> {
           vehicleHireCostCalculator.calculate(carCategoryList, null, null);
       });
    }

    @Test
    void givenCarListAndHirePeriodProvided_whenCalculateHireCost_thenReturnCostOfHire() {
        //Given
        List<CarCategory> listOfDifferentCars = Arrays.asList(CarCategory.values());
        LocalDate dateFrom = LocalDate.parse("2020-09-17");
        LocalDate dateTo = LocalDate.parse("2020-09-20");
        //When
        int actualResult = vehicleHireCostCalculator.calculate(listOfDifferentCars, dateFrom, dateTo);
        //Then
        int expectedResult = 330;
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
