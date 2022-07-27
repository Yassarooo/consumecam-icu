package com.jazara.icu.consumecam;

import nu.pattern.OpenCV;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDiscoveryClient
@EnableScheduling
@SpringBootApplication
public class ConsumeCamApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ConsumeCamApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        OpenCV.loadShared();
    }
}