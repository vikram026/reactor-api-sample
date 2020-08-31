package com.nisum.webflux.basicoperators;

/**
 * Custom class to handle the exception exteding throwable
 */
public class CustomException extends Throwable {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomException(Throwable e) {
        this.message = e.getMessage();
    }
}
