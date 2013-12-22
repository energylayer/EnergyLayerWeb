package com.energylayer.web.controller;

import com.energylayer.model.UserWeb;
import com.energylayer.service.SecService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * @author: rkotelnikov
 */
@Controller
public class LoginController {

    @Autowired
    private SecService secService;

    @RequestMapping(value = "/login", method = GET)
    public String login(){
        return "/login/login";
    }

    @RequestMapping(value = "/login/register", method = GET)
    public String register(){
        return "/login/register";
    }

    @RequestMapping(value = "/login/create", method = POST)
    public String create(@Valid @ModelAttribute UserWeb userWeb, BindingResult errors){
        if(errors.hasErrors()){
            return "/login/register";
        }
        if(secService.userExists(userWeb.getUsername())){
            errors.rejectValue("username", "username.already.exists");
            return "/login/register";
        }
        secService.createUser(userWeb);
        return "redirect:/";
    }

    @ModelAttribute
    public UserWeb userWeb(){
        return new UserWeb();
    }
}
