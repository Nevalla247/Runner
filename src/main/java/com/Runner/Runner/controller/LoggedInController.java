
package com.Runner.Runner.controller;

import com.Runner.Runner.domain.Account;
import com.Runner.Runner.domain.EditProfile;
import com.Runner.Runner.domain.Run;
import com.Runner.Runner.domain.User;
import com.Runner.Runner.repository.AccountRepository;
import com.Runner.Runner.repository.RunRepository;
import com.Runner.Runner.repository.UserRepository;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    @Autowired
    private RunRepository runRepo;
    
    @GetMapping("/profile")
    public String profile(@ModelAttribute EditProfile editprofile, Model model){
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println(username);
        Account account = accRepo.findByUsername(username);
        System.out.println(account.getId());
        User usr = usrRepo.findByAccount_id(account.getId());
            
            
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
        
        
        Account account = accRepo.findByUsername(username);
        
        System.out.println(account);
        
        User usr = usrRepo.findByAccount_id(account.getId());
        
        
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
        Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
        String username = auth2.getName();
        System.out.println(username);
        Account account = accRepo.findByUsername(username);
        
        User usr = usrRepo.findByAccount_id(account.getId());
            
            
        model.addAttribute("name",usr.getFirst_name());
        
        Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        
        model.addAttribute("date", dateFormatter.format(now));
        
        return "groups";
    }
    
    @GetMapping("/runs")
    public String runs(Model model, Principal user){
        
        // Haetaan autentikoidun käyttäjän tiedot
  
        Account account = accRepo.findByUsername(user.getName());
       
        User usr = usrRepo.findByAccount_id(account.getId());
         
        model.addAttribute("name",usr.getFirst_name());
        
        // Päiväys
        Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        // Lisätään modeliin päiväys
        model.addAttribute("date", dateFormatter.format(now));
        
    
        // Käyttäjän juoksutiedot
        List<Run> runs = usr.getRuns();
        
        // Lisätään modeliin juoksutiedot
        model.addAttribute("runs",runs);
        
        return "runs";
    }
    
    @PostMapping("/run")
    public String lisaa(@RequestParam String location, @RequestParam double duration, @RequestParam double distance,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rundate){
        
        Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
        String username = auth2.getName();
        
        Account acc = accRepo.findByUsername(username);
        
        User usr = usrRepo.findByAccount_id(acc.getId());
        
        Run run = new Run();
        run.setLocation(location);
        run.setDuration(duration);
        run.setDistance(distance);
        run.setRundate(rundate);
        run.setUser(usr);
        
        usr.addRun(run);
        
        runRepo.save(run);
        usrRepo.save(usr);
        
        
        return "redirect:/runs";
        
    }
    
    @DeleteMapping("/runs/{runId}")
    public String poista(@PathVariable Long runId){
        System.out.println(runId);
        runRepo.deleteById(runId);
        
        return "redirect:/runs";
    }
    
    
    
    
}
