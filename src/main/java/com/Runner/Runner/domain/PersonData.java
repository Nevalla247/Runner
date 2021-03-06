// Luokka, jota käytetään hyväksi rekisteröinnissä

package com.Runner.Runner.domain;

import javax.persistence.Entity;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonData{
    
    @NotEmpty
    @Size(min=8, max=20)
    private String username;
    
    @NotEmpty
    @Size(min=2, max=20)
    private String password;
    
    
    @NotEmpty
    @Size(min=5, max=20)
    private String first_name;
   
    @NotEmpty
    @Size(min=2, max=20)
    private String last_name;
    
    private String nickname;
    
    @NotEmpty
    @Email
    private String email;
}
