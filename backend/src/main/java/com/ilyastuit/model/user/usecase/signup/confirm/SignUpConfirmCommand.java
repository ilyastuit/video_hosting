package com.ilyastuit.model.user.usecase.signup.confirm;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignUpConfirmCommand {

    @Email(message = "Incorrect email.")
    public String email;

    @NotBlank(message = "Token should not be empty.")
    public String token;

}
