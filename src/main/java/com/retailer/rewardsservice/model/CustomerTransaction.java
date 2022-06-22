package com.retailer.rewardsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTransaction {
    private String transactionId;
    private LocalDate transactionDate;
    @NonNull
    private long transactionAmount;
    private String customerId;
}