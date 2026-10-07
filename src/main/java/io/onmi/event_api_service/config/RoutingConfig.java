package io.onmi.event_api_service.config;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RoutingConfig {

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