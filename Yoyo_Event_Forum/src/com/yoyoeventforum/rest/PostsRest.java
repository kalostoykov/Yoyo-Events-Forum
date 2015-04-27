package com.yoyoeventforum.rest;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.yoyoeventforum.model.Post;
import com.yoyoeventforum.model.User;
import com.yoyoeventforum.service.PostsService;
import com.yoyoeventforum.service.Services;
import com.yoyoeventforum.service.UsersService;

@Path("posts")
public class PostsRest {
	
	private final PostsService postsService;
	private final UsersService usersService;
	


	public PostsRest() {
		postsService = Services.getPostsService();
		usersService = Services.getUsersService();
	}

	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Post> getPosts() {
		return postsService.getPosts();
	}
	
	@GET
	@Path("/{postId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Post getPost(@PathParam("postId") long postId) {
		return postsService.getPost(postId);
	}
	
	@GET
	@Path("/{postId}/goingbyusers")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<User> getUserByGoingPost(@PathParam("postId") long postId) {
		final Post goingPost = postsService.getPost(postId);
		return usersService.getUsersByGoingPost(goingPost);
	}
	
	@POST
	@Path("/")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Post createPost(@Context SecurityContext security, Post post) {
		final User author = usersService.getUserByUsername(security.getUserPrincipal().getName());
		post.setAuthor(author);
		return postsService.createPost(post);
	}
	
	@POST
	@Path("/{postId}/goingbyusers")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Set<User> goingPost(@Context SecurityContext security, @PathParam("postId")long postId) {
		final User goingByUser = usersService.getUserByUsername(security.getUserPrincipal().getName());
		return postsService.goingPost(postId, goingByUser);
	}
	
	@PUT
	@Path("/{postId}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Post updatePost(@PathParam("postId") long postId,
		Post post) {
		return postsService.updatePost(postId, post);
	}
	
	@DELETE
	@Path("/{postId}")
	public void deletePost(@PathParam("postId") long postId) {
		postsService.deletePost(postId);
	}
}
