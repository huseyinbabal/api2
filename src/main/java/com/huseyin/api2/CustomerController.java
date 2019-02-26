package com.huseyin.api2;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class CustomerController {

    private final RestTemplate restTemplate;

    private final MeterRegistry meterRegistry;

    private final ErrorCodeService errorCodeService;

    public CustomerController(RestTemplate restTemplate, MeterRegistry meterRegistry, ErrorCodeService errorCodeService) {
        this.restTemplate = restTemplate;
        this.meterRegistry = meterRegistry;
        this.errorCodeService = errorCodeService;
    }

    @GetMapping("/customers/{id}")
    public Uuid getCustomer(@PathVariable("id") String customerId) {
        log.info("Getting isbn for customer: {}", customerId);
        return restTemplate.getForObject("https://httpbin.org/uuid", Uuid.class);
    }

    @GetMapping("/counter")
    public void counter() {
        meterRegistry.counter("my-counter").increment();
    }

    @GetMapping("/error_code/{errorCode}")
    public void errorCode(@PathVariable("errorCode") int errorCode) {
        errorCodeService.setErrorCode(errorCode);
    }

    @Data
    private static class Uuid {
        private String uuid;
    }
}
