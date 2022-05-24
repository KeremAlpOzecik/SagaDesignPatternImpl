package com.keremalp.felxibleaccountservice.config;

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

    public static final String CREATE_CREDIT = "create_credit_message_queue";
    public static final String CREATE_CREDIT_FROM_ACCOUNT= "create_credit_from_account_message_queue";
    public static final String CREATE_CREDIT_ROUTING_KEY = "create_credit_message_routingKey";
    public static final String CREATE_CREDIT_FROM_ACCOUNT_ROUTING_KEY = "create_credit_from_account_message_routingKey";

    public static final String CANCEL_TRANSACTION = "transaction_queue";
    public static final String ROUTING_KEY_TRANSACTION_CANCEL = "transaction_cancel_routingKey";

    public static final String CANCEL_ACCOUNT_TRANSACTION = "cancel_account_transaction_queue";
    public static final String ROUTING_KEY_ACCOUNT_TRANSACTION_CANCEL = "account_transaction_cancel_routingKey";

    public static final String CREATE_CIF = "message_queue";
    public static final String CREATE_ACCOUNT = "message_queue_account";
    public static final String CREATE_CIF_DONE = "message_queue_done";
    public static final String CREATE_CIF_CANCEL = "message_queue_cancel";
    public static final String CREATE_ACCOUNT_DONE = "message_queue_account_done";
    public static final String CREATE_ACCOUNT_CANCEL = "message_queue_account_cancel";

    public static final String CREATE_ACCOUNT_CUSTOMER = "message_queue_account_customer";

    public static final String CREATE_FLEXIBLE_ACCOUNT_CUSTOMER = "message_queue_flexible_account_customer";
    public static final String CREATE_FLEXIBLE_ACCOUNT_ACCOUNT = "message_queue_flexible_account_account";
    public static final String CREATE_FLEXIBLE_ACCOUNT_FLEX_ACCOUNT = "message_queue_flexible_account_flex_account";

    public static final String CREATE_FLEX_ACC = "message_queue_flex_account";
    public static final String CREATE_FLEX_ACC_DONE = "message_queue_flex_account_done";
    public static final String CREATE_FLEX_ACC_CANCEL = "message_queue_flex_account_cancel";


    public static final String EXCHANGE = "message_exchange";

    public static final String ROUTING_KEY = "message_routingKey";
    public static final String ROUTING_KEY_DONE = "message_routingKey_done";
    public static final String ROUTING_KEY_CANCEL = "message_routingKey_cancel";
    public static final String ACCOUNT_ROUTING_KEY = "message_account_routingKey";
    public static final String ACCOUNT_ROUTING_KEY_DONE = "message_account_routingKey_done";
    public static final String ACCOUNT_ROUTING_KEY_CANCEL = "message_account_routingKey_cancel";


    public static final String ACCOUNT_CUSTOMER_ROUTING_KEY= "message_account_customer_routingKey";
    public static final String FLEXIBLE_ACCOUNT_CUSTOMER_ROUTING_KEY= "message_flexible_account_customer_routingKey";
    public static final String FLEXIBLE_ACCOUNT_ACCOUNT_ROUTING_KEY= "message_flexible_account_account_routingKey";
    public static final String FLEXIBLE_ACCOUNT_FLEX_ACCOUNT_ROUTING_KEY= "message_flexible_account_flex_account_routingKey";
    public static final String FLEX_ACC_ROUTING_KEY= "message_flex_acc_routingKey";
    public static final String FLEX_ACC_ROUTING_KEY_CANCEL= "message_flex_acc_routingKey_cancel";

    public static final String FLEX_ACC_DONE_ROUTING_KEY= "message_flex_acc_done_routingKey";



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