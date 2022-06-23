package com.example.restaurant.domin;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(Long id) {
        super("Could not find restaurant : " + id);
    }
}
