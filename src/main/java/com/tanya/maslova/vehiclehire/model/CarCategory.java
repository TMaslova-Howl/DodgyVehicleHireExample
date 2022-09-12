package com.tanya.maslova.vehiclehire.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.Period;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CarCategory {
    SMALL(25, "small"), ESTATE(35, "estate"), VAN(50, "van");

    private int pricePerDay;
    private String category;

    CarCategory(int pricePerDay, String category) {
        this.pricePerDay = pricePerDay;
        this.category = category;
    }

    public int calculateCostPerDateRange(LocalDate from, LocalDate to) {
        int days = Period.between(from, to).getDays();
        return this.pricePerDay * days;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public String getCategory() {
        return category;
    }
}

