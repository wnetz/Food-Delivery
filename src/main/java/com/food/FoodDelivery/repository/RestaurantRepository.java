package com.food.FoodDelivery.repository;

import com.food.FoodDelivery.entity.Dish;
import com.food.FoodDelivery.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long>
{
}
