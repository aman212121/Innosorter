package com.innohub.innosorter.management;

import com.innohub.innosorter.entity.Administrator;
import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Post;
import com.innohub.innosorter.entity.User;
import com.innohub.innosorter.util.ApplicationConstants;

public class IssueManager {

    public void addPostToCluser(User user, Cluster clusterOne, Post postOne) {
        if (!(user instanceof Administrator)){
            throw new RuntimeException(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);
        }

    }

    public void removePostFromCluster(User user, Cluster clusterOne, Post postOne) {
        if (!(user instanceof Administrator)){
            throw new RuntimeException(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);
        }
        
    }

}
