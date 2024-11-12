package com.quanghuyfs.ecommerce.product.exception;

import lombok.AllArgsConstructor;

import java.util.Map;
@AllArgsConstructor
public class ErrorResponse {
    Map<String,String>errors;
}
