package com.food.FoodDelivery.service.impl;

import com.food.FoodDelivery.entity.Dish;
import com.food.FoodDelivery.entity.Restaurant;
import com.food.FoodDelivery.exception.ResourceNotFoundException;
import com.food.FoodDelivery.repository.DishDto;
import com.food.FoodDelivery.repository.DishRepository;
import com.food.FoodDelivery.repository.RestaurantRepository;
import com.food.FoodDelivery.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<DishDto> getAllDishes() {
        return dishRepository.findAll().stream().map(dish -> new DishDto(dish.getDishID(),dish.getName(), dish.getPrice(),dish.getRestaurant())).collect(Collectors.toList());
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
        Dish dish = dishRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Dish","Id",id));
        Restaurant restaurant = restaurantRepository.findById(dish.getRestaurant().getRestaurantID()).orElseThrow(()->new ResourceNotFoundException("Restaurant","Id",dish.getRestaurant().getRestaurantID()));
        restaurant.getDishes().remove(dish);
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
