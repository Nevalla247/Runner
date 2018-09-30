
package com.Runner.Runner.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Tee
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends AbstractPersistable<Long> {
    
    private String username;
    private String password;
    
    // yhden suhde yhteen mappaus
    @OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="account")
    private User user;
    
}
