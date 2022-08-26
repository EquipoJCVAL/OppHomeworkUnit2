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
    Double findAverageOfEmployeeCount();

    @Query(value = "SELECT AVG(dd.employee_count) as median_val FROM " +
            "(SELECT d.employee_count, @rownum\\:=@rownum+1 as 'row_number', @total_rows\\:=@rownum FROM account d, " +
            "(SELECT @rownum\\:=0) r WHERE d.employee_count is NOT NULL ORDER BY d.employee_count) as dd " +
            "WHERE dd.row_number IN ( FLOOR((@total_rows+1)/2), FLOOR((@total_rows+2)/2) )", nativeQuery = true)
    Double findMedianOfEmployeeCount();

    @Query("SELECT MAX(employeeCount) FROM Account")
    Integer findMaxOfEmployeeCount();

    @Query("SELECT MIN(employeeCount) FROM Account")
    Integer findMinOfEmployeeCount();

}
