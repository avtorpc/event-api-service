package io.onmi.event_api_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "")
public class RoutingProperties {

    private Map<String, RouteDefinition> routes;

    @Data
    public static class RouteDefinition {

        private KafkaDefinition kafka;

        private List<TargetDefinition> targets;
    }

    @Data
    public static class KafkaDefinition {

        private List<String> topics;
    }

    @Data
    public static class TargetDefinition {

        private String service;

        private String path;
    }
}