package com.jazara.icu.consumecam.service;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ConsumeCamService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private Long i = 0L;

    @Async
    @Scheduled(fixedRate = 1000L)
    public CompletableFuture<?> consumeCam(String url) {
        LOGGER.info("Running AI Instance for streaming : " + url);

        VideoCapture capture = new VideoCapture(url);
        Mat mat = new Mat();
        capture.read(mat);
        //send mat to ai backend and handle response
        LOGGER.info("Done " + this.i++ + " frame for : " + url);
        return CompletableFuture.completedFuture(null);
    }
}