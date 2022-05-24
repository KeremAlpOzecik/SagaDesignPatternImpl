package com.keremalp.accountservice.listener;

import com.keremalp.accountservice.config.MQConfig;
import com.keremalp.accountservice.dto.events.cancel.AccountCancelEvent;
import com.keremalp.accountservice.dto.events.cancel.AccountTransactionCancelEvent;
import com.keremalp.accountservice.dto.events.done.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import static com.keremalp.accountservice.config.MQConfig.*;

@Log4j2
@Component
@RequiredArgsConstructor
public class AccountCreateEventListener {
    private final RabbitTemplate rabbitTemplate;
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void cifOpen(AccountCreateDoneEvent event) {
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ACCOUNT_ROUTING_KEY_DONE, event);

        log.info("Acount Açma İşlemi Yapıldı --> "+event);


    }
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void flexAccOpen(AccountCreateDoneEventForFlexibleAccount event) {
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.FLEXIBLE_ACCOUNT_FLEX_ACCOUNT_ROUTING_KEY, event);

        log.info("Flex Account Açma İşlemi İçin Account Açma İşlemi Yapıldı --> "+event);


    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void creditOpen(AccountCreateDoneEventForCredit event) {
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, CREATE_CREDIT_CREDIT_ROUTING_KEY, event);

        log.info("Credit Açma İşlemi İçin Account Açma İşlemi Yapıldı --> "+event);


    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void creditOpen(AccountCreateDoneEventForCreditCard event) {
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, CREATE_CREDIT_CARD_CREDIT_CARD_ROUTING_KEY, event);

        log.info("Credit Card Açma İşlemi İçin Account Açma İşlemi Yapıldı --> "+event);


    }


    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void hgsOpen(AccountCreateDoneEventForHgs event) {
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, CREATE_HGS_HGS_ROUTING_KEY, event);

        log.info("Hgs Açma İşlemi İçin Account Açma İşlemi Yapıldı --> "+event);


    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onAccountCancelledEvent(AccountCancelEvent event) {

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ACCOUNT_ROUTING_KEY_CANCEL,event);
        log.info("Rollback İşlemi Gerçekleşti --> "+event);
    }
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onFlexibleAccountTransactionFailEvent(AccountCancelEvent event) {
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, ACCOUNT_ROUTING_KEY_CANCEL, event);
        log.info("Rollback İşlemi Gerçekleşti --> "+event);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onFlexibleAccountTransactionFailEvent(AccountTransactionCancelEvent event) {
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, ROUTING_KEY_ACCOUNT_TRANSACTION_CANCEL, event);
        log.info("Rollback İşlemi Gerçekleşti --> "+event);
    }
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onFlexibleAccountTransactionFailEvent2(AccountTransactionCancelEvent event) {
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, ROUTING_KEY_ACCOUNT_TRANSACTION_CANCEL, event);
        log.info("Rollback İşlemi Gerçekleşti --> "+event);
    }

}