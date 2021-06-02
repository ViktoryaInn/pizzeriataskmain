package com.example.pizzeriataskmain.controller;

import com.example.pizzeriataskmain.dbService.dataSets.Usr;
import com.example.pizzeriataskmain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    static final Logger logger = LoggerFactory.getLogger(ShowController.class);

    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String registration() {
        logger.info("Страница регистрации");
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView registration(@ModelAttribute("login") String login,
                                     @ModelAttribute("password") String password) {
        System.out.println(login + " " + password);
        ModelAndView modelAndView = new ModelAndView();
        if(userService.findByLogin(login) == null){
            userService.register(new Usr(login, password, "USER"));
            logger.info(String.format("Пользователь «%s» успешно зарегистрирован", login));
            modelAndView.setViewName("login");
        }else {
            modelAndView.setViewName("registration");
            modelAndView.addObject("error", true);
            logger.info(String.format("Пользователь с логином «%s» уже существует", login));
        }
        return modelAndView;
    }
}
