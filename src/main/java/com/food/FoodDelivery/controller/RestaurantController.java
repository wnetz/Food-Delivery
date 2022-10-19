package com.food.FoodDelivery.controller;

import com.food.FoodDelivery.entity.Restaurant;
import com.food.FoodDelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController
{
    private RestaurantService restaurantService;
    public RestaurantController(RestaurantService restaurantService)
    {
        super();
        this.restaurantService = restaurantService;
    }

    @PostMapping()
    public ResponseEntity<Restaurant> saveRestaurant(@RequestBody Restaurant restaurant)
    {
        return new ResponseEntity<>(restaurantService.saveRestaurant(restaurant), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Restaurant> getAllRestaurant()
    {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id") long id)
    {
        return new ResponseEntity<>(restaurantService.getRestaurantById(id),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable("id") long id)
    {
        return new ResponseEntity<>(restaurantService.updateRestaurant(restaurant,id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable("id") long id)
    {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>("Restaurant deleted", HttpStatus.OK);
    }
}
