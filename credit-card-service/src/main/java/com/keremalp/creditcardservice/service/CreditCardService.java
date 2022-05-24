package com.keremalp.creditcardservice.service;

import com.keremalp.creditcardservice.converter.CreditCardMapper;
import com.keremalp.creditcardservice.dao.CreditCardDao;
import com.keremalp.creditcardservice.dto.events.done.CreditCardCreateDoneEvent;
import com.keremalp.creditcardservice.dto.products.CreateCreditCardDto;
import com.keremalp.creditcardservice.entity.CreditCard;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

@Log4j2
@Service
@RequiredArgsConstructor
public class CreditCardService {
    private final CreditCardDao creditCardDao;


    private final ApplicationEventPublisher publisher;

    @Transactional()
    public void createCreditCard(CreateCreditCardDto createCreditCardDto, String transactionId) {


        CreditCard creditCard = CreditCardMapper.INSTANCE.creditCardDtoToCreditCard(createCreditCardDto);
        creditCard.setCreditCardLimit(generateCreditCardLimit());
        creditCard.setCreditCardNumber(generateCreditCardNumber());

        LocalDate currentDate = LocalDate.now();
        creditCard.setCreatedDate(currentDate);

        createCreditCardDto.setCreditCardLimit(creditCard.getCreditCardLimit());
        createCreditCardDto.setCreditCardNumber(creditCard.getCreditCardNumber());

        creditCard.setCreditCardStatus(CreditCard.CreditCardStatus.CREATE);
        CreditCard save = creditCardDao.save(creditCard);
        publishCreditCreateDone(save, transactionId);


    }

    @Transactional()
    public void createCreditCardForNewCustomer(CreateCreditCardDto createCreditCardDto, String transactionId) {

        CreditCard creditCard = CreditCardMapper.INSTANCE.creditCardDtoToCreditCard(createCreditCardDto);
        creditCard.setCreditCardLimit(generateCreditCardLimit());
        creditCard.setCreditCardNumber(generateCreditCardNumber());

        createCreditCardDto.setCreditCardLimit(creditCard.getCreditCardLimit());
        createCreditCardDto.setCreditCardNumber(creditCard.getCreditCardNumber());

        LocalDate currentDate = LocalDate.now();
        creditCard.setCreatedDate(currentDate);


        creditCard.setCreditCardStatus(CreditCard.CreditCardStatus.CREATE);
        CreditCard save = creditCardDao.save(creditCard);
        publishCreditCreateDone(save, transactionId);


    }

    private void publishCreditCreateDone(CreditCard creditCard, String transactionId) {
        CreateCreditCardDto createCreditCardDto = CreditCardMapper.INSTANCE.creditCardToCreditCardDto(creditCard);
        CreditCardCreateDoneEvent event = new CreditCardCreateDoneEvent((transactionId), createCreditCardDto, new Date());
        publisher.publishEvent(event);
    }

   /* private void publishCanceledCreditCreate(Credit credit, String transactionId) {
        CreateCreditDto createCreditDto = CreditMapper.INSTANCE.creditToCreditDto(credit);
        CreditCancelEvent event = new CreditCancelEvent(transactionId, createCreditDto, new Date());
        publisher.publishEvent(event);

    }

    private void publishCanceledCreditCreateDirectToManagerService(Credit credit, String transactionId) {
        CreateCreditDto createCreditDto = CreditMapper.INSTANCE.creditToCreditDto(credit);
        CreditTransactionCancelEvent event = new CreditTransactionCancelEvent(transactionId, createCreditDto, new Date());
        publisher.publishEvent(event);

    }*/

    private Long generateCreditCardLimit() {
        //random number
        return (long) (Math.random() * 1000);
    }

    //generate credit number 25 digit
    private String generateCreditCardNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 25; i++) {
            stringBuilder.append((int) (Math.random() * 10));
        }
        return stringBuilder.toString();
    }

}


