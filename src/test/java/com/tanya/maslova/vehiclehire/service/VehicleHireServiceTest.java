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

}
