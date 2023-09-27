package com.brunoferro.transactiontest.model;

import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    private final UUID id;
    private final Company company;
    private final Customer customer;
    private final Double value;
    private final LocalDate date;

    public Transaction(Company company, Customer customer, Double value) {
        this.company = company;
        this.customer = customer;
        this.value = value;
        this.id = UUID.randomUUID();
        this.date = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }
}
