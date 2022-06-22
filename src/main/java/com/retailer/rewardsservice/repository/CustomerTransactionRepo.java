package com.retailer.rewardsservice.repository;

import com.retailer.rewardsservice.model.CustomerTransaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CustomerTransactionRepo {

    public Map<String, CustomerTransaction> transactionMap;
    public CustomerTransactionRepo() {
        this.transactionMap = new HashMap<>();
        this.transactionMap.put("custTran1", new CustomerTransaction("custTran1",
                LocalDate.now(), 100L, "cust1"));
        this.transactionMap.put("custTran2", new CustomerTransaction("custTran2",
                LocalDate.now().minusMonths(1), 200L, "cust2"));
        this.transactionMap.put("custTran3", new CustomerTransaction("custTran3",
                LocalDate.now().minusMonths(2), 300L, "cust3"));
        this.transactionMap.put("custTran4", new CustomerTransaction("custTran4",
                LocalDate.now().minusMonths(2), 150L, "cust1"));

    }

    public void createTransaction(CustomerTransaction transaction) {
        this.transactionMap.put(transaction.getTransactionId(), transaction);
    }

    public boolean containsKey(String transactionId) {

        return this.transactionMap.get(transactionId) != null;
    }

    public List<CustomerTransaction> getTransactions() {
        return this.transactionMap
                .entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public List<CustomerTransaction> getTransactionsByCustomer(String customerId) {
        return this.transactionMap
                .entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(v -> customerId.equals(v.getCustomerId()))
                .collect(Collectors.toList());
    }
}
