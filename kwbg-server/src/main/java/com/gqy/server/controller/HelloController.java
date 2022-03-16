package com.gqy.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ice_water
 * @Date: 2022/03/02/11:40 AM
 * @Description: 要做耿沁园的男人
 */

@RestController
public class HelloController {
    @GetMapping("/employee/basic/hello")
    public String hello2(){
        return "/employee/basic/hello"; }
    @GetMapping("/employee/advanced/hello")
    public String hello3(){
        return "/employee/advanced/hello"; }
}
