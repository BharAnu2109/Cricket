package com.cricket.player;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.cricket.player", "com.cricket.common"})
@EnableDiscoveryClient
public class PlayerServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(PlayerServiceApplication.class, args);
    }
}