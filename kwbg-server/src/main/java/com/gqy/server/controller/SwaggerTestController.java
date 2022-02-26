package com.gqy.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ice_water
 * @Date: 2022/02/25/7:02 PM
 * @Description: 要做耿沁园的男人
 */

@RestController
public class SwaggerTestController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
