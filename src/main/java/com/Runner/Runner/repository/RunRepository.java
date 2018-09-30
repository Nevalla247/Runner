
package com.Runner.Runner.repository;

import com.Runner.Runner.domain.Run;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tee
 */

public interface RunRepository extends JpaRepository<Run,Long> {
    
}
