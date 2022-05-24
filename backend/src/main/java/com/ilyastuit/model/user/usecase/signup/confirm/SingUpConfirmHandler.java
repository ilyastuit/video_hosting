package com.ilyastuit.model.user.usecase.signup.confirm;

import com.ilyastuit.model.service.Flusher;
import com.ilyastuit.model.exception.DomainException;
import com.ilyastuit.model.user.entity.user.Email;
import com.ilyastuit.model.user.entity.user.User;
import com.ilyastuit.model.user.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

public class SingUpConfirmHandler {

    private final UserRepository users;
    private final Flusher flusher;

    public SingUpConfirmHandler( final UserRepository users, final Flusher flusher) {
        this.users = users;
        this.flusher = flusher;
    }

    @Transactional
    public void handle(SignUpConfirmCommand signUpConfirmCommand) throws DomainException {
        User user = this.users.getByEmail(new Email(signUpConfirmCommand.email));

        user.confirmSignUp(signUpConfirmCommand.token, LocalDateTime.now());

        this.flusher.flush();
    }
}
