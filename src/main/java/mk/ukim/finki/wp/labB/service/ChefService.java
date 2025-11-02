package mk.ukim.finki.wp.labB.service;

import mk.ukim.finki.wp.labB.model.Chef;

import java.util.List;

public interface ChefService {
    List<Chef> listChefs();
    Chef findById(Long id);
    Chef addDishToChef(Long chefId, String dishId);
}