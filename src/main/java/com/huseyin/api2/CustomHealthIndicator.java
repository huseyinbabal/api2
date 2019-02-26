package com.huseyin.api2;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    private final ErrorCodeService errorCodeService;

    public CustomHealthIndicator(ErrorCodeService errorCodeService) {
        this.errorCodeService = errorCodeService;
    }

    @Override
    public Health health() {
        int errorCode = errorCodeService.getErrorCode();
        if (errorCode != 0) {
            return Health.down()
                .withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }
}
