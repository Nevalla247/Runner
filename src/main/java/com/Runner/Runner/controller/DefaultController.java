
package com.Runner.Runner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Tee
 */

@Controller
public class DefaultController {
    
    @GetMapping("/")
    public String handleDefault() {
        return "redirect:/home";
    }
}
