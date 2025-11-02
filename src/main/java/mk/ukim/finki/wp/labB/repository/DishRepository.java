package mk.ukim.finki.wp.labB.repository;

import mk.ukim.finki.wp.labB.model.Dish;

import java.util.List;
public interface DishRepository {
    List<Dish> findAll();
    Dish findByDishId(String dishId);
}