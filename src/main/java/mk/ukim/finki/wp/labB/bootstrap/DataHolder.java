package mk.ukim.finki.wp.labB.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.labB.model.Chef;
import mk.ukim.finki.wp.labB.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init(){
        dishes.add(new Dish("D1", "Spaghetti Carbonara", "Italian", 20));
        dishes.add(new Dish("D2", "Sushi", "Japanese", 30));
        dishes.add(new Dish("D3", "Tacos", "Mexican", 15));
        dishes.add(new Dish("D4", "Croissant", "French", 25));
        dishes.add(new Dish("D5", "Pad Thai", "Thai", 20));


//        chefs.add(new Chef(1L, "Gordon", "Ramsay", "World-renowned chef",
//                new ArrayList<>(List.of(dishes.get(0), dishes.get(1)))));
//        chefs.add(new Chef(2L, "Jamie", "Oliver", "British chef and restaurateur",
//                new ArrayList<>(List.of(dishes.get(2)))));
//        chefs.add(new Chef(3L, "Alice", "Waters", "Pioneer of organic food",
//                new ArrayList<>(List.of(dishes.get(3)))));
//        chefs.add(new Chef(4L, "Masaharu", "Morimoto", "Japanese Iron Chef",
//                new ArrayList<>(List.of(dishes.get(1), dishes.get(4)))));
//        chefs.add(new Chef(5L, "Thomas", "Keller", "American chef and restaurateur",
//                new ArrayList<>(List.of(dishes.get(0), dishes.get(3)))));

        //predefined dishes to each chef


        chefs.add(new Chef(1L, "Gordon", "Ramsay", "World-renowned chef", new ArrayList<>()));
        chefs.add(new Chef(2L, "Jamie", "Oliver", "British chef and restaurateur", new ArrayList<>()));
        chefs.add(new Chef(3L, "Alice", "Waters", "Pioneer of organic food", new ArrayList<>()));
        chefs.add(new Chef(4L, "Masaharu", "Morimoto", "Japanese Iron Chef", new ArrayList<>()));
        chefs.add(new Chef(5L, "Thomas", "Keller", "American chef and restaurateur", new ArrayList<>()));
    }
}
