package com.keremalp.customerservice.listener;

import com.keremalp.customerservice.config.MQConfig;
import com.keremalp.customerservice.dto.events.cancel.AccountCancelEvent;
import com.keremalp.customerservice.dto.events.create.*;
import com.keremalp.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class MessageListener {
    private final CustomerService customerService;
    @RabbitListener(queues = MQConfig.CREATE_CIF)
    public void listener(CifOpenCreatedEvent message) {
        log.info("Customer Açma Talebi Alındı: " + message);
        customerService.createCustomer(message.getCustomer(), message.getTransactionId());

    }
    @RabbitListener(queues = MQConfig.CREATE_ACCOUNT_CUSTOMER)
    public void listener(AccountCreateRequestEvent message) {
        log.info("Account Açmak İçin Customer Yaratma Talebi Alındı: " + message);
        customerService.createCustomerForAccount(message.getCustomer(), message.getTransactionId());

    }

    @RabbitListener(queues = MQConfig.CREATE_FLEXIBLE_ACCOUNT_CUSTOMER)
    public void listener(FlexibleAccountCreateRequestEvent message) {
        log.info("Flexible Account Açmak İçin Customer Yaratma Talebi Alındı: " + message);
        customerService.createCustomerForFlexibleAccount(message.getCustomer(), message.getTransactionId());

    }
    @RabbitListener(queues = MQConfig.CREATE_CREDIT_FROM_CUSTOMER)
    public void listener(CreditCreateRequestEvent message) {
        log.info("Credit Açmak İçin Customer Yaratma Talebi Alındı: " + message);
        customerService.createCustomerForCredit(message.getCustomer(), message.getTransactionId());

    }

    @RabbitListener(queues = MQConfig.CREATE_CREDIT_CARD_FROM_CUSTOMER)
    public void listener(CreditCardCreateRequestEvent message) {
        log.info("Credit Card Açmak İçin Customer Yaratma Talebi Alındı: " + message);
        customerService.createCustomerForCreditCard(message.getCustomer(), message.getTransactionId());

    }


    @RabbitListener(queues = MQConfig.CREATE_HGS_FROM_CUSTOMER)
    public void listener(HgsCreateRequestEvent message) {
        log.info("HGS Açmak İçin Customer Yaratma Talebi Alındı: " + message);
        customerService.createCustomerForHgs(message.getCustomer(), message.getTransactionId());

    }
    @RabbitListener(queues = MQConfig.CREATE_ACCOUNT_CANCEL)
    public void listener(AccountCancelEvent message) {
        try {
            customerService.deleteCustomerForAccountTransactionFail(message.getTransactionId(),message);
            log.info("Rollback Olduğu İçin Customer Silindi: " + message);
        } catch (Exception e) {
            log.error("Cannot delete customer, reason: {}", e.getMessage());
        }


    }


}
