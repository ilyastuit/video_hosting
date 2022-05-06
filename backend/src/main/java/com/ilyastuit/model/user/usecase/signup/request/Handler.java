package com.ilyastuit.model.user.usecase.signup.request;

import com.ilyastuit.model.service.Flusher;
import com.ilyastuit.model.exceptions.DomainException;
import com.ilyastuit.model.user.entity.user.ConfirmToken;
import com.ilyastuit.model.user.entity.user.Email;
import com.ilyastuit.model.user.entity.user.User;
import com.ilyastuit.model.user.entity.user.UserId;
import com.ilyastuit.model.user.repository.UserRepository;
import com.ilyastuit.model.user.service.PasswordEncoder;
import com.ilyastuit.model.user.service.ConfirmTokenizer;

import java.time.LocalDateTime;

public class Handler {

    private final UserRepository users;
    private final PasswordEncoder hasher;
    private final ConfirmTokenizer tokenizer;
    private final Flusher flusher;

    public Handler(UserRepository users, PasswordEncoder hasher, ConfirmTokenizer tokenizer, Flusher flusher) {
        this.users = users;
        this.hasher = hasher;
        this.tokenizer = tokenizer;
        this.flusher = flusher;
    }

    public void handle(Command command) throws DomainException {
        Email email = new Email(command.email);

        if (this.users.hasByEmail(email)) {
            throw new DomainException("User wth this email already exists.");
        }

        ConfirmToken token = tokenizer.generate();
        User user = new User(
                UserId.next(),
                LocalDateTime.now(),
                email,
                this.hasher.hash(command.password),
                token
        );

        this.users.add(user);

        this.flusher.flush();
    }

}
