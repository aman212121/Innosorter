package com.innohub.innosorter.repo;

import com.innohub.innosorter.entity.Cluster;

public interface IssueRepositoryService {
	void storeIssue(Cluster cluster);
	void deleteClusterIssue(Cluster issue);
	void updateClusterIssue(Cluster newIssue);
}

