package com.jazara.icu.consumecam.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jazara.icu.consumecam.config.AuthServiceClient;
import com.jazara.icu.consumecam.domain.Cam;
import com.jazara.icu.consumecam.domain.CamDTO;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ConsumeCamService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    List<Cam> camsList = new ArrayList<Cam>();

    @Autowired
    private AuthServiceClient authServiceClient;

    public void consumeCam(String url) throws Exception {
        LOGGER.info("Running AI Instance for streaming : " + url);
        ResponseEntity<Map<String, Object>> m = authServiceClient.GetAllCams();

        if (m.getBody().get("success").equals(false)) {
            throw new Exception("error sending request to auth-service");
        } else {
            try {

                ObjectMapper mapper = new ObjectMapper();
                camsList = mapper.convertValue(
                        m.getBody().get("result"),
                        new TypeReference<List<Cam>>() {
                        }
                );
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }

            LOGGER.info("MAPPER : " + camsList.size());
        }
    }

    @Scheduled(fixedRate = 5000L)
    public void startScheduledTask() throws Exception {
        LOGGER.info("started scheduledTask with list size : " + camsList.size());
        if (camsList.size() > 1) {
            for (final Cam cam : camsList) {
                LOGGER.info("sending cam to ai : " + cam.toString());

                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                final String baseUrl = "http://31.207.44.31:5000" + "/api/Models/Predict";
                URI uri = new URI(baseUrl);

                CamDTO camDTO = new CamDTO(cam.getId(), cam.getName(), cam.getUrl());

                ResponseEntity<String> result = restTemplate.postForEntity(uri, JSONValue.toJSONString(camDTO), String.class);

                LOGGER.info(JSONValue.toJSONString(camDTO) + "       sent");

                if (result.getStatusCodeValue() == 200) {
                    LOGGER.info(JSONValue.toJSONString(camDTO) + "       sent success for  : " + camDTO.getId());
                }

            }
        } else {
            LOGGER.info("camsList is Empty !! ");
        }
    }

}