
package com.Runner.Runner.repository;

import com.Runner.Runner.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tee
 */
public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByUsername(String username);
}
