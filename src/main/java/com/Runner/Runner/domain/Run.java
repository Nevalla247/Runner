// Luokka yksittäiselle juoksutiedolle, liittyy yksittäiseen käyttäjään
package com.Runner.Runner.domain;

import java.util.Date;
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
    
    private Date date;
    
    private double duration;
    
    private String location;
    
    @ManyToOne
    @JoinColumn(name="run_id", nullable=false)
    private User user;
    
}
