package com.tsurkan.controller;

import com.tsurkan.entities.User;
import com.tsurkan.exception.UserNotFoundException;
import com.tsurkan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/form")
    public ModelAndView loginForm(){
        return new ModelAndView("login", "user", new User());
    }

    @PostMapping(value = "/check")
    public ModelAndView check(@ModelAttribute("user") User user){
        try {
            if(userService.signIn(user.getLogin(), user.getPassword()))
                 return new ModelAndView("start-page", "username", user.getLogin());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return new ModelAndView("hello", "message", "failed :c");
    }
}
