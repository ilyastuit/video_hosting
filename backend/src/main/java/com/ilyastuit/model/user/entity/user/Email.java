package com.ilyastuit.model.user.entity.user;

import org.springframework.util.Assert;

public class Email {

    private final String email;

    public Email(String email) {
        Assert.hasText(email, () -> "Email must not be empty.");

        this.email = email.toLowerCase();
    }

    public String getEmail() {
        return email;
    }
}
