package com.keremalp.accountservice.listener;


import com.keremalp.accountservice.config.MQConfig;
import com.keremalp.accountservice.dto.events.cancel.CreditCancelEvent;
import com.keremalp.accountservice.dto.events.cancel.CreditCardCancelEvent;
import com.keremalp.accountservice.dto.events.cancel.FlexAccountCancelEvent;
import com.keremalp.accountservice.dto.events.cancel.HgsCancelEvent;
import com.keremalp.accountservice.dto.events.create.*;
import com.keremalp.accountservice.dto.product.CreateAccountForCreditCardRequestDto;
import com.keremalp.accountservice.dto.product.CreateAccountForCreditRequestDto;
import com.keremalp.accountservice.dto.product.CreateAccountForHgsRequestDto;
import com.keremalp.accountservice.dto.product.CreateAccountRequestDto;
import com.keremalp.accountservice.entity.AccountException;
import com.keremalp.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Log4j2
public class MessageListener {
    private final AccountService accountService;

    @RabbitListener(queues = MQConfig.CREATE_ACCOUNT)
    public void listener(AccountCreateEvent event)  {

        log.info("Account Açma Talebi Alındı -->" + event);

        try {
            accountService.createAccount(event.getAccount(), event.getTransactionId());
        } catch (AccountException e) {
            log.error("Cannot create account, reason: {}", e.getMessage());
        }


    }

    @RabbitListener(queues = MQConfig.CREATE_ACCOUNT_CUSTOMER)
    public void listener(AccountCreateRequestEvent event) {


        log.info("Yeni Müşteri Kaydı İçin Account Açma Talebi Alındı -->" + event);

        CreateAccountRequestDto account = new CreateAccountRequestDto();
        account.setName(event.getCustomer().getName());
        account.setSurname(event.getCustomer().getSurname());
        account.setAddress(event.getCustomer().getAddress());
        account.setPhone(event.getCustomer().getPhone());
        account.setCustomerNumber(event.getCustomer().getCustomerNumber());
        account.setEmail(event.getCustomer().getEmail());

        try {
            accountService.createAccount(account, event.getTransactionId());
        } catch (AccountException e) {
            log.error("Cannot create account, reason: {}", e.getMessage());
        }


    }

    @RabbitListener(queues = MQConfig.CREATE_FLEXIBLE_ACCOUNT_ACCOUNT)
    public void listener2(FlexAccountCreateRequestEvent event) {

        log.info("Flex Account İçin Account Oluştur Talebi Geldi " + event);


        try {
            CreateAccountRequestDto account = new CreateAccountRequestDto();
            account.setName(event.getCustomer().getCustomerName());
            account.setSurname(event.getCustomer().getCustomerSurname());
            account.setAddress(event.getCustomer().getAddress());
            account.setPhone(event.getCustomer().getPhoneNumber());
            account.setCustomerNumber(event.getCustomer().getCustomerNumber());
            account.setEmail(event.getCustomer().getCustomerEmail());
            accountService.createAccountForFlexAccount(account, event.getTransactionId());
        } catch (Exception e) {
            log.error("Cannot create account, reason: {}", e.getMessage());
        }


    }

    @RabbitListener(queues = MQConfig.CREATE_CREDIT_FROM_ACCOUNT)
    public void listener2(CreditCreateRequestEvent event) {

        log.info("Credit İçin Account Oluştur Talebi Geldi " + event);


        try {
            CreateAccountForCreditRequestDto account = new CreateAccountForCreditRequestDto();
            account.setCustomerName(event.getCustomer().getCustomerName());
            account.setCustomerSurname(event.getCustomer().getCustomerSurname());
            account.setAddress(event.getCustomer().getAddress());
            account.setPhoneNumber(event.getCustomer().getPhoneNumber());
            account.setCustomerNumber(event.getCustomer().getCustomerNumber());
            account.setCustomerEmail(event.getCustomer().getCustomerEmail());
            accountService.createAccountForCredit(account, event.getTransactionId());
        } catch (Exception e) {
            log.error("Cannot create account, reason: {}", e.getMessage());
        }


    }

    @RabbitListener(queues = MQConfig.CREATE_CREDIT_CARD_FROM_ACCOUNT)
    public void listener2(CreditCardCreateRequestEvent event) {

        log.info("Credit İçin Account Oluştur Talebi Geldi " + event);


        try {
            CreateAccountForCreditCardRequestDto account = new CreateAccountForCreditCardRequestDto();
            account.setCustomerName(event.getCustomer().getCustomerName());
            account.setCustomerSurname(event.getCustomer().getCustomerSurname());
            account.setAddress(event.getCustomer().getAddress());
            account.setPhoneNumber(event.getCustomer().getPhoneNumber());
            account.setCustomerNumber(event.getCustomer().getCustomerNumber());
            account.setCustomerEmail(event.getCustomer().getCustomerEmail());
            accountService.createAccountForCreditCard(account, event.getTransactionId());
        } catch (Exception e) {
            log.error("Cannot create account, reason: {}", e.getMessage());
        }


    }

    @RabbitListener(queues = MQConfig.CREATE_HGS_FROM_ACCOUNT)
    public void listener2(HgsCreateRequestEvent event) {

        log.info("Hgs İçin Account Oluştur Talebi Geldi " + event);


        try {
            CreateAccountForHgsRequestDto account = new CreateAccountForHgsRequestDto();
            account.setCustomerName(event.getCustomer().getCustomerName());
            account.setCustomerSurname(event.getCustomer().getCustomerSurname());
            account.setAddress(event.getCustomer().getAddress());
            account.setPhoneNumber(event.getCustomer().getPhoneNumber());
            account.setCustomerNumber(event.getCustomer().getCustomerNumber());
            account.setCustomerEmail(event.getCustomer().getCustomerEmail());
            account.setCarPlate(event.getCustomer().getCarPlate());
            accountService.createAccountForHgs(account, event.getTransactionId());
        } catch (Exception e) {
            log.error("Cannot create account, reason: {}", e.getMessage());
        }


    }




    @RabbitListener(queues = MQConfig.CREATE_FLEX_ACC_CANCEL)
    public void listener(FlexAccountCancelEvent event) {
        log.info("Flex Account Rollback Oldu Hesap Silme Talebi Alındı " + event);
        try {
            accountService.deleteAccountForFlexibleAccountTransactionFail(event.getTransactionId(), event);
        } catch (Exception e) {

            log.error("Cannot delete account, reason: {}", e.getMessage());
        }
    }

    @RabbitListener(queues = MQConfig.CREATE_CREDIT_CANCEL)
    public void listener(CreditCancelEvent event) {
        log.info("Credit Rollback Oldu Hesap Silme Talebi Alındı " + event);
        try {
            accountService.deleteAccountForCreditTransactionFail(event.getTransactionId(), event);
        } catch (Exception e) {

            log.error("Cannot delete account, reason: {}", e.getMessage());
        }
    }


    @RabbitListener(queues = MQConfig.CREATE_CREDIT_CARD_CANCEL)
    public void listener(CreditCardCancelEvent event) {
        log.info("Credit CARD Rollback Oldu Hesap Silme Talebi Alındı " + event);
        try {
            accountService.deleteAccountForCreditCardTransactionFail(event.getTransactionId(), event);
        } catch (Exception e) {

            log.error("Cannot delete account, reason: {}", e.getMessage());
        }
    }

    @RabbitListener(queues = MQConfig.CREATE_HGS_CANCEL)
    public void listener(HgsCancelEvent event) {
        log.info("Hgs Rollback Oldu Hesap Silme Talebi Alındı " + event);
        try {
            accountService.deleteAccountForHgsTransactionFail(event.getTransactionId(), event);
        } catch (Exception e) {

            log.error("Cannot delete account, reason: {}", e.getMessage());
        }
    }
}
