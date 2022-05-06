package com.ilyastuit.infrastructure.model.user.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ilyastuit.model.user.service.PasswordEncoder;

public class BCryptPasswordEncoder implements PasswordEncoder {

    private final int cost;

    public BCryptPasswordEncoder(int cost) {
        this.cost = cost;
    }

    @Override
    public String hash(String password) {
        return BCrypt.withDefaults().hashToString(this.cost, password.toCharArray());
    }

    @Override
    public boolean validate(String password, String hash) {
        return BCrypt.verifyer().verify(password.toCharArray(), hash).verified;
    }

}
