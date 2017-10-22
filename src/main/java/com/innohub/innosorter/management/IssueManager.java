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
        if (!(user instanceof Administrator)){
            throw new RuntimeException(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);
        }
        issueRepositoryService.addPostToCluster(clusterOne, postOne);

    }

    public void removePostFromCluster(User user, Cluster clusterOne, Post postOne) {
        if (!(user instanceof Administrator)){
            throw new RuntimeException(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);
        }
        
    }

}
