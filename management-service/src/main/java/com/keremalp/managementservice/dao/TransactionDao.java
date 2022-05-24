package com.keremalp.managementservice.dao;

import com.keremalp.managementservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long>  {
    Optional<Transaction> findByTransactionId(String transactionId);
    Optional<Transaction> findByCustomerNumber(Long customerNumber);

}
