package com.example.pizzeriataskmain.controller;

import com.example.pizzeriataskmain.dbService.dataSets.Ingredient;
import com.example.pizzeriataskmain.dbService.dataSets.Order;
import com.example.pizzeriataskmain.model.OrderDTO;
import com.example.pizzeriataskmain.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;

@Controller
public class OrderController {

    static final Logger logger = LoggerFactory.getLogger(ShowController.class);

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ModelAndView getOrders(){
        Order[] orders = orderService.getOrderList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderList");
        logger.info("Страница заказов");
        if(orders.length == 0){
            logger.info("Список заказов пуст");
            return modelAndView;
        }
        LinkedList<OrderDTO> responseOrders = new LinkedList<>();
        for(Order order: orders){
            Ingredient[] ingredients = orderService.getIngredientsByOrder(order.getId());
            responseOrders.add(new OrderDTO(order.getClientName(), order.getClientPhone(), order.getCost(), order.getDate(), ingredients));
        }
        logger.info("Выведены все заказы");
        modelAndView.addObject("orders", responseOrders);
        return modelAndView;
    }

    @PostMapping("/orders/add")
    public void addOrder(@ModelAttribute("name") String name,
                         @ModelAttribute("phone") String phone,
                         @ModelAttribute("cost") String cost,
                         @ModelAttribute("ingredients") String ingredientsString){
        Order order = new Order(name, phone, Integer.parseInt(cost), java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        orderService.addOrder(order);
        String[] ingredients = ingredientsString.split(" ");
        for(String ingredient: ingredients){
            orderService.addIngredientToOrder(order.getId(), ingredient);
        }
        logger.info(String.format("Заказ для «%s» создан", name));
    }
}
