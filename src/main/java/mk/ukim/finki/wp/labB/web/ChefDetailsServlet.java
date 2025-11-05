package mk.ukim.finki.wp.labB.web;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.labB.model.Chef;
import mk.ukim.finki.wp.labB.model.Dish;
import mk.ukim.finki.wp.labB.service.ChefService;
import mk.ukim.finki.wp.labB.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "chefDetailsServlet", urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {

    private final ChefService chefService;
    private final DishService dishService;
    private final SpringTemplateEngine templateEngine;

    public ChefDetailsServlet(ChefService chefService, DishService dishService,
                              SpringTemplateEngine templateEngine) {
        this.chefService = chefService;
        this.dishService = dishService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        Long chefId = Long.valueOf(req.getParameter("chefId"));
        String dishId = req.getParameter("dishId");
        String action = req.getParameter("action");

        Chef chef = chefService.findById(chefId);

        if ("delete".equals(action)) {
            Dish dish = dishService.findByDishId(dishId);
            chef.getDishes().remove(dish);
        } else {

            Dish dish = dishService.findByDishId(dishId);
            chef.getDishes().add(dish);

            ServletContext context = getServletContext();
            Integer totalAdded = (Integer) context.getAttribute("totalDishesAdded");
            if (totalAdded == null) totalAdded = 0;
            context.setAttribute("totalDishesAdded", totalAdded + 1);
        }



        var webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("chef", chef);
        context.setVariable("totalDishesAdded", getServletContext().getAttribute("totalDishesAdded"));

        templateEngine.process("chefDetails.html", context, resp.getWriter());
    }
}

