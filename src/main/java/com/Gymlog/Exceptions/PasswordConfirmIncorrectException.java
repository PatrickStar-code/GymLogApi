package com.Gymlog.Exceptions;

public class PasswordConfirmIncorrectException extends BussinesRuleException {
    public PasswordConfirmIncorrectException(String code, String message) {
        super(code, message);
    }
}
