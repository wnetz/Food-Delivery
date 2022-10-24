package com.food.FoodDelivery.repository;

import com.food.FoodDelivery.entity.Restaurant;
import lombok.Data;

@Data
public class DishDto {
    private long id;
    private String name;
    private double price;
    private long restaurantID;

    public DishDto(long id, String name, double price, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.restaurantID = restaurant.getRestaurantID();
    }
    // getters etc..
}
