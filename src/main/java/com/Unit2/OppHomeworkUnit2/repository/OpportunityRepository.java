package com.Unit2.OppHomeworkUnit2.repository;

import com.Unit2.OppHomeworkUnit2.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    // QUANTITY STATES
    @Query("SELECT AVG(quantity) FROM Opportunity")
    Integer findAverageOfQuantity();

    @Query(value = "SELECT AVG(dd.quantity) as median_val FROM" +
            " (SELECT d.quantity, @rownum=@rownum+1 as 'row_number', " +
            "@total_rows=@rownum FROM Opportunities d, (SELECT @rownum=0) r " +
            "WHERE d.quantity is NOT NULL ORDER BY d.quantity) as dd " +
            "WHERE dd.row_number IN ( FLOOR((@total_rows+1)/2), FLOOR((@total_rows+2)/2) )", nativeQuery = true)
    Double findMedianOfQuantity();

    @Query("SELECT MAX(quantity) FROM Opportunity")
    Integer findMaxOfQuantity();

    @Query("SELECT MIN(quantity) FROM Opportunity")
    Integer findMinOfQuantity();


    // OPPORTUNITIES BY CITY
    @Query(value = "SELECT account.city, COUNT(account.city) AS opportunities FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id GROUP BY account.city", nativeQuery = true)
    Integer countAllOpportunitiesByCity();

    @Query(value = "SELECT account.city, COUNT(account.city) AS close_lost_opp FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id WHERE opportunity.status = 3 GROUP BY account.city", nativeQuery = true)
    Integer countAllClosedWonByCity();

    @Query(value = "SELECT account.city, COUNT(account.city) AS close_won_opp FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id WHERE opportunity.status = 2 GROUP BY account.city", nativeQuery = true)
    Integer countAllClosedLostByCity();

    @Query(value = "SELECT account.city, COUNT(account.city) AS open_opp FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id WHERE opportunity.status = 1 GROUP BY account.city", nativeQuery = true)
    Integer countAllOpenByCity();



    // OPPORTUNITIES BY COUNTRY
    @Query(value = "SELECT account.country, COUNT(account.country) AS opportunities FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id GROUP BY account.country", nativeQuery = true)
    Integer countAllOpportunitiesByCountry();

    @Query(value = "SELECT account.country, COUNT(account.country) AS close_lost_opp FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id WHERE opportunity.status = 3 GROUP BY account.country", nativeQuery = true)
    Integer countAllClosedWonByCountry();

    @Query(value = "SELECT account.country, COUNT(account.country) AS close_won_opp FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id WHERE opportunity.status = 2 GROUP BY account.country", nativeQuery = true)
    Integer countAllClosedLostByCountry();

    @Query(value = "SELECT account.country, COUNT(account.country) AS open_opp FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id WHERE opportunity.status = 1 GROUP BY account.country", nativeQuery = true)
    Integer countAllOpenByCountry();



    // OPPORTUNITIES BY INDUSTRY
    @Query(value = "SELECT account.industry, COUNT(account.industry) AS opportunities FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id GROUP BY account.industry", nativeQuery = true)
    Integer countAllByIndustry();

    @Query(value = "SELECT account.industry, COUNT(account.industry) AS close_lost_opp FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id WHERE opportunity.status = 3 GROUP BY account.industry", nativeQuery = true)
    Integer countAllClosedWonByIndustry();

    @Query(value = "SELECT account.industry, COUNT(account.industry) AS close_won_opp FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id WHERE opportunity.status = 2 GROUP BY account.industry", nativeQuery = true)
    Integer countAllClosedLostByIndustry();

    @Query(value = "SELECT account.industry, COUNT(account.industry) AS open_opp FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id WHERE opportunity.status = 1 GROUP BY account.industry", nativeQuery = true)
    Integer countAllOpenByIndustry();



    // OPPORTUNITY STATES
    @Query(value = "SELECT AVG(count) as avarage FROM (SELECT COUNT(account_opportunity_id) as count FROM opportunity GROUP BY account_opportunity_id) as result", nativeQuery = true)
    Integer findAveragePerAccount();

//    @Query(value = "", nativeQuery = true)
//    Integer findMedianPerAccount();

    @Query(value = "SELECT MAX(count) as maximum FROM (SELECT COUNT(account_opportunity_id) as count FROM opportunity GROUP BY account_opportunity_id) as result", nativeQuery = true)
    Integer findMaxPerAccount();

    @Query(value = "SELECT MIN(count) as minimum FROM (SELECT COUNT(account_opportunity_id) as count FROM opportunity GROUP BY account_opportunity_id) as result", nativeQuery = true)
    Integer findMinPerAccount();

    // REPORT BY PRODUCT

    @Query(nativeQuery = true, value = "SELECT product, COUNT(id) AS opportunities FROM opportunity  GROUP BY product")
    Integer findAllOpportunitiesByProduct();
    @Query(nativeQuery = true, value = "SELECT product, status, COUNT(id) AS opportunities FROM opportunity  WHERE status = 1 GROUP BY product")
    Integer findAllClosedLostByProduct();
    @Query(nativeQuery = true, value = "SELECT product, status, COUNT(id) AS opportunities FROM opportunity  WHERE status = 2 GROUP BY product")
    Integer findAllClosedWonByProduct();
    @Query(nativeQuery = true, value = "SELECT product, status, COUNT(id) AS opportunities FROM opportunity  WHERE status = 3 GROUP BY product")
    Integer findAllOpenByProduct();



}

