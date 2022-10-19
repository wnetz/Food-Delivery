package com.food.FoodDelivery.service;

import com.food.FoodDelivery.entity.Dish;

import java.util.List;

public interface DishService
{
    Dish createDish(Dish dish, long rID);
    List<Dish> getAllDishes();
    List<Dish> getAllDishesByRestaurantId(long id);
    Dish getDishById(long id);
    Dish updateDish(Dish dish, long id);
    void deleteDishById(long id);
    void deleteAllDishesOfRestaurant(long id);
}
