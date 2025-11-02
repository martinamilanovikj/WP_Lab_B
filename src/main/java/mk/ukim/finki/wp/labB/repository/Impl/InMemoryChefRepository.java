package mk.ukim.finki.wp.labB.repository.Impl;

import mk.ukim.finki.wp.labB.bootstrap.DataHolder;
import mk.ukim.finki.wp.labB.model.Chef;
import mk.ukim.finki.wp.labB.repository.ChefRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryChefRepository implements ChefRepository {

    public List<Chef> findAll(){
        return DataHolder.chefs;
    }
    public Optional <Chef> findById(Long id){
      return DataHolder.chefs.stream()
              .filter(chef -> chef.getId().equals(id))
              .findFirst();
    }
    public Chef save(Chef chef){
        Optional <Chef> existingChef = findById(chef.getId());
        if(existingChef.isPresent()){
            Chef c = existingChef.get();
            c.setFirstName(chef.getFirstName());
            c.setLastName(chef.getLastName());
            c.setBio(chef.getBio());
            c.setDishes(chef.getDishes());
            return c;
        } else {
            DataHolder.chefs.add(chef);
            return chef;
        }

    }
}
