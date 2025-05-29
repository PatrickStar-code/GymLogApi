package com.Gymlog.Controllers.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUser(@Email @NotBlank String email, @NotBlank String password) {

}
