package com.yoyoeventforum.service;

public class Services {
	private static PostsService postsService;
	
	public synchronized static PostsService getPostsService() {
		if(postsService == null) {
			postsService = new PostsService();
		}
		return postsService;
	}
	
	public static void setPostsService(PostsService postsService) {
		Services.postsService = postsService;
	}
}
