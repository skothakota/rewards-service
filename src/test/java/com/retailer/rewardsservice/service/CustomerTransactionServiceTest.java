package com.retailer.rewardsservice.service;

import com.retailer.rewardsservice.model.CustomerTransaction;
import com.retailer.rewardsservice.repository.CustomerTransactionRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerTransactionServiceTest {

    public static final String CUSTOMER_ID = "custId";

    @InjectMocks
    private CustomerTransactionService transactionService;
    @Mock
    private CustomerTransactionRepo transactionRepo;

    private CustomerTransaction transaction = new CustomerTransaction("custTran5",
            LocalDate.now().minusMonths(1), 200L, "custId");
    private List<CustomerTransaction> transactions =
            Arrays.asList(this.transaction);

    @BeforeAll
    public void init() {
        this.transactionService = new CustomerTransactionService();

    }

    @DisplayName("GET request for Transaction by customerId")
    @Test
    public void createTransaction() {
        Mockito.doNothing()
                .when(this.transactionRepo)
                .createTransaction(Mockito.any(CustomerTransaction.class));

        CustomerTransaction transaction = this.transactionService
                .createTransaction(this.transaction);
        Assertions.assertAll("create",
                () -> assertEquals(CUSTOMER_ID, transaction.getCustomerId())
        );
        Mockito.verify(this.transactionRepo).createTransaction(this.transaction);
    }

    @DisplayName("ClientId Validation")
    @Test
    public void isValidClient() {
        Mockito.doReturn(this.transactions)
                .when(this.transactionRepo)
                .getTransactionsByCustomer(CUSTOMER_ID);

        CustomerTransaction transaction = this.transactionService
                .isValidClient(CUSTOMER_ID);
        Assert.assertEquals(CUSTOMER_ID, transaction.getCustomerId());
        Mockito.verify(this.transactionRepo)
                .getTransactionsByCustomer(CUSTOMER_ID);
    }

    @DisplayName("Invalid ClientId Validation")
    @Test
    public void isValidClientWithInvalidClientId() {
        Mockito.doReturn(null)
                .when(this.transactionRepo)
                .getTransactionsByCustomer("INVALID_CLIENT_ID");

        CustomerTransaction transaction = this.transactionService
                .isValidClient(CUSTOMER_ID);
        Assert.assertNull(transaction);
        Mockito.verify(this.transactionRepo)
                .getTransactionsByCustomer(Mockito.anyString());
    }

    @DisplayName("All existing transactions")
    @Test
    public void getTransactions() {
        Mockito.doReturn(this.transactions)
                .when(this.transactionRepo)
                .getTransactions();

        List<CustomerTransaction> transactions = this.transactionService
                .getTransactions();
        Assertions.assertAll("transactions",
                () -> assertEquals(1, transactions.size()),
                () -> assertEquals(CUSTOMER_ID, transactions.get(0).getCustomerId())
        );
        Mockito.verify(this.transactionRepo).getTransactions();
    }

    @DisplayName("Transactions by CustomerId")
    @Test
    public void getTransactionsByCustomer() {
        Mockito.doReturn(this.transactions)
                .when(this.transactionRepo)
                .getTransactionsByCustomer(CUSTOMER_ID);

        List<CustomerTransaction> transactions = this.transactionService
                .getTransactionsByCustomer(CUSTOMER_ID);
        Assert.assertEquals(1, transactions.size());
        Assert.assertEquals(CUSTOMER_ID, transactions.get(0).getCustomerId());
        Assertions.assertAll("transactions",
                () -> assertEquals(1, transactions.size()),
                () -> assertEquals(CUSTOMER_ID, transactions.get(0).getCustomerId())
        );
        Mockito.verify(this.transactionRepo).getTransactionsByCustomer(CUSTOMER_ID);
    }
}