package com.innohub.innosorter.entity;

import java.util.List;

public class Cluster {
	
	String title;
	String summary;
	Integer numOfForumPosts;
	Integer numOfUserImpacted;
	String context;
	Integer priority;
	List<User> assignees;
	List<Post> posts;
	String currentStatus;
      boolean isClusterExsist;
      int clusterID;

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getNumOfForumPosts() {
		return numOfForumPosts;
	}
	public void setNumOfForumPosts(Integer numOfForumPosts) {
		this.numOfForumPosts = numOfForumPosts;
	}
	public Integer getNumOfUserImpacted() {
		return numOfUserImpacted;
	}
	public void setNumOfUserImpacted(Integer numOfUserImpacted) {
		this.numOfUserImpacted = numOfUserImpacted;
	}

	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority =  priority;
	}
	
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public List<User> getAssignees() {
		return assignees;
	}
	public void setAssignees(List<User> assignees) {
		this.assignees = assignees;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	public int getClusterID() {
		return clusterID;
	}
	
}
