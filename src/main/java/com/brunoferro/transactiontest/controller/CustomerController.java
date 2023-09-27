package com.brunoferro.transactiontest.controller;

import com.brunoferro.transactiontest.dto.CustomerDTO;
import com.brunoferro.transactiontest.model.Customer;
import com.brunoferro.transactiontest.service.DbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        var listCustomers = DbService.listCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(listCustomers);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Object> getCustomer(@PathVariable(value = "cpf") Integer cpf) throws Exception {
        Optional<Customer> customer = Optional.ofNullable(DbService.findCustomer(cpf));
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDTO customerDTO) throws Exception {
        var customer = new Customer(customerDTO);
        DbService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
}
