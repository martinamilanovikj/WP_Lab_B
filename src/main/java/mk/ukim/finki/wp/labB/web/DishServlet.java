package mk.ukim.finki.wp.labB.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.labB.model.Chef;
import mk.ukim.finki.wp.labB.model.Dish;
import mk.ukim.finki.wp.labB.service.ChefService;
import mk.ukim.finki.wp.labB.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//@WebServlet(name = "DishServlet", urlPatterns = "/dish")
//public class DishServlet extends HttpServlet {
//
//    private final SpringTemplateEngine templateEngine;
//    private final DishService dishService;
//    private final ChefService chefService;
//
//    public DishServlet(SpringTemplateEngine templateEngine, DishService dishService, ChefService chefService) {
//        this.templateEngine = templateEngine;
//        this.dishService = dishService;
//        this.chefService = chefService;
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//        Long chefId = Long.valueOf(req.getParameter("chefId"));
//        Chef chef = chefService.findById(chefId);
//        List<Dish> dishes = dishService.listDishes();
//
//        var webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//        context.setVariable("chef", chef);
//        context.setVariable("dishes", dishes);
//
//
//        templateEngine.process("dishesList.html", context, resp.getWriter());
//    }
//
//
//
//}
@WebServlet(name = "DishServlet", urlPatterns = "/dish")
public class DishServlet extends HttpServlet {

    private final SpringTemplateEngine templateEngine;
    private final DishService dishService;
    private final ChefService chefService;

    public DishServlet(SpringTemplateEngine templateEngine, DishService dishService, ChefService chefService) {
        this.templateEngine = templateEngine;
        this.dishService = dishService;
        this.chefService = chefService;
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//        List<Dish> dishes = dishService.listDishes();
//
//        var webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//        context.setVariable("dishes", dishes);
//
//        templateEngine.process("dishesList.html", context, resp.getWriter());
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long chefId = Long.valueOf(req.getParameter("chefId"));
        Chef chef = chefService.findById(chefId);
        List<Dish> dishes = dishService.listDishes();


        HttpSession session = req.getSession();
        LinkedList<Chef> lastChefs = (LinkedList<Chef>) session.getAttribute("lastChefs");
        if (lastChefs == null) {
            lastChefs = new LinkedList<>();
        }

        lastChefs.removeIf(c -> c.getId().equals(chef.getId()));
        lastChefs.addFirst(chef);


        while (lastChefs.size() > 3) {
            lastChefs.removeLast();
        }

        session.setAttribute("lastChefs", lastChefs);

        var webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("chef", chef);
        context.setVariable("dishes", dishes);
        context.setVariable("lastChefs", lastChefs);

        templateEngine.process("dishesList.html", context, resp.getWriter());
    }
}
