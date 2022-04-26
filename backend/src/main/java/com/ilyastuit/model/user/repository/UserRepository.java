package com.ilyastuit.model.user.repository;

import com.ilyastuit.model.user.entity.user.Email;
import com.ilyastuit.model.user.entity.user.User;

public interface UserRepository {

    boolean hasByEmail(Email email);

    User getByEmail(Email email);

    void add(User user);
}
