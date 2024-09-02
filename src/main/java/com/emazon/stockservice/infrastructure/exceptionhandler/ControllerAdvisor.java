package com.emazon.stockservice.infrastructure.exceptionhandler;

import com.emazon.stockservice.domain.exceptions.DomainException;
import com.emazon.stockservice.domain.exceptions.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final Map<ErrorCode, HttpStatus> ERROR_CODE_STATUS_MAP;

    static {
        ERROR_CODE_STATUS_MAP = new EnumMap<>(ErrorCode.class);
        ERROR_CODE_STATUS_MAP.put(ErrorCode.CATEGORY_ALREADY_EXISTS, HttpStatus.CONFLICT);
        ERROR_CODE_STATUS_MAP.put(ErrorCode.INVALID_CATEGORY_NAME, HttpStatus.BAD_REQUEST);
        ERROR_CODE_STATUS_MAP.put(ErrorCode.CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND);
        ERROR_CODE_STATUS_MAP.put(ErrorCode.BRAND_ALREADY_EXISTS, HttpStatus.CONFLICT);
        ERROR_CODE_STATUS_MAP.put(ErrorCode.INVALID_BRAND_NAME, HttpStatus.BAD_REQUEST);
        ERROR_CODE_STATUS_MAP.put(ErrorCode.BRAND_NOT_FOUND, HttpStatus.NOT_FOUND);
        ERROR_CODE_STATUS_MAP.put(ErrorCode.ARTICLE_ALREADY_EXISTS, HttpStatus.CONFLICT);
        ERROR_CODE_STATUS_MAP.put(ErrorCode.INVALID_ARTICLE_NAME, HttpStatus.BAD_REQUEST);
        ERROR_CODE_STATUS_MAP.put(ErrorCode.INVALID_ARTICLE_PRICE, HttpStatus.BAD_REQUEST);
        ERROR_CODE_STATUS_MAP.put(ErrorCode.INVALID_ARTICLE_CATEGORIES, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Map<String, Object>> handleDomainException(DomainException ex, WebRequest request) {
        HttpStatus status = ERROR_CODE_STATUS_MAP.getOrDefault(ex.getErrorCode(), HttpStatus.INTERNAL_SERVER_ERROR);

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message_error", ex.getMessage());
        body.put("path", request.getDescription(false).substring(4));

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", "Validation failed");
        body.put("path", request.getDescription(false).substring(4));

        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> errorDetails = new HashMap<>();
                    errorDetails.put("field", fieldError.getField());
                    errorDetails.put("message", fieldError.getDefaultMessage());
                    return errorDetails;
                })
                .toList();

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
