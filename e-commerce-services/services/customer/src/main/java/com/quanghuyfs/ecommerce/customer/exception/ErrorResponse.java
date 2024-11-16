package com.quanghuyfs.ecommerce.customer.exception;

import lombok.AllArgsConstructor;

import java.util.Map;
@AllArgsConstructor
public class ErrorResponse {
    Map<String,String>errors;
}
