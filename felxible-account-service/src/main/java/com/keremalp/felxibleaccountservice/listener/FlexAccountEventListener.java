package com.keremalp.felxibleaccountservice.listener;


import com.keremalp.felxibleaccountservice.config.MQConfig;
import com.keremalp.felxibleaccountservice.dto.events.cancel.FlexAccountCancelEvent;
import com.keremalp.felxibleaccountservice.dto.events.cancel.FlexAccountTransactionCancelEvent;
import com.keremalp.felxibleaccountservice.dto.events.done.FlexAccountCreateDoneEvent;
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
public class  FlexAccountEventListener {
    private final RabbitTemplate rabbitTemplate;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void cifOpen(FlexAccountCreateDoneEvent event) {
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.FLEX_ACC_DONE_ROUTING_KEY, event);

        log.info("Flex Account Açma İşlemi Başarılı --> "+event);


    }


    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onAccountCancelledEvent(FlexAccountCancelEvent event) {

        log.warn("Rollback İşlemi Gerçekleşti --> "+event);
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.FLEX_ACC_ROUTING_KEY_CANCEL,event);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onAccountCancelledEvent(FlexAccountTransactionCancelEvent event) {

        log.warn("Rollback İşlemi Gerçekleşti --> "+event);
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY_TRANSACTION_CANCEL,event);
    }


}