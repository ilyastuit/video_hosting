package com.ilyastuit.model.user.service;

public interface PasswordEncoder {

    String hash(String password);

    boolean validate(String password, String hash);
}
