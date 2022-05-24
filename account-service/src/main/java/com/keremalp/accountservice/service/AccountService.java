package com.keremalp.accountservice.service;

import com.keremalp.accountservice.converter.AccountMapper;
import com.keremalp.accountservice.dao.AccountDao;
import com.keremalp.accountservice.dto.events.cancel.*;
import com.keremalp.accountservice.dto.events.done.*;
import com.keremalp.accountservice.dto.product.CreateAccountForCreditCardRequestDto;
import com.keremalp.accountservice.dto.product.CreateAccountForCreditRequestDto;
import com.keremalp.accountservice.dto.product.CreateAccountForHgsRequestDto;
import com.keremalp.accountservice.dto.product.CreateAccountRequestDto;
import com.keremalp.accountservice.entity.Account;
import com.keremalp.accountservice.entity.AccountException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountDao accountDao;
    private final ApplicationEventPublisher publisher;

    @Transactional()
    public void createAccount(CreateAccountRequestDto createAccountRequestDto, String transactionId) {
        Account account = AccountMapper.INSTANCE.createAccountOPENRequestToAccount(createAccountRequestDto);
        account.setAccountNumber(createAccountNumber());

        //Burada bi yerde kontrol yapılıp hata verilecek ki kayıt olmasın
        Optional<Account> optionalAccount = accountDao.findByAccountNumber(account.getAccountNumber());
        if (optionalAccount.isPresent()) {
            publishCanceledAccountCreate(account, transactionId);
            throw new AccountException("Account is already exists");
        }

        account.setAccountStatus(Account.AccountStatus.CREATED);
        Account save = accountDao.save(account);
        publishAccountCreateDone(save, transactionId);


    }

    @Transactional()
    public void createAccountForFlexAccount(CreateAccountRequestDto createAccountRequestDto, String transactionId) {
        Account account = AccountMapper.INSTANCE.createAccountOPENRequestToAccount(createAccountRequestDto);
        account.setAccountNumber(createAccountNumber());

        //Burada bi yerde kontrol yapılıp hata verilecek ki kayıt olmasın
        Optional<Account> optionalAccount = accountDao.findByAccountNumber(account.getAccountNumber());
        if (optionalAccount.isPresent()) {
            publishCanceledTransactionAccountCreate(account, transactionId);
            throw new AccountException("Account is already exists");
        }

        account.setAccountStatus(Account.AccountStatus.CREATED);
        Account save = accountDao.save(account);
        publishAccountCreateDoneToFlexAccount(save, transactionId);


    }

    private void publishAccountCreateDone(Account account, String transactionId) {
        CreateAccountRequestDto createAccountRequestDto = AccountMapper.INSTANCE.accountToCreateAccountRequestDto(account);
        AccountCreateDoneEvent event = new AccountCreateDoneEvent((transactionId), createAccountRequestDto, new Date());
        publisher.publishEvent(event);
    }

    private void publishAccountCreateDoneToFlexAccount(Account account, String transactionId) {
        CreateAccountRequestDto createAccountRequestDto = AccountMapper.INSTANCE.accountToCreateAccountRequestDto(account);
        AccountCreateDoneEventForFlexibleAccount event = new AccountCreateDoneEventForFlexibleAccount((transactionId), createAccountRequestDto, new Date());
        publisher.publishEvent(event);
    }

    private void publishAccountCreateDoneToCredit(CreateAccountForCreditRequestDto account, String transactionId) {

        AccountCreateDoneEventForCredit event = new AccountCreateDoneEventForCredit((transactionId), account, new Date());
        publisher.publishEvent(event);
    }

    private void publishAccountCreateDoneToCreditCard(CreateAccountForCreditCardRequestDto account, String transactionId) {

        AccountCreateDoneEventForCreditCard event = new AccountCreateDoneEventForCreditCard((transactionId), account, new Date());
        publisher.publishEvent(event);
    }


    private void publishAccountCreateDoneToHgs(CreateAccountForHgsRequestDto account, String transactionId) {

        AccountCreateDoneEventForHgs event = new AccountCreateDoneEventForHgs((transactionId), account, new Date());
        publisher.publishEvent(event);
    }

    private void publishCanceledAccountCreate(Account account, String transactionId) {
        CreateAccountRequestDto createAccountRequestDto = AccountMapper.INSTANCE.accountToCreateAccountRequestDto(account);
        AccountCancelEvent event = new AccountCancelEvent(transactionId, createAccountRequestDto, new Date());
        publisher.publishEvent(event);

    }

    private void publishCanceledTransactionAccountCreate(Account account, String transactionId) {
        CreateAccountRequestDto createAccountRequestDto = AccountMapper.INSTANCE.accountToCreateAccountRequestDto(account);
        AccountTransactionCancelEvent event = new AccountTransactionCancelEvent(transactionId, createAccountRequestDto, new Date());
        publisher.publishEvent(event);

    }

    // 6 digit random long number
    private Long createAccountNumber() {
        return (long) (Math.random() * 100000);
    }

    @Transactional
    public void deleteAccount(Long id) {
        accountDao.deleteById(id);
    }

    @Transactional
    public void deleteAccountForFlexibleAccountTransactionFail(String transactionId, FlexAccountCancelEvent event) {
        Optional<Account> account = accountDao.findByAccountNumber(event.getAccount().getAccountNumber());

        if (account.isPresent()) {

            try {

                accountDao.delete(account.get());
                publishCanceledTransactionAccountCreate(account.get(), transactionId);


            } catch (Exception e) {
                publishCanceledTransactionAccountCreate(account.get(), transactionId);
            }
        }
    }

    @Transactional()
    public void createAccountForCredit(CreateAccountForCreditRequestDto createAccountForCreditRequestDto, String transactionId) {
        Account account = AccountMapper.INSTANCE.createAccountForCreditToAccount(createAccountForCreditRequestDto);
        account.setAccountNumber(createAccountNumber());

        //Burada bi yerde kontrol yapılıp hata verilecek ki kayıt olmasın
        Optional<Account> optionalAccount = accountDao.findByAccountNumber(account.getAccountNumber());
        if (optionalAccount.isPresent()) {
            publishCanceledTransactionAccountCreate(account, transactionId);
            throw new AccountException("Account is already exists");
        }

        account.setAccountStatus(Account.AccountStatus.CREATED);
        accountDao.save(account);
        createAccountForCreditRequestDto.setAccountNumber(account.getAccountNumber());
        publishAccountCreateDoneToCredit(createAccountForCreditRequestDto, transactionId);


    }

    @Transactional
    public void deleteAccountForCreditTransactionFail(String transactionId, CreditCancelEvent event) {
        Optional<Account> account = accountDao.findByAccountNumber(event.getAccount().getAccountNumber());

        if (account.isPresent()) {

            try {

                accountDao.delete(account.get());
                publishCanceledTransactionAccountCreate(account.get(), transactionId);


            } catch (Exception e) {
                publishCanceledTransactionAccountCreate(account.get(), transactionId);
            }
        }
    }


    //CREDIT CARD
    @Transactional()
    public void createAccountForCreditCard(CreateAccountForCreditCardRequestDto createAccountForCreditCardRequestDto, String transactionId) {
        Account account = AccountMapper.INSTANCE.createAccountForCreditCardToAccount(createAccountForCreditCardRequestDto);
        account.setAccountNumber(createAccountNumber());

        //Burada bi yerde kontrol yapılıp hata verilecek ki kayıt olmasın
        Optional<Account> optionalAccount = accountDao.findByAccountNumber(account.getAccountNumber());
        if (optionalAccount.isPresent()) {
            publishCanceledTransactionAccountCreate(account, transactionId);
            throw new AccountException("Account is already exists");
        }

        account.setAccountStatus(Account.AccountStatus.CREATED);
        accountDao.save(account);
        createAccountForCreditCardRequestDto.setAccountNumber(account.getAccountNumber());
        publishAccountCreateDoneToCreditCard(createAccountForCreditCardRequestDto, transactionId);


    }

    @Transactional
    public void deleteAccountForCreditCardTransactionFail(String transactionId, CreditCardCancelEvent event) {
        Optional<Account> account = accountDao.findByAccountNumber(event.getAccount().getAccountNumber());

        if (account.isPresent()) {

            try {

                accountDao.delete(account.get());
                publishCanceledTransactionAccountCreate(account.get(), transactionId);


            } catch (Exception e) {
                publishCanceledTransactionAccountCreate(account.get(), transactionId);
            }
        }
    }


    //HGS

    @Transactional()
    public void createAccountForHgs(CreateAccountForHgsRequestDto createAccountForHgsRequestDto, String transactionId) {
        Account account = AccountMapper.INSTANCE.createAccountForHgsToAccount(createAccountForHgsRequestDto);
        account.setAccountNumber(createAccountNumber());

        //Burada bi yerde kontrol yapılıp hata verilecek ki kayıt olmasın
        Optional<Account> optionalAccount = accountDao.findByAccountNumber(account.getAccountNumber());
        if (optionalAccount.isPresent()) {
            publishCanceledTransactionAccountCreate(account, transactionId);
            throw new AccountException("Account is already exists");
        }

        account.setAccountStatus(Account.AccountStatus.CREATED);
        accountDao.save(account);
        createAccountForHgsRequestDto.setAccountNumber(account.getAccountNumber());
        publishAccountCreateDoneToHgs(createAccountForHgsRequestDto, transactionId);


    }

    @Transactional
    public void deleteAccountForHgsTransactionFail(String transactionId, HgsCancelEvent event) {
        Optional<Account> account = accountDao.findByAccountNumber(event.getAccount().getAccountNumber());

        if (account.isPresent()) {

            try {

                accountDao.delete(account.get());
                publishCanceledTransactionAccountCreate(account.get(), transactionId);


            } catch (Exception e) {
                publishCanceledTransactionAccountCreate(account.get(), transactionId);
            }
        }
    }
}
