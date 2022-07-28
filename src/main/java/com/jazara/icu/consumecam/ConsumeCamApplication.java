package com.jazara.icu.consumecam;

import nu.pattern.OpenCV;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDiscoveryClient
@EnableScheduling
@EnableCircuitBreaker
@EnableFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ConsumeCamApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ConsumeCamApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        OpenCV.loadShared();
    }
}