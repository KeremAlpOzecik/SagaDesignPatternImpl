package com.keremalp.managementservice.listener;

import com.keremalp.managementservice.config.MQConfig;
import com.keremalp.managementservice.dto.events.cancel.*;
import com.keremalp.managementservice.dto.events.done.*;
import com.keremalp.managementservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;

@Component
@RequiredArgsConstructor
@Log4j2
public class MessageListener {
    private final TransactionService transactionService;

    @RabbitListener(queues = MQConfig.CREATE_CIF_DONE)
    public void listener(CifOpenCreatedDoneEvent event) throws AccountNotFoundException {


        log.info("Müşteri Kaydı Tamamlandı " + event.getTransactionId());
        transactionService.updateCustomerCreationAsDone(event);

    }

    //Customerden gelen iptal isteği
    @RabbitListener(queues = MQConfig.CREATE_CIF_CANCEL)
    public void listener(CancelTransactionEvent event) {


        log.info(event.getTransactionId() + " Transaction İptal Edildi");
        transactionService.cancelTransaction(event.getTransactionId());

    }
//Accountdan gelen iptal isteği

    @RabbitListener(queues = MQConfig.CANCEL_ACCOUNT_TRANSACTION)
    public void listener(AccountTransactionCancelEvent event) {


        log.info(event.getTransactionId() + " Transaction İptal Edildi");
        transactionService.cancelTransaction(event.getTransactionId());

    }
//Flex Accountdan gelen iptal isteği

    @RabbitListener(queues = MQConfig.CANCEL_TRANSACTION)
    public void listener(FlexAccountTransactionCancelEvent event) {


        log.info(event.getTransactionId() + " Transaction İptal Edildi");
        transactionService.cancelTransaction(event.getTransactionId());

    }
    //CREDIT den gelen iptal isteği

    @RabbitListener(queues = MQConfig.CANCEL_CREDIT_TRANSACTION)
    public void listener(CreditTransactionCancelEvent event) {


        log.info(event.getTransactionId() + " Transaction İptal Edildi");
        transactionService.cancelTransaction(event.getTransactionId());

    }
    //CREDIT Card den gelen iptal isteği

    @RabbitListener(queues = MQConfig.CANCEL_CREDIT_CARD_TRANSACTION)
    public void listener(CreditCardTransactionCancelEvent event) {


        log.info(event.getTransactionId() + " Transaction İptal Edildi");
        transactionService.cancelTransaction(event.getTransactionId());

    }

    @RabbitListener(queues = MQConfig.CANCEL_HGS_TRANSACTION)
    public void listener(HgsTransactionCancelEvent event) {


        log.info(event.getTransactionId() + " Transaction İptal Edildi");
        transactionService.cancelTransaction(event.getTransactionId());

    }


    @RabbitListener(queues = MQConfig.CREATE_ACCOUNT_DONE)
    public void listener(AccountCreateDoneEvent event) throws AccountNotFoundException {
        log.info(event + " Hesap Kaydı Tamamlandı ");
        transactionService.updateAccountCreationAsDone(event);


    }

    @RabbitListener(queues = MQConfig.CREATE_FLEX_ACC_DONE)
    public void listener(FlexibleAccCreateDone event) throws AccountNotFoundException {


        log.info(event + " Flexible Account Kaydı Tamamlandı");

        try {
            transactionService.updateFlexibleAccountCreationAsDone(event);
        } catch (Exception e) {
            log.error("Cannot create account, reason: {}", e.getMessage());
        }


    }

    @RabbitListener(queues = MQConfig.CREATE_CREDIT_DONE)
    public void listener(CreditCreateDone event) {
        try {
            transactionService.updateCreditCreationAsDone(event);
            log.info(event + " CREDIT Kaydı Tamamlandı");
        } catch (Exception e) {
            log.error("Cannot create CREDIT, reason: {}", e.getMessage());
        }
    }

    @RabbitListener(queues = MQConfig.CREATE_CREDIT_CARD_DONE)
    public void listener(CreditCardCreateDone event) {
        try {
            transactionService.updateCreditCardCreationAsDone(event);
            log.info(event + " CREDIT Card Kaydı Tamamlandı");
        } catch (Exception e) {
            log.error("Cannot create CREDIT CARD, reason: {}", e.getMessage());
        }
    }



    @RabbitListener(queues = MQConfig.CREATE_HGS_DONE)
    public void listener(HgsCreateDone event) {
        try {
            transactionService.updateHgsCreationAsDone(event);
            log.info(event + " HGS Kaydı Tamamlandı");
        } catch (Exception e) {
            log.error("Cannot create HGS, reason: {}", e.getMessage());
        }
    }
}
