package com.example.pizzeriataskmain.model;

//import pizzeria.dbService.dataSets.Ingredient;

import com.example.pizzeriataskmain.dbService.dataSets.Ingredient;

import java.util.Date;

public class OrderDTO {
    private String clientName;

    private String clientPhone;

    private int cost;

    private String date;

    private Ingredient[] ingredients;

    public OrderDTO(String clientName, String clientPhone, int cost, Date date, Ingredient[] ingredients){
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.cost = cost;
        this.date = date.toString();
        this.ingredients = ingredients;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public int getCost() {
        return cost;
    }

    public String getDate() {
        return date;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }
}
