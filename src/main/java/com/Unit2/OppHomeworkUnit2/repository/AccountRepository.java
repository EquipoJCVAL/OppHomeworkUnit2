package com.Unit2.OppHomeworkUnit2.repository;

import com.Unit2.OppHomeworkUnit2.model.Account;
import com.Unit2.OppHomeworkUnit2.model.Enums.Industry;
import com.Unit2.OppHomeworkUnit2.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // EMPLOYEE COUNT STATES
    @Query("SELECT AVG(employeeCount) FROM Account")
    Integer findAverageOfEmployeeCount();

    @Query(value = "SELECT AVG(dd.employee_count) as median_val FROM" +
            " (SELECT d.employee_count, @rownum=@rownum+1 as 'row_number', " +
            "@total_rows=@rownum FROM Accounts d, (SELECT @rownum=0) r " +
            "WHERE d.employee_count is NOT NULL ORDER BY d.employee_count) as dd " +
            "WHERE dd.row_number IN ( FLOOR((@total_rows+1)/2), FLOOR((@total_rows+2)/2) )", nativeQuery = true)
    Integer findMedianOfEmployeeCount();

    @Query("SELECT MAX(employeeCount) FROM Account")
    Integer findMaxOfEmployeeCount();

    @Query("SELECT MIN(employeeCount) FROM Account")
    Integer findMinOfEmployeeCount();


    // OPPORTUNITIES BY CITY

    @Query(nativeQuery = true, value = "SELECT account.country, COUNT(account.country) AS opportunities FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id GROUP BY account.country")
    Integer countAllOpportunitiesByCity();
    @Query(nativeQuery = true, value = "    SELECT account.country, COUNT(account.country) AS close_lost_opp FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id WHERE opportunity.status = 3 GROUP BY account.country")
    Integer countAllClosedWonByCity();
    @Query(nativeQuery = true, value = "SELECT account.country, COUNT(account.country) AS close_won_opp FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id WHERE opportunity.status = 2 GROUP BY account.country")
    Integer countAllClosedLostByCity();
    @Query(nativeQuery = true, value = "SELECT account.country, COUNT(account.country) AS close_won_opp FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id WHERE opportunity.status = 1 GROUP BY account.country")
    Integer countAllOpenByCity();



    // OPPORTUNITIES BY INDUSTRY

    @Query(nativeQuery = true, value = "SELECT account.industry, COUNT(account.industry) AS opportunities FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id GROUP BY account.industry")
    Integer countAllByIndustry();
    @Query(nativeQuery = true, value = "SELECT account.industry, COUNT(account.industry) AS close_lost_opp FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id WHERE opportunity.status = 3 GROUP BY account.industry")
    Integer countAllClosedWonByIndustry();
    @Query(nativeQuery = true, value = "SELECT account.industry, COUNT(account.industry) AS close_won_opp FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id WHERE opportunity.status = 2 GROUP BY account.industry")
    Integer countAllClosedLostByIndustry();
    @Query(nativeQuery = true, value = "SELECT account.industry, COUNT(account.industry) AS close_won_opp FROM opportunity JOIN account ON opportunity.account_opportunity_id = account.id WHERE opportunity.status = 1 GROUP BY account.industry")
    Integer countAllOpenByIndustry();



}
