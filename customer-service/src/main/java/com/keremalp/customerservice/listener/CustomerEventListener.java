package com.keremalp.customerservice.listener;

import com.keremalp.customerservice.config.MQConfig;
import com.keremalp.customerservice.dto.events.cancel.CancelTransactionEvent;
import com.keremalp.customerservice.dto.events.create.*;
import com.keremalp.customerservice.dto.events.done.CifOpenCreatedDoneEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Log4j2
@RequiredArgsConstructor
public class CustomerEventListener {

    private final RabbitTemplate template;


    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onTransactionDoneEvent(CifOpenCreatedDoneEvent event) {
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY_DONE, event);
        log.info("Customer Created " + event);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onAccountTransactionEvent(CreateAccountForNotExistCustomerEvent event) {
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ACCOUNT_CUSTOMER_ROUTING_KEY, event);
        log.info("Hesap Açma İşlemi İçin Müşteri Kaydı Tamamlandı " + event);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onAccountTransactionEvent(CreateFlexibleAccountForNotExistCustomerEvent event) {
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.FLEXIBLE_ACCOUNT_ACCOUNT_ROUTING_KEY, event);
        log.info("Flex Account Açma İşlemi İçin Müşteri Kaydı Tamamlandı" + event);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onAccountTransactionEvent(CreateCreditForNotExistCustomerEvent event) {
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_CREDIT_FROM_ACCOUNT_ROUTING_KEY, event);
        log.info("Credit Açma İşlemi İçin Müşteri Kaydı Tamamlandı" + event);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onAccountTransactionEvent(CreateCreditCardForNotExistCustomerEvent event) {
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_CREDIT_CARD_FROM_ACCOUNT_ROUTING_KEY, event);
        log.info("Credit Card Açma İşlemi İçin Müşteri Kaydı Tamamlandı" + event);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onAccountTransactionEvent(CreateHgsForNotExistCustomerEvent event) {
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_HGS_FROM_ACCOUNT_ROUTING_KEY, event);
        log.info("HGS Açma İşlemi İçin Müşteri Kaydı Tamamlandı" + event);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onAccountTransactionFailEvent(CancelTransactionEvent event) {
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY_CANCEL, event);
        log.info("Müşteri Kaydı Silindi" + event);
    }


}
