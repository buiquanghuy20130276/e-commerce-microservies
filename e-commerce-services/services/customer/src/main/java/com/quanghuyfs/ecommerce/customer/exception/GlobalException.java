package com.quanghuyfs.ecommerce.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String>handleException(CustomerNotFoundException exp){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exp.getMsg());
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleException(MethodArgumentNotValidException exp){
        var erorrs = new HashMap<String,String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error->{
                    var fieldName = ((FieldError)error).getField();
                    var errorMessage =error.getDefaultMessage();
                    erorrs.put(fieldName,errorMessage);
                });
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(erorrs));
    }


}
