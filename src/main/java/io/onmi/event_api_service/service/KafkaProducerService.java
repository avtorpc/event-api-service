package io.onmi.event_api_service.service;

import io.onmi.event_api_service.dto.EventRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final TopicResolverService topicResolverService;

    public void send(EventRequest event) {

        for (String topic : topicResolverService.resolveTopics(
                event.getEventType()
        )) {

            log.info(
                    "Sending event [{}] to topic [{}]",
                    event.getEventId(),
                    topic
            );

            kafkaTemplate.send(
                    topic,
                    event.getEventId(),
                    event
            );
        }
    }
}