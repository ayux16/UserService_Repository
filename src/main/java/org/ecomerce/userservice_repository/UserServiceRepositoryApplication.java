package org.ecomerce.userservice_repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceRepositoryApplication.class, args);
    }

}
