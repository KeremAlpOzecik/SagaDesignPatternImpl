package com.keremalp.managementservice.controller;

import com.keremalp.managementservice.dto.products.account.CreateAccountRequestDto;
import com.keremalp.managementservice.dto.products.cif.CreateCifOpenRequestDto;
import com.keremalp.managementservice.dto.products.credit.CreateCreditDto;
import com.keremalp.managementservice.dto.products.credit_card.CreateCreditCardDto;
import com.keremalp.managementservice.dto.products.flex_acc.CreateFlexibleAccountDto;
import com.keremalp.managementservice.dto.products.hgs.CreateHgsDto;
import com.keremalp.managementservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/customer/create")
    public void createCifTransaction(@RequestBody CreateCifOpenRequestDto requestDto) {
        transactionService.createCifOpenTransaction(requestDto);
    }

    @PostMapping("/account/create")
    public void createAccountTransaction(@RequestBody CreateAccountRequestDto requestDto) {
        transactionService.createAccountTransaction(requestDto);
    }

    @PostMapping("/flexaccount/create")
    public void createFlexAccountTransaction(@RequestBody CreateFlexibleAccountDto requestDto) {
        transactionService.createFlexibleAccountTransaction(requestDto);
    }

    @PostMapping("/credit/create")
    public void createCreditTransaction(@RequestBody CreateCreditDto requestDto) {
        transactionService.createCreditTransaction(requestDto);
    }

    @PostMapping("/creditcard/create")
    public void createCreditCardTransaction(@RequestBody CreateCreditCardDto requestDto) {
        transactionService.createCreditCardTransaction(requestDto);
    }

    @PostMapping("/hgs/create")
    public void createHgsTransaction(@RequestBody CreateHgsDto requestDto) {
        transactionService.createHgsTransaction(requestDto);
    }

}

