package com.jazara.icu.consumecam.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jazara.icu.consumecam.config.AuthServiceClient;
import com.jazara.icu.consumecam.domain.CamDTO;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
public class ConsumeCamService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private Long i = 0L;

    List<CamDTO> camsList = new ArrayList<CamDTO>();

    @Autowired
    private AuthServiceClient authServiceClient;

    @Async
    public void consumeCam(String url) throws Exception {
        LOGGER.info("Running AI Instance for streaming : " + url);
        ResponseEntity<Map<String, Object>> m = authServiceClient.GetAllCams();

        if (m.getBody().get("success").equals(false)) {
            throw new Exception("error sending request to auth-service");
        } else {
            ObjectMapper mapper = new ObjectMapper();
            camsList = mapper.convertValue(
                    m.getBody().get("result"),
                    new TypeReference<List<CamDTO>>() {
                    }
            );

            LOGGER.info("MAPPER : " + camsList.size());
            this.startScheduledTask();
        }
    }

    public void startScheduledTask() throws Exception {
        LOGGER.info("started scheduledTask with list size : " + camsList.size());
        if (camsList.size() > 1) {
            for (final CamDTO cam : camsList)
                this.runCamThread(cam).exceptionally(handleRunThreadFailure);
        } else {
            LOGGER.info("camsList is Empty !! ");
        }
    }

    @Async
    public CompletableFuture<Boolean> runCamThread(CamDTO cam) throws InterruptedException {

        LOGGER.info("Running thread for " + cam.getUrl());

        VideoCapture capture = new VideoCapture(cam.getUrl());
        Mat mat = new Mat();
        if (capture.read(mat)) {
            //send mat to ai backend and handle response
            LOGGER.info("Done " + this.i++ + " frame for : " + cam.getUrl());
            Thread.sleep(1000);
            LOGGER.info("Completing " + " execution for : " + cam.getUrl());
        }
        return runCamThread(cam);
    }

    private Function<Throwable, Boolean> handleRunThreadFailure = throwable -> {
        LOGGER.error("Failed to run thread: {}", throwable);
        return false;
    };

}