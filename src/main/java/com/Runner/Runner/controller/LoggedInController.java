
package com.Runner.Runner.controller;

import com.Runner.Runner.domain.Account;
import com.Runner.Runner.domain.EditProfile;
import com.Runner.Runner.domain.PersonData;
import com.Runner.Runner.domain.User;
import com.Runner.Runner.repository.AccountRepository;
import com.Runner.Runner.repository.UserRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class LoggedInController {
    
    @Autowired
    private AccountRepository accRepo;
    
    
    @Autowired
    private UserRepository usrRepo;
    
    @GetMapping("/profile")
    public String profile(@ModelAttribute EditProfile editprofile, Model model){
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        Account account = accRepo.findByUsername(username);
        
        User usr = usrRepo.findByAccount(account);
            
            
        model.addAttribute("user",usr);
        
        Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        
        model.addAttribute("date", dateFormatter.format(now));
        
        model.addAttribute("username",username);
        
        return "profile";
    }
    
    @PostMapping("/updatedata")
    public String update(@Valid @ModelAttribute EditProfile editprofile, BindingResult bindingresult ){
        
        if(bindingresult.hasErrors()){
            return "profile";
        }
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        String username = auth.getName();
        System.out.println("username");
        
        Account account = accRepo.findByUsername(username);
        
        User usr = usrRepo.findByAccount(account);
        
        
        // Mahdolliset tietopäivitykset tietokantaan
        if(!editprofile.getFirst_name().equals("")){
            usr.setFirst_name(editprofile.getFirst_name());
        }
        if(!editprofile.getLast_name().equals("")){
            usr.setLast_name(editprofile.getLast_name());
        }
        if(!editprofile.getNickname().equals("")){
            usr.setNickname(editprofile.getNickname());
        }
        if(!editprofile.getEmail().equals("")){
            usr.setEmail(editprofile.getEmail());
        }
        usrRepo.save(usr);
        
        return "redirect:/profile";
    }
    
    
    
    
    @GetMapping("/groups")
    public String groups(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        Account account = accRepo.findByUsername(username);
        
        User usr = usrRepo.findByAccount(account);
            
            
        model.addAttribute("name",usr.getFirst_name());
        
        Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        
        model.addAttribute("date", dateFormatter.format(now));
        
        return "groups";
    }
    
    
    
}
