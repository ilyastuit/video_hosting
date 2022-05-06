package com.ilyastuit.model.user.entity.user;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class UserId implements Serializable {

    private final String id;

    public UserId(@NotBlank(message = "Id must not be empty.") String id) {
        this.id = id;
    }

    public static UserId next() {
        return new UserId(UUID.randomUUID().toString());
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.id;
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
