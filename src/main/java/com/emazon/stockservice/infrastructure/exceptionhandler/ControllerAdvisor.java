package com.emazon.stockservice.infrastructure.exceptionhandler;

import com.emazon.stockservice.domain.exceptions.DomainException;
import com.emazon.stockservice.domain.exceptions.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "Message";
    private static final Map<ErrorCode, HttpStatus> ERROR_CODE_STATUS_MAP;

    static {
        ERROR_CODE_STATUS_MAP = new EnumMap<>(ErrorCode.class);
        ERROR_CODE_STATUS_MAP.put(ErrorCode.CATEGORY_ALREADY_EXISTS, HttpStatus.CONFLICT);
        ERROR_CODE_STATUS_MAP.put(ErrorCode.INVALID_CATEGORY_NAME, HttpStatus.BAD_REQUEST);
        ERROR_CODE_STATUS_MAP.put(ErrorCode.CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Map<String, String>> handleDomainException(DomainException ex) {
        HttpStatus status = ERROR_CODE_STATUS_MAP.getOrDefault(ex.getErrorCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(status)
                .body(Collections.singletonMap(MESSAGE, ex.getMessage()));
    }
}
