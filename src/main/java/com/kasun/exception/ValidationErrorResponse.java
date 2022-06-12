package com.kasun.exception;

import java.util.Map;

public record ValidationErrorResponse(Map<String,String> errors) {
}
