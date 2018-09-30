
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
    
    // Uuden tunnuksen luonti, käytetään syötteiden validointia
    @Transactional
    @PostMapping("/newaccount")
    public String create(@Valid @ModelAttribute PersonData persondata, BindingResult bindingresult ){
        
        // jos virheitä, palautetaan sivu uudelleen
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
        
        // Tallennetaan user ja account
        accRepo.save(acc);
        usrRepo.save(usr);
        
        return "redirect:/success";
    }
    
    @GetMapping("/userlogged")
    public String main(Model model){
        
        // Käyttäjätietojen haku
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        Account account = accRepo.findByUsername(username);
        
        User usr = usrRepo.findByAccount_id(account.getId());
        System.out.println(usr.getFirst_name());
        
        // Lisätään malliin nimi
        model.addAttribute("name",usr.getFirst_name());
        
        Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        
        // Lisätään malliin päivämäärä
        model.addAttribute("date", dateFormatter.format(now));
        
        return "mainpage";
    }
    
   
    
    
    
}
