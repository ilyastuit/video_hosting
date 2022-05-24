package com.ilyastuit.infrastructure.model.user.service;

import com.ilyastuit.model.user.entity.user.ConfirmToken;
import com.ilyastuit.model.user.service.ConfirmTokenizer;

import java.time.LocalDateTime;
import java.util.Random;

public class RandConfirmTokenizer implements ConfirmTokenizer {

    private final long intervalInDays;
    private static final Random RANDOMIZER = new Random();

    public RandConfirmTokenizer(long intervalInDays) {
        this.intervalInDays = intervalInDays;
    }

    @Override
    public ConfirmToken generate() {
        return new ConfirmToken(
                String.valueOf(RANDOMIZER.nextInt()),
                LocalDateTime.now().plusDays(intervalInDays)
        );
    }

}
