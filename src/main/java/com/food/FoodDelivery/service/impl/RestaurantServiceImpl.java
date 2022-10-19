package com.food.FoodDelivery.service.impl;

import com.food.FoodDelivery.entity.Restaurant;
import com.food.FoodDelivery.exception.ResourceNotFoundException;
import com.food.FoodDelivery.repository.RestaurantRepository;
import com.food.FoodDelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService
{
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantById(long id) {
        return restaurantRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Restaurant","Id",id) );
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant, long id) {
        Restaurant e = getRestaurantById(id);
        e.setAddress(restaurant.getAddress());
        e.setName(restaurant.getName());
        restaurantRepository.save(e);
        return e;
    }

    @Override
    public void deleteRestaurant(long id) {
        //if restaurant does not exist throw an error
        restaurantRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Restaurant","Id",id));
        restaurantRepository.deleteById(id);
    }
}
