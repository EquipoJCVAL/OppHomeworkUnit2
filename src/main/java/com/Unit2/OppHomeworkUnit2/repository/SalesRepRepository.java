package com.Unit2.OppHomeworkUnit2.repository;

import com.Unit2.OppHomeworkUnit2.model.Lead;
import com.Unit2.OppHomeworkUnit2.model.Opportunity;
import com.Unit2.OppHomeworkUnit2.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Long> {

/*
    public List<Lead> findLeadBySalesRep();

    public List<Opportunity> findOpportunityBySalesRep();

    @Query("SELECT * FROM Opportunity WHERE status LIKE 'CLOSED_WON' AND WHERE SalesRep LIKE :salesRep")
    public List<Opportunity> findOpportunityBySalesRepAndStatusWon();

    @Query("SELECT * FROM Opportunity WHERE status LIKE 'CLOSED_LOST' AND WHERE SalesRep LIKE :salesRep")
    public List<Opportunity> findOpportunityBySalesRepAndStatusLost();




 */
}

