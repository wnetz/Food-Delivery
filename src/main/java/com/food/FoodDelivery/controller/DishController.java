package com.food.FoodDelivery.controller;

import com.food.FoodDelivery.entity.Dish;
import com.food.FoodDelivery.repository.DishDto;
import com.food.FoodDelivery.repository.RestaurantRepository;
import com.food.FoodDelivery.service.DishService;
import com.food.FoodDelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DishController
{
    private DishService dishService;
    public DishController(DishService dishService)
    {
        super();
        this.dishService = dishService;
    }

    @PostMapping("/restaurant/{Restaurant_id}/dishes")
    public ResponseEntity<Dish> createDish(@PathVariable(value = "Restaurant_id") Long id, @RequestBody Dish dishRequest)
    {
        return new ResponseEntity<>(dishService.createDish(dishRequest,id), HttpStatus.CREATED);
    }
    @GetMapping("/restaurant/{Restaurant_id}/dishes")
    public ResponseEntity<List<Dish>> getAllDishesByRestaurantId(@PathVariable(value = "Restaurant_id") Long id)
    {
        return new ResponseEntity<>(dishService.getAllDishesByRestaurantId(id), HttpStatus.OK);
    }
    @GetMapping("/dishes/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable(value = "id") Long id)
    {
        return new ResponseEntity<>(dishService.getDishById(id), HttpStatus.OK);
    }

    @GetMapping("/dishes")
    public ResponseEntity<List<DishDto>> getAllDishes()
    {
        return new ResponseEntity<>(dishService.getAllDishes(), HttpStatus.OK);
    }

    @PutMapping("/dishes/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable("id") long id, @RequestBody Dish dishRequest)
    {
        return new ResponseEntity<>(dishService.updateDish(dishRequest,id), HttpStatus.OK);
    }

    @DeleteMapping("/dishes/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable("id") long id) {
        dishService.deleteDishById(id);
        return new ResponseEntity<>("dish deleted", HttpStatus.OK);
    }

    @DeleteMapping("/restaurant/{Restaurant_id}/dishes")
    public ResponseEntity<String> deleteAllDishesOfRestaurant(@PathVariable(value = "Restaurant_id") Long id) {
        dishService.deleteAllDishesOfRestaurant(id);
        return new ResponseEntity<>("dish deleted", HttpStatus.OK);
    }
}
