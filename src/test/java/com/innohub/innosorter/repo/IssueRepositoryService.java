package com.innohub.innosorter.repo;

public interface IssueRepositoryService {
	void storeIssue(Cluster cluster);
	string getTitle(Issue issue);
}