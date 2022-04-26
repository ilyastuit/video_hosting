package com.ilyastuit.model.user.entity.user;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpTest {

    @Test
    void testSuccess() {
        UserId id = UserId.next();
        LocalDateTime date = LocalDateTime.now();
        Email email = new Email("mail@example.com");
        String hash = "hash";
        ConfirmToken token = new ConfirmToken("token", LocalDateTime.now().plusDays(1L));

        User user = new User(
                id,
                date,
                email,
                hash,
                token
        );

        assertTrue(user.isWait());
        assertFalse(user.isActive());

        assertEquals(id, user.getId());
        assertEquals(date, user.getDate());
        assertEquals(email, user.getEmail());
        assertEquals(hash, user.getPasswordHash());
        assertEquals(token, user.getConfirmToken());
    }

}
