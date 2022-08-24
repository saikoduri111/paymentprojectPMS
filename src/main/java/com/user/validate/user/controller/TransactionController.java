package com.user.validate.user.controller;

import com.user.validate.user.exception.CustomerIdNotFoundException;
import com.user.validate.user.exception.InvalidTransactionIdException;
import com.user.validate.user.exception.TransactionException;
import com.user.validate.user.model.TempTransactionDetails;
import com.user.validate.user.model.Transaction;
import com.user.validate.user.service.Status;
import com.user.validate.user.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @CrossOrigin({"http://localhost:4200/", "http://localhost:4200/"})
    @GetMapping("transactionDetails/{transactionId}")
    public ResponseEntity<Transaction> getTransactionDetails(@PathVariable("transactionId") int transactionId) throws InvalidTransactionIdException {

        Transaction transaction = transactionService.getTransactionDetails(transactionId);
        return new ResponseEntity<>(transaction, HttpStatus.OK);

    }

    @CrossOrigin({"http://localhost:4200/", "http://localhost:4200/"})
    @PostMapping("/addTransactionDetails")
    public ResponseEntity<Integer> setTransactionDetails(@RequestBody Transaction transaction) throws Exception {

        Transaction transaction1 = transactionService.setTransactionDetails(transaction);
        return new ResponseEntity<>(transaction1.getTransactionId(), HttpStatus.CREATED);

    }

}
