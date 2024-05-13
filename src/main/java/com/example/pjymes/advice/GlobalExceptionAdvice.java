package com.example.pjymes.advice;

import com.example.pjymes.dto.ErrorResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {

        // 중복된 id 에러에 대한 메시지를 작성하거나 로깅할 수 있습니다.
        String errorMessage = "이미 등록된 코드 입니다. 다른 코드를 선택해주세요.";
        log.info(errorMessage);
        // 클라이언트에게 반환할 오류 응답을 생성합니다.
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.CONFLICT, errorMessage);

        // ResponseEntity를 사용하여 오류 응답을 반환합니다.
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.CONFLICT);
    }
}
