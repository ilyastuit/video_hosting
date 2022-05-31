package com.ilyastuit.model.user.entity.user;

import com.ilyastuit.infrastructure.model.user.entity.user.converter.EmailConverter;
import com.ilyastuit.infrastructure.model.user.entity.user.converter.UserStatusConverter;
import com.ilyastuit.model.exception.DomainException;
import com.ilyastuit.infrastructure.model.user.entity.user.converter.UserIdConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class User {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    @Convert(converter = UserIdConverter.class)
    private UserId id;

    private LocalDateTime date;

    @Convert(converter = EmailConverter.class)
    private Email email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Embedded
    @AttributeOverride(name = "token", column = @Column(name = "confirm_token_token"))
    @AttributeOverride(name = "expires", column = @Column(name = "confirm_token_expires"))
    private ConfirmToken confirmToken;

    @Column(length = 16)
    @Convert(converter = UserStatusConverter.class)
    private UserStatus status;

    protected User() {}

    public User(UserId id, LocalDateTime date, Email email, String passwordHash, ConfirmToken confirmToken) {
        this.id = id;
        this.date = date;
        this.email = email;
        this.passwordHash = passwordHash;
        this.confirmToken = confirmToken;
        status = UserStatus.WAIT;
    }

    public void confirmSignUp(String token, LocalDateTime date) throws DomainException {
        if (this.isActive()) {
            throw new DomainException("User is already active.");
        }

        this.confirmToken.validate(token, date);

        this.status = UserStatus.ACTIVE;
        this.confirmToken = null;
    }

    public boolean isWait() {
        return this.status.equals(UserStatus.WAIT);
    }

    public boolean isActive() {
        return this.status.equals(UserStatus.ACTIVE);
    }

    public UserId getId() {
        return id;
    }

    public ConfirmToken getConfirmToken() {
        return confirmToken;
    }

    public Email getEmail() {
        return email;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @PostLoad
    public void checkEmbeds() {
        if (this.confirmToken != null && this.confirmToken.isEmpty()) {
            this.confirmToken = null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(date, user.date) &&
                Objects.equals(email, user.email) &&
                Objects.equals(passwordHash, user.passwordHash) &&
                Objects.equals(confirmToken, user.confirmToken) &&
                Objects.equals(status, user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, email, passwordHash, confirmToken, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", date=" + date +
                ", email=" + email +
                ", passwordHash='" + passwordHash + '\'' +
                ", confirmToken=" + confirmToken +
                ", status='" + status + '\'' +
                '}';
    }
}
