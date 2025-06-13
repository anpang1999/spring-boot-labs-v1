//package com.captainyun7.ch2codeyourself._04_3tiers_crud.exception;
//
//import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.ErrorResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ErrorResponse> postNotFoundException(PostNotFoundException e){
//        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage());
//
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(errorResponse);
//    }
//}
