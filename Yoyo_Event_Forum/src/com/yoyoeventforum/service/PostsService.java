package com.yoyoeventforum.service;


import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.yoyoeventforum.model.Post;
import com.yoyoeventforum.model.User;

public class PostsService {
	private final EntityManagerFactory emf;
	public PostsService() {
		emf = Services.getEntityManagerFactory();
	}

	public List<Post> getPosts() {
		final EntityManager em =
			emf.createEntityManager();
		try {
			return em
				.createNamedQuery("allPosts", Post.class)
				.getResultList();
		} finally {
			em.close();
		}
	}

	public List<Post> getPostsByAuthor(User author) {
		final EntityManager em =
			emf.createEntityManager();
		try {
			return em
				.createNamedQuery("postsByAuthor", Post.class)
				.setParameter("author", author)
				.getResultList();
		} finally {
			em.close();
		}
	}	
	
	public Post getPost(long postId) {
		final EntityManager em =
			emf.createEntityManager();
		try {
			return em.find(Post.class, postId);
		} finally {
			em.close();
		}
	}
	
	public synchronized Post createPost(Post post) {
		EntityManager em =
			emf.createEntityManager();
		final EntityTransaction tx =
			em.getTransaction();
		try {
			tx.begin();
			em.persist(post);
			tx.commit();
			return post;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}
		
	}
	
	public Post updatePost(long postId, Post post) {
		EntityManager em =
			emf.createEntityManager();
		final EntityTransaction tx =
			em.getTransaction();
		try {
			tx.begin();
			final Post fromDb = em.find(Post.class, postId);
			if (fromDb != null) {
				fromDb.setType(post.getType());
				fromDb.setTitle(post.getTitle());
				fromDb.setPlace(post.getPlace());
				fromDb.setDate(post.getDate());
				fromDb.setTime(post.getTime());
				fromDb.setDescription(post.getDescription());
				em.merge(fromDb);
			}
			tx.commit();
			return fromDb;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}
	}
	
	public Set<User> goingPost(long postId, User goingByUser) {
		EntityManager em = emf.createEntityManager();
			final EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				final Post fromDb = em.find(Post.class, postId);
				if (fromDb != null) {
					fromDb.getGoingByUsers().add(goingByUser);
					em.merge(fromDb);
				}
				tx.commit();
				return fromDb.getGoingByUsers();
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				em.close();
			}
	}
	
	public void deletePost(long postId) {
		EntityManager em =
			emf.createEntityManager();
		final EntityTransaction tx =
			em.getTransaction();
		try {
			tx.begin();
			final Post fromDb = em.find(Post.class, postId);
			if (fromDb != null) {
				em.remove(fromDb);
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}
	}

}
