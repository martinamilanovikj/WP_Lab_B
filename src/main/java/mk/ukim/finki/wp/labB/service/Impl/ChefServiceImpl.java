package mk.ukim.finki.wp.labB.service.Impl;

import mk.ukim.finki.wp.labB.model.Chef;
import mk.ukim.finki.wp.labB.model.Dish;
import mk.ukim.finki.wp.labB.repository.ChefRepository;
import mk.ukim.finki.wp.labB.repository.DishRepository;
import mk.ukim.finki.wp.labB.service.ChefService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public ChefServiceImpl(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Chef> listChefs() {
        return this.chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return this.chefRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Chef chef = this.chefRepository.findById(chefId).orElseThrow(RuntimeException::new);
        Dish dish = this.dishRepository.findByDishId(dishId);

        if(chef == null || dish == null) return null;

        chef.getDishes().add(dish);
        return chefRepository.save(chef);

    }
}
