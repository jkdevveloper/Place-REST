package com.jkdev.placeRest.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping("/")
    String getHello(){
        return "Hello World!";
    }


}
