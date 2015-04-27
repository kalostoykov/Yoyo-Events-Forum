package com.yoyoeventforum.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.yoyoeventforum.service.PostsService;
import com.yoyoeventforum.service.UsersService;

public class Services {

	private static PostsService postsService;
	private static UsersService usersService;
	private static EntityManagerFactory entityManagerFactory;

	public synchronized static PostsService getPostsService() {
		// lazy loading
		if (postsService == null) {
			postsService = new PostsService();
		}
		return postsService;
	}
	
	static void setPostsService(PostsService postsService) {
		Services.postsService = postsService;
	}
	
	public synchronized static EntityManagerFactory getEntityManagerFactory() {
		// lazy loading
		if (entityManagerFactory == null) {
			try {
				Class.forName("org.apache.derby.jdbc.ClientDriver");
			} catch (ClassNotFoundException e) {
				throw new IllegalStateException("No driver", e);
			}
			entityManagerFactory = Persistence.createEntityManagerFactory("Yoyo_Event_Forum");
		}
		return entityManagerFactory;
	}
	
	static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		Services.entityManagerFactory = entityManagerFactory;
	}
	
	public synchronized static UsersService getUsersService() {
		// lazy loading
		if (usersService == null) {
			usersService = new UsersService();
		}
		return usersService;
	}
	
	static void setUsersService(UsersService usersService) {
		Services.usersService = usersService;
	}
}
