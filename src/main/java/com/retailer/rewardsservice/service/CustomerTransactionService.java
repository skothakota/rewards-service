package com.retailer.rewardsservice.service;

import com.retailer.rewardsservice.model.CustomerTransaction;
import com.retailer.rewardsservice.repository.CustomerTransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerTransactionService {

    @Autowired
    public CustomerTransactionRepo transactionRepo;

    //TODO: ideally defaulted customerTransaction object will be returned.
    public CustomerTransaction createTransaction(CustomerTransaction customerTransaction) {
        this.transactionRepo.createTransaction(customerTransaction);
        return customerTransaction;
    }

    public CustomerTransaction isValidClient(String customerId) {
        return this.transactionRepo.getTransactionsByCustomer(customerId)
                .stream().findFirst().orElse(null);
    }

    public List<CustomerTransaction> getTransactions(){
      return this.transactionRepo.getTransactions();
    }

    public List<CustomerTransaction> getTransactionsByCustomer(String customerId){
        return this.transactionRepo
                .getTransactionsByCustomer(customerId);
    }
}
