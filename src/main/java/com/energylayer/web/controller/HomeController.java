package com.energylayer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author: rkotelnikov
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/", method = GET)
    public String home(){
        return "/home/home";
    }
}
