package com.ilyastuit.model.user.usecase.signup.confirm;

import com.ilyastuit.model.service.Flusher;
import com.ilyastuit.model.exceptions.DomainException;
import com.ilyastuit.model.user.entity.user.Email;
import com.ilyastuit.model.user.entity.user.User;
import com.ilyastuit.model.user.repository.UserRepository;

import java.time.LocalDateTime;

public class Handler {

    private final UserRepository users;
    private final Flusher flusher;

    public Handler(UserRepository users, Flusher flusher) {
        this.users = users;
        this.flusher = flusher;
    }

    public void handle(Command command) throws DomainException {
        User user = this.users.getByEmail(new Email(command.email));

        user.confirmSignUp(command.token, LocalDateTime.now());

        this.flusher.flush();
    }
}
