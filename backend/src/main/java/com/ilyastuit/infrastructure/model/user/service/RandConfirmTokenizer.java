package com.ilyastuit.infrastructure.model.user.service;

import com.ilyastuit.model.user.entity.user.ConfirmToken;
import com.ilyastuit.model.user.service.ConfirmTokenizer;

import java.time.LocalDateTime;

public class RandConfirmTokenizer implements ConfirmTokenizer {

    private final long intervalInDays;

    public RandConfirmTokenizer(long intervalInDays) {
        this.intervalInDays = intervalInDays;
    }

    @Override
    public ConfirmToken generate() {
        return new ConfirmToken(
                String.valueOf((Math.random() * 1_000_000)),
                LocalDateTime.now().plusDays(intervalInDays)
        );
    }

}
