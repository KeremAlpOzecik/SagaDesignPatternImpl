package com.keremalp.creditcardservice.dao;

import com.keremalp.creditcardservice.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardDao extends JpaRepository<CreditCard, Long> {

    List<CreditCard> findByCustomerNumber(Long customerNumber);

}
