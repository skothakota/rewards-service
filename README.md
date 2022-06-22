# rewards-service
Rewards Service mainly responsible in calculation reward point to a set of retail customers based to their purchase based on business rules including date range and total spending 


#Steps to run this Springboot project

# prerequisite software 
    1. Jdk 11 & above
    2. Maven build tool

Step1: run main method in RewardsServiceApplication
        internally tomcat uses port 8080 to run embedded server
Step2: In postman or some rest client try using sample Get API url: "http://localhost:8080/rewards/customer/cust2"
                                        &
        Can also create CustomerTransaction using Post API url: "http://localhost:8080/transactions" and request body