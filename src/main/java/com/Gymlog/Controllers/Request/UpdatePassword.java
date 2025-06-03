package com.Gymlog.Controllers.Request;

public record UpdatePassword(
    String newPassword,
    String confirmPassword
) {
}
