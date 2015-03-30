package com.yoyoeventforum.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yoyoeventforum.model.Post;
import com.yoyoeventforum.model.User;
import com.yoyoeventforum.service.PostsService;
import com.yoyoeventforum.service.Services;

@Path("posts")
public class PostsRest {
	private final PostsService postsService;
	private final User defaultAuthor;
	
	public PostsRest() {
		postsService = Services.getPostsService();
		
		defaultAuthor = new User();
		defaultAuthor.setUsername("yoyoforumadmin");
		defaultAuthor.setEmail("yoyoforum@event");
		defaultAuthor.setPassword("password");
	}
	
	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Post> getPosts() {
		return postsService.getPosts();
	}
	
	@GET
	@Path("/{postId}")
	@Produces({MediaType.APPLICATION_JSON})
	public Post getPost(@PathParam("postId") long postId) {
		return postsService.getPost(postId);
	}
	
	@POST
	@Path("/")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Post createPost(Post post) {
		return postsService.createPost(post);
	}
	
	@PUT
	@Path("/{postId}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Post updatePost(@PathParam("postId") long postId, Post post) {
		return postsService.updatePost(postId, post);
	}
	
	@DELETE
	@Path("/{postId}")
	public void deletePost(@PathParam("postId") long postId) {
		postsService.deletePost(postId);
	}
	
}
