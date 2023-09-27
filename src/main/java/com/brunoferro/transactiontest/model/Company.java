package com.brunoferro.transactiontest.model;

import com.brunoferro.transactiontest.dto.CompanyDTO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Company  {
    private BigInteger cnpj;
    private String name;
    private Double balance;
    private Double rate;

    private static final Map<Customer, Double> totalBalanceByUser = new HashMap<>();
    private static final List<Transaction> transactions = new ArrayList<>();

    public Company(BigInteger cnpj, String name, Double rate) {
        this.cnpj = cnpj;
        this.name = name;
        this.rate = rate;
        this.balance = 0.0;
    }

    public Company(CompanyDTO companyDTO) {
        this.cnpj = companyDTO.cnpj();
        this.name = companyDTO.name();
        this.rate = companyDTO.rate();
        this.balance = 0.0;
    }

    public static Transaction deposit(Company company, Customer customer, Double value) throws Exception {
        if (value < 0.1) {
            throw new Exception("The value can't be less than 0.1");
        }

        company.putInBalance(value  - value * company.getRate());

        for (Customer balanceByCustomer : totalBalanceByUser.keySet()) {
            if (customer.getCpf().equals(balanceByCustomer.getCpf())) {
                var valueOld = totalBalanceByUser.get(balanceByCustomer);
                var valueNew = valueOld + (value * company.getRate());
                totalBalanceByUser.replace(balanceByCustomer, valueOld, valueNew);
            }
        }
        totalBalanceByUser.put(customer, value);
        var transaction = new Transaction(company, customer, value);
        transactions.add(transaction);
        return transaction;
    }

    public static Transaction withdraw(Company company, Customer customer, Double value) throws Exception {
        Double totalBalance = updateBalanceCompanyByCustomer(customer);

        if (value > totalBalance) {
            throw new Exception("The value can't be less than total balance");
        }

        company.takeOutBalance(value);

        var transaction = new Transaction(company, customer, value);
        transactions.add(transaction);
        return transaction;
    }

    private static Double updateBalanceCompanyByCustomer(Customer customer) {
        AtomicReference<Double> totalBalance = new AtomicReference<>(0.0);

        for (Customer balanceByCustomer : totalBalanceByUser.keySet()) {
            if (customer.getCpf().equals(balanceByCustomer.getCpf())) {
                totalBalance.updateAndGet(v -> v + totalBalanceByUser.get(balanceByCustomer));
            }
        }

        return totalBalance.get();
    }

    public void takeOutBalance(Double value) throws Exception {
        if (value > balance) {
            throw new Exception("The value can't be less than balance");
        }

        this.balance += value;
    }

    public void putInBalance(Double value) throws Exception{
        if (value < 0) {
            throw new Exception("The value can't be less than zero");
        }
        this.balance += value;
    }


    public BigInteger getCnpj() {
        return cnpj;
    }

    public void setCnpj(BigInteger cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
