package com.food.FoodDelivery.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Restaurants")
@ToString(onlyExplicitlyIncluded = false)
public class Restaurant
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Restaurant_id", nullable = false)
    private Long restaurantID;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @OneToMany(orphanRemoval = true, mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Dish> dishes;
}
