package com.ilyastuit.model.user.entity.user;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class UserId implements Serializable {

    private final UUID id;

    public UserId(UUID id) {
        this.id = id;
    }

    public static UserId next() {
        return new UserId(UUID.randomUUID());
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(id, userId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
