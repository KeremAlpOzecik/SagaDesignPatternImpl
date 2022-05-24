package com.keremalp.creditcardservice.listener;


import com.keremalp.creditcardservice.config.MQConfig;
import com.keremalp.creditcardservice.dto.events.create.CreateCreditCardEvent;
import com.keremalp.creditcardservice.dto.events.create.CreateCreditCardEventFromAccount;
import com.keremalp.creditcardservice.dto.products.CreateCreditCardDto;
import com.keremalp.creditcardservice.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;

@Component
@RequiredArgsConstructor
@Log4j2
public class MessageListener {
    private final CreditCardService creditCardService;

    @RabbitListener(queues = MQConfig.CREATE_CREDIT_CARD)//CREATE_CREDIT_CARD
    public void listener(CreateCreditCardEvent event) throws AccountNotFoundException {


        log.info("CREDIT Card açmak için talep alındı "+event);

        try {
            creditCardService.createCreditCard(event.getCreateCreditDto(),event.getTransactionId());
        } catch (Exception e) {
            log.error("Cannot create account, reason: {}", e.getMessage());
        }




    }
    @RabbitListener(queues = MQConfig.CREATE_CREDIT_CARD_CREDIT_CARD)
    public void listener2(CreateCreditCardEventFromAccount event) throws AccountNotFoundException{


        log.info("Yeni Müşteri için Credit CARD Açma Talebi Alındı "+event);

        try {
            CreateCreditCardDto creditCardDto = new CreateCreditCardDto();
            creditCardDto.setAccountNumber(event.getAccount().getAccountNumber());
            creditCardDto.setAddress(event.getAccount().getAddress());
            creditCardDto.setCustomerName(event.getAccount().getCustomerName());
            creditCardDto.setCustomerSurname(event.getAccount().getCustomerSurname());
            creditCardDto.setPhoneNumber(event.getAccount().getPhoneNumber());
            creditCardDto.setCustomerNumber(event.getAccount().getCustomerNumber());
            creditCardDto.setCustomerEmail(event.getAccount().getCustomerEmail());
            creditCardService.createCreditCardForNewCustomer(creditCardDto,event.getTransactionId());
        } catch (Exception e) {
            log.error("Cannot create CREDIT CARD, reason: {}", e.getMessage());
        }




    }




}
