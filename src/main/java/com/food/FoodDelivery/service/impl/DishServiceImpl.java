package com.food.FoodDelivery.service.impl;

import com.food.FoodDelivery.entity.Dish;
import com.food.FoodDelivery.entity.Restaurant;
import com.food.FoodDelivery.exception.ResourceNotFoundException;
import com.food.FoodDelivery.repository.DishRepository;
import com.food.FoodDelivery.repository.RestaurantRepository;
import com.food.FoodDelivery.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService
{
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private DishRepository dishRepository;

    @Override
    public Dish createDish(Dish dish, long rID)
    {
        Restaurant restaurant = restaurantRepository.findById(rID).orElseThrow(()->new ResourceNotFoundException("Restaurant","Id",rID));
        dish.setRestaurant(restaurant);
        return dishRepository.save(dish);
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    @Override
    public List<Dish> getAllDishesByRestaurantId(long id)
    {
        if(!restaurantRepository.existsById(id))
            throw new ResourceNotFoundException("Teacher","Id",id);
        return dishRepository.findByRestaurantId(id);
    }

    @Override
    public Dish getDishById(long id) {
        return dishRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Dish","Id",id));
    }

    @Override
    public Dish updateDish(Dish dish, long id)
    {
        Dish e = getDishById(id);
        e.setName(dish.getName());
        e.setPrice(dish.getPrice());
        return dishRepository.save(e);
    }

    @Override
    public void deleteDishById(long id) {
        dishRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Dish","Id",id));
        dishRepository.deleteById(id);
    }

    @Override
    public void deleteAllDishesOfRestaurant(long id)
    {
        if (!restaurantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Teacher","Id",id);
        }
        //dishRepository.deleteByRestaurantId(id);
    }
}
