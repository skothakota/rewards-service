package com.retailer.rewardsservice.controller;

import com.retailer.rewardsservice.model.CustomerTransaction;
import com.retailer.rewardsservice.service.CustomerTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class CustomerTransactionController {

    @Autowired
    private CustomerTransactionService transactionService;

    @GetMapping("/customer")
    public ResponseEntity<List<CustomerTransaction>> getTransactionsByCustomer(@RequestParam String customerId){
        List<CustomerTransaction> transactions = this.transactionService.getTransactionsByCustomer(customerId);

         return new ResponseEntity(transactions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerTransaction> createTransaction(@RequestBody CustomerTransaction transaction){
        CustomerTransaction transactions = this.transactionService.createTransaction(transaction);

        return new ResponseEntity(transactions, HttpStatus.CREATED);
    }
}
