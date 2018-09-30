
package com.Runner.Runner.controller;

import com.Runner.Runner.domain.Account;
import com.Runner.Runner.repository.AccountRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Tee
 */

@Controller
public class DefaultController {
    
    @Autowired
    private AccountRepository userDetailsRepository;
    
    
    @GetMapping("/")
    public String handleDefault() {
        return "redirect:/home";
    }
 
    
}
