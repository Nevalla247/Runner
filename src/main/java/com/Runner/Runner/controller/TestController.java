/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Runner.Runner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Tee
 */

@Controller
public class TestController {
    @GetMapping("*")
    @ResponseBody
    public String home(){
        return "Hello World!";
    }
}
