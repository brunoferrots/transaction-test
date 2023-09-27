package com.brunoferro.transactiontest.model;

import com.brunoferro.transactiontest.dto.CustomerDTO;

public class Customer {
    private String name;
    private String email;
    private Integer cpf;

    public Customer(String name, String email, Integer cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public Customer(CustomerDTO customerDTO) throws Exception {

        this.name = customerDTO.name();
        this.email = customerDTO.email();
        this.cpf = customerDTO.cpf();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }


}