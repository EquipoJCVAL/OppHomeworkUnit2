package com.Unit2.OppHomeworkUnit2.repository;

import com.Unit2.OppHomeworkUnit2.model.Account;
import com.Unit2.OppHomeworkUnit2.model.Enums.Industry;
import com.Unit2.OppHomeworkUnit2.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    // EMPLOYEE COUNT STATES

    Integer findAverageOfEmployeeCount();

    Integer findMedianOfEmployeeCount();

    Integer findMaxOfEmployeeCount();

    Integer findMinOfEmployeeCount();

    // OPPORTUNITIES BY COUNTRY
    Integer countAllByCountry(String country);
    Integer countAllClosedWonByCountry(String country);
    Integer countAllClosedLostByCountry(String country);
    Integer countAllOpenByCountry(String country);




    // OPPORTUNITIES BY CITY
    Integer countAllByCity(String city);
    Integer countAllClosedWonByCity(String city);
    Integer countAllClosedLostByCity(String city);
    Integer countAllOpenByCity(String city);

    // OPPORTUNITIES BY INDUSTRY

    Integer countAllByIndustry(Industry industry);
    Integer countAllClosedWonByIndustry(Industry industry);
    Integer countAllClosedLostByIndustry(Industry industry);
    Integer countAllOpenByIndustry(Industry industry);

}
