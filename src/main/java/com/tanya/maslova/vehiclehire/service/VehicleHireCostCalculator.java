package com.tanya.maslova.vehiclehire.service;

import com.tanya.maslova.vehiclehire.model.CarCategory;
import com.tanya.maslova.vehiclehire.service.exception.InvalidHirePeriodException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class VehicleHireCostCalculator {

    public int calculate(List<CarCategory> carsToHire, LocalDate from, LocalDate to){
        if(invalidHirePeriod(from, to)){
            throw new InvalidHirePeriodException();
        }
        return  Optional.ofNullable(carsToHire)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .mapToInt(carCategory ->carCategory.calculateCostPerDateRange(from,to))
                .sum();
    }

    private boolean invalidHirePeriod(LocalDate from, LocalDate to){
        return periodNotProvided(from, to) || dateToIsBeforeDateFrom(from, to);
    }

    private boolean dateToIsBeforeDateFrom(LocalDate from, LocalDate to){
        return to.isBefore(from);
    }

    private boolean periodNotProvided(LocalDate from, LocalDate to){
        return from == null || to == null;
    }
}
