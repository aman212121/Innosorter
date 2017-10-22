package com.innohub.innosorter.repo;

import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.repo.IssueRepositoryService;
import com.innohub.innosorter.util.ApplicationConstants;

public class IssueRepositoryServiceImpl implements IssueRepositoryService {

	public void storeIssue(Cluster cluster) {
		if (cluster.getContext() == null) {
			throw new RuntimeException(ApplicationConstants.CLUSTER_CONTEXT_NOT_AVAILABLE_MSG);
		} else if (cluster.getTitle() == null) {
			throw new RuntimeException(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);
		} else if (cluster.getNumOfUserImpacted() == null) {
			throw new RuntimeException(ApplicationConstants.CLUSTER_NUM_OF_IMPACTED_USER_NOT_AVAILABLE_MSG);
		}
	}
}
