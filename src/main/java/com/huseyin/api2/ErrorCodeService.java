package com.huseyin.api2;

import org.springframework.stereotype.Component;

@Component
public class ErrorCodeService {

    private int errorCode = 0;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
