package io.onmi.event_api_service.service;

import io.onmi.event_api_service.config.RoutingConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicResolverService {

    private final RoutingConfigLoader routingConfigLoader;

    public List<String> resolveTopics(String eventType) {

        RoutingConfig.RouteDefinition route =
                routingConfigLoader
                        .getRoutingConfig()
                        .getRoutes()
                        .get(eventType);

        if (route == null) {

            throw new IllegalArgumentException(
                    "No route configured for event type: " + eventType
            );
        }

        if (route.getKafka() == null ||
                route.getKafka().getTopics() == null ||
                route.getKafka().getTopics().isEmpty()) {

            throw new IllegalArgumentException(
                    "No kafka topics configured for event type: " + eventType
            );
        }

        return route.getKafka().getTopics();
    }
}