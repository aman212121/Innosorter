package com.innohub.innosorter.management;

import com.innohub.innosorter.entity.Administrator;
import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Post;
import com.innohub.innosorter.entity.User;
import com.innohub.innosorter.repo.IssueRepositoryService;
import com.innohub.innosorter.repo.IssueRepositoryServiceImpl;
import com.innohub.innosorter.util.ApplicationConstants;

public class IssueManager {

    IssueRepositoryService issueRepositoryService = new IssueRepositoryServiceImpl();

    public void addPostToCluser(User user, Cluster clusterOne, Post postOne) {
    	
    	Cluster cluster= new Cluster();
    	Post post=new Post();
    	
        if (!(user instanceof Administrator)){
        		throw new RuntimeException(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);
        }
        else {
        	
	        	if(issueRepositoryService.checkClusterExist(clusterOne.getClusterID())) {
	        		throw new RuntimeException(ApplicationConstants.CLUSTER_DOES_NOT_EXSIST_MSG);
	        	}
	        	if(issueRepositoryService.checkPostExist(post.getPostID())) {
	        		throw new RuntimeException(ApplicationConstants.FORUM_POST_DOES_NOT_EXSIST_MSG);
	        	}
	        	
	        	if((issueRepositoryService.checkClusterExist(clusterOne.getClusterID() )) && (issueRepositoryService.checkPostExist(post.getPostID()))) {
	        		throw new RuntimeException(ApplicationConstants.LUSTE_AND_FORUM_POST_DO_NOT_EXSIST_MSG);
	        	}
	        	
	        		
	        	}
        issueRepositoryService.addPostToCluster(clusterOne, postOne);
        	

    }

    public void removePostFromCluster(User user, Cluster clusterOne, Post postOne) {
        if (!(user instanceof Administrator)){
            throw new RuntimeException(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);
        }
        
    }

}
