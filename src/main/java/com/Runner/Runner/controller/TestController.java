/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Runner.Runner.controller;

import com.Runner.Runner.domain.User;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Tee
 */

@Controller
public class TestController {
    @GetMapping("/home")
    public String home(){
        return "index";
    }
    
    
    
    @GetMapping("/success")
    public String success(){
        return "success";
    }
    
    
}   


