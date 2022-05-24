package com.ilyastuit.infrastructure.model.user.repository;

import com.ilyastuit.model.exception.DomainException;
import com.ilyastuit.model.exception.EntityNotFoundException;
import com.ilyastuit.model.user.entity.user.Email;
import com.ilyastuit.model.user.entity.user.User;
import com.ilyastuit.model.user.repository.UserRepository;

import javax.persistence.*;

public class JPASQLUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean hasByEmail(Email email) {
        return entityManager
                .createQuery("select count(u.id) from User u where u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult() > 0;
    }

    @Override
    public User getByEmail(Email email) throws DomainException {
        User user;

        try {
            user = entityManager
                    .createQuery("select u from User u where u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("User is not found.", e.getCause());
        } catch (NonUniqueResultException e) {
            throw new DomainException(e.getMessage(), e.getCause());
        }

        return user;
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }
}
