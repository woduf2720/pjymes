package com.example.pjymes.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class GlobalExceptionAdvice {
    // 포괄적으로 처리가능함 하지만 세부적으로 처리하기 어렵다
    // 에러 자동처리, 수동처리 각각 나뉘기 때문에
    // 어떻게 처리할지 고려 필요
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        log.info(ex);
        String errorMessage = "이미 등록된 코드 입니다. 다른 코드를 입력해주세요.";
        log.info(errorMessage);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }
}
