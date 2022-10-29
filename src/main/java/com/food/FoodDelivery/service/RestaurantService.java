package com.food.FoodDelivery.service;

import com.food.FoodDelivery.entity.Restaurant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantService 
{
    Restaurant saveRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantById(long id);
    Restaurant updateRestaurant(Restaurant restaurant, long id);
    void deleteRestaurant(long id);

    void addFile(long id, MultipartFile file);
}
