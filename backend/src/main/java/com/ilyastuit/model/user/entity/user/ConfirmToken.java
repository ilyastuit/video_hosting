package com.ilyastuit.model.user.entity.user;

import org.springframework.util.Assert;

import java.time.LocalDateTime;

public class ConfirmToken {

    private final String token;
    private final LocalDateTime expires;

    public ConfirmToken(String token, LocalDateTime expires) {
        Assert.hasText(token, "Token should not be empty.");

        this.token = token;
        this.expires = expires;
    }

    public boolean isExpiredTo(LocalDateTime date) {
        return this.expires.isBefore(date);
    }

    public String getToken() {
        return token;
    }

    public boolean isEqualTo(String token) {
        return this.token.equals(token);
    }
}
