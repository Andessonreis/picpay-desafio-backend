package com.github.andessonreis.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.andessonreis.api.domain.transaction.Transaction;
import com.github.andessonreis.api.domain.transaction.dto.TransactionDTO;
import com.github.andessonreis.api.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    
    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        var transactions = transactionService.listTransaction();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

   
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/transaction")
    public ResponseEntity<?> createTransaction(@RequestBody @Valid TransactionDTO transactionDTO) throws Exception{

        Transaction newTransaction = transactionService.createTransaction(transactionDTO);

        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }
}


