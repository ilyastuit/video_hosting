package com.ilyastuit.model.user.usecase.signup.request;

import com.ilyastuit.model.service.Flusher;
import com.ilyastuit.model.exception.DomainException;
import com.ilyastuit.model.user.entity.user.ConfirmToken;
import com.ilyastuit.model.user.entity.user.Email;
import com.ilyastuit.model.user.entity.user.User;
import com.ilyastuit.model.user.entity.user.UserId;
import com.ilyastuit.model.user.repository.UserRepository;
import com.ilyastuit.model.user.service.PasswordEncoder;
import com.ilyastuit.model.user.service.ConfirmTokenizer;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

public class SignUpRequestHandler {

    private final UserRepository users;
    private final PasswordEncoder hasher;
    private final ConfirmTokenizer tokenizer;
    private final Flusher flusher;

    public SignUpRequestHandler(
            final UserRepository users,
            final PasswordEncoder hasher,
            final ConfirmTokenizer tokenizer,
            final Flusher flusher) {
        this.users = users;
        this.hasher = hasher;
        this.tokenizer = tokenizer;
        this.flusher = flusher;
    }

    @Transactional
    public UserId handle(SignUpRequestCommand signUpRequestCommand) throws DomainException {
        Email email = new Email(signUpRequestCommand.email);

        if (this.users.hasByEmail(email)) {
            throw new DomainException("User with this email already exists.");
        }

        ConfirmToken token = tokenizer.generate();
        User user = new User(
                UserId.next(),
                LocalDateTime.now(),
                email,
                this.hasher.hash(signUpRequestCommand.password),
                token
        );

        this.users.add(user);

        this.flusher.flush();

        return user.getId();
    }

}
