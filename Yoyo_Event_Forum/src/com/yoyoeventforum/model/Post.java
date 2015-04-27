package com.yoyoeventforum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name="Posts")
@NamedQueries({
	@NamedQuery(name = "allPosts", 
		query = "SELECT p from Posts p"),
	@NamedQuery(name = "postsByAuthor", 
		query = "SELECT p from Posts p where p.author=:author")
})
public class Post {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(optional=false)
	private User author;
	
	@Column(nullable=false, length=50)
	private String type;
	
	@Column(nullable=false, length=50)
	private String title;
	
	@Column(nullable=false, length=50)
	private String place;
	
	@Column(nullable=false, length=50)
	private String date;
	
	@Column(nullable=false, length=50)
	private String time;
	
	@Column(nullable=false, length=500)
	private String description;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
