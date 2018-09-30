
package com.Runner.Runner.controller;

import com.Runner.Runner.domain.Account;
import com.Runner.Runner.domain.PersonData;
import com.Runner.Runner.domain.User;
import com.Runner.Runner.repository.AccountRepository;
import com.Runner.Runner.repository.UserRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Tee
 */

@Controller
public class UserController {
    
    @Autowired
    private AccountRepository accRepo;
    
    
    @Autowired
    private UserRepository usrRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/register")
    public String register(@ModelAttribute PersonData persondata){
        return "register";
    }
    
    
    @Transactional
    @PostMapping("/newaccount")
    public String create(@Valid @ModelAttribute PersonData persondata, BindingResult bindingresult ){
        
        if(bindingresult.hasErrors()){
            return "register";
        }
        
        // Jos käyttäjätunnus on jo olemassa ei yritetä lisätä tiliä
        if (accRepo.findByUsername(persondata.getUsername()) != null) {
            return "register";
        }
        
        // Luodaan objekti joka lisätään account-tauluun
        Account acc = new Account();
        acc.setUsername(persondata.getUsername());
         // encryptataan salasana
        acc.setPassword(passwordEncoder.encode(persondata.getPassword()));
        
        
        
        // Luodaan objekti joka lisätään user-tauluun
        User usr = new User();
        usr.setFirst_name(persondata.getFirst_name());
        usr.setLast_name(persondata.getLast_name());
        usr.setNickname(persondata.getNickname());
        usr.setEmail(persondata.getEmail());
       
        
        usr.setAccount(acc);
        acc.setUser(usr);
        
        accRepo.save(acc);
        usrRepo.save(usr);
        
        return "redirect:/success";
    }
    
    @GetMapping("/userlogged")
    public String main(Model model){
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        Account account = accRepo.findByUsername(username);
        
        User usr = usrRepo.findByAccount(account);
        System.out.println(usr.getFirst_name());
            
        model.addAttribute("name",usr.getFirst_name());
        
        Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        
        model.addAttribute("date", dateFormatter.format(now));
        
        return "mainpage";
    }
    
   
    
    
    
}
