package com.ilyastuit.model.user.entity.user;

import com.ilyastuit.model.builder.UserBuilder;
import com.ilyastuit.model.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ConfirmTest {

    @Test
    void testSuccess() throws DomainException {
        LocalDateTime now = LocalDateTime.now();
        ConfirmToken token = new ConfirmToken("token", now.plusDays(1L));

        User user = this.signUp(now, token);

        assertTrue(user.isWait());
        assertFalse(user.isActive());
        assertNotNull(user.getConfirmToken());

        user.confirmSignUp(token.getToken(), now);

        assertFalse(user.isWait());
        assertTrue(user.isActive());
        assertNull(user.getConfirmToken());
    }

    @Test
    void testInvalidToken() {
        LocalDateTime now = LocalDateTime.now();
        ConfirmToken token = new ConfirmToken("token", now.plusDays(1L));

        User user = this.signUp(now, token);

        DomainException exception = assertThrows(DomainException.class, () -> user.confirmSignUp("invalid token", now));

        assertEquals("Confirm token is invalid.", exception.getMessage());
    }

    @Test
    void testAlreadyActive() throws DomainException {
        LocalDateTime now = LocalDateTime.now();
        ConfirmToken token = new ConfirmToken("token", now.plusDays(1L));

        User user = this.signUp(now, token);
        user.confirmSignUp(token.getToken(), now);

        DomainException exception = assertThrows(DomainException.class, () -> user.confirmSignUp(token.getToken(), now));

        assertEquals("User is already active.", exception.getMessage());
    }

    @Test
    void testExpiredToken() {
        LocalDateTime now = LocalDateTime.now();
        ConfirmToken token = new ConfirmToken("token", now);

        User user = this.signUp(now, token);

        DomainException exception = assertThrows(DomainException.class, () -> user.confirmSignUp(token.getToken(), now.plusDays(1L)));

        assertEquals("Confirm token is expired.", exception.getMessage());
    }

    private User signUp(LocalDateTime date, ConfirmToken token) {
        return (new UserBuilder())
                .withDate(date)
                .withConfirmToken(token)
                .build();
    }
}
