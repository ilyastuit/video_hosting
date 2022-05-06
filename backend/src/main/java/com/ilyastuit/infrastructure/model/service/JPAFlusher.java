package com.ilyastuit.infrastructure.model.service;

import com.ilyastuit.model.service.Flusher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPAFlusher implements Flusher {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void flush() {
        entityManager.flush();
    }

}
