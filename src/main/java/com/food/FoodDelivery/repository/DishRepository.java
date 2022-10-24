package com.food.FoodDelivery.repository;


import com.food.FoodDelivery.entity.Dish;
import com.food.FoodDelivery.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


public interface DishRepository extends JpaRepository<Dish,Long>
{
    @Query("FROM Dish WHERE Restaurant_id = :id")
    List<Dish> findByRestaurantId(@Param("id")Long id);
    @Query(value = "select new com.food.FoodDelivery.repository.DishDto(a.dishID, a.name, a.price, a.restaurant) from Dish a")
    List<DishDto> getDishDto();
}
