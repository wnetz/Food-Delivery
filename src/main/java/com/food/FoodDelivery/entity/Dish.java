package com.food.FoodDelivery.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Dish_id", nullable = false)
    private Long dishID;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Restaurant restaurant;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
}
