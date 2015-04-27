package com.yoyoeventforum.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yoyoeventforum.model.Post;
import com.yoyoeventforum.model.User;
import com.yoyoeventforum.service.PostsService;
import com.yoyoeventforum.service.Services;
import com.yoyoeventforum.service.UsersService;

@Path("users")
public class UsersRest {
	private final UsersService usersService;
	private final PostsService postsService;

	public UsersRest() {
		usersService = Services.getUsersService();
		postsService = Services.getPostsService();
	}

	@GET
	@Path("/{userId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User getUser(@PathParam("userId") long userId) {
		return usersService.getUser(userId);
	}
	@GET
	@Path("/{userId}/posts")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Post> getUserPosts(@PathParam("userId") long userId) {
		final User author = usersService.getUser(userId);
		return postsService.getPostsByAuthor(author);
	}
	
	@POST
	@Path("/")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User createUser(User user) {
		return usersService.createUser(user);
	}
}
