package com.andre.compasso.exception;

import org.springframework.http.ResponseEntity;

public class RuleException extends Exception {

    private ResponseEntity response;

    public RuleException(ResponseEntity response){
        this.response = response;
    }

    public ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
    }
}
