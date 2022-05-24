package com.keremalp.felxibleaccountservice.service;

import com.keremalp.felxibleaccountservice.converter.FlexAccountMapper;
import com.keremalp.felxibleaccountservice.dao.FlexAccDao;
import com.keremalp.felxibleaccountservice.dto.events.cancel.FlexAccountCancelEvent;
import com.keremalp.felxibleaccountservice.dto.events.cancel.FlexAccountTransactionCancelEvent;
import com.keremalp.felxibleaccountservice.dto.events.done.FlexAccountCreateDoneEvent;
import com.keremalp.felxibleaccountservice.dto.product.CreateFlexibleAccountDto;
import com.keremalp.felxibleaccountservice.entity.FlexAcc;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Log4j2
@Service
@RequiredArgsConstructor
public class FlexAccService {
    private final FlexAccDao flexAccDao;
    private final ApplicationEventPublisher publisher;

    @Transactional()
    public void createFlexibleAccount(CreateFlexibleAccountDto createFlexibleAccountDto, String transactionId)  {

        FlexAcc flexAcc = FlexAccountMapper.INSTANCE.ctoB(createFlexibleAccountDto);
        flexAcc.setAccountLimit(1000L);

        //Burada bi yerde kontrol yapılıp hata verilecek ki kayıt olmasın
        if(  flexAcc.getAccountLimit()==1000L)
         {  publishCanceledFlexAccountCreateDirectToManagerService(flexAcc,transactionId);
            throw new IllegalStateException("Flex Account Limit is not approved");
        }


        flexAcc.setFlexAccStatus(FlexAcc.FlexAccStatus.OPEN);
        FlexAcc save = flexAccDao.save(flexAcc);
        publishFlexAccountCreateDone(save,transactionId);


    }
    @Transactional()
    public void createFlexibleAccountForNewCustomer(CreateFlexibleAccountDto createFlexibleAccountDto, String transactionId)  {

        FlexAcc flexAcc = FlexAccountMapper.INSTANCE.ctoB(createFlexibleAccountDto);
        flexAcc.setAccountLimit(1000L);

        //Burada bi yerde kontrol yapılıp hata verilecek ki kayıt olmasın
        if(  flexAcc.getAccountLimit()==1000L)
        {
            publishCanceledFlexAccountCreate(flexAcc,transactionId);
            throw new IllegalStateException("Flex Account Limit is not approved");
        }


        flexAcc.setFlexAccStatus(FlexAcc.FlexAccStatus.OPEN);
        FlexAcc save = flexAccDao.save(flexAcc);
        publishFlexAccountCreateDone(save,transactionId);


    }
    private void publishFlexAccountCreateDone(FlexAcc account, String transactionId) {
        CreateFlexibleAccountDto createAccountRequestDto = FlexAccountMapper.INSTANCE.accountToCreateFlexAccountRequestDto(account);
        FlexAccountCreateDoneEvent event = new FlexAccountCreateDoneEvent((transactionId),createAccountRequestDto,new Date());
        publisher.publishEvent(event);
    }
    private void publishCanceledFlexAccountCreate(FlexAcc account, String transactionId) {
        CreateFlexibleAccountDto createAccountRequestDto = FlexAccountMapper.INSTANCE.accountToCreateFlexAccountRequestDto(account);
        FlexAccountCancelEvent event = new FlexAccountCancelEvent(transactionId,createAccountRequestDto,new Date());
        publisher.publishEvent(event);

    }
    private void publishCanceledFlexAccountCreateDirectToManagerService(FlexAcc account, String transactionId) {
        CreateFlexibleAccountDto createAccountRequestDto = FlexAccountMapper.INSTANCE.accountToCreateFlexAccountRequestDto(account);
        FlexAccountTransactionCancelEvent event = new FlexAccountTransactionCancelEvent(transactionId,createAccountRequestDto,new Date());
        publisher.publishEvent(event);

    }

}
