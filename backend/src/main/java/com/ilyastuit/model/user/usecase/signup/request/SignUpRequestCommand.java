package com.ilyastuit.model.user.usecase.signup.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignUpRequestCommand {

    @Email(message = "Incorrect email.")
    public String email;

    @NotBlank(message = "Password must not be empty.")
    public String password;
}
