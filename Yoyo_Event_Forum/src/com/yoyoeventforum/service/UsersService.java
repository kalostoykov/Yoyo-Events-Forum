package com.yoyoeventforum.service;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.yoyoeventforum.model.User;

public class UsersService {
	private final EntityManagerFactory emf;
	public UsersService() {
		emf = Services.getEntityManagerFactory();
	}

	public User getUserByUsername(String username) {
		final EntityManager em =
			emf.createEntityManager();
		try {
			return em
				.createNamedQuery("userByUsername", User.class)
				.setParameter("username", username)
				.getSingleResult();
		} finally {
			em.close();
		}
	}
	
	public User getUser(long userId) {
		final EntityManager em =
			emf.createEntityManager();
		try {
			return em.find(User.class, userId);
		} finally {
			em.close();
		}
	}

	public synchronized User createUser(User user) {
		EntityManager em =
			emf.createEntityManager();
		final EntityTransaction tx =
			em.getTransaction();
		try {
			tx.begin();
			em.persist(user);
			tx.commit();
			return user;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}
	}
}
