package com.bridgelabz.model;

/**
 * @author Sachin Barpete
 * @purpose model for user
 */
public class User {

	private String name;
	private String job;
	private String id;
	private String createdAt;

	public User() {
		super();
	}

	public User(String name, String job) {
		super();
		this.name = name;
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}
