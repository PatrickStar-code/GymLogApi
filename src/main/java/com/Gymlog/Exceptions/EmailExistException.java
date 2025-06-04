package com.Gymlog.Exceptions;


public class EmailExistException extends BussinesRuleException {
    public EmailExistException(String code, String message) {
        super(code, message);
    }
}
