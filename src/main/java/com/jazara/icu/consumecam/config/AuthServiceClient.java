package com.jazara.icu.consumecam.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@FeignClient(name = "auth-service", fallback = AuthServiceClient.AuthServiceClientClientFallback.class)
public interface AuthServiceClient {

    @GetMapping(value = "/api/cam/getAll")
    ResponseEntity<Map<String, Object>> GetAllCams();

    @Component
    class AuthServiceClientClientFallback implements AuthServiceClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceClientClientFallback.class);

        @Override
        public ResponseEntity<Map<String, Object>> GetAllCams() {
            LOGGER.info("couldnt reach auth-service , fallback method");
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "fallback method");
            map.put("result", "");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }

    }
}
