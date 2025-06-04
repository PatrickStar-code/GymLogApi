package com.Gymlog.Exceptions;

public class NotFoundException  extends BussinesRuleException {
    public NotFoundException(String code, String message) {
        super(code, message);
    }
}
