package com.Gymlog.Exceptions;

import lombok.Data;


public class TokenGenerationException extends BussinesRuleException {
    public TokenGenerationException(String code, String message) {
        super(code, message);
    }
}
