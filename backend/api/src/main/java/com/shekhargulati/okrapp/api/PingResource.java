package com.shekhargulati.okrapp.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/ping")
public class PingResource {

    @GetMapping
    public PingResponse ping() {
        return new PingResponse("pong");
    }
}

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
record PingResponse(String message) {
}