
package com.Runner.Runner.repository;

import com.Runner.Runner.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tee
 */
public interface UserRepository extends JpaRepository<User,Long>{
    
}
