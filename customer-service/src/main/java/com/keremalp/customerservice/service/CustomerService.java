package com.keremalp.customerservice.service;

import com.keremalp.customerservice.converter.CustomerMapper;
import com.keremalp.customerservice.dao.CustomerDao;
import com.keremalp.customerservice.dto.events.cancel.AccountCancelEvent;
import com.keremalp.customerservice.dto.events.cancel.CancelTransactionEvent;
import com.keremalp.customerservice.dto.events.create.*;
import com.keremalp.customerservice.dto.events.done.CifOpenCreatedDoneEvent;
import com.keremalp.customerservice.dto.product.*;
import com.keremalp.customerservice.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerDao customerDao;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public void createCustomer(CreateCifOpenRequestDto createCifOpenRequestDto, String transactionId) {
        Customer customer = CustomerMapper.INSTANCE.createCifOpenRequestToCustomer(createCifOpenRequestDto);
        customer.setCustomerNumber(createCustomerNumber());
        customer.setCustomerStatus(Customer.CustomerStatus.CREATED);
        Customer save = customerDao.save(customer);
        publishCustomerCreateDone(save, transactionId);
    }

    private void publishCustomerCreateDone(Customer customer, String transactionId) {
        CreateCifOpenRequestDto createCifOpenRequestDto = CustomerMapper.INSTANCE.customerToCreateCifOpen(customer);
        CifOpenCreatedDoneEvent event = new CifOpenCreatedDoneEvent((transactionId), createCifOpenRequestDto, new Date());
        publisher.publishEvent(event);
    }


    @Transactional
    public void createCustomerForAccount(CreateCifOpenRequestDto createCifOpenRequestDto, String transactionId) {
        Customer customer = CustomerMapper.INSTANCE.createCifOpenRequestToCustomer(createCifOpenRequestDto);
        customer.setCustomerNumber(createCustomerNumber());
        customer.setCustomerStatus(Customer.CustomerStatus.CREATED);
        Customer save = customerDao.save(customer);
        publishCustomerCreateDoneForAccount(save, transactionId);
    }

    private void publishCustomerCreateDoneForAccount(Customer customer, String transactionId) {
        CreateCifOpenForAccountDto createCifOpenForAccountDto = CustomerMapper.INSTANCE.customerToCreateCifOpenForAccountDto(customer);
        CreateAccountForNotExistCustomerEvent event = new CreateAccountForNotExistCustomerEvent((transactionId), createCifOpenForAccountDto, new Date());
        publisher.publishEvent(event);
    }

    @Transactional
    public void createCustomerForFlexibleAccount(CreateCifOpenRequestDto createCifOpenRequestDto, String transactionId) {
        Customer customer = CustomerMapper.INSTANCE.createCifOpenRequestToCustomer(createCifOpenRequestDto);
        customer.setCustomerNumber(createCustomerNumber());
        customer.setCustomerStatus(Customer.CustomerStatus.CREATED);
        Customer save = customerDao.save(customer);
        publishCustomerCreateDoneForFlexibleAccount(save, transactionId);
    }

    private void publishCustomerCreateDoneForFlexibleAccount(Customer customer, String transactionId) {
        CreateCifOpenForFlexibleAccountDto createCifOpenForFlexibleAccountDto = CustomerMapper.INSTANCE.cToFlex(customer);
        CreateFlexibleAccountForNotExistCustomerEvent event = new CreateFlexibleAccountForNotExistCustomerEvent((transactionId), createCifOpenForFlexibleAccountDto, new Date());
        publisher.publishEvent(event);
    }

    @Transactional
    public void createCustomerForCredit(CreateCifOpenForCreditDto createCifOpenForCreditDto, String transactionId) {
        Customer customer1 = new Customer();

        customer1.setId( createCifOpenForCreditDto.getId() );
        customer1.setName( createCifOpenForCreditDto.getCustomerName() );
        customer1.setSurname( createCifOpenForCreditDto.getCustomerSurname() );
        customer1.setEmail( createCifOpenForCreditDto.getCustomerEmail() );
        customer1.setPhone( createCifOpenForCreditDto.getPhoneNumber() );
        customer1.setAddress( createCifOpenForCreditDto.getAddress() );
        customer1.setCustomerNumber(createCustomerNumber());

        customer1.setCustomerNumber(createCustomerNumber());
        customer1.setCustomerStatus(Customer.CustomerStatus.CREATED);
        customerDao.save(customer1);
        createCifOpenForCreditDto.setCustomerNumber(customer1.getCustomerNumber());
        publishCustomerCreateDoneForCredit(createCifOpenForCreditDto, transactionId);
    }

    private void publishCustomerCreateDoneForCredit(CreateCifOpenForCreditDto customer, String transactionId) {

        CreateCreditForNotExistCustomerEvent event = new CreateCreditForNotExistCustomerEvent((transactionId), customer, new Date());
        publisher.publishEvent(event);
    }

    //Credit Card


    @Transactional
    public void createCustomerForCreditCard(CreateCifOpenForCreditCardDto createCifOpenForCreditCardDto, String transactionId) {
        Customer customer1 = new Customer();

        customer1.setId( createCifOpenForCreditCardDto.getId() );
        customer1.setName( createCifOpenForCreditCardDto.getCustomerName() );
        customer1.setSurname( createCifOpenForCreditCardDto.getCustomerSurname() );
        customer1.setEmail( createCifOpenForCreditCardDto.getCustomerEmail() );
        customer1.setPhone( createCifOpenForCreditCardDto.getPhoneNumber() );
        customer1.setAddress( createCifOpenForCreditCardDto.getAddress() );
        customer1.setCustomerNumber(createCustomerNumber());

        customer1.setCustomerNumber(createCustomerNumber());
        customer1.setCustomerStatus(Customer.CustomerStatus.CREATED);
        customerDao.save(customer1);
        createCifOpenForCreditCardDto.setCustomerNumber(customer1.getCustomerNumber());
        publishCustomerCreateDoneForCreditCard(createCifOpenForCreditCardDto, transactionId);
    }

    private void publishCustomerCreateDoneForCreditCard(CreateCifOpenForCreditCardDto customer, String transactionId) {

        CreateCreditCardForNotExistCustomerEvent event = new CreateCreditCardForNotExistCustomerEvent((transactionId), customer, new Date());
        publisher.publishEvent(event);
    }


    //HGS

    @Transactional
    public void createCustomerForHgs(CreateCifOpenForHgsDto createCifOpenForHgsDto, String transactionId) {
        Customer customer1 = new Customer();

        customer1.setId( createCifOpenForHgsDto.getId() );
        customer1.setName( createCifOpenForHgsDto.getCustomerName() );
        customer1.setSurname( createCifOpenForHgsDto.getCustomerSurname() );
        customer1.setEmail( createCifOpenForHgsDto.getCustomerEmail() );
        customer1.setPhone( createCifOpenForHgsDto.getPhoneNumber() );
        customer1.setAddress( createCifOpenForHgsDto.getAddress() );
        customer1.setCustomerNumber(createCustomerNumber());

        customer1.setCustomerNumber(createCustomerNumber());
        customer1.setCustomerStatus(Customer.CustomerStatus.CREATED);
        customerDao.save(customer1);
        createCifOpenForHgsDto.setCustomerNumber(customer1.getCustomerNumber());
        publishCustomerCreateDoneForHgs(createCifOpenForHgsDto, transactionId);
    }

    private void publishCustomerCreateDoneForHgs(CreateCifOpenForHgsDto customer, String transactionId) {

        CreateHgsForNotExistCustomerEvent event = new CreateHgsForNotExistCustomerEvent((transactionId), customer, new Date());
        publisher.publishEvent(event);
    }


    private void publishCanceledAccountCreate(CancelTransactionEvent transactionEvent) {
        publisher.publishEvent(transactionEvent);
    }


    private Long createCustomerNumber() {
        //random long number 6 digits
        return (long) (Math.random() * 1000000);
    }

    public Customer getCustomer(Long customerNumber) {
        return customerDao.findByCustomerNumber(customerNumber);
    }

    @Transactional
    public void deleteCustomerForAccountTransactionFail(String transactionId, AccountCancelEvent event) {


        Customer customer = customerDao.findByCustomerNumber(event.getAccount().getCustomerNumber());
        customerDao.delete(customer);
        CancelTransactionEvent cancelTransactionEvent = new CancelTransactionEvent(transactionId, event, new Date());
        publishCanceledAccountCreate(cancelTransactionEvent);


    }
}
