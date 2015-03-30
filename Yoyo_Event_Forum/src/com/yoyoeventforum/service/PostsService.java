package com.yoyoeventforum.service;


import java.util.ArrayList;
import java.util.List;

import com.yoyoeventforum.model.Post;

public class PostsService {
	private final List<Post> posts = new ArrayList<Post>();
	private long lastPostId = 0;
	
	public List<Post> getPosts() {
		return posts;
	}
	
	public Post getPost(long postId) {
		for (Post next : posts) {
			if(next.getId() == postId) {
				return next;
			}
		}
		return null;
	}
	
	public synchronized Post createPost(Post post) {
		lastPostId++;
		post.setId(lastPostId);
		posts.add(post);
		return post;
	}
	
	public Post updatePost(long postId, Post post) {
		final Post updatedPost = getPost(postId);
		updatedPost.setType(post.getType());
		updatedPost.setTitle(post.getTitle());
		updatedPost.setPlace(post.getPlace());
		updatedPost.setDate(post.getDate());
		updatedPost.setTime(post.getTime());
		updatedPost.setDescription(post.getDescription());
		
		return updatedPost;
	}
	
	public void deletePost(long postId) {
		final Post toBeDeleted = getPost(postId);
		posts.remove(toBeDeleted);
	}
}
