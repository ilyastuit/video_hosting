package com.ilyastuit.http.v1.auth.signup;

import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class SignUpRequestRunner {

    @AfterEach
    @BeforeEach
    void deleteUser() {
        System.out.println("Hello from deleteUser");
    }

    @Karate.Test
    Karate testSignUpFeature() {
        return Karate.run().relativeTo(getClass());
    }

}