package com.keremalp.felxibleaccountservice.listener;


import com.keremalp.felxibleaccountservice.config.MQConfig;
import com.keremalp.felxibleaccountservice.dto.events.create.CreateFlexibleAccountEvent;

import com.keremalp.felxibleaccountservice.dto.events.create.CreateFlexibleAccountEventFromAccount;
import com.keremalp.felxibleaccountservice.dto.product.CreateFlexibleAccountDto;
import com.keremalp.felxibleaccountservice.service.FlexAccService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;

@Component
@RequiredArgsConstructor
@Log4j2
public class MessageListener {
    private final FlexAccService  flexAccService;

    @RabbitListener(queues = MQConfig.CREATE_FLEX_ACC)
        public void listener(CreateFlexibleAccountEvent event) throws AccountNotFoundException{


        log.info("Flexible Account açmak için talep alındı "+event);

        try {
            flexAccService.createFlexibleAccount(event.getFlexibleAccount(),event.getTransactionId());
        } catch (Exception e) {
            log.error("Cannot create account, reason: {}", e.getMessage());
        }




    }
    @RabbitListener(queues = MQConfig.CREATE_FLEXIBLE_ACCOUNT_FLEX_ACCOUNT)
    public void listener2(CreateFlexibleAccountEventFromAccount event) throws AccountNotFoundException{


        log.info("Yeni Müşteri için Flex Account Açma Talebi Alındı "+event);

        try {
            CreateFlexibleAccountDto createFlexibleAccountDto = new CreateFlexibleAccountDto();
            createFlexibleAccountDto.setAccountNumber(event.getAccount().getAccountNumber());
            createFlexibleAccountDto.setAddress(event.getAccount().getAddress());
            createFlexibleAccountDto.setCustomerName(event.getAccount().getName());
            createFlexibleAccountDto.setPhoneNumber(event.getAccount().getPhone());
            createFlexibleAccountDto.setCustomerNumber(event.getAccount().getCustomerNumber());
           flexAccService.createFlexibleAccountForNewCustomer(createFlexibleAccountDto,event.getTransactionId());
        } catch (Exception e) {
            log.error("Cannot create account, reason: {}", e.getMessage());
        }




    }



}
