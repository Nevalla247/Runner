
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
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AccountRepository userDetailsRepository;
    
    @PostConstruct
    public void init() {
        if (userDetailsRepository.findByUsername("maxwell") != null) {
            return;
        }

        Account user = new Account();
        user.setUsername("maxwell");
        user.setPassword(passwordEncoder.encode("smart"));
        
        Account user2 = new Account();
        user2.setUsername("copycat");
        // Vaikka salasana on sama tietokannassa oleva hash on eri
        user2.setPassword(passwordEncoder.encode("smart"));
        
        user2 = userDetailsRepository.save(user2);

        user = userDetailsRepository.save(user);
    }
    
    @GetMapping("/")
    public String handleDefault() {
        return "redirect:/home";
    }
}
