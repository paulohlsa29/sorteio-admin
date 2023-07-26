package br.com.admin.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.admin.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CommonExceptionHandler {

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorEntity> internalServerError(HttpServletRequest request, Exception e) {
                return ResponseEntity.status(
                                HttpStatus.INTERNAL_SERVER_ERROR).body(
                                                ErrorEntity.builder()
                                                                .timestamp(LocalDateTime.now())
                                                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                                                .error(HttpStatus.INTERNAL_SERVER_ERROR
                                                                                .getReasonPhrase())
                                                                .message(e.getMessage())
                                                                .path(request.getRequestURI())
                                                                .build());
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ErrorEntity> badRequestError(HttpServletRequest request, Exception e) {
                return ResponseEntity.status(
                                HttpStatus.BAD_REQUEST).body(
                                                ErrorEntity.builder()
                                                                .timestamp(LocalDateTime.now())
                                                                .status(HttpStatus.BAD_REQUEST.value())
                                                                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                                                .message(e.getMessage())
                                                                .path(request.getRequestURI())
                                                                .build());
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorEntity> notFoundError(HttpServletRequest request, Exception e) {
                return ResponseEntity.status(
                                HttpStatus.BAD_REQUEST).body(
                                                ErrorEntity.builder()
                                                                .timestamp(LocalDateTime.now())
                                                                .status(HttpStatus.BAD_REQUEST.value())
                                                                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                                                .message(e.getMessage())
                                                                .path(request.getRequestURI())
                                                                .build());
        }
}
