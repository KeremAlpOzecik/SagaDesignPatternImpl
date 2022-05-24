package com.keremalp.creditservice.listener;


import com.keremalp.creditservice.config.MQConfig;
import com.keremalp.creditservice.dto.events.cancel.CreditCancelEvent;
import com.keremalp.creditservice.dto.events.cancel.CreditTransactionCancelEvent;
import com.keremalp.creditservice.dto.events.done.CreditCreateDoneEvent;
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
public class CreditEventListener {
    private final RabbitTemplate rabbitTemplate;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void creditOpen(CreditCreateDoneEvent event) {
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_CREDIT_DONE_ROUTING_KEY, event);

        log.info("CREDIT Açma İşlemi Başarılı --> "+event);


    }


    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onAccountCancelledEvent(CreditCancelEvent event) {

        log.warn("Rollback İşlemi Gerçekleşti --> "+event);
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_CREDIT_ROUTING_KEY_CANCEL,event);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onAccountCancelledEvent(CreditTransactionCancelEvent event) {

        log.warn("Rollback İşlemi Gerçekleşti --> "+event);
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREDIT_TRANSACTION_CANCEL_ROUTING_KEY,event);
    }


}