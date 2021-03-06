package com.energylayer.web.controller;

import com.energylayer.model.UserQuery;
import com.energylayer.service.SecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * @author: rkotelnikov
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private SecService secService;

    @RequestMapping(value = "", method = GET)
    public String login(){
        return "/login/login";
    }

    @RequestMapping(value = "/register", method = GET)
    public String register(){
        return "/login/register";
    }

    @RequestMapping(value = "/create", method = POST)
    public String create(@Valid @ModelAttribute UserQuery userQuery, BindingResult errors){
        if(errors.hasErrors()){
            return "/login/register";
        }
        if(secService.userExists(userQuery.getEmail())){
            errors.rejectValue("email", "email.already.exists");
            return "/login/register";
        }
        secService.createUser(userQuery);
        return "redirect:/";
    }

    @ModelAttribute
    public UserQuery userQuery(){
        return new UserQuery();
    }
}
