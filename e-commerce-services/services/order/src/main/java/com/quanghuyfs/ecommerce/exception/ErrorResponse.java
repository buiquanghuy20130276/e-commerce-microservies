package com.quanghuyfs.ecommerce.exception;

import lombok.AllArgsConstructor;

import java.util.Map;
@AllArgsConstructor
public class ErrorResponse {
    Map<String,String>errors;
}
