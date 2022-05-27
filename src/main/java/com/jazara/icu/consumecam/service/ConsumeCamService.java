package com.jazara.icu.consumecam.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConsumeCamService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public void consumeCam(String url) {
        LOGGER.info("Running AI Instance for streaming : " + url);
    }
}