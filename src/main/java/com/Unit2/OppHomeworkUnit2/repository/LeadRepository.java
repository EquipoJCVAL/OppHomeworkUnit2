package com.Unit2.OppHomeworkUnit2.repository;

import com.Unit2.OppHomeworkUnit2.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

    public List<Lead> OrderBySalesRepLead();
}
