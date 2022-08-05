package com.jazara.icu.consumecam.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jazara.icu.consumecam.config.AuthServiceClient;
import com.jazara.icu.consumecam.domain.CamDTO;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
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

    public void consumeCam(String url) throws Exception {
        LOGGER.info("Running AI Instance for streaming : " + url);
        ResponseEntity<Map<String, Object>> m = authServiceClient.GetAllCams();

        //if (m.getBody().get("success").equals(false)) {
        //    throw new Exception("error sending request to auth-service");
        //} else {
        //    ObjectMapper mapper = new ObjectMapper();
        //    camsList = mapper.convertValue(
        //            m.getBody().get("result"),
        //            new TypeReference<List<CamDTO>>() {
        //            }
        //    );

            LOGGER.info("MAPPER : " + camsList.size());
        //}
    }

    @Scheduled(fixedRate = 5000L)
    public void startScheduledTask() throws Exception {
        LOGGER.info("started scheduledTask with list size : " + camsList.size());
        if (camsList.size() > 1) {
            for (final CamDTO cam : camsList) {
                LOGGER.info("sending cam to ai : " + cam.toString());

                RestTemplate restTemplate = new RestTemplate();

                final String baseUrl = "";
                URI uri = new URI(baseUrl);

                ResponseEntity<String> result = restTemplate.postForEntity(uri, cam, String.class);

                if (result.getStatusCodeValue() != 200) {
                    LOGGER.info("failed sending request to  : " + baseUrl);
                }

            }
        } else {
            LOGGER.info("camsList is Empty !! ");
        }
    }

}