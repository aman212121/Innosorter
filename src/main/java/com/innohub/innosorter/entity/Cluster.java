package com.innohub.innosorter.entity;

import java.util.List;

public class Cluster {
	
	String title;
	String summary;
	Integer numOfRelatedPosts;
	Integer numOfUserImpacted;
	String context;
	List<User> assignees;
	String currentStatus;
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
	public Integer getNumOfRelatedPosts() {
		return numOfRelatedPosts;
	}
	public void setNumOfRelatedPosts(Integer numOfRelatedPosts) {
		this.numOfRelatedPosts = numOfRelatedPosts;
	}
	public Integer getNumOfUserImpacted() {
		return numOfUserImpacted;
	}
	public void setNumOfUserImpacted(Integer numOfUserImpacted) {
		this.numOfUserImpacted = numOfUserImpacted;
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
	
}
