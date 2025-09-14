package com.cricket.common.exception;

public class CricketBusinessException extends RuntimeException {
    private String errorCode;
    
    public CricketBusinessException(String message) {
        super(message);
    }
    
    public CricketBusinessException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public CricketBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public String getErrorCode() {
        return errorCode;
    }
}