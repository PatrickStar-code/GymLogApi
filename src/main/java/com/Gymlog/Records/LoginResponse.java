package com.Gymlog.Records;

public record LoginResponse(String tokenJwt, String refreshJwt, boolean profileComplete) {
}
