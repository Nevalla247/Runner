
package com.Runner.Runner.controller;

import com.Runner.Runner.domain.User;
import com.Runner.Runner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Tee
 */
@Controller
public class HomeController {
    
    @Autowired
    private UserRepository usrRepo;
    
    @GetMapping("/home")
    public String home(){
        
        return "index";
    }
    
   
    @GetMapping("/success")
    public String success(){
        
        return "success";
    }
    
    
}
