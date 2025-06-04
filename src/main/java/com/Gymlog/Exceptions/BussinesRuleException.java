package com.Gymlog.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class BussinesRuleException extends RuntimeException {
    private final String code;
    private final String message;
}
