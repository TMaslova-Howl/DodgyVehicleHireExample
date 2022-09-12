package com.tanya.maslova.vehiclehire.service;

import com.tanya.maslova.vehiclehire.dao.AdminFees;
import com.tanya.maslova.vehiclehire.dao.VehicleHireRepository;
import com.tanya.maslova.vehiclehire.model.CarCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class VehicleHireServiceTest {
    @Mock
    private VehicleHireRepository vehicleHireRepository;
    @Mock
    private VehicleHireCostCalculator vehicleHireCostCalculator;
    @Mock
    private AdminFees adminFees;
    @InjectMocks
    private VehicleHireService vehicleHireService;

    @Test
    void shouldCombineAdminFeesAndHireCosts(){
        //Given
        given(adminFees.getAdminFees()).willReturn(50);
        given(vehicleHireCostCalculator
                .calculate(any(), any(), any()))
                .willReturn(100);
        List<CarCategory> listOfDifferentCars = Arrays.asList(CarCategory.values());
        LocalDate dateFrom = LocalDate.parse("2020-09-17");
        LocalDate dateTo = LocalDate.parse("2020-09-20");
        //When
        int finalFee = vehicleHireService.getHireCostWithAdminFees(listOfDifferentCars, dateFrom, dateTo);
        //Then
        assertThat(finalFee).isEqualTo(150);
        verify(adminFees, times(1)).getAdminFees();
        verify(vehicleHireCostCalculator, times(1))
                .calculate(listOfDifferentCars, dateFrom, dateTo);
    }

    @Test
    void shouldCallVehicleRepository_whenCalledForAllAvailableCars(){
        //When
        vehicleHireService.getAllAvailableCars();
        //Then
        verify(vehicleHireRepository, times(1)).findCarsByHiredTrue();
    }

    @Test
     void shouldReturnPartOfTheCostOfAllVehiclesSuccessfully_whenListOfCarsIsNotEmpty() {
        //Given
        List<CarCategory> listOfDifferentCars = Arrays.asList(CarCategory.values());
        LocalDate dateFrom = LocalDate.parse("2020-09-17");
        LocalDate dateTo = LocalDate.parse("2020-09-20");
        //When
        int actualResult = vehicleHireService.calculateHireCost(listOfDifferentCars, dateFrom, dateTo);
        //Then
        int expectedResult = 234;
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
     void givenEmptyListOfCars_whenCalculateHireCost_thenReturnZero() {
        //Given
        List<CarCategory> carCategoryList = Collections.emptyList();
        LocalDate dateFrom = LocalDate.parse("2020-09-17");
        LocalDate dateTo = LocalDate.parse("2020-09-20");
        //When
        int actualResult = vehicleHireService.calculateHireCost(carCategoryList, dateFrom, dateTo);
        //Then
        assertThat(actualResult).isEqualTo(0);
    }

    @Test
    public void givenNullArgument_whenCalculateHireCost_thenReturnZero() {
        LocalDate dateFrom = LocalDate.parse("2020-09-17");
        LocalDate dateTo = LocalDate.parse("2020-09-20");
        int expectedResult = 0;
        assertEquals(expectedResult, vehicleHireService.calculateHireCost(null, dateFrom, dateTo));
    }

    @Test
    public void givenNoDateFrom_whenCalculateHireCost_thenReturnZero() {
        LocalDate dateTo = LocalDate.parse("2020-09-20");
        int expectedResult = 0;
        assertEquals(expectedResult, vehicleHireService.calculateHireCost(null, null, dateTo));
    }

    @Test
    public void givenNoDateTo_whenCalculateHireCost_thenReturnZero() {
        LocalDate dateFrom = LocalDate.parse("2020-09-17");
        int expectedResult = 0;
        assertEquals(expectedResult, vehicleHireService.calculateHireCost(null, dateFrom, null));
    }

    private List<CarCategory> getListOfDifferentCars() {
        return Arrays.asList(CarCategory.values());
    }
}
