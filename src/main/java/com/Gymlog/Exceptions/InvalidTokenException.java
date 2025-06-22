package com.Gymlog.Exceptions;


public class InvalidTokenException extends BussinesRuleException {
    public InvalidTokenException(String code, String message) {
        super(code, message);
    }
}
