package com.keremalp.hgsservice.service;

import com.keremalp.hgsservice.converter.HgsMapper;
import com.keremalp.hgsservice.dao.HgsDao;
import com.keremalp.hgsservice.dto.events.cancel.HgsCancelEvent;
import com.keremalp.hgsservice.dto.events.cancel.HgsTransactionCancelEvent;
import com.keremalp.hgsservice.dto.events.done.HgsCreateDoneEvent;
import com.keremalp.hgsservice.dto.product.CreateHgsDto;
import com.keremalp.hgsservice.entity.Hgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Log4j2
@Service
@RequiredArgsConstructor
public class HgsService {
    private final HgsDao hgsDao;



    private final ApplicationEventPublisher publisher;

    @Transactional()
    public void createHgs(CreateHgsDto createHgsDto, String transactionId) {


        Hgs hgs = HgsMapper.INSTANCE.creditDtoToHgs(createHgsDto);

        hgs.setHgsStatus(Hgs.HgsStatus.CREATE);
        Hgs save = hgsDao.save(hgs);
        publishHgsCreateDone(save, transactionId);


    }

    @Transactional()
    public void createHgsForNewCustomer(CreateHgsDto createHgsDto, String transactionId) {

        Hgs hgs = HgsMapper.INSTANCE.creditDtoToHgs(createHgsDto);


        hgs.setHgsStatus(Hgs.HgsStatus.CREATE);
        Hgs save = hgsDao.save(hgs);
        publishHgsCreateDone(save, transactionId);

    }

    private void publishHgsCreateDone(Hgs hgs, String transactionId) {
        CreateHgsDto createHgsDto = HgsMapper.INSTANCE.hgsToCreditDto(hgs);
        HgsCreateDoneEvent event = new HgsCreateDoneEvent((transactionId), createHgsDto, new Date());
        publisher.publishEvent(event);
    }

    private void publishCanceledHgsCreate(Hgs hgs, String transactionId) {
        CreateHgsDto createHgsDto = HgsMapper.INSTANCE.hgsToCreditDto(hgs);
        HgsCancelEvent event = new HgsCancelEvent(transactionId, createHgsDto, new Date());
        publisher.publishEvent(event);

    }

    private void publishCanceledHgsCreateDirectToManagerService(Hgs hgs, String transactionId) {
        CreateHgsDto createHgsDto = HgsMapper.INSTANCE.hgsToCreditDto(hgs);
        HgsTransactionCancelEvent event = new HgsTransactionCancelEvent(transactionId, createHgsDto, new Date());
        publisher.publishEvent(event);

    }

}


