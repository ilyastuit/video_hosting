package com.ilyastuit.model.user.service;

public interface PasswordHasher {

    String hash(String password);

    boolean validate(String password, String hash);
}
