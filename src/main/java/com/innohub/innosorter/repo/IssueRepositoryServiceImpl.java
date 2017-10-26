package com.innohub.innosorter.repo;

import com.innohub.innosorter.entity.Administrator;
import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.User;
import com.innohub.innosorter.entity.Post;
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

	public boolean deleteCluster(User user, Cluster cluster) {
		
		if (!(user instanceof Administrator)){
            throw new RuntimeException(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);
        }
		else {
			return true;
		}
	}

    public void addPostToCluster(Cluster clusterOne, Post postOne) {
        // TODO Auto-generated method stub
        
    }

    public boolean checkClusterExist(int clusterID) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean checkPostExist(int postID) {
        // TODO Auto-generated method stub
        return false;
    }

    public void removePostFromCluster(Cluster cluster, Post post) {
        // TODO Auto-generated method stub
        
    }
}
