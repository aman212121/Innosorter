package com.innohub.innosorter.entity;

public class User {
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	private String name;

	public User(String name) {
		this.name = name;
	}

	public User() {
	}
}