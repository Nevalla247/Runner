package com.Runner.Runner.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
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
    
    private String username;
}
