package com.innohub.innosorter.management;

import java.sql.SQLException;

import com.innohub.innosorter.entity.Administrator;
import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Developer;
import com.innohub.innosorter.entity.Post;
import com.innohub.innosorter.entity.User;
import com.innohub.innosorter.repo.IssueRepositoryService;
import com.innohub.innosorter.repo.IssueRepositoryServiceImpl;
import com.innohub.innosorter.util.ApplicationConstants;

public class IssueManager {

    IssueRepositoryService issueRepositoryService;

    public IssueManager(IssueRepositoryService issueRepositoryService){
        this.issueRepositoryService = issueRepositoryService;
    }

    public void addPostToCluser(User user, Cluster clusterOne, Post postOne) throws SQLException {

        if (!(user instanceof Administrator)) {
            throw new RuntimeException(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);
        } else {

            if (!issueRepositoryService.checkClusterExist(clusterOne.getClusterID())) {
                throw new RuntimeException(ApplicationConstants.CLUSTER_DOES_NOT_EXSIST_MSG);
            }
            if (!issueRepositoryService.checkPostExist(postOne.getPostID())) {
                throw new RuntimeException(ApplicationConstants.FORUM_POST_DOES_NOT_EXSIST_MSG);
            }
        }
        issueRepositoryService.addPostToCluster(clusterOne.getClusterID(), postOne);

    }

    public void removePostFromCluster(User user, Cluster clusterOne, Post postOne) throws SQLException {
        if (!(user instanceof Administrator)){
            throw new RuntimeException(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);
        }else {

            if (!issueRepositoryService.checkClusterExist(clusterOne.getClusterID())) {
                throw new RuntimeException(ApplicationConstants.CLUSTER_DOES_NOT_EXSIST_MSG);
            }
            if (!issueRepositoryService.checkPostExist(postOne.getPostID())) {
                throw new RuntimeException(ApplicationConstants.FORUM_POST_DOES_NOT_EXSIST_MSG);
            }
        }
        issueRepositoryService.removePostFromCluster(clusterOne, postOne);
        
    }

    public void assignIssueToUser(Integer clusterId, String string) {
        issueRepositoryService.assignIssueToUser(clusterId, string);
        // TODO Auto-generated method stub
        
    }

}
