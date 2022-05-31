package com.ilyastuit.model.builder;

import com.ilyastuit.model.user.entity.user.ConfirmToken;
import com.ilyastuit.model.user.entity.user.Email;
import com.ilyastuit.model.user.entity.user.User;
import com.ilyastuit.model.user.entity.user.UserId;

import java.time.LocalDateTime;

public class UserBuilder implements Cloneable {

    private UserId id = UserId.next();
    private LocalDateTime date = LocalDateTime.now();
    private Email email = new Email("mail@example.com");
    private String passwordHash = "hash";
    private ConfirmToken confirmToken = new ConfirmToken("token", LocalDateTime.now().plusDays(1L));

    public UserBuilder withUserId(UserId id) {
        UserBuilder clone = getClone();
        clone.id = id;
        return clone;
    }

    public UserBuilder withDate(LocalDateTime date) {
        UserBuilder clone = getClone();
        clone.date = date;
        return clone;
    }

    public UserBuilder withEmail(Email email) {
        UserBuilder clone = getClone();
        clone.email = email;
        return clone;
    }

    public UserBuilder withPasswordHash(String passwordHash) {
        UserBuilder clone = getClone();
        clone.passwordHash = passwordHash;
        return clone;
    }

    public UserBuilder withConfirmToken(ConfirmToken confirmToken) {
        UserBuilder clone = getClone();
        clone.confirmToken = confirmToken;
        return clone;
    }

    public User build() {
        return new User(
                this.id,
                this.date,
                this.email,
                this.passwordHash,
                this.confirmToken
        );
    }

    private UserBuilder getClone() {
        UserBuilder clone = null;
        try {
            clone = (UserBuilder) this.clone();
        } catch (CloneNotSupportedException ignored) {
        }

        return clone;
    }
}
