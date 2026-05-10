package com.Gymlog.Exceptions;

public class EmailNotVerifiedException extends BussinesRuleException {
    public EmailNotVerifiedException(String code, String message) {
        super(code, message);
    }
}
