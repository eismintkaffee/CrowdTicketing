package com.caveofprogramming.spring.web.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="offers")
public class Offer {

	@Id
	@GeneratedValue
	private int id;
	
	@Size(min=5, max=255, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name="title")
	private String title;
	@Column(name="type")
	private String type;
	@Column(name="vanue")
	private String vanue;
	@Column(name="date")
	private String date;
	@Column(name="available")
	private int available;
	@Column(name="total")
	private int total;
	
	
	@ManyToOne
	@JoinColumn(name="username")
	private User user;
	
	public Offer() {
		this.user = new User();
	}

	public Offer( String title, String type, String vanue, String date,
			int available, int total, User user) {
		super();
		
		this.title = title;
		this.type = type;
		this.vanue = vanue;
		this.date = date;
		this.available = available;
		this.total = total;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}
	public String getUsername() {
		return user.getUsername();
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getVanue() {
		return vanue;
	}

	public void setVanue(String vanue) {
		this.vanue = vanue;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", title=" + title + ", type=" + type
				+ ", vanue=" + vanue + ", date=" + date + ", available="
				+ available + ", total=" + total + ", user=" + user + "]";
	}

	
	

	
	

}
