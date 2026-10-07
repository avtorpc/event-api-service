package io.onmi.event_api_service.controller;

import io.onmi.event_api_service.dto.EventRequest;
import io.onmi.event_api_service.service.KafkaProducerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event/kafka")
@RequiredArgsConstructor
public class VerificationController {

    private final KafkaProducerService kafkaProducerService;

//    @PostMapping("/mail-send")
//    public ResponseEntity<Void> publish(
//            @Valid @RequestBody EventRequest request
//    ) {
//
//        kafkaProducerService.send(request);
//
//        return ResponseEntity.accepted().build();
//    }

    @PostMapping("/mail-send")
    public ResponseEntity<Void> publish(HttpServletRequest request,
                                        @RequestBody EventRequest body) {

        System.out.println("REQUEST URI = " + request.getRequestURI());
        System.out.println("REQUEST URL = " + request.getRequestURL());

        kafkaProducerService.send(body);
        return ResponseEntity.accepted().build();
    }

    public void init() {
        System.out.println(">>> EVENT CONTROLLER LOADED /event/kafka");
    }
}