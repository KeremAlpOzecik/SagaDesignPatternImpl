package com.keremalp.creditservice.dao;

import com.keremalp.creditservice.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CreditDao extends JpaRepository<Credit, Long> {

    List<Credit> findByCustomerNumber(Long customerNumber);
    @Query("select count (c) from Credit c where (c.createdDate =?1 and c.customerNumber=?2)")
    int countCreditByDateAndCustomerNumber(LocalDate createdDate, Long customerNumber);



}
