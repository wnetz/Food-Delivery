package com.food.FoodDelivery.service;

import com.food.FoodDelivery.entity.Dish;
import com.food.FoodDelivery.repository.DishDto;

import java.util.List;

public interface DishService
{
    Dish createDish(Dish dish, long rID);
    List<DishDto> getAllDishes();
    List<Dish> getAllDishesByRestaurantId(long id);
    Dish getDishById(long id);
    Dish updateDish(Dish dish, long id);
    void deleteDishById(long id);
    void deleteAllDishesOfRestaurant(long id);
}
