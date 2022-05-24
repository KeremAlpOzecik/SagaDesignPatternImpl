package com.keremalp.creditservice.listener;


import com.keremalp.creditservice.config.MQConfig;
import com.keremalp.creditservice.dto.events.create.CreateCreditEvent;
import com.keremalp.creditservice.dto.events.create.CreateCreditEventFromAccount;
import com.keremalp.creditservice.dto.product.CreateCreditDto;
import com.keremalp.creditservice.service.CreditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;

@Component
@RequiredArgsConstructor
@Log4j2
public class MessageListener {
    private final CreditService creditService;

    @RabbitListener(queues = MQConfig.CREATE_CREDIT)//CREATE_CREDIT
    public void listener(CreateCreditEvent event) throws AccountNotFoundException {


        log.info("CREDIT açmak için talep alındı "+event);

        try {
            creditService.createCredit(event.getCreateCreditDto(),event.getTransactionId());
        } catch (Exception e) {
            log.error("Cannot create account, reason: {}", e.getMessage());
        }




    }
    @RabbitListener(queues = MQConfig.CREATE_CREDIT_CREDIT) //CREATE_CREDIT_ACCOUNT
    public void listener2(CreateCreditEventFromAccount event) throws AccountNotFoundException{


        log.info("Yeni Müşteri için Flex Account Açma Talebi Alındı "+event);

        try {
            CreateCreditDto createCreditDto = new CreateCreditDto();
            createCreditDto.setAccountNumber(event.getAccount().getAccountNumber());
            createCreditDto.setAddress(event.getAccount().getAddress());
            createCreditDto.setCustomerName(event.getAccount().getCustomerName());
            createCreditDto.setCustomerSurname(event.getAccount().getCustomerSurname());
            createCreditDto.setPhoneNumber(event.getAccount().getPhoneNumber());
            createCreditDto.setCustomerNumber(event.getAccount().getCustomerNumber());
            createCreditDto.setCustomerEmail(event.getAccount().getCustomerEmail());
            creditService.createCreditForNewCustomer(createCreditDto,event.getTransactionId());
        } catch (Exception e) {
            log.error("Cannot create account, reason: {}", e.getMessage());
        }




    }




}
