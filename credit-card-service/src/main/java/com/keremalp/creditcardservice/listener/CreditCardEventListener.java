package com.keremalp.creditcardservice.listener;


import com.keremalp.creditcardservice.config.MQConfig;
import com.keremalp.creditcardservice.dto.events.cancel.CreditCardCancelEvent;
import com.keremalp.creditcardservice.dto.events.cancel.CreditCardTransactionCancelEvent;
import com.keremalp.creditcardservice.dto.events.done.CreditCardCreateDoneEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Log4j2
@Component
@RequiredArgsConstructor
public class CreditCardEventListener {
    private final RabbitTemplate rabbitTemplate;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void creditOpen(CreditCardCreateDoneEvent event) {
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_CREDIT_CARD_DONE_ROUTING_KEY, event);

        log.info("CREDIT CARD Açma İşlemi Başarılı --> "+event);


    }


    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onAccountCancelledEvent(CreditCardCancelEvent event) {

        log.warn("Rollback İşlemi Gerçekleşti --> "+event);
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_CREDIT_CARD_ROUTING_KEY_CANCEL,event);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onAccountCancelledEvent(CreditCardTransactionCancelEvent event) {

        log.warn("Rollback İşlemi Gerçekleşti --> "+event);
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREDIT_CARD_TRANSACTION_CANCEL_ROUTING_KEY,event);
    }


}