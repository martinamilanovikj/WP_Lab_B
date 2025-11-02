package mk.ukim.finki.wp.labB.service;

import mk.ukim.finki.wp.labB.model.Dish;

import java.util.List;

public interface DishService {
    List<Dish> listDishes();
    Dish findByDishId(String dishId);
}