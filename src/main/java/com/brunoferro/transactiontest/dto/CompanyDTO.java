package com.brunoferro.transactiontest.dto;

import java.math.BigInteger;

public record CompanyDTO(BigInteger cnpj, String name, Double rate) {
}
