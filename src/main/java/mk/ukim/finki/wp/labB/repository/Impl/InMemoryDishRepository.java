package mk.ukim.finki.wp.labB.repository.Impl;

import mk.ukim.finki.wp.labB.bootstrap.DataHolder;
import mk.ukim.finki.wp.labB.model.Dish;
import mk.ukim.finki.wp.labB.repository.DishRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryDishRepository implements DishRepository {
    public List<Dish> findAll(){
        return DataHolder.dishes;
    }

    public Dish findByDishId(String dishId){
        return DataHolder.dishes.stream()
                .filter(d -> dishId.equals(d.getDishId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Dish not found!"));
    }
}
