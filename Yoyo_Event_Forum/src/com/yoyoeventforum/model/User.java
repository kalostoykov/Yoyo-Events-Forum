package com.yoyoeventforum.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity(name="Users")
@NamedQueries({
	@NamedQuery(name = "userByUsername", 
			query = "SELECT u from Users u where u.username=:username"),
	@NamedQuery(name = "usersByGoingPost",
			query = "SELECT u from Users u where (:goingPost) " + "MEMBER OF u.goingPosts")	
})

public class User {
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false, length=50, unique=true)
	private String username;
	
	@Column(nullable=false, length=50)
	private String password;
	
	@Column(nullable=false, length=50, unique=true)
	private String email;
	
	@ManyToMany(mappedBy="goingByUsers")
	private Set<Post> goingPosts = new HashSet<Post>();
	
	@XmlTransient
	public Set<Post> getGoingPosts() {
		return goingPosts;
	}
	public void setGoingPosts(Set<Post> goingPosts) {
		this.goingPosts = goingPosts;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	//pass must not send
	@XmlTransient
	public String getPassword() {
		return password;
	}
	
	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
