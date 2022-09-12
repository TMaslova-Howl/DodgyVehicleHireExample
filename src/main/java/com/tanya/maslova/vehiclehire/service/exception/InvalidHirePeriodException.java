package com.tanya.maslova.vehiclehire.service.exception;

public class InvalidHirePeriodException extends IllegalArgumentException {

    private static final String INVALID_PERIOD_ERROR = "Invalid period for hire";

    public InvalidHirePeriodException() {
        super(INVALID_PERIOD_ERROR);
    }
}
