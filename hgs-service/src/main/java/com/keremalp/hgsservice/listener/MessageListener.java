package com.keremalp.hgsservice.listener;


import com.keremalp.hgsservice.config.MQConfig;
import com.keremalp.hgsservice.dto.events.create.CreateHgsEvent;
import com.keremalp.hgsservice.dto.events.create.CreateHgsEventFromAccount;
import com.keremalp.hgsservice.dto.product.CreateHgsDto;
import com.keremalp.hgsservice.service.HgsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class MessageListener {
    private final HgsService hgsService;

    @RabbitListener(queues = MQConfig.CREATE_HGS)//CREATE_HGS
    public void listener(CreateHgsEvent event) {


        log.info("HGS açmak için talep alındı "+event);

        try {
            hgsService.createHgs(event.getHgs(),event.getTransactionId());
        } catch (Exception e) {
            log.error("Cannot create hgs, reason: {}", e.getMessage());
        }




    }
    @RabbitListener(queues = MQConfig.CREATE_HGS_HGS)
    public void listener2(CreateHgsEventFromAccount event) {


        log.info("Yeni Müşteri için HGS Açma Talebi Alındı "+event);

        try {
            CreateHgsDto hgsDto = new CreateHgsDto();
            hgsDto.setAccountNumber(event.getAccount().getAccountNumber());
            hgsDto.setAddress(event.getAccount().getAddress());
            hgsDto.setCustomerName(event.getAccount().getCustomerName());
            hgsDto.setCustomerSurname(event.getAccount().getCustomerSurname());
            hgsDto.setPhoneNumber(event.getAccount().getPhoneNumber());
            hgsDto.setCustomerNumber(event.getAccount().getCustomerNumber());
            hgsDto.setCustomerEmail(event.getAccount().getCustomerEmail());
            hgsDto.setCarPlate(event.getAccount().getCarPlate());
            hgsService.createHgsForNewCustomer(hgsDto,event.getTransactionId());
        } catch (Exception e) {
            log.error("Cannot create account, reason: {}", e.getMessage());
        }




    }




}
