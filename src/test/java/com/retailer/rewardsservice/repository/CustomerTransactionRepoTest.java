package com.retailer.rewardsservice.repository;

import com.retailer.rewardsservice.model.CustomerTransaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class CustomerTransactionRepoTest {

    private CustomerTransactionRepo transactionRepo;

    public CustomerTransaction transaction;
    @Before
    public void setUp() {
        this.transactionRepo = new CustomerTransactionRepo();
        this.transaction = new CustomerTransaction("custTran5",
                LocalDate.now().minusMonths(1), 200L, "cust2");
    }

    @Test
    public void createTransaction() {
        this.transactionRepo.createTransaction(transaction);
        List<CustomerTransaction> transactions = this.transactionRepo.getTransactions();

        Assert.assertEquals(5, transactions.size());
    }

    @Test
    public void containsKey() {
        boolean isTransactionIdContains = this.transactionRepo
                .containsKey("custTran5");

        Assert.assertFalse(isTransactionIdContains);
    }

    @Test
    public void getTransactions() {
        this.transactionRepo.createTransaction(transaction);

        List<CustomerTransaction> transactions = this.transactionRepo.getTransactions();
        Assert.assertEquals(5, transactions.size());
    }

    @Test
    public void getTransactionsByCustomer() {
        this.transactionRepo.createTransaction(transaction);

        List<CustomerTransaction> transactions = this.transactionRepo
                .getTransactionsByCustomer("cust1");
        Assert.assertEquals(2, transactions.size());
    }
}