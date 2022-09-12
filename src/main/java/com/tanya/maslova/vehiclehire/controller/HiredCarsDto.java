package com.tanya.maslova.vehiclehire.controller;

import com.tanya.maslova.vehiclehire.model.CarCategory;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HiredCarsDto {
    private List<CarCategory> carCategories;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
