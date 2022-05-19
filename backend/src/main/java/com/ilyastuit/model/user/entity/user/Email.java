package com.ilyastuit.model.user.entity.user;

import java.util.Objects;

public class Email {

    private final String emailValue;

    public Email(String emailValue) {
        this.emailValue = emailValue.toLowerCase();
    }

    public String getEmailValue() {
        return emailValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(emailValue, email1.emailValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailValue);
    }

    @Override
    public String toString() {
        return "Email{" +
                "email='" + emailValue + '\'' +
                '}';
    }
}
