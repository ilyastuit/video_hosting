package com.ilyastuit.model.user.entity.user;

import com.ilyastuit.model.exceptions.DomainException;
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

    public void validate(String token, LocalDateTime date) throws DomainException {
        if (!this.isEqualTo(token)) {
            throw new DomainException("Confirm token is invalid.");
        }
        if (this.isExpiredTo(date)) {
            throw new DomainException("Confirm token is expired.");
        }
    }

    private boolean isExpiredTo(LocalDateTime date) {
        return this.expires.isBefore(date);
    }

    private boolean isEqualTo(String token) {
        return this.token.equals(token);
    }

    public String getToken() {
        return token;
    }
}
