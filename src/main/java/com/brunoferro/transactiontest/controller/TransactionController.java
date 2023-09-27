package com.brunoferro.transactiontest.controller;

import com.brunoferro.transactiontest.dto.TransactionDTO;
import com.brunoferro.transactiontest.model.Transaction;
import com.brunoferro.transactiontest.service.DbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @PostMapping
    public ResponseEntity<Transaction> makeTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        var company = DbService.findCompany(transactionDTO.cnpj());
        var customer = DbService.findCustomer(transactionDTO.cpf());
        var value = transactionDTO.value();

        var transaction = DbService.resolveTransaction(company, customer, value);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }


}
