package com.ilyastuit.model.user.usecase.signup.confirm;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class Command {

    @Email(message = "Incorrect email.")
    public String email;

    @NotBlank(message = "Token should not be empty.")
    public String token;

}
