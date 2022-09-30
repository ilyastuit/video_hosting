package com.ilyastuit.http.v1.auth.signup;

import com.ilyastuit.BaseTest;
import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.AfterEach;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

class SignUpRequestRunner extends BaseTest {

    @PersistenceContext
    private EntityManager entityManager;

    @AfterEach
    void deleteUser() {
        entityManager
                .createNativeQuery("delete from user_users")
                .executeUpdate();
    }

    @Karate.Test
    Karate testSignUpFeature() {
        return Karate.run().relativeTo(getClass());
    }

}