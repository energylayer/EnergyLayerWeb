package com.energylayer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author: rkotelnikov
 */
@Controller
public class TestController {

    @RequestMapping(value = "/test", method = GET)
    public String test(){
        return "/test/test";
    }
}
