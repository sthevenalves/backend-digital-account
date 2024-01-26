package com.picpaysimplified.controllers;

import com.picpaysimplified.domain.transaction.Transaction;
import com.picpaysimplified.dtos.TransactionDTO;
import com.picpaysimplified.servicies.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createdTransaction (@RequestBody TransactionDTO dto) throws Exception {
        Transaction newTransaction = transactionService.createTransaction(dto);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }
}
