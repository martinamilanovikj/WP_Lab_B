package mk.ukim.finki.wp.labB.service.Impl;

import mk.ukim.finki.wp.labB.model.Dish;
import mk.ukim.finki.wp.labB.repository.DishRepository;
import mk.ukim.finki.wp.labB.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Dish> listDishes() {
        return this.dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return this.dishRepository.findByDishId(dishId);
    }
}
