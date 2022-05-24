package com.keremalp.creditservice.service;

import com.keremalp.creditservice.converter.CreditMapper;
import com.keremalp.creditservice.dao.CreditDao;
import com.keremalp.creditservice.dto.events.cancel.CreditCancelEvent;
import com.keremalp.creditservice.dto.events.cancel.CreditTransactionCancelEvent;
import com.keremalp.creditservice.dto.events.done.CreditCreateDoneEvent;
import com.keremalp.creditservice.dto.product.CreateCreditDto;
import com.keremalp.creditservice.entity.Credit;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
@Service
@RequiredArgsConstructor
public class CreditService {
    private final CreditDao creditDao;



    private final ApplicationEventPublisher publisher;

    @Transactional()
    public void createCredit(CreateCreditDto createCreditDto, String transactionId) {


        Credit credit = CreditMapper.INSTANCE.creditDtoToCredit(createCreditDto);
        credit.setCreditAmount(generateCreditLimit());
        credit.setCreditNumber(generateCreditNumber());

        LocalDate currentDate = LocalDate.now();
        credit.setCreatedDate(currentDate);
        int currentDay = currentDate.getDayOfMonth();

        int creditWithDateAndCustomerNumber = creditDao.countCreditByDateAndCustomerNumber(currentDate, createCreditDto.getCustomerNumber());
        log.warn("Şu an bu kritere uyan kredi sayısı,{}",creditWithDateAndCustomerNumber);

       /* creditDao.findByCustomerNumber(createCreditDto.getCustomerNumber()).forEach(c -> {

                    if (c.getCreatedDate().getDayOfMonth() == currentDay) {
                        count.getAndIncrement();
                    }

                }
        );*/
        if (creditWithDateAndCustomerNumber >=   2) {
            publishCanceledCreditCreateDirectToManagerService(credit, transactionId);
            throw new IllegalStateException("Bir günde 2 tane krediden fazla oluşturulamaz");

        }
        credit.setCreditStatus(Credit.CreditStatus.CREATE);
        Credit save = creditDao.save(credit);
        publishCreditCreateDone(save, transactionId);


    }

    @Transactional()
    public void createCreditForNewCustomer(CreateCreditDto createCreditDto, String transactionId) {

        Credit credit = CreditMapper.INSTANCE.creditDtoToCredit(createCreditDto);
        credit.setCreditAmount(generateCreditLimit());
        credit.setCreditNumber(generateCreditNumber());
        LocalDate currentDate = LocalDate.now();
        credit.setCreatedDate(currentDate);
        int currentDay = currentDate.getDayOfMonth();

        int creditWithDateAndCustomerNumber = creditDao.countCreditByDateAndCustomerNumber(currentDate, createCreditDto.getCustomerNumber());
        log.warn("Şu an bu kritere uyan kredi sayısı,{}",creditWithDateAndCustomerNumber);

        if (creditWithDateAndCustomerNumber > 2) {
            publishCanceledCreditCreate(credit, transactionId);
            throw new IllegalStateException("Bir günde 2 tane krediden fazla oluşturulamaz");

        }

        credit.setCreditStatus(Credit.CreditStatus.CREATE);
        Credit save = creditDao.save(credit);
        publishCreditCreateDone(save, transactionId);


    }

    private void publishCreditCreateDone(Credit credit, String transactionId) {
        CreateCreditDto createCreditDto = CreditMapper.INSTANCE.creditToCreditDto(credit);
        CreditCreateDoneEvent event = new CreditCreateDoneEvent((transactionId), createCreditDto, new Date());
        publisher.publishEvent(event);
    }

    private void publishCanceledCreditCreate(Credit credit, String transactionId) {
        CreateCreditDto createCreditDto = CreditMapper.INSTANCE.creditToCreditDto(credit);
        CreditCancelEvent event = new CreditCancelEvent(transactionId, createCreditDto, new Date());
        publisher.publishEvent(event);

    }

    private void publishCanceledCreditCreateDirectToManagerService(Credit credit, String transactionId) {
        CreateCreditDto createCreditDto = CreditMapper.INSTANCE.creditToCreditDto(credit);
        CreditTransactionCancelEvent event = new CreditTransactionCancelEvent(transactionId, createCreditDto, new Date());
        publisher.publishEvent(event);

    }

    private Long generateCreditLimit() {
        //random number
        return (long) (Math.random() * 1000);
    }

    //generate credit number 25 digit
    private String generateCreditNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 25; i++) {
            stringBuilder.append((int) (Math.random() * 10));
        }
        return stringBuilder.toString();
    }

}


