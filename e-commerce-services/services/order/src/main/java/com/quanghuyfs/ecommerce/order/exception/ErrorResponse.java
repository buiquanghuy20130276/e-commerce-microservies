package com.quanghuyfs.ecommerce.order.exception;

import lombok.AllArgsConstructor;

import java.util.Map;
@AllArgsConstructor
public class ErrorResponse {
    Map<String,String>errors;
}
