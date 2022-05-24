package com.keremalp.hgsservice.dao;

import com.keremalp.hgsservice.entity.Hgs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HgsDao extends JpaRepository<Hgs, Long> {

    List<Hgs> findByCustomerNumber(Long customerNumber);




}
