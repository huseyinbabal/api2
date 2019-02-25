package com.huseyin.api2;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class CustomerController {

    private final RestTemplate restTemplate;

    public CustomerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/customers/{id}")
    public Uuid getCustomer(@PathVariable("id") String customerId) {
        log.info("Getting isbn for customer: {}", customerId);
        return restTemplate.getForObject("https://httpbin.org/uuid", Uuid.class);
    }

    @Data
    private static class Uuid {
        private String uuid;
    }
}
