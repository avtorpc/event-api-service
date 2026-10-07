package io.onmi.event_api_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventRequest {

    @JsonProperty("event_id")
    @NotBlank
    private String eventId;

    @JsonProperty("event_type")
    @NotBlank
    private String eventType;

    @JsonProperty("event_version")
    @NotNull
    private Integer eventVersion;

    @JsonProperty("occurred_at")
    @NotBlank
    private String occurredAt;

    @JsonProperty("trace_id")
    @NotBlank
    private String traceId;

    @JsonProperty("correlation_id")
    @NotBlank
    private String correlationId;

    @JsonProperty("producer")
    @Valid
    @NotNull
    private ProducerInfo producer;

    @JsonProperty("callback")
    private String callback;

    /**
     * Raw business payload.
     *
     * Producer intentionally does not deserialize
     * payload into POJO classes.
     */
    @JsonProperty("payload")
    @NotNull
    private JsonNode payload;
}