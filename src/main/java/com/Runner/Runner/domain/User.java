package com.Runner.Runner.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractPersistable<Long> {

       
    private String first_name;
   
    private String last_name;
    
    private String nickname;
    
    private String email;
    
    
    @OneToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name="account_id", nullable =false)
    private Account account;
    
    @OneToMany(mappedBy="user")
    private List<Run> runs;
    
    
    public List<Run> getRuns(){
        if(this.runs == null){
            this.runs = new ArrayList<>();
        }
        
        return this.runs;
    }
    
}
