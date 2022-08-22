package com.Unit2.OppHomeworkUnit2.repository;

import com.Unit2.OppHomeworkUnit2.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {

    // OPPORTUNITY STATES
    @Query("SELECT AVG(Account.opportunityList) FROM Account")
    Integer findAvarageOfOpportunityByAccount();
    @Query("")
    Integer findMedianOpportunityByAccount();
    @Query("")
    Integer findMaxOfOpportunityByAccount();
    @Query("")
    Integer findMinOfOpportunityByAccount();

    // QUANTITY STATES
    @Query("")
    Integer findAllByCountry(String country);
}
