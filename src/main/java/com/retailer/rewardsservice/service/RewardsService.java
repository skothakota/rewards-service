package com.retailer.rewardsservice.service;

import com.retailer.rewardsservice.exception.ResourceNotFoundError;
import com.retailer.rewardsservice.model.CustomerTransaction;
import com.retailer.rewardsservice.model.Rewards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardsService {

    @Value("${base.reward.limit}")
    private int baseRewardLimit;
    @Value("${base.reward.multiplier}")
    private int baseRewardMultiplier;
    @Value("${multi.reward.limit}")
    private int multiRewardLimit;
    @Value("${multi.reward.multiplier}")
    private int multiRewardMultiplier;

    @Autowired
    public CustomerTransactionService customerTransactionService;


    public Rewards getRewardByCustomer(String customerId) throws ResourceNotFoundError {

        CustomerTransaction transaction = this.customerTransactionService.isValidClient(customerId);

        if(transaction == null) {
            throw new ResourceNotFoundError("Customer with "+ customerId + " is invalid");
        }
        List<CustomerTransaction> transactions = this.customerTransactionService
                .getTransactionsByCustomer(customerId);
        float totalPoints = transactions.stream()
                .map(t -> rewardsPerTransaction(t))
                .collect(Collectors.reducing(0.0f, (total, reward) -> total + reward));

        return new Rewards(customerId, totalPoints);

    }

    private float rewardsPerTransaction(CustomerTransaction t) {
        long amount = t.getTransactionAmount();
        return Math.max(amount - multiRewardLimit, 0) * multiRewardMultiplier +
                Math.min(Math.max(amount - baseRewardLimit, 0) * 1, baseRewardLimit) * baseRewardMultiplier;
    }
}
