// Luokka yksittäiselle juoksutiedolle, liittyy yksittäiseen käyttäjään
package com.Runner.Runner.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Run extends AbstractPersistable<Long>{
    
    private double distance;
    
    private double duration;
    
    private String location;
    
    private LocalDate rundate;
    
    // Käyttäjällä voi olla useita juoksutietoja
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    
}
