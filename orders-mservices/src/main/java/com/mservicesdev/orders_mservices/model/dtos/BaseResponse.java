package com.mservicesdev.orders_mservices.model.dtos;

public record BaseResponse(String[] errorMessages) {

    public boolean hasError() {
        return errorMessages != null && errorMessages.length > 0;
    }

}
