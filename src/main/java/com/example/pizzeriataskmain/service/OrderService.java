package com.example.pizzeriataskmain.service;

import com.example.pizzeriataskmain.dbService.DBService;
import com.example.pizzeriataskmain.dbService.dataSets.Ingredient;
import com.example.pizzeriataskmain.dbService.dataSets.Order;
import org.springframework.stereotype.Service;
//import pizzeria.dbService.DBService;
//import pizzeria.dbService.dataSets.Ingredient;
//import pizzeria.dbService.dataSets.Order;

@Service
public class OrderService {
    private final DBService dbService = new DBService();

    public Order[] getOrderList(){
        return dbService.getListOrder();
    }

    public Ingredient[] getIngredientsByOrder(String id){
        return dbService.getIngredientsByOrder(id);
    }

    public void addOrder(Order order){
        dbService.addOrder(order);
    }

    public void addIngredientToOrder(String orderId, String ingredientId){
        dbService.addIngredientToOrder(orderId, ingredientId);
    }
}
