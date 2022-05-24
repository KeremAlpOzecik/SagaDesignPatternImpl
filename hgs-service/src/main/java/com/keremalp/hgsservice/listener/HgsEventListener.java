package com.keremalp.hgsservice.listener;


import com.keremalp.hgsservice.config.MQConfig;
import com.keremalp.hgsservice.dto.events.cancel.HgsCancelEvent;
import com.keremalp.hgsservice.dto.events.cancel.HgsTransactionCancelEvent;
import com.keremalp.hgsservice.dto.events.done.HgsCreateDoneEvent;
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
public class HgsEventListener {
    private final RabbitTemplate rabbitTemplate;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void creditOpen(HgsCreateDoneEvent event) {
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_HGS_DONE_ROUTING_KEY, event);

        log.info("HGS Açma İşlemi Başarılı --> "+event);


    }


    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onAccountCancelledEvent(HgsCancelEvent event) {

        log.warn("Rollback İşlemi Gerçekleşti --> "+event);
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_HGS_ROUTING_KEY_CANCEL,event);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onAccountCancelledEvent(HgsTransactionCancelEvent event) {

        log.warn("Rollback İşlemi Gerçekleşti --> "+event);
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.HGS_TRANSACTION_CANCEL_ROUTING_KEY,event);
    }


}