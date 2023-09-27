package com.brunoferro.transactiontest.service;

import com.brunoferro.transactiontest.model.Company;
import com.brunoferro.transactiontest.model.Customer;
import com.brunoferro.transactiontest.model.Transaction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class DbService {
    private static final List<Company> companies = new ArrayList<>();
    private static final List<Customer> customers = new ArrayList<>();


    public static Transaction resolveTransaction(Company company, Customer customer, Double value) throws Exception {
        if (value > 0) {
            return Company.deposit(company, customer, value);
        } else {
            return Company.withdraw(company, customer, value);
        }
    }

    public static void createCompany(Company company) throws Exception{
        var cnpj = company.getCnpj();
        var cpnjExist = cnpjExists(cnpj);
        if (cpnjExist) {
            throw new Exception("CNPJ already exists");
        }
        companies.add(company);
    }

    public static List<Company> listCompanies() {
        return companies;
    }

    public static Company findCompany(BigInteger cnpj) throws Exception{
        var cpnjExist = cnpjExists(cnpj);
        if (!cpnjExist) {
            throw new Exception("CNPJ not exists");
        }

        return companies.stream()
                .filter(company -> company.getCnpj().equals(cnpj))
                .toList()
                .get(0);
    }

    public static void deleteCompany(BigInteger cnpj) throws Exception {
        var cpnjExist = cnpjExists(cnpj);
        if (!cpnjExist) {
            throw new Exception("CNPJ not exists");
        }

        companies.removeIf(company -> company.getCnpj().equals(cnpj));
    }

    public static void createCustomer(Customer customer) throws Exception{
        var cpf = customer.getCpf();
        var cpfExists = cpfExists(cpf);
        if (cpfExists) {
            throw new Exception("CPF Already exists");
        }
        customers.add(customer);
    }

    public static Customer findCustomer(Integer cpf) throws Exception {
        var cpfExists = cpfExists(cpf);
        if (!cpfExists) {
            throw new Exception("CPF not exists");
        }

        return customers.stream()
                .filter(customer -> customer.getCpf().equals(cpf))
                .toList()
                .get(0);
    }

    public static List<Customer> listCustomers() {
        return customers;
    }

    public static void deleteCustomer(Integer cpf) throws Exception {
        var cpfExists = cpfExists(cpf);
        if (!cpfExists) {
            throw new Exception("CPF not exists");
        }

        customers.removeIf(customer -> customer.getCpf().equals(cpf));
    }

    private static Boolean cnpjExists(BigInteger cnpj) {
        return companies.stream().anyMatch(company -> company.getCnpj().equals(cnpj));
    }

    private static Boolean cpfExists(Integer cpf) {
        return customers.stream().anyMatch(customer -> customer.getCpf().equals(cpf));
    }

}
