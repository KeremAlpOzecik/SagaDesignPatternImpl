package com.keremalp.managementservice.service;

import com.keremalp.managementservice.dao.TransactionDao;
import com.keremalp.managementservice.dto.events.create.*;
import com.keremalp.managementservice.dto.events.done.*;
import com.keremalp.managementservice.dto.products.CreateNotificationDto;
import com.keremalp.managementservice.dto.products.account.CreateAccountRequestDto;
import com.keremalp.managementservice.dto.products.account.CreateCifOpenForAccountDto;
import com.keremalp.managementservice.dto.products.cif.CreateCifOpenRequestDto;
import com.keremalp.managementservice.dto.products.credit.CreateAccountForCreditDto;
import com.keremalp.managementservice.dto.products.credit.CreateCifOpenForCreditDto;
import com.keremalp.managementservice.dto.products.credit.CreateCreditDto;
import com.keremalp.managementservice.dto.products.credit_card.CreateAccountForCreditCardDto;
import com.keremalp.managementservice.dto.products.credit_card.CreateCifOpenForCreditCardDto;
import com.keremalp.managementservice.dto.products.credit_card.CreateCreditCardDto;
import com.keremalp.managementservice.dto.products.flex_acc.CreateAccountForFlexibleAccountDto;
import com.keremalp.managementservice.dto.products.flex_acc.CreateCifOpenForFlexibleAccountDto;
import com.keremalp.managementservice.dto.products.flex_acc.CreateFlexibleAccountDto;
import com.keremalp.managementservice.dto.products.hgs.CreateAccountForHgsDto;
import com.keremalp.managementservice.dto.products.hgs.CreateCifOpenForHgsDto;
import com.keremalp.managementservice.dto.products.hgs.CreateHgsDto;
import com.keremalp.managementservice.entity.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class TransactionService {
    private final TransactionDao transactionDao;
    private final ApplicationEventPublisher publisher;


    // CIF OPEN
    @Transactional
    public Transaction createCifOpenTransaction(CreateCifOpenRequestDto createCifOpenRequestDto) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType("CIF_OPEN");
        String transactionId = UUID.randomUUID().toString();
        transaction.setTransactionId(transactionId);
        transaction.setCustomerNumber(createCifOpenRequestDto.getCustomerNumber());
        transaction.setTransactionStatus(Transaction.TransactionStatus.NEW);
        Transaction save = transactionDao.save(transaction);
        publishCIF(createCifOpenRequestDto, transactionId);

        return save;


    }

    private void publishCIF(CreateCifOpenRequestDto createCifOpenRequestDto, String transactionId) {
        CifOpenCreatedEvent event = new CifOpenCreatedEvent(transactionId, createCifOpenRequestDto, new Date());
        publisher.publishEvent(event);
    }

    private void publishCIFforAccount(CreateCifOpenForAccountDto createAccountRequestDto, String transactionId) {

        CreateAccountForNotExistCustomerEvent event = new CreateAccountForNotExistCustomerEvent(transactionId, createAccountRequestDto, new Date());
        publisher.publishEvent(event);
    }

    @Transactional
    public void updateCustomerCreationAsDone(CifOpenCreatedDoneEvent cifOpenCreatedDoneEvent) {

        Optional<Transaction> transaction = transactionDao.findByTransactionId(cifOpenCreatedDoneEvent.getTransactionId());
        if (transaction.isPresent()) {
            Transaction transaction1 = transaction.get();
            transaction1.setTransactionStatus(Transaction.TransactionStatus.DONE);
            transaction1.setContract(cifOpenCreatedDoneEvent.getCustomer().toString());
            transaction1.setCustomerNumber(cifOpenCreatedDoneEvent.getCustomer().getCustomerNumber());
            transactionDao.save(transaction1);
            CreateNotificationDto createNotificationDto = new CreateNotificationDto();
            createNotificationDto.setContract(transaction1.getContract());
            createNotificationDto.setEmail(cifOpenCreatedDoneEvent.getCustomer().getEmail());
            publishTransactionNotification(createNotificationDto, cifOpenCreatedDoneEvent.getTransactionId());

        } else {
            log.error("Account not found");
        }
    }

    //ACCOUNT OPEN
    @Transactional
    public Transaction createAccountTransaction(CreateAccountRequestDto createAccountRequestDto) {
        //Eğer müşteri numarası girilmezsse burdan çalışır


        String transactionId = UUID.randomUUID().toString();
        if (createAccountRequestDto.getCustomerNumber().equals(0L)) {
            CreateCifOpenForAccountDto createCifOpenForAccountDto = new CreateCifOpenForAccountDto();
            createCifOpenForAccountDto.setSurname(createAccountRequestDto.getSurname());
            createCifOpenForAccountDto.setName(createAccountRequestDto.getName());
            createCifOpenForAccountDto.setAddress(createAccountRequestDto.getAddress());
            createCifOpenForAccountDto.setEmail(createAccountRequestDto.getEmail());
            createCifOpenForAccountDto.setPhone(createAccountRequestDto.getPhone());
            createCifOpenForAccountDto.setCustomerNumber(createAccountRequestDto.getCustomerNumber());


            Transaction transaction = new Transaction();
            transaction.setTransactionType("Account_CREATE");

            publishCIFforAccount(createCifOpenForAccountDto, transactionId);
            transaction.setTransactionId(transactionId);
            transaction.setCustomerNumber(createAccountRequestDto.getCustomerNumber());
            transaction.setTransactionStatus(Transaction.TransactionStatus.NEW);


            return transactionDao.save(transaction);


        } else {
            Transaction transaction = new Transaction();
            transaction.setTransactionType("Account_CREATE");
            transaction.setTransactionId(transactionId);
            transaction.setCustomerNumber(createAccountRequestDto.getCustomerNumber());
            transaction.setTransactionStatus(Transaction.TransactionStatus.NEW);
            Transaction save = transactionDao.save(transaction);
            publishAccount(createAccountRequestDto, transactionId);

            return save;

        }
    }

    public void publishAccount(CreateAccountRequestDto createAccountRequestDto, String transactionId) {
        AccountCreateEvent event = new AccountCreateEvent(transactionId, createAccountRequestDto, new Date());
        publisher.publishEvent(event);
    }

    @Transactional
    public void updateAccountCreationAsDone(AccountCreateDoneEvent accountCreateDoneEvent) {
        Optional<Transaction> transaction = transactionDao.findByTransactionId(accountCreateDoneEvent.getTransactionId());
        if (transaction.isPresent()) {
            Transaction transaction1 = transaction.get();
            transaction1.setTransactionStatus(Transaction.TransactionStatus.DONE);
            transaction1.setContract(accountCreateDoneEvent.getAccount().toString());
            transaction1.setCustomerNumber(accountCreateDoneEvent.getAccount().getCustomerNumber());
            transactionDao.save(transaction1);
            CreateNotificationDto createNotificationDto = new CreateNotificationDto();
            createNotificationDto.setContract(transaction1.getContract());
            createNotificationDto.setEmail(accountCreateDoneEvent.getAccount().getEmail());
            publishTransactionNotification(createNotificationDto, accountCreateDoneEvent.getTransactionId());
            log.info("Account Açma İşlemi Başarıyla Sonlandı --> " + accountCreateDoneEvent.getTransactionId());
        } else {
            log.error("Account not found");
        }
    }

    @Transactional
    public void cancelTransaction(String transactionId) {

        Optional<Transaction> optionalTransaction = transactionDao.findByTransactionId(transactionId);
        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            transaction.setTransactionStatus(Transaction.TransactionStatus.CANCELED);
            transactionDao.save(transaction);
            log.info("Transaction canceled");

        } else {
            log.error("Cannot find transaction by transaction id");
        }
    }

    //Flexible Acc OPEN

    @Transactional
    public Transaction createFlexibleAccountTransaction(CreateFlexibleAccountDto createFlexibleAccountDto) {
        //Eğer müşteri numarası girilmezsse burdan çalışır


        String transactionId = UUID.randomUUID().toString();
        if (createFlexibleAccountDto.getCustomerNumber().equals(0L)) {
            CreateCifOpenForFlexibleAccountDto createCifOpenForFlexibleAccountDto = new CreateCifOpenForFlexibleAccountDto();
//            createCifOpenForFlexibleAccountDto.setSurname(createFlexibleAccountDto.getSurname());
            createCifOpenForFlexibleAccountDto.setName(createFlexibleAccountDto.getCustomerName());
            createCifOpenForFlexibleAccountDto.setAddress(createFlexibleAccountDto.getAddress());
//            createCifOpenForFlexibleAccountDto.setEmail(createFlexibleAccountDto.getEmail());
            createCifOpenForFlexibleAccountDto.setPhone(createFlexibleAccountDto.getPhoneNumber());
            createCifOpenForFlexibleAccountDto.setCustomerNumber(createFlexibleAccountDto.getCustomerNumber());


            Transaction transaction = new Transaction();
            transaction.setTransactionType("FLEXIBLE_ACCOUNT_OPEN");

            publishCIFforFlexibleAccount(createCifOpenForFlexibleAccountDto, transactionId);
            transaction.setTransactionId(transactionId);
            transaction.setCustomerNumber(createFlexibleAccountDto.getCustomerNumber());
            transaction.setTransactionStatus(Transaction.TransactionStatus.NEW);

            return transactionDao.save(transaction);


        } else if (createFlexibleAccountDto.getCustomerNumber() != 0L && createFlexibleAccountDto.getAccountNumber() == 0L) {

            CreateAccountForFlexibleAccountDto forFlexibleAccountDto = new CreateAccountForFlexibleAccountDto();
//            createCifOpenForFlexibleAccountDto.setSurname(createFlexibleAccountDto.getSurname());
            forFlexibleAccountDto.setName(createFlexibleAccountDto.getCustomerName());
            forFlexibleAccountDto.setAddress(createFlexibleAccountDto.getAddress());
//            createCifOpenForFlexibleAccountDto.setEmail(createFlexibleAccountDto.getEmail());
            forFlexibleAccountDto.setPhone(createFlexibleAccountDto.getPhoneNumber());
            forFlexibleAccountDto.setCustomerNumber(createFlexibleAccountDto.getCustomerNumber());


            Transaction transaction = new Transaction();
            transaction.setTransactionType("FLEXIBLE_ACCOUNT_OPEN");

            publishAccountForFlexibleAccount(forFlexibleAccountDto, transactionId);
            transaction.setTransactionId(transactionId);
            transaction.setCustomerNumber(createFlexibleAccountDto.getCustomerNumber());
            transaction.setTransactionStatus(Transaction.TransactionStatus.NEW);

            return transactionDao.save(transaction);
        } else {
            Transaction transaction = new Transaction();
            transaction.setTransactionType("FLEXIBLE_ACCOUNT_OPEN");
            transaction.setTransactionId(transactionId);
            transaction.setCustomerNumber(createFlexibleAccountDto.getCustomerNumber());
            transaction.setTransactionStatus(Transaction.TransactionStatus.NEW);
            Transaction save = transactionDao.save(transaction);
            publishFlexibleAccount(createFlexibleAccountDto, transactionId);

            return save;

        }
    }

    public void publishFlexibleAccount(CreateFlexibleAccountDto createFlexibleAccountDto, String transactionId) {
        FlexibleAccountCreateEvent event = new FlexibleAccountCreateEvent(transactionId, createFlexibleAccountDto, new Date());
        publisher.publishEvent(event);
    }

    private void publishCIFforFlexibleAccount(CreateCifOpenForFlexibleAccountDto createCifOpenForFlexibleAccountDto, String transactionId) {

        CreateFlexibleAccountForNotExistCustomerEvent event = new CreateFlexibleAccountForNotExistCustomerEvent(transactionId, createCifOpenForFlexibleAccountDto, new Date());
        publisher.publishEvent(event);
    }

    public void publishAccountForFlexibleAccount(CreateAccountForFlexibleAccountDto createAccountForFlexibleAccountDto, String transactionId) {
        CreateFlexibleAccountForNotExistAccountEvent event = new CreateFlexibleAccountForNotExistAccountEvent(transactionId, createAccountForFlexibleAccountDto, new Date());
        publisher.publishEvent(event);
    }


    @Transactional
    public void updateFlexibleAccountCreationAsDone(FlexibleAccCreateDone flexibleAccCreateDone) {
        Optional<Transaction> transaction = transactionDao.findByTransactionId(flexibleAccCreateDone.getTransactionId());
        if (transaction.isPresent()) {
            Transaction transaction1 = transaction.get();
            transaction1.setTransactionStatus(Transaction.TransactionStatus.DONE);
            transaction1.setContract(flexibleAccCreateDone.getAccount().toString());
            transaction1.setCustomerNumber(flexibleAccCreateDone.getAccount().getCustomerNumber());
            transactionDao.save(transaction1);
            CreateNotificationDto createNotificationDto = new CreateNotificationDto();
            createNotificationDto.setContract(transaction1.getContract());
            createNotificationDto.setEmail(flexibleAccCreateDone.getAccount().getCustomerName());
            publishTransactionNotification(createNotificationDto, flexibleAccCreateDone.getTransactionId());

            log.info("Flexible Account Açma İşlemi Başarılı -->>> " + flexibleAccCreateDone.getTransactionId());
        } else {
            log.error("Transaction not found");
        }
    }

    //Credit Open

    @Transactional
    public Transaction createCreditTransaction(CreateCreditDto createCreditDto) {
        //Eğer müşteri numarası girilmezsse burdan çalışır


        String transactionId = UUID.randomUUID().toString();
        if (createCreditDto.getCustomerNumber().equals(0L)) {
            CreateCifOpenForCreditDto createCifOpenForCreditDto = new CreateCifOpenForCreditDto();
            createCifOpenForCreditDto.setCustomerSurname(createCreditDto.getCustomerSurname());
            createCifOpenForCreditDto.setCustomerName(createCreditDto.getCustomerName());
            createCifOpenForCreditDto.setAddress(createCreditDto.getAddress());
            createCifOpenForCreditDto.setCustomerEmail(createCreditDto.getCustomerEmail());
            createCifOpenForCreditDto.setPhoneNumber(createCreditDto.getPhoneNumber());
            createCifOpenForCreditDto.setCustomerNumber(createCreditDto.getCustomerNumber());
            createCifOpenForCreditDto.setAccountNumber(createCreditDto.getAccountNumber());


            Transaction transaction = new Transaction();
            transaction.setTransactionType("CREDIT_OPEN");

            publishCifForCredit(createCifOpenForCreditDto, transactionId);
            transaction.setTransactionId(transactionId);
            transaction.setCustomerNumber(createCreditDto.getCustomerNumber());
            transaction.setTransactionStatus(Transaction.TransactionStatus.NEW);

            return transactionDao.save(transaction);


        } else if (createCreditDto.getCustomerNumber() != 0L && createCreditDto.getAccountNumber() == 0L) {

            CreateAccountForCreditDto createAccountForCreditDto = new CreateAccountForCreditDto();
            createAccountForCreditDto.setCustomerSurname(createCreditDto.getCustomerSurname());
            createAccountForCreditDto.setCustomerName(createCreditDto.getCustomerName());

            createAccountForCreditDto.setAddress(createCreditDto.getAddress());
            createAccountForCreditDto.setCustomerEmail(createCreditDto.getCustomerEmail());
            createAccountForCreditDto.setPhoneNumber(createCreditDto.getPhoneNumber());
            createAccountForCreditDto.setCustomerNumber(createCreditDto.getCustomerNumber());

            Transaction transaction = new Transaction();
            transaction.setTransactionType("CREDIT_OPEN");

            publishAccountForCredit(createAccountForCreditDto, transactionId);
            transaction.setTransactionId(transactionId);
            transaction.setCustomerNumber(createCreditDto.getCustomerNumber());
            transaction.setTransactionStatus(Transaction.TransactionStatus.NEW);

            return transactionDao.save(transaction);
        } else {
            Transaction transaction = new Transaction();
            transaction.setTransactionType("CREDIT_OPEN");
            transaction.setTransactionId(transactionId);
            transaction.setCustomerNumber(createCreditDto.getCustomerNumber());
            transaction.setTransactionStatus(Transaction.TransactionStatus.NEW);
            Transaction save = transactionDao.save(transaction);
            publishCredit(createCreditDto, transactionId);

            return save;

        }
    }

    public void publishCredit(CreateCreditDto createCreditDto, String transactionId) {
        CreditCreateEvent event = new CreditCreateEvent(transactionId, createCreditDto, new Date());
        publisher.publishEvent(event);
    }

    private void publishCifForCredit(CreateCifOpenForCreditDto createCifOpenForCreditDto, String transactionId) {

        CreateCreditForNotExistCustomerEvent event = new CreateCreditForNotExistCustomerEvent(transactionId, createCifOpenForCreditDto, new Date());
        publisher.publishEvent(event);
    }

    public void publishAccountForCredit(CreateAccountForCreditDto createAccountForCreditDto, String transactionId) {
        CreateCreditForNotExistAccountEvent event = new CreateCreditForNotExistAccountEvent(transactionId, createAccountForCreditDto, new Date());
        publisher.publishEvent(event);
    }

    @Transactional
    public void updateCreditCreationAsDone(CreditCreateDone creditCreateDone) {
        Optional<Transaction> transaction = transactionDao.findByTransactionId(creditCreateDone.getTransactionId());
        if (transaction.isPresent()) {
            Transaction transaction1 = transaction.get();
            transaction1.setTransactionStatus(Transaction.TransactionStatus.DONE);
            transaction1.setContract(creditCreateDone.getCredit().toString());
            transaction1.setCustomerNumber(creditCreateDone.getCredit().getCustomerNumber());
            transactionDao.save(transaction1);
            CreateNotificationDto createNotificationDto = new CreateNotificationDto();
            createNotificationDto.setContract(transaction1.getContract());
            createNotificationDto.setEmail(creditCreateDone.getCredit().getCustomerEmail());
            publishTransactionNotification(createNotificationDto, creditCreateDone.getTransactionId());
            log.info("Credit Açma İşlemi Başarılı -->>> " + creditCreateDone.getTransactionId());
        } else {
            log.error("Transaction not found");
        }
    }


    //Credit Card Open

    @Transactional
    public Transaction createCreditCardTransaction(CreateCreditCardDto createCreditCardDto) {
        //Eğer müşteri numarası girilmezsse burdan çalışır


        String transactionId = UUID.randomUUID().toString();
        if (createCreditCardDto.getCustomerNumber().equals(0L)) {
            CreateCifOpenForCreditCardDto createCifOpenForCreditCardDto = new CreateCifOpenForCreditCardDto();
            createCifOpenForCreditCardDto.setCustomerSurname(createCreditCardDto.getCustomerSurname());
            createCifOpenForCreditCardDto.setCustomerName(createCreditCardDto.getCustomerName());
            createCifOpenForCreditCardDto.setAddress(createCreditCardDto.getAddress());
            createCifOpenForCreditCardDto.setCustomerEmail(createCreditCardDto.getCustomerEmail());
            createCifOpenForCreditCardDto.setPhoneNumber(createCreditCardDto.getPhoneNumber());
            createCifOpenForCreditCardDto.setCustomerNumber(createCreditCardDto.getCustomerNumber());
            createCifOpenForCreditCardDto.setAccountNumber(createCreditCardDto.getAccountNumber());


            Transaction transaction = new Transaction();
            transaction.setTransactionType("CREDIT_CARD_OPEN");

            publishCifForCreditCard(createCifOpenForCreditCardDto, transactionId);
            transaction.setTransactionId(transactionId);
            transaction.setCustomerNumber(createCreditCardDto.getCustomerNumber());
            transaction.setTransactionStatus(Transaction.TransactionStatus.NEW);

            return transactionDao.save(transaction);


        } else if (createCreditCardDto.getCustomerNumber() != 0L & createCreditCardDto.getAccountNumber() == 0L) {

            CreateAccountForCreditCardDto accountForCreditDto = new CreateAccountForCreditCardDto();
            accountForCreditDto.setCustomerSurname(createCreditCardDto.getCustomerSurname());
            accountForCreditDto.setCustomerName(createCreditCardDto.getCustomerName());

            accountForCreditDto.setAddress(createCreditCardDto.getAddress());
            accountForCreditDto.setCustomerEmail(createCreditCardDto.getCustomerEmail());
            accountForCreditDto.setPhoneNumber(createCreditCardDto.getPhoneNumber());
            accountForCreditDto.setCustomerNumber(createCreditCardDto.getCustomerNumber());

            Transaction transaction = new Transaction();
            transaction.setTransactionType("CREDIT_CARD_OPEN");

            publishAccountForCreditCard(accountForCreditDto, transactionId);
            transaction.setTransactionId(transactionId);
            transaction.setCustomerNumber(createCreditCardDto.getCustomerNumber());
            transaction.setTransactionStatus(Transaction.TransactionStatus.NEW);

            return transactionDao.save(transaction);
        } else {
            Transaction transaction = new Transaction();
            transaction.setTransactionType("CREDIT_CARD_OPEN");
            transaction.setTransactionId(transactionId);
            transaction.setCustomerNumber(createCreditCardDto.getCustomerNumber());
            transaction.setTransactionStatus(Transaction.TransactionStatus.NEW);
            Transaction save = transactionDao.save(transaction);
            publishCreditCard(createCreditCardDto, transactionId);

            return save;

        }
    }

    public void publishCreditCard(CreateCreditCardDto createCreditCardDto, String transactionId) {
        CreditCardCreateEvent event = new CreditCardCreateEvent(transactionId, createCreditCardDto, new Date());
        publisher.publishEvent(event);
    }

    private void publishCifForCreditCard(CreateCifOpenForCreditCardDto createCifOpenForCreditCardDto, String transactionId) {

        CreateCreditCardForNotExistCustomerEvent event = new CreateCreditCardForNotExistCustomerEvent(transactionId, createCifOpenForCreditCardDto, new Date());
        publisher.publishEvent(event);
    }

    public void publishAccountForCreditCard(CreateAccountForCreditCardDto createAccountForCreditCardDto, String transactionId) {
        CreateCreditCardForNotExistAccountEvent event = new CreateCreditCardForNotExistAccountEvent(transactionId, createAccountForCreditCardDto, new Date());
        publisher.publishEvent(event);
    }

    @Transactional
    public void updateCreditCardCreationAsDone(CreditCardCreateDone creditCardCreateDone) {
        Optional<Transaction> transaction = transactionDao.findByTransactionId(creditCardCreateDone.getTransactionId());
        if (transaction.isPresent()) {
            Transaction transaction1 = transaction.get();
            transaction1.setTransactionStatus(Transaction.TransactionStatus.DONE);
            transaction1.setContract(creditCardCreateDone.getCreditCard().toString());
            transaction1.setCustomerNumber(creditCardCreateDone.getCreditCard().getCustomerNumber());
            transactionDao.save(transaction1);
            CreateNotificationDto createNotificationDto = new CreateNotificationDto();
            createNotificationDto.setContract(transaction1.getContract());
            createNotificationDto.setEmail(creditCardCreateDone.getCreditCard().getCustomerEmail());
            publishTransactionNotification(createNotificationDto, creditCardCreateDone.getTransactionId());
            log.info("Credit Card Açma İşlemi Başarılı -->>> " + creditCardCreateDone.getTransactionId());
        } else {
            log.error("Transaction not found");
        }
    }

    // HGS OPEN


    @Transactional
    public Transaction createHgsTransaction(CreateHgsDto createHgsDto) {
        //Eğer müşteri numarası girilmezsse burdan çalışır


        String transactionId = UUID.randomUUID().toString();
        if (createHgsDto.getCustomerNumber().equals(0L)) {
            CreateCifOpenForHgsDto hgsDto = new CreateCifOpenForHgsDto();
            hgsDto.setCustomerSurname(createHgsDto.getCustomerSurname());
            hgsDto.setCustomerName(createHgsDto.getCustomerName());
            hgsDto.setAddress(createHgsDto.getAddress());
            hgsDto.setCustomerEmail(createHgsDto.getCustomerEmail());
            hgsDto.setPhoneNumber(createHgsDto.getPhoneNumber());
            hgsDto.setCustomerNumber(createHgsDto.getCustomerNumber());
            hgsDto.setAccountNumber(createHgsDto.getAccountNumber());
            hgsDto.setCarPlate(createHgsDto.getCarPlate());


            Transaction transaction = new Transaction();
            transaction.setTransactionType("HGS_OPEN");

            publishCifForHgs(hgsDto, transactionId);
            transaction.setTransactionId(transactionId);
            transaction.setCustomerNumber(createHgsDto.getCustomerNumber());
            transaction.setTransactionStatus(Transaction.TransactionStatus.NEW);

            return transactionDao.save(transaction);


        } else if (createHgsDto.getCustomerNumber() != 0L & createHgsDto.getAccountNumber() == 0L) {

            CreateAccountForHgsDto hgsDto = new CreateAccountForHgsDto();
            hgsDto.setCustomerSurname(createHgsDto.getCustomerSurname());
            hgsDto.setCustomerName(createHgsDto.getCustomerName());
            hgsDto.setAddress(createHgsDto.getAddress());
            hgsDto.setCustomerEmail(createHgsDto.getCustomerEmail());
            hgsDto.setPhoneNumber(createHgsDto.getPhoneNumber());
            hgsDto.setCustomerNumber(createHgsDto.getCustomerNumber());
            hgsDto.setAccountNumber(createHgsDto.getAccountNumber());
            hgsDto.setCarPlate(createHgsDto.getCarPlate());

            Transaction transaction = new Transaction();
            transaction.setTransactionType("HGS_OPEN");

            publishAccountForHgs(hgsDto, transactionId);
            transaction.setTransactionId(transactionId);
            transaction.setCustomerNumber(createHgsDto.getCustomerNumber());
            transaction.setTransactionStatus(Transaction.TransactionStatus.NEW);

            return transactionDao.save(transaction);
        } else {
            Transaction transaction = new Transaction();
            transaction.setTransactionType("HGS_OPEN");
            transaction.setTransactionId(transactionId);
            transaction.setCustomerNumber(createHgsDto.getCustomerNumber());
            transaction.setTransactionStatus(Transaction.TransactionStatus.NEW);
            Transaction save = transactionDao.save(transaction);
            publishHgs(createHgsDto, transactionId);

            return save;

        }
    }

    public void publishHgs(CreateHgsDto hgsDto, String transactionId) {
        HgsCreateEvent event = new HgsCreateEvent(transactionId, hgsDto, new Date());
        publisher.publishEvent(event);
    }

    private void publishCifForHgs(CreateCifOpenForHgsDto createCifOpenForHgsDto, String transactionId) {

        CreateHgsForNotExistCustomerEvent event = new CreateHgsForNotExistCustomerEvent(transactionId, createCifOpenForHgsDto, new Date());
        publisher.publishEvent(event);
    }

    public void publishAccountForHgs(CreateAccountForHgsDto createAccountForHgsDto, String transactionId) {
        CreateHgsForNotExistAccountEvent event = new CreateHgsForNotExistAccountEvent(transactionId, createAccountForHgsDto, new Date());
        publisher.publishEvent(event);
    }

    @Transactional
    public void updateHgsCreationAsDone(HgsCreateDone hgsCreateDone) {
        Optional<Transaction> transaction = transactionDao.findByTransactionId(hgsCreateDone.getTransactionId());
        if (transaction.isPresent()) {
            Transaction transaction1 = transaction.get();
            transaction1.setTransactionStatus(Transaction.TransactionStatus.DONE);
            transaction1.setContract(hgsCreateDone.getHgs().toString());
            transaction1.setCustomerNumber(hgsCreateDone.getHgs().getCustomerNumber());
            transactionDao.save(transaction1);

            CreateNotificationDto createNotificationDto = new CreateNotificationDto();
            createNotificationDto.setContract(transaction1.getContract());
            createNotificationDto.setEmail(hgsCreateDone.getHgs().getCustomerEmail());
            publishTransactionNotification(createNotificationDto, hgsCreateDone.getTransactionId());

            log.info("Hgs Açma İşlemi Başarılı -->>> " + hgsCreateDone.getTransactionId());
        } else {
            log.error("Transaction not found");
        }
    }

    public void publishTransactionNotification(CreateNotificationDto createNotificationDto, String transactionId) {
        CreateNotificationEvent event = new CreateNotificationEvent(transactionId, createNotificationDto, new Date());
        publisher.publishEvent(event);
    }

}
