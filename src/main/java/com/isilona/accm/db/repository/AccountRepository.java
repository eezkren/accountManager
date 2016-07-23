package com.isilona.accm.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isilona.accm.db.model.Account;

/**
 * @author iivanov
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByFirstName(String firstName);

}