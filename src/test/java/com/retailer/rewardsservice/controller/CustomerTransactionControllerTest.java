package com.retailer.rewardsservice.controller;

import com.retailer.rewardsservice.model.CustomerTransaction;
import com.retailer.rewardsservice.service.CustomerTransactionService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CustomerTransactionControllerTest {

    public static final String CUSTOMER_ID = "custId";

    @InjectMocks
    private  CustomerTransactionController transactionController;
    @Mock
    private CustomerTransactionService transactionService;

    private CustomerTransaction transaction = new CustomerTransaction("custTran5",
            LocalDate.now().minusMonths(1), 200L, "custId");
    private List<CustomerTransaction> transactions =
            Arrays.asList(this.transaction);

    @BeforeAll
    public void init(){
        this.transactionController = new CustomerTransactionController();
    }

    @DisplayName("GET request for Transaction by customerId")
    @Test
    public void getTransactionsByCustomer() {
        Mockito.doReturn(this.transactions)
                .when(this.transactionService)
                .getTransactionsByCustomer(CUSTOMER_ID);

        ResponseEntity<List<CustomerTransaction>> response = this.transactionController
                .getTransactionsByCustomer(CUSTOMER_ID);
        List<CustomerTransaction> transactions = response.getBody();
        Assertions.assertAll("response",
                () -> assertEquals(200, response.getStatusCodeValue()),
                () -> assertEquals(1, transactions.size()),
                () -> assertEquals(CUSTOMER_ID, transactions.get(0).getCustomerId())
        );
        Mockito.verify(this.transactionService).getTransactionsByCustomer(CUSTOMER_ID);
    }

    @DisplayName("POST request Create Transaction")
    @Test
    public void createTransaction() {
        Mockito.doReturn(this.transaction)
                .when(this.transactionService)
                .createTransaction(this.transaction);

        ResponseEntity<CustomerTransaction> response = this.transactionController
                .createTransaction(this.transaction);
        CustomerTransaction transaction = response.getBody();
        Assertions.assertAll("response",
                () -> assertEquals(201, response.getStatusCodeValue()),
                () -> assertEquals(CUSTOMER_ID, transaction.getCustomerId())
        );
        Mockito.verify(this.transactionService).createTransaction(this.transaction);
    }
}