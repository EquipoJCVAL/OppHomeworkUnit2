package com.Unit2.OppHomeworkUnit2.repository;

import com.Unit2.OppHomeworkUnit2.model.Account;
import com.Unit2.OppHomeworkUnit2.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
