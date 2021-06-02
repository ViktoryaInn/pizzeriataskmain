package com.example.pizzeriataskmain.controller;

import com.example.pizzeriataskmain.dbService.dataSets.Ingredient;
import com.example.pizzeriataskmain.service.IngredientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IngredientController {

    static final Logger logger = LoggerFactory.getLogger(ShowController.class);

    @Autowired
    IngredientService ingredientService;

    @GetMapping("/ingredients/add")
    public ModelAndView getAddViewIngredient(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addIngredient");
        logger.info("Страница добавления ингредиентов");
        return modelAndView;
    }

    @PostMapping("/ingredients/add")
    public String addIngredient(@ModelAttribute("name") String name,
                                @ModelAttribute("price") String price){
        Ingredient ingredient = new Ingredient(name, Integer.parseInt(price));
        ingredientService.addIngredient(ingredient);
        logger.info(String.format("Ингредиент «%s» добавлен в список ингредиентов", name));
        return "redirect:/";
    }

    @GetMapping("/ingredients/change/{id}")
    public ModelAndView getChangeViewIngredient(@PathVariable("id") String id){
        Ingredient ingredient = ingredientService.getIngredient(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ingredient", ingredient);
        modelAndView.setViewName("changeIngredient");
        logger.info("Страница изменения ингредиентов");
        return modelAndView;
    }

    @PostMapping("/ingredients/change/{id}")
    public String changeIngredient(@PathVariable("id") String pathId,
                                   @ModelAttribute("name") String name,
                                   @ModelAttribute("price") String price){

        Ingredient ingredient = new Ingredient(pathId, name, Integer.parseInt(price));
        ingredientService.updateIngredient(ingredient);
        logger.info(String.format("Ингредиент «%s» изменен", name));
        return "redirect:/";
    }

    @GetMapping( "/ingredients/delete/{id}")
    public String deleteIngredient(@PathVariable("id") String id){
        String ingredientName = ingredientService.getIngredient(id).getName();
        ingredientService.deleteIngredient(id);
        logger.info(String.format("Ингредиент «%s» удален", ingredientName));
        return "redirect:/";
    }
}
