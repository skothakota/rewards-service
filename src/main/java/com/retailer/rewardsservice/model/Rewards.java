package com.retailer.rewardsservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rewards {
    private String customerId;
    private float totalRewards;
}
