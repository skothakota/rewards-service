package com.retailer.rewardsservice.controller;

import com.retailer.rewardsservice.exception.ResourceNotFoundError;
import com.retailer.rewardsservice.model.Rewards;
import com.retailer.rewardsservice.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @GetMapping(path= "/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rewards> getRewardByCustomer(@PathVariable String customerId)
            throws ResourceNotFoundError {
         Rewards rewards = this.rewardsService.getRewardByCustomer(customerId);

         return new ResponseEntity(rewards, HttpStatus.OK);
    }
}
