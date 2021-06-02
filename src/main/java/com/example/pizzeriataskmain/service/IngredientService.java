package com.example.pizzeriataskmain.service;

import com.example.pizzeriataskmain.dbService.DBService;
import com.example.pizzeriataskmain.dbService.dataSets.Ingredient;
import org.springframework.stereotype.Service;
//import pizzeria.dbService.DBService;
//import pizzeria.dbService.dataSets.Ingredient;

@Service
public class IngredientService {
    private final DBService dbService = new DBService();

    public Ingredient[] getIngredientsList(){
        return dbService.getListIngredient();
    }

    public Ingredient getIngredient(String id){
        return dbService.getIngredient(id);
    }

    public void addIngredient(Ingredient ingredient){
        dbService.addIngredient(ingredient);
    }

    public void updateIngredient(Ingredient ingredient){
        dbService.updateIngredient(ingredient);
    }

    public void deleteIngredient(String id){
        dbService.deleteIngredient(id);
    }
}
