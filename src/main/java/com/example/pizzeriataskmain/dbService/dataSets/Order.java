package com.example.pizzeriataskmain.dbService.dataSets;

import java.util.Date;
import java.util.UUID;

public class Order {
    public Order() {}

    private String id = UUID.randomUUID().toString();

    private String clientName;

    private String clientPhone;

    private int cost;

    private Date date;

    public Order(String id, String clientName, String clientPhone, int cost, Date date){
        this.id = id;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.cost = cost;
        this.date = date;
    }

    public Order(String clientName, String clientPhone, int cost, Date date){
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.cost = cost;
        this.date = date;
    }

    public String getId() {
        return id;
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

    public Date getDate() {return date;}

    public void setClientName(String clientName){
        this.clientName = clientName;
    }

    public void setClientPhone(String clientPhone){
        this.clientPhone = clientPhone;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
