package io.onmi.event_api_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.onmi.event_api_service.config.RoutingConfig;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
@Getter
public class RoutingConfigLoader {

    @Value("${routing.file}")
    private String routingFile;

    private RoutingConfig routingConfig;

    @PostConstruct
    public void load() throws Exception {

        log.info(
                "Loading routing config from [{}]",
                routingFile
        );

        ObjectMapper mapper = new ObjectMapper(
                new YAMLFactory()
        );

        routingConfig = mapper.readValue(
                new File(routingFile),
                RoutingConfig.class
        );

        log.info("Routing config loaded successfully");
    }
}