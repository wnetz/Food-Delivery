package com.food.FoodDelivery.service.impl;

import com.food.FoodDelivery.entity.Restaurant;
import com.food.FoodDelivery.exception.ResourceNotFoundException;
import com.food.FoodDelivery.repository.RestaurantRepository;
import com.food.FoodDelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
        File file = new File("src/main/java/com/food/FoodDelivery/files/"+id + ".pdf");
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            file = new File("src/main/java/com/food/FoodDelivery/files/"+id + ".doc");
            if (file.delete()) {
                System.out.println("Deleted the file: " + file.getName());
            } else {
                System.out.println("Failed to delete the file.");
            }
        }
    }

    @Override
    public void addFile(long id, MultipartFile file) {
        System.out.println(file.getName());
        File newFile = new File("src/main/java/com/food/FoodDelivery/files/"+id + (file.getContentType().equals("application/pdf")?".pdf":".doc"));
        try (OutputStream os = new FileOutputStream(newFile)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
