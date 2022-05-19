package com.ilyastuit.model.user.entity.user;

import com.ilyastuit.model.exceptions.DomainException;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class ConfirmToken {

    @Access(AccessType.FIELD)
    private String token;

    @Access(AccessType.FIELD)
    private LocalDateTime expires;

    protected ConfirmToken() {}

    public ConfirmToken(String token, LocalDateTime expires) {
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

    public boolean isEmpty() {
        return this.token == null || this.token.isBlank();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfirmToken token1 = (ConfirmToken) o;
        return Objects.equals(token, token1.token) &&
                Objects.equals(expires, token1.expires);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, expires);
    }

    @Override
    public String toString() {
        return "ConfirmToken{" +
                "token='" + token + '\'' +
                ", expires=" + expires +
                '}';
    }
}
