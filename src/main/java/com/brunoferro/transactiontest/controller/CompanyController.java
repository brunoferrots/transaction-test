package com.brunoferro.transactiontest.controller;

import com.brunoferro.transactiontest.dto.CompanyDTO;
import com.brunoferro.transactiontest.model.Company;
import com.brunoferro.transactiontest.service.DbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/companies")
public class CompanyController {

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies() {
        var listCompanies = DbService.listCompanies();
        return ResponseEntity.status(HttpStatus.OK).body(listCompanies);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<Object> getCompany(@PathVariable(value = "cnpj") BigInteger cnpj) throws Exception {
        Optional<Company> company = Optional.ofNullable(DbService.findCompany(cnpj));
        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody CompanyDTO companyDTO) throws Exception {
        var company = new Company(companyDTO);
        DbService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }
}
