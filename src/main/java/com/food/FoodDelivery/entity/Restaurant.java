package com.food.FoodDelivery.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Restaurants")
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
}
