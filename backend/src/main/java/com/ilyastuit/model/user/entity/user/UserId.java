package com.ilyastuit.model.user.entity.user;

import org.springframework.util.Assert;

import java.util.UUID;

public class UserId {

    private final String id;

    public UserId(String id) {
        Assert.hasText(id, "Id must not be empty.");

        this.id = id;
    }

    public static UserId next() {
        return new UserId(UUID.randomUUID().toString());
    }

    public String getId() {
        return id;
    }
}
