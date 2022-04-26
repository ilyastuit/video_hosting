package com.ilyastuit.model.user.entity.user;

import com.ilyastuit.model.exceptions.DomainException;

import java.time.LocalDateTime;

public class User {

    private static final String USER_WAIT = "wait";
    private static final String USER_ACTIVE = "active";

    private UserId id;
    private Email email;
    private LocalDateTime date;
    private String password;
    private ConfirmToken confirmToken;
    private String status;

    public User(UserId id, LocalDateTime date, Email email, String password, ConfirmToken confirmToken) {
        this.id = id;
        this.email = email;
        this.date = date;
        this.password = password;
        this.confirmToken = confirmToken;
        status = User.USER_WAIT;
    }

    public void confirmSignUp(String token, LocalDateTime date) throws DomainException {
        if (this.isActive()) {
            throw new DomainException("User is already active.");
        }

        this.confirmToken.validate(token, date);

        this.status = User.USER_ACTIVE;
        this.confirmToken = null;
    }

    public boolean isWait() {
        return this.status.equals(User.USER_WAIT);
    }

    public boolean isActive() {
        return this.status.equals(User.USER_ACTIVE);
    }

    public void setConfirmToken(String token, LocalDateTime date) {
        this.confirmToken = new ConfirmToken(token, date);
    }

    public UserId getId() {
        return id;
    }

    public ConfirmToken getConfirmToken() {
        return confirmToken;
    }

    public Email getEmail() {
        return email;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getPasswordHash() {
        return password;
    }
}
