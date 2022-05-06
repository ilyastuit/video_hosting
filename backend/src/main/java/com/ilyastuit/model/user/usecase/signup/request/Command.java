package com.ilyastuit.model.user.usecase.signup.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class Command {

    @Email(message = "Incorrect email.")
    public String email;

    @NotBlank(message = "Password must not be empty.")
    public String password;
}
