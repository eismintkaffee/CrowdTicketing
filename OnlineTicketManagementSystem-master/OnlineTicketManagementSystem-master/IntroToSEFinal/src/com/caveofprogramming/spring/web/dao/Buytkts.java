package com.caveofprogramming.spring.web.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tktbuy")
public class Buytkts {
	
	@Id
	@GeneratedValue
	private int tbuyid;
	
	@Column(name="username")
	private String username;
	
	@Column(name="id")
	private int id;
	
	@Column(name="tnum")
	private int tnum;
	
	@Column(name="title")
	private String title;
	
	@Column(name="type")
	private String type;
	
	@Column(name="venue")
	private String venue;
	
	@Column(name="date")
	private String date;
	
	
	
	
	public Buytkts() {
		
	}




	public int getTbuyid() {
		return tbuyid;
	}




	public void setTbuyid(int tbuyid) {
		this.tbuyid = tbuyid;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public int getTnum() {
		return tnum;
	}




	public void setTnum(int tnum) {
		this.tnum = tnum;
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




	public void setType(String type) {
		this.type = type;
	}




	public String getVenue() {
		return venue;
	}




	public void setVenue(String venue) {
		this.venue = venue;
	}




	public String getDate() {
		return date;
	}




	public void setDate(String date) {
		this.date = date;
	}




	public Buytkts(int tbuyid, String username, int id, int tnum, String title,
			String type, String venue, String date) {
		super();
		this.tbuyid = tbuyid;
		this.username = username;
		this.id = id;
		this.tnum = tnum;
		this.title = title;
		this.type = type;
		this.venue = venue;
		this.date = date;
	}

	

}
