package com.brunoferro.transactiontest.dto;

import java.math.BigInteger;

public record TransactionDTO(Integer cpf, BigInteger cnpj, Double value) {
}
