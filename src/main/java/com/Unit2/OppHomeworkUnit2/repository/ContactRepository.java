package com.Unit2.OppHomeworkUnit2.repository;

import com.Unit2.OppHomeworkUnit2.model.Contact;
import com.Unit2.OppHomeworkUnit2.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
