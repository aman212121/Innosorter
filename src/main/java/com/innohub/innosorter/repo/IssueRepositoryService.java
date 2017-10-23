package com.innohub.innosorter.repo;

import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Post;
import com.innohub.innosorter.entity.User;

public interface IssueRepositoryService {
	void storeIssue(Cluster cluster);

	void addPostToCluster(Cluster cluster, Post post);
	 
	boolean deleteCluster(User user, Cluster cluster);
}