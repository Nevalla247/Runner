
package com.Runner.Runner.controller;

import com.Runner.Runner.domain.Account;
import com.Runner.Runner.domain.User;
import com.Runner.Runner.repository.AccountRepository;
import com.Runner.Runner.repository.RunRepository;
import com.Runner.Runner.repository.UserRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Tee
 */

@Controller
public class RunController {
    
    private AccountRepository accRepo;
    
    private UserRepository usrRepo;
    
    private RunRepository runRepo;
    
   @GetMapping("/runs")
    public String runs(Model model){
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        Account account = accRepo.findByUsername(username);
        
        User usr = usrRepo.findByAccount(account);
            
            
        model.addAttribute("name",usr.getFirst_name());
        
        Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        
        model.addAttribute("date", dateFormatter.format(now));
        
        return "runs";
    }
    
    
}
