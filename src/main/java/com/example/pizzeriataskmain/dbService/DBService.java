package com.example.pizzeriataskmain.dbService;

import com.example.pizzeriataskmain.dbService.dao.IngredientDAO;
import com.example.pizzeriataskmain.dbService.dao.OrderDAO;
import com.example.pizzeriataskmain.dbService.dao.OrderIngredientsDAO;
import com.example.pizzeriataskmain.dbService.dao.UserDAO;
import com.example.pizzeriataskmain.dbService.dataSets.Ingredient;
import com.example.pizzeriataskmain.dbService.dataSets.Order;
import com.example.pizzeriataskmain.dbService.dataSets.OrderIngredients;
import com.example.pizzeriataskmain.dbService.dataSets.Usr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getMySQLConnection();
        System.out.println("Соединение с СУБД выполнено");
    }

    private Connection getMySQLConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/pizzeria_db";
            String login = "root";
            String pass = "123456789aa_AA";

            return DriverManager.getConnection(url, login, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Ingredient[] getListIngredient() {
        try {
            return new IngredientDAO(connection).getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new Ingredient[0]; // TODO: исправить
        }
    }

    public Ingredient getIngredient(String id) {
        try{
            return new IngredientDAO(connection).get(id);
        } catch (SQLException e){
            e.printStackTrace();
            return new Ingredient(); // TODO: исправить
        }
    }

    public void addIngredient(Ingredient ingredient) {
        try {
            connection.setAutoCommit(false);
            new IngredientDAO(connection).insert(ingredient);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateIngredient(Ingredient ingredient) {
        try {
            connection.setAutoCommit(false);
            new IngredientDAO(connection).update(ingredient);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteIngredient(String id) {
        try {
            connection.setAutoCommit(false);
            new IngredientDAO(connection).delete(id);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Order[] getListOrder() {
        try {
            return new OrderDAO(connection).getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new Order[0]; // TODO: исправить
        }
    }

    public Order getOrder(String id) {
        try{
            return new OrderDAO(connection).get(id);
        } catch (SQLException e){
            e.printStackTrace();
            return new Order(); // TODO: исправить
        }
    }

    public Ingredient[] getIngredientsByOrder(String orderId){
        try{
            OrderIngredients[] orderIngredients = new OrderIngredientsDAO(connection).getByOrder(orderId);
            var list = new LinkedList<Ingredient>();
            for(OrderIngredients orderIngredient: orderIngredients){
                list.add(new IngredientDAO(connection).get(orderIngredient.getIngredientId()));
            }
            return list.toArray(new Ingredient[0]);
        }catch(SQLException e){
            e.printStackTrace();
            return new Ingredient[0];
        }
    }

    public void addOrder(Order order) {
        try {
            connection.setAutoCommit(false);
            new OrderDAO(connection).insert(order);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addIngredientToOrder(String orderId, String ingredientId){
        try{
            connection.setAutoCommit(false);
            new OrderIngredientsDAO(connection).insert(orderId, ingredientId);
            connection.commit();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateOrder(Order order) {
        try {
            connection.setAutoCommit(false);
            new OrderDAO(connection).update(order);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(String id) {
        try {
            connection.setAutoCommit(false);
            new OrderDAO(connection).delete(id);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(Usr user){
        try{
            connection.setAutoCommit(false);
            new UserDAO(connection).insert(user);
            connection.commit();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Usr getUser(String login) {
        return new UserDAO(connection).get(login);
    }

    public boolean checkUserExists(String login) throws SQLException {
        return new UserDAO(connection).checkUserExists(login);
    }
}
