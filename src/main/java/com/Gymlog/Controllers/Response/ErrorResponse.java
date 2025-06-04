package com.Gymlog.Controllers.Response;

public record ErrorResponse(
        String code,
        String message
) {
}
