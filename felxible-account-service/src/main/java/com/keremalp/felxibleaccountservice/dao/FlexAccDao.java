package com.keremalp.felxibleaccountservice.dao;

import com.keremalp.felxibleaccountservice.entity.FlexAcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlexAccDao extends JpaRepository<FlexAcc, Long> {

}
