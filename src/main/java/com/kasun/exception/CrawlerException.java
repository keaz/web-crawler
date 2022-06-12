package com.kasun.exception;

public class CrawlerException extends RuntimeException {

    public CrawlerException(String message, Exception cause) {
        super(message, cause);
    }
}
