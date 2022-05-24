package com.keremalp.managementservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String CREATE_NOTIFICATION = "create_notification_queue";
    public static final String CREATE_NOTIFICATION_ROUTING_KEY = "create_notification_routingKey";






    public static final String CANCEL_CREDIT_TRANSACTION = "cancel_credit_transaction_queue";
    public static final String CREDIT_TRANSACTION_CANCEL_ROUTING_KEY = "credit_transaction_cancel_routingKey";

    public static final String CANCEL_CREDIT_CARD_TRANSACTION = "cancel_credit_card_transaction_queue";
    public static final String CREDIT_CARD_TRANSACTION_CANCEL_ROUTING_KEY = "credit_card_transaction_cancel_routingKey";

    public static final String CANCEL_HGS_TRANSACTION = "cancel_hgs_transaction_queue";
    public static final String HGS_TRANSACTION_CANCEL_ROUTING_KEY = "hgs_transaction_cancel_routingKey";


    public static final String CANCEL_TRANSACTION = "transaction_queue";
    public static final String ROUTING_KEY_TRANSACTION_CANCEL = "transaction_cancel_routingKey";

    public static final String CANCEL_ACCOUNT_TRANSACTION = "cancel_account_transaction_queue";
    public static final String ROUTING_KEY_ACCOUNT_TRANSACTION_CANCEL = "account_transaction_cancel_routingKey";

    public static final String CREATE_CIF = "message_queue";
    public static final String CREATE_CIF_DONE = "message_queue_done";
    public static final String CREATE_CIF_CANCEL = "message_queue_cancel";

    public static final String CREATE_ACCOUNT = "message_queue_account";
    public static final String CREATE_ACCOUNT_DONE = "message_queue_account_done";
    public static final String CREATE_ACCOUNT_CANCEL = "message_queue_account_cancel";

    public static final String CREATE_ACCOUNT_CUSTOMER = "message_queue_account_customer";

    public static final String CREATE_FLEX_ACC = "message_queue_flex_account";
    public static final String CREATE_FLEXIBLE_ACCOUNT_CUSTOMER = "message_queue_flexible_account_customer";
    public static final String CREATE_FLEXIBLE_ACCOUNT_ACCOUNT = "message_queue_flexible_account_account";
    public static final String CREATE_FLEXIBLE_ACCOUNT_FLEX_ACCOUNT = "message_queue_flexible_account_flex_account";
    public static final String CREATE_FLEX_ACC_DONE = "message_queue_flex_account_done";
    public static final String CREATE_FLEX_ACC_CANCEL = "message_queue_flex_account_cancel";

    public static final String CREATE_CREDIT = "create_credit_message_queue";
    public static final String CREATE_CREDIT_FROM_CUSTOMER = "message_queue_create_credit_from_customer";
    public static final String CREATE_CREDIT_FROM_ACCOUNT= "create_credit_from_account_message_queue";
    public static final String CREATE_CREDIT_CREDIT = "message_queue_credit_credit";
    public static final String CREATE_CREDIT_DONE = "message_queue_credit_done";
    public static final String CREATE_CREDIT_CANCEL = "message_queue_credit_cancel";

    public static final String CREATE_CREDIT_CARD = "create_credit_card_message_queue";
    public static final String CREATE_CREDIT_CARD_FROM_CUSTOMER = "message_queue_create_credit_card_from_customer";
    public static final String CREATE_CREDIT_CARD_FROM_ACCOUNT= "create_credit_card_from_account_message_queue";
    public static final String CREATE_CREDIT_CARD_CREDIT_CARD = "message_queue_credit_card_credit_card";
    public static final String CREATE_CREDIT_CARD_DONE = "message_queue_credit_card_done";
    public static final String CREATE_CREDIT_CARD_CANCEL = "message_queue_credit_card_cancel";

    public static final String CREATE_HGS = "create_hgs_message_queue";
    public static final String CREATE_HGS_FROM_CUSTOMER = "message_queue_create_hgs_from_customer";
    public static final String CREATE_HGS_FROM_ACCOUNT= "create_hgs_from_account_message_queue";
    public static final String CREATE_HGS_HGS = "message_queue_hgs_hgs";
    public static final String CREATE_HGS_DONE = "message_queue_hgs_done";
    public static final String CREATE_HGS_CANCEL = "message_queue_hgs_cancel";

    public static final String EXCHANGE = "message_exchange";

    public static final String ROUTING_KEY = "message_routingKey";
    public static final String ROUTING_KEY_DONE = "message_routingKey_done";
    public static final String ROUTING_KEY_CANCEL = "message_routingKey_cancel";
    public static final String ACCOUNT_ROUTING_KEY = "message_account_routingKey";
    public static final String ACCOUNT_ROUTING_KEY_DONE = "message_account_routingKey_done";
    public static final String ACCOUNT_ROUTING_KEY_CANCEL = "message_account_routingKey_cancel";
    public static final String ACCOUNT_CUSTOMER_ROUTING_KEY= "message_account_customer_routingKey";

    public static final String FLEX_ACC_ROUTING_KEY= "message_flex_acc_routingKey";
    public static final String FLEXIBLE_ACCOUNT_CUSTOMER_ROUTING_KEY= "message_flexible_account_customer_routingKey";
    public static final String FLEXIBLE_ACCOUNT_ACCOUNT_ROUTING_KEY= "message_flexible_account_account_routingKey";
    public static final String FLEXIBLE_ACCOUNT_FLEX_ACCOUNT_ROUTING_KEY= "message_flexible_account_flex_account_routingKey";
    public static final String FLEX_ACC_ROUTING_KEY_CANCEL= "message_flex_acc_routingKey_cancel";
    public static final String FLEX_ACC_DONE_ROUTING_KEY= "message_flex_acc_done_routingKey";

    public static final String CREATE_CREDIT_ROUTING_KEY = "create_credit_message_routingKey";
    public static final String CREATE_CREDIT_FROM_CUSTOMER_ROUTING_KEY = "create_credit_from_customer_message_routingKey";
    public static final String CREATE_CREDIT_FROM_ACCOUNT_ROUTING_KEY = "create_credit_from_account_message_routingKey";
    public static final String CREATE_CREDIT_CREDIT_ROUTING_KEY= "message_credit_credit_routingKey";
    public static final String CREATE_CREDIT_ROUTING_KEY_CANCEL= "message_credit_routingKey_cancel";
    public static final String CREATE_CREDIT_DONE_ROUTING_KEY= "message_credit_done_routingKey";

    public static final String CREATE_CREDIT_CARD_ROUTING_KEY = "create_credit_card_message_routingKey";
    public static final String CREATE_CREDIT_CARD_FROM_CUSTOMER_ROUTING_KEY = "create_credit_card_from_customer_message_routingKey";
    public static final String CREATE_CREDIT_CARD_FROM_ACCOUNT_ROUTING_KEY = "create_credit_card_from_account_message_routingKey";
    public static final String CREATE_CREDIT_CARD_CREDIT_CARD_ROUTING_KEY= "message_credit_card_credit_card_routingKey";
    public static final String CREATE_CREDIT_CARD_ROUTING_KEY_CANCEL= "message_credit_card_routingKey_cancel";
    public static final String CREATE_CREDIT_CARD_DONE_ROUTING_KEY= "message_credit_card_done_routingKey";

    public static final String CREATE_HGS_ROUTING_KEY = "create_hgs_message_routingKey";
    public static final String CREATE_HGS_FROM_CUSTOMER_ROUTING_KEY = "create_hgs_from_customer_message_routingKey";
    public static final String CREATE_HGS_FROM_ACCOUNT_ROUTING_KEY = "create_hgs_from_account_message_routingKey";
    public static final String CREATE_HGS_HGS_ROUTING_KEY= "message_hgs_hgs_routingKey";
    public static final String CREATE_HGS_ROUTING_KEY_CANCEL= "message_hgs_routingKey_cancel";
    public static final String CREATE_HGS_DONE_ROUTING_KEY= "message_hgs_done_routingKey";




    @Bean
    public Queue queue() {
        return  new Queue(CREATE_CIF);
    }
    @Bean
    public Queue queue2() {
        return  new Queue(CREATE_CIF_DONE);
    }
    @Bean
    public Queue queue3() {
        return  new Queue(CREATE_ACCOUNT);
    }
    @Bean
    public Queue queue4() {
        return  new Queue(CREATE_ACCOUNT_DONE);
    }
    @Bean
    public Queue queue5() {
        return  new Queue(CREATE_ACCOUNT_CUSTOMER);
    }
    @Bean
    public Queue queue6() {
        return  new Queue(CREATE_ACCOUNT_CANCEL);
    }
    @Bean
    public Queue queue7() {
        return  new Queue(CREATE_CIF_CANCEL);
    }
    @Bean
    public Queue queue8() {
        return  new Queue(CANCEL_TRANSACTION);
    }
    @Bean
    public Queue queue9() {
        return  new Queue(CREATE_FLEX_ACC);
    }
    @Bean
    public Queue queue10() {
        return  new Queue(CREATE_FLEX_ACC_DONE);
    }
    @Bean
    public Queue queue11() {
        return  new Queue(CREATE_FLEXIBLE_ACCOUNT_CUSTOMER);
    }
    @Bean
    public Queue queue12() {
        return  new Queue(CREATE_FLEXIBLE_ACCOUNT_ACCOUNT);
    }
    @Bean
    public Queue queue13() {
        return  new Queue(CREATE_FLEXIBLE_ACCOUNT_FLEX_ACCOUNT);
    }
    @Bean
    public Queue queue14() {
        return  new Queue(CREATE_FLEX_ACC_CANCEL);
    }
    @Bean
    public Queue queue15() {
        return  new Queue(CANCEL_ACCOUNT_TRANSACTION);
    }
    @Bean
    public Queue queue16() {
        return  new Queue(CREATE_CREDIT);
    }
    @Bean
    public Queue queue17() {
        return  new Queue(CREATE_CREDIT_FROM_ACCOUNT);
    }
    @Bean
    public Queue queue18() {
        return  new Queue(CREATE_CREDIT_FROM_CUSTOMER);
    }
    @Bean
    public Queue queue19() {
        return  new Queue(CREATE_CREDIT_CREDIT);
    }
    @Bean
    public Queue queue20() {
        return  new Queue(CREATE_CREDIT_DONE);
    }
    @Bean
    public Queue queue21() {
        return  new Queue(CREATE_CREDIT_CANCEL);
    }
    @Bean
    public Queue queue22() {
        return  new Queue(CANCEL_CREDIT_TRANSACTION);
    }

    @Bean
    public Queue queue23() {
        return  new Queue(CREATE_CREDIT_CARD);
    }
    @Bean
    public Queue queue24() {
        return  new Queue(CREATE_CREDIT_CARD_FROM_ACCOUNT);
    }
    @Bean
    public Queue queue25() {
        return  new Queue(CREATE_CREDIT_CARD_FROM_CUSTOMER);
    }
    @Bean
    public Queue queue26() {
        return  new Queue(CREATE_CREDIT_CARD_CREDIT_CARD);
    }
    @Bean
    public Queue queue27() {
        return  new Queue(CREATE_CREDIT_CARD_DONE);
    }
    @Bean
    public Queue queue28() {
        return  new Queue(CREATE_CREDIT_CARD_CANCEL);
    }
    @Bean
    public Queue queue29() {
        return  new Queue(CANCEL_CREDIT_CARD_TRANSACTION);
    }

    @Bean
    public Queue queue30() {
        return  new Queue(CREATE_HGS);
    }
    @Bean
    public Queue queue31() {
        return  new Queue(CREATE_HGS_FROM_ACCOUNT);
    }
    @Bean
    public Queue queue32() {
        return  new Queue(CREATE_HGS_FROM_CUSTOMER);
    }
    @Bean
    public Queue queue33() {
        return  new Queue(CREATE_HGS_HGS);
    }
    @Bean
    public Queue queue34() {
        return  new Queue(CREATE_HGS_DONE);
    }
    @Bean
    public Queue queue35() {
        return  new Queue(CREATE_HGS_CANCEL);
    }
    @Bean
    public Queue queue36() {
        return  new Queue(CANCEL_HGS_TRANSACTION);
    }
    @Bean
    public Queue queue37() {
        return  new Queue(CREATE_NOTIFICATION);
    }


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }



    @Bean
    public Binding binding(@Qualifier("queue")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }
    @Bean
    public Binding binding2(@Qualifier("queue2")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY_DONE);
    }
    @Bean
    public Binding binding3(@Qualifier("queue3")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ACCOUNT_ROUTING_KEY);
    }
    @Bean
    public Binding binding4(@Qualifier("queue4")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ACCOUNT_ROUTING_KEY_DONE);
    }
    @Bean
    public Binding binding5(@Qualifier("queue5")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ACCOUNT_CUSTOMER_ROUTING_KEY);
    }
    @Bean
    public Binding binding6(@Qualifier("queue6")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ACCOUNT_ROUTING_KEY_CANCEL);
    }
    @Bean
    public Binding binding7(@Qualifier("queue7")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY_CANCEL);
    }
    @Bean
    public Binding binding8(@Qualifier("queue8")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY_TRANSACTION_CANCEL);
    }
    @Bean
    public Binding binding9(@Qualifier("queue9")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(FLEX_ACC_ROUTING_KEY);
    }
    @Bean
    public Binding binding10(@Qualifier("queue10")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(FLEX_ACC_DONE_ROUTING_KEY);
    }
    @Bean
    public Binding binding11(@Qualifier("queue11")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(FLEXIBLE_ACCOUNT_CUSTOMER_ROUTING_KEY);
    }
    @Bean
    public Binding binding12(@Qualifier("queue12")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(FLEXIBLE_ACCOUNT_ACCOUNT_ROUTING_KEY);
    }
    @Bean
    public Binding binding13(@Qualifier("queue13")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(FLEXIBLE_ACCOUNT_FLEX_ACCOUNT_ROUTING_KEY);
    }
    @Bean
    public Binding binding14(@Qualifier("queue14")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(FLEX_ACC_ROUTING_KEY_CANCEL);
    }
    @Bean
    public Binding binding15(@Qualifier("queue15")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY_ACCOUNT_TRANSACTION_CANCEL);
    }
    @Bean
    public Binding binding16(@Qualifier("queue16")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_CREDIT_ROUTING_KEY);
    }
    @Bean
    public Binding binding17(@Qualifier("queue17")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_CREDIT_FROM_ACCOUNT_ROUTING_KEY);
    }
    @Bean
    public Binding binding18(@Qualifier("queue18")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_CREDIT_FROM_CUSTOMER_ROUTING_KEY);
    }
    @Bean
    public Binding binding19(@Qualifier("queue19")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_CREDIT_CREDIT_ROUTING_KEY);
    }
    @Bean
    public Binding binding20(@Qualifier("queue20")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_CREDIT_DONE_ROUTING_KEY);
    }
    @Bean
    public Binding binding21(@Qualifier("queue21")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_CREDIT_ROUTING_KEY_CANCEL);
    }
    @Bean
    public Binding binding22(@Qualifier("queue22")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREDIT_TRANSACTION_CANCEL_ROUTING_KEY);
    }

    @Bean
    public Binding binding23(@Qualifier("queue23")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_CREDIT_CARD_ROUTING_KEY);
    }
    @Bean
    public Binding binding24(@Qualifier("queue24")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_CREDIT_CARD_FROM_ACCOUNT_ROUTING_KEY);
    }
    @Bean
    public Binding binding25(@Qualifier("queue25")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_CREDIT_CARD_FROM_CUSTOMER_ROUTING_KEY);
    }
    @Bean
    public Binding binding26(@Qualifier("queue26")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_CREDIT_CARD_CREDIT_CARD_ROUTING_KEY);
    }
    @Bean
    public Binding binding27(@Qualifier("queue27")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_CREDIT_CARD_DONE_ROUTING_KEY);
    }
    @Bean
    public Binding binding28(@Qualifier("queue28")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_CREDIT_CARD_ROUTING_KEY_CANCEL);
    }
    @Bean
    public Binding binding29(@Qualifier("queue29")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREDIT_CARD_TRANSACTION_CANCEL_ROUTING_KEY);
    }

    @Bean
    public Binding binding30(@Qualifier("queue30")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_HGS_ROUTING_KEY);
    }
    @Bean
    public Binding binding31(@Qualifier("queue31")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_HGS_FROM_ACCOUNT_ROUTING_KEY);
    }
    @Bean
    public Binding binding32(@Qualifier("queue32")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_HGS_FROM_CUSTOMER_ROUTING_KEY);
    }
    @Bean
    public Binding binding33(@Qualifier("queue33")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_HGS_HGS_ROUTING_KEY);
    }
    @Bean
    public Binding binding34(@Qualifier("queue34")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_HGS_DONE_ROUTING_KEY);
    }
    @Bean
    public Binding binding35(@Qualifier("queue35")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_HGS_ROUTING_KEY_CANCEL);
    }
    @Bean
    public Binding binding36(@Qualifier("queue36")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(HGS_TRANSACTION_CANCEL_ROUTING_KEY);
    }
    @Bean
    public Binding binding37(@Qualifier("queue37")Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CREATE_NOTIFICATION_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return  template;
    }
}