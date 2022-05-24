package com.keremalp.managementservice.listener;

import com.keremalp.managementservice.config.MQConfig;
import com.keremalp.managementservice.dto.events.create.*;

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
public class TransactionCreateEventListener {
    private final RabbitTemplate rabbitTemplate;
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void cifOpen(CifOpenCreatedEvent event) {
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, event);

        log.info("Customer Create Event --> "+event);


    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void accountOpen(AccountCreateEvent event) {

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ACCOUNT_ROUTING_KEY, event);

        log.info("Account Create Event --> "+event);


    }
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void accountOpen(CreateFlexibleAccountForNotExistAccountEvent event) {

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.FLEXIBLE_ACCOUNT_ACCOUNT_ROUTING_KEY, event);

        log.info("Account Create Event For Flexible Account --> "+event);


    }
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void cifOpen(CreateAccountForNotExistCustomerEvent event) {

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ACCOUNT_CUSTOMER_ROUTING_KEY, event);

        log.info("Customer Create Event For Account Creating --> "+event);


    }
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void cifOpen(CreateFlexibleAccountForNotExistCustomerEvent event) {

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.FLEXIBLE_ACCOUNT_CUSTOMER_ROUTING_KEY, event);

        log.info("Customer Create For Flexible Account Creating --> "+event);


    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void flexAccountOpen(FlexibleAccountCreateEvent event) {

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.FLEX_ACC_ROUTING_KEY, event);

        log.info("Flexible Account Create Event --> "+event);


    }
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void flexAccountOpen(CreditCreateEvent event) {

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_CREDIT_ROUTING_KEY, event);

        log.info("CREDIT Create Event --> "+event);


    }
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void creditOpen(CreateCreditForNotExistCustomerEvent event) {

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_CREDIT_FROM_CUSTOMER_ROUTING_KEY, event);

        log.info("Customer Create For Credit Creating --> "+event);


    }
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void creditOpen(CreateCreditForNotExistAccountEvent event) {

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_CREDIT_FROM_ACCOUNT_ROUTING_KEY, event);

        log.info("Account Create For Credit Creating --> "+event);


    }




    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void creditCardOpen(CreditCardCreateEvent event) {

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_CREDIT_CARD_ROUTING_KEY, event);

        log.info("CREDIT CARD Create Event --> "+event);


    }
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void creditCardOpen(CreateCreditCardForNotExistCustomerEvent event) {

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_CREDIT_CARD_FROM_CUSTOMER_ROUTING_KEY, event);

        log.info("Customer Create For Credit CARD Creating --> "+event);


    }
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void creditCardOpen(CreateCreditCardForNotExistAccountEvent event) {

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_CREDIT_CARD_FROM_ACCOUNT_ROUTING_KEY, event);

        log.info("Account Create For Credit CARD Creating --> "+event);


    }


    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void hgsOpen(HgsCreateEvent event) {

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_HGS_ROUTING_KEY, event);

        log.info("HGS Create Event --> "+event);


    }
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void hgsOpen(CreateHgsForNotExistCustomerEvent event) {

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_HGS_FROM_CUSTOMER_ROUTING_KEY, event);

        log.info("Customer Create For HGS Creating --> "+event);


    }
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void hgsOpen(CreateHgsForNotExistAccountEvent event) {

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_HGS_FROM_ACCOUNT_ROUTING_KEY, event);

        log.info("Account Create For HGS Creating --> "+event);


    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void hgsOpen(CreateNotificationEvent event) {

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_NOTIFICATION_ROUTING_KEY, event);

        log.info("Sözleşme E posta adresine gönderilecektir --> "+event);


    }



}